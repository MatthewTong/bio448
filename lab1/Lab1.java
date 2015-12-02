//CPE448
//Matthew Tong
//Matthew Versaggi

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab1 {

    private static ArrayList<String> arr;
    private static ArrayList<String> dict;
    private static Bio bio;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        Scanner scan = new Scanner(new File("/usr/share/dict/linux.words"));
        
        bio = new Bio();
        arr = new ArrayList();
        dict = new ArrayList();
        
        while(scan.hasNext())
            dict.add(scan.nextLine());
       
        while (in.hasNextLine())
            arr.add(in.nextLine());

        for (int i = 0; i < 4; i++)
            arr.add(bio.reverseComplement(arr.get(i)));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String res = CheckMatch(arr.get(i), arr.get(j));
                if (res != null) {
                    System.out.println(res);
                    return;
                }    
            }
        }

        System.out.println("No matches found at all. This is bad.");
    }

    public static void Test1() {
        System.out.println(bio.merge("AGTGTATGACAT", "TATGACATGCATA"));
        System.out.println(bio.merge("TATTGTGCAA", "GCAACACATTC"));

        System.out.println("AGTGTATGACAT");
        System.out.println("TATGACATGCATA");
        System.out.println("TATTGTGCAA");
        System.out.println("GCAACACATTC");

        System.out.println(bio.reverseComplement("AGTGTATGACAT"));
        System.out.println(bio.reverseComplement("TATGACATGCATA"));
        System.out.println(bio.reverseComplement("TATTGTGCAA"));
        System.out.println(bio.reverseComplement("GCAACACATTC"));
    }

    // Attempts to merge and translate two sequences with the hope of finding
    //    a matching word in the given dictionary
    public static String CheckMatch(String seq1, String seq2) {
        String mergeRes = bio.merge(seq1, seq2);
        String transRes = null;

        if (mergeRes != null) {
            for (int i = 0; i < 3; i++) {
                transRes = bio.translation(mergeRes, i + 1);
                if (checkDictionary(transRes))
                    return transRes;
            }
            mergeRes = bio.reverseComplement(mergeRes);
            for (int i = 0; i < 3; i++) {
                transRes = bio.translation(mergeRes, i + 1);
                if (checkDictionary(transRes))
                    return transRes;
            }
        }

        return null;
    }

    // Check dictionary to see if given String argument matches a word
    public static boolean checkDictionary(String s) {
        
        for (int i = 0; i < dict.size(); i++) {
            if (s.equalsIgnoreCase(dict.get(i))) {
                return true;
            }
        }
        return false;
    }
}

class Bio {
    private static final HashMap<String, Character> myMap;
    static
    {
        myMap = new HashMap<String, Character>();
        myMap.put("TTT", 'F');
        myMap.put("TTC", 'F');
        myMap.put("TTA", 'L');
        myMap.put("TTG", 'L');
        myMap.put("TCT", 'S');
        myMap.put("TCC", 'S');
        myMap.put("TCA", 'S');
        myMap.put("TCG", 'S');
        myMap.put("TAT", 'Y');
        myMap.put("TAC", 'Y');
        myMap.put("TAA", ' ');
        myMap.put("TAG", ' ');
        myMap.put("TGT", 'C');
        myMap.put("TGC", 'C');
        myMap.put("TGA", ' ');
        myMap.put("TGG", 'W');
        
        myMap.put("CTT", 'L');
        myMap.put("CTC", 'L');
        myMap.put("CTA", 'L');
        myMap.put("CTG", 'L');
        myMap.put("CCT", 'P');
        myMap.put("CCC", 'P');
        myMap.put("CCA", 'P');
        myMap.put("CCG", 'P');
        myMap.put("CAT", 'H');
        myMap.put("CAC", 'H');
        myMap.put("CAA", 'Q');
        myMap.put("CAG", 'Q');
        myMap.put("CGT", 'R');
        myMap.put("CGC", 'R');
        myMap.put("CGA", 'R');
        myMap.put("CGG", 'R');

        myMap.put("ATT", 'I');
        myMap.put("ATC", 'I');
        myMap.put("ATA", 'I');
        myMap.put("ATG", 'M');
        myMap.put("ACT", 'T');
        myMap.put("ACC", 'T');
        myMap.put("ACA", 'T');
        myMap.put("ACG", 'T');
        myMap.put("AAT", 'N');
        myMap.put("AAC", 'N');
        myMap.put("AAA", 'K');
        myMap.put("AAG", 'K');
        myMap.put("AGT", 'S');
        myMap.put("AGC", 'S');
        myMap.put("AGA", 'R');
        myMap.put("AGG", 'R');

        myMap.put("GTT", 'V');
        myMap.put("GTC", 'V');
        myMap.put("GTA", 'V');
        myMap.put("GTG", 'V');
        myMap.put("GCT", 'A');
        myMap.put("GCC", 'A');
        myMap.put("GCA", 'A');
        myMap.put("GCG", 'A');
        myMap.put("GAT", 'D');
        myMap.put("GAC", 'D');
        myMap.put("GAA", 'E');
        myMap.put("GAG", 'E');
        myMap.put("GGT", 'G');
        myMap.put("GGC", 'G');
        myMap.put("GGA", 'G');
        myMap.put("GGG", 'G');
    }

    public Bio() {}

    String reverseComplement(String s) {
        String rc = "";
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'A') {
                rc += 'T';   
            }
            else if (s.charAt(i) == 'T') {
                rc += 'A';   
            }
            else if (s.charAt(i) == 'C') {
                rc += 'G';   
            }
            else if (s.charAt(i) == 'G') {
                rc += 'C';   
            }
        }

        return rc;
    }

    String translation(String s, int rFrame) {
        String word = "";

        for (int i = rFrame - 1; i < s.length() - 2; i += 3) {
            
            String letter = s.substring(i, i+3);
            //System.out.println(letter);    // For debugging
            word += myMap.get(letter);
        }
        return word;
    }

    String merge(String x, String y) {
        boolean flag = false, check = true;
        int temp = 4;
        int foundIndex = -1;
        
        for (int i = x.length() - 4; i >= 0; i--, temp++) {
            if (temp >= x.length() || temp >= y.length()) {
                break;
            }

            if (x.substring(i, x.length()).equals(y.substring(0, temp))) {
                check = true;
                flag = true;
                foundIndex = x.length() - i;
            }
            else if (flag) {
                check = false;
            }

            if (!check && flag) {
                break;
            }
        }

        if (!flag) {
            return null;
        }

        String res = "";
        res += x.substring(0, foundIndex);
        res += x.substring(foundIndex, x.length());
        res += y.substring(y.length() - (y.length() - foundIndex), y.length());

        return res;
    }
}


