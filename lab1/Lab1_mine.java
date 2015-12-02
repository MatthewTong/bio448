import java.util.Map;
import java.util.HashMap;

public class Lab1 {

    public static void main(String[] args) {
        Bio bio = new Bio();
        test1();
        test2();
        test3();
    }

    public static void test1() {
        Bio bio = new Bio();
        System.out.println("Checking Reverse Complement");
        System.out.println("TGCCGTAAT");
        System.out.println(bio.reverseComplement("ATTACGGCA"));
        System.out.println("-------");
        System.out.println("AAAATTCGTA");
        System.out.println(bio.reverseComplement("TACGAATTTT"));
        System.out.println("-------");
    }

    public static void test2() {
        Bio bio = new Bio();
        System.out.println("Checking Translation");
        System.out.println("FFLSY");
        System.out.println(bio.translation("TTTTTTCTTTCTTAT", 1));
        System.out.println("-------");
        System.out.println("WHALE");
        System.out.println(bio.translation("TGGCACGCTCTGGAG", 1));
        System.out.println("-------");
        System.out.println(bio.translation("ATTT", 2));
        System.out.println("F");
    }

    public static void test3() {
        Bio bio = new Bio();
        System.out.println("Checking merge");
        System.out.println("AAGTTTCGGAAA");
        System.out.println(bio.merge("AAGTTTCGG", "TTTCGGAAA"));
        System.out.println("-------");
        System.out.println("GTCATCGTAAATC");
        System.out.println(bio.merge("GTCATCGTAA", "GTAAATC"));
        System.out.println("-------");
        System.out.println("GTACCTACGTACG");
        System.out.println(bio.merge("GTACCT", "TACCTACGTACG"));
    }
}

class Bio {
    private static Map<String, String> myMap;
    static
    {
        myMap = new HashMap<String, String>();
        myMap.put("TTT", "F");
        myMap.put("TTC", "F");
        myMap.put("TTA", "L");
        myMap.put("TTG", "L");
        myMap.put("TCT", "S");
        myMap.put("TCC", "S");
        myMap.put("TCA", "S");
        myMap.put("TCG", "S");
        myMap.put("TAT", "Y");
        myMap.put("TAC", "Y");
        myMap.put("TAA", " ");
        myMap.put("TAG", " ");
        myMap.put("TGT", "C");
        myMap.put("TGC", "C");
        myMap.put("TGA", " ");
        myMap.put("TGG", "W");
        
        myMap.put("CTT", "L");
        myMap.put("CTC", "L");
        myMap.put("CTA", "L");
        myMap.put("CTG", "L");
        myMap.put("CCT", "P");
        myMap.put("CCC", "P");
        myMap.put("CCA", "P");
        myMap.put("CCG", "P");
        myMap.put("CAT", "H");
        myMap.put("CAC", "H");
        myMap.put("CAA", "Q");
        myMap.put("CAG", "Q");
        myMap.put("CGT", "R");
        myMap.put("CGC", "R");
        myMap.put("CGA", "R");
        myMap.put("CGG", "R");

        myMap.put("ATT", "I");
        myMap.put("ATC", "I");
        myMap.put("ATA", "I");
        myMap.put("ATG", "M");
        myMap.put("ACT", "T");
        myMap.put("ACC", "T");
        myMap.put("ACA", "T");
        myMap.put("ACG", "T");
        myMap.put("AAT", "N");
        myMap.put("AAC", "N");
        myMap.put("AAA", "K");
        myMap.put("AAG", "K");
        myMap.put("AGT", "S");
        myMap.put("AGC", "S");
        myMap.put("AGA", "R");
        myMap.put("AGG", "R");

        myMap.put("GTT", "V");
        myMap.put("GTC", "V");
        myMap.put("GTA", "V");
        myMap.put("GTG", "V");
        myMap.put("GCT", "A");
        myMap.put("GCC", "A");
        myMap.put("GCA", "A");
        myMap.put("GCG", "A");
        myMap.put("GAT", "D");
        myMap.put("GAC", "D");
        myMap.put("GAA", "E");
        myMap.put("GAG", "E");
        myMap.put("GGT", "G");
        myMap.put("GGC", "G");
        myMap.put("GGA", "G");
        myMap.put("GGG", "G");
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

    String translation(String s, int n) {
        String word = "";
        for (int i = n-1; i < s.length() - 2; i += 3) {
            char x = s.charAt(i);
            char y = s.charAt((i+1));
            char z = s.charAt((i+2));

            String letter = "";
            letter += x;
            letter += y;
            letter += z;
            System.out.println(letter);
            word += myMap.get(letter); 
        }
        return word;
    }

    String merge(String x, String y) {
        boolean flag = false, check = true;
        int temp = 4;
        int foundIndex = -1;
        
        for (int i = x.length() - 4; i >= 0; i--) {
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
            temp++;
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
