import java.io.*;
import java.util.Scanner;

public class Lab3 {
    private int geneCount = 0;          // k in handout
    private int DNAStringLength = 0;    // N in handout
    private float avgGeneSize = -1f;

    public void handleGTF(String file) {
        geneCount = 0;
        int totalGeneLength = 0;
        try {
            Scanner sc = new Scanner(new FileReader(file));
            String prev = "";
            int prevStart = 0, prevEnd = 0;
            int geneStart = 0, geneEnd = 0;
            while (sc.hasNextLine()) {
                sc.next(); sc.next(); sc.next();    // skip first 3 cols
                int start = sc.nextInt();
                int end = sc.nextInt();
                sc.next(); sc.next(); sc.next(); sc.next(); // skip next 4 cols
                String geneID = sc.next();
                geneID = geneID.substring(2, geneID.indexOf('-'));  // between "" and -
                if (!geneID.equals(prev)) {
                    if (!prev.isEmpty()) {
                        int geneLength = prevEnd - geneStart;
                        totalGeneLength += geneLength;
                    }
                    prev = geneID;
                    geneEnd = prevEnd;
                    geneStart = start;
                    ++geneCount;
                }
                prevStart = start;
                prevEnd = end;
                sc.nextLine();
            }

            if (!prev.isEmpty()) {  // very last gene
                int geneLength = prevEnd - geneStart;
                totalGeneLength += geneLength;
            }

            this.avgGeneSize = (float)totalGeneLength / geneCount;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void handleFASTA(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();  // skip first line
            DNAStringLength = 0;
            while ((line = br.readLine()) != null) {
                DNAStringLength += line.trim().length() - 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public float getAvgGeneSize() {
        return avgGeneSize;
    }

    public double getGeneDensity() {
        return (double) geneCount / DNAStringLength;
    }

    public double getNucleotidesToGenes() {
        return DNAStringLength / (1000d * geneCount);
    }

    // sample usage
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: Lab3 <GTF file> <FASTA file>");
            return;
        }

        Lab3 l = new Lab3();
        l.handleGTF(args[0]);
        l.handleFASTA(args[1]);

        System.out.printf("avg gene size: %.2f\n", l.getAvgGeneSize());
        System.out.printf("gene density: %.2f\n", l.getGeneDensity());
        System.out.printf("nucleotides to gene ratio: %.2f\n", l.getNucleotidesToGenes());
    }
}
