package lab4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Single sequence suffix tree.
 */
public class SuffixTree {
    private static final String TERMINAL_CHAR = "$";

    private Node root;

    public SuffixTree(String sequence) {
        root = new Node();
        sequence += TERMINAL_CHAR;
        for (int i = 0; i < sequence.length() - 1; i++) {
            root.insert(sequence.substring(i), i);
        }
    }

    public Set<Integer> search(String toSearch) {
        List<Integer> results = root.search(toSearch);
        Set<Integer> filtered = new HashSet<Integer>(results);

        filtered.remove(-1);

        return filtered;
    }

    private class Node {
        String val;
        int pos = -1;
        List<Node> children = new ArrayList<Node>();

        public void insert(String sequence, int position) {
            if (val == null) {
                val = sequence;
                pos = position;
                return;
            }

            int idx = getDifferenceIdx(sequence, val);

            if (idx >= sequence.length()) {
                // done, although this shouldn't happen
                System.out.println("Err, ran out of insert chars");
            } else if (idx == val.length()) {
                if (children.size() == 0) {
                    val = sequence;
                } else {
                    Node child = getChildStartingWith(sequence.charAt(idx));
                    if (child == null) {
                        children.add(child = new Node());
                    }
                    child.insert(sequence.substring(idx), position);
                }
            } else {
                Node left = new Node();
                Node right = new Node();

                left.val = val.substring(idx);
                this.val = val.substring(0, idx);
                left.pos = pos;
                this.pos = -1; // invalidate current position

                right.insert(sequence.substring(idx), position);

                children.add(left);
                children.add(right);
            }
        }

        public List<Integer> search(String toSearch) {
            int idx = getDifferenceIdx(val, toSearch);

            if (idx >= toSearch.length()) {
                return curPositions();
            } else if (idx == val.length()) {
                Node child = getChildStartingWith(toSearch.charAt(idx));
                if (child != null) {
                    return child.search(toSearch.substring(idx));
                }
            }

            return new ArrayList<Integer>(0);
        }

        private List<Integer> curPositions() {
            List<Integer> positions = new ArrayList<Integer>();

            if (children.size() == 0) {
                positions.add(pos);
            } else {
                for (Node child : children) {
                    positions.addAll(child.curPositions());
                }
            }

            return positions;
        }

        private int getDifferenceIdx(String a, String b) {
            char[] first = a.toCharArray();
            char[] second = b.toCharArray();
            int i = 0;

            for (; i < a.length() && i < b.length(); i++) {
                if (first[i] != second[i])
                    break;
            }

            return i;
        }

        // NOTE: can return null if no such child exists
        private Node getChildStartingWith(char c) {
            for (Node child : children) {
                if (child.val.length() > 0 && child.val.charAt(0) == c)
                    return child;
            }

            return null;
        }

        @Override
        public String toString() {
            return toString(0);
        }

        private String toString(int depth) {
            StringBuilder string = new StringBuilder();

            for (int i = 0; i < depth; i++) {
                string.append('\t');
            }

            if (val != null && val.length() > 0) {
                string.append(val).append('\n');
            } else {
                string.append("EMPTY\n");
            }

            for (Node child : children) {
                string.append(child.toString(depth + 1));
            }

            return string.toString();
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public static void main(String[] args) {
        SuffixTree tree = new SuffixTree("ATTATCATGATU");

        System.out.println(tree);
        System.out.println(tree.search("ATTATCATGATU"));// expect 0
        System.out.println(tree.search("ATT"));         // expect 0
        System.out.println(tree.search("AT"));          // expect 0, 3, 6, 9
        System.out.println(tree.search("W"));           // expect []
    }
}
