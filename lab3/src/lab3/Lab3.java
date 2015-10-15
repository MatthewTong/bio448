package lab3;

import java.io.*;
import java.util.Scanner;

public class Lab3 {
    private int geneCount = 0;          // k in handout
    private int DNAStringLength = 0;    // N in handout
    private float avgGeneSize = -1f;

    public void handleGTF(File file) {
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
                        int geneLength = prevEnd - geneStart + 1;
                        totalGeneLength += geneLength;
			//System.out.println(prev + ":" + geneStart + "-" + prevEnd + " " + geneLength);
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
                int geneLength = prevEnd - geneStart + 1;
                totalGeneLength += geneLength;
		//System.out.println(prev + ":" + geneStart + "-" + prevEnd + " " + geneLength);
            }

            this.avgGeneSize = (float)totalGeneLength / (float)geneCount;

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void handleFASTA(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();  // skip first line
            DNAStringLength = 0;
            while ((line = br.readLine()) != null) {
                DNAStringLength += line.trim().length();
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
        return 1000d * (float)geneCount / (float)DNAStringLength;
    }

    public double getNucleotidesToGenes() {
        return (float)DNAStringLength / (float)(1000d * geneCount);
    }

    public InfoHolder start(File f1, File f2) {
        handleGTF(f1);
        handleFASTA(f2);
        
        InfoHolder i = new InfoHolder(getAvgGeneSize(), geneCount, DNAStringLength/1000d, getGeneDensity(), DNAStringLength, getNucleotidesToGenes());

	//System.out.printf("gc %d, len %d\n", l.geneCount, l.DNAStringLength);
        System.out.printf("avg gene size: %.2f\n", getAvgGeneSize());
        System.out.printf("gene density: %.4f\n", getGeneDensity());
        System.out.printf("nucleotides to gene ratio: %.2f\n", getNucleotidesToGenes());
        
        return i;
    }
}

class InfoHolder {
    public float avgGene;
    public int numGenes;
    public double totalBP;
    public double geneDensity;
    public int numNucleotides;
    public double numNucleotidesGene;

    public InfoHolder(float a, int nG, double t, double gD, int nN, double nNG) {
        avgGene = a;
        numGenes = nG;
        totalBP = t;
        geneDensity = gD;
        numNucleotides = nN;
        numNucleotidesGene = nNG;
    }
}