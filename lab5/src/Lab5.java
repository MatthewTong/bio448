
package lab5;

import java.io.*;
import java.util.*;
import java.awt.TextArea;

// move window of size 200 character by character
// after each move, check if window meets 2 criteria
// if so report window position
public class Lab5 {
	private static final int WINDOW_SIZE = 200;

	public static void printCpgs(File file, TextArea out) throws IOException {
		Scanner in = new Scanner(new FileReader(file));
		StringBuilder dna = new StringBuilder();
		List<String> sequences = new ArrayList<String>();
		while (in.hasNext()) {
			String next = in.next().trim();
			if (next.charAt(0) == '>') continue;
			if (next.length() == 0) {
				sequences.add(dna.toString());
				dna = new StringBuilder();
			}

			dna.append(next);
		}

		sequences.add(dna.toString());

                int cnt = 0;

		for (String sequence : sequences) {
			int windowPos = 0;
                        boolean lastC = false;
			int gCount = 0, cCount = 0, cgPairCount = 0;
                        for (int i = 0; i < WINDOW_SIZE; i++) {
                                char curChar = sequence.toCharArray()[windowPos + i];
                                if (curChar == 'c' || curChar == 'C') {
                                        ++cCount;
                                        lastC = true;
                                } else if (curChar == 'g' || curChar == 'G') {
                                        ++gCount;
                                        if (lastC) {
                                                ++cgPairCount;
                                        }
                                        lastC = false;
                                } else {
                                        lastC = false;
                                }
                        }
                        double obsExp = cgPairCount * WINDOW_SIZE / Math.max(1, (cCount * gCount));
                        double gcPercent = (gCount + cCount) / WINDOW_SIZE;
                        if (gcPercent > 0.5 && obsExp > 0.6) {
                            out.append("CPG Island detected in region " + (windowPos + 1) + " to " +
                                                        (windowPos + 1 + WINDOW_SIZE) + " (Obs/Exp = " + obsExp + " and %GC = " + gcPercent + ")\n");
                                cnt++;
                        }
                        ++windowPos;
			while (windowPos + WINDOW_SIZE < sequence.length()) {
                             char curChar = sequence.toCharArray()[windowPos - 1];
                             if (curChar == 'g' || curChar == 'G') {
                                 --gCount;
                             } else if (curChar == 'c' || curChar == 'C') {
                                 --cCount;
                             }
                             curChar = sequence.toCharArray()[windowPos - 1 + WINDOW_SIZE];
                             if (curChar == 'g' || curChar == 'G') {
                                 ++gCount;
                             } else if (curChar == 'c' || curChar == 'C') {
                                 ++cCount;
                             }
                            obsExp = cgPairCount * (double)WINDOW_SIZE / Math.max(1, (cCount * gCount));
                            gcPercent = (gCount + cCount) / (double)WINDOW_SIZE;
                            if (gcPercent > 0.5 && obsExp > 0.6) {
                                    out.append("CPG Island detected in region " + (windowPos + 1) + " to " +
                                                        (windowPos + 1 + WINDOW_SIZE) + " (Obs/Exp = " + obsExp + " and %GC = " + gcPercent + ")\n");
                                    //System.out.println("Found island: " + (windowPos + 1));
                                    cnt++;
                            }
                            ++windowPos;
			}
                
                        if (cnt == 0) System.out.println("Found no islands");
			/*
			boolean inCpg = false;
			boolean wasC = true;
			int idx = 0, start = -1;
			for (char c : sequence.toCharArray()) {
				if (inCpg) {
					if (wasC && (c == 'G' || c == 'g')) {
						wasC = false;
					} else if (!wasC && (c == 'C' || c == 'c')) {
						wasC = true;
					} else {
						System.out.println(start + ", " + (wasC ? idx - 2 : idx -1 ));
						//wasC = true;
						inCpg = false;
					}
				}
				else if (c == 'C' || c == 'c') {
					inCpg = true;
					start = idx;
					wasC = true;
				}
				idx++;
			}
			if (inCpg) {
				System.out.println(start + ", " + (wasC ? sequence.length() - 2 : sequence.length() -1 ));
			}
			*/
		}
	}

	private static double obsExp(int numCGPairs, int numCs, int numGs) {
		return numCGPairs * WINDOW_SIZE / (numCs * numGs);
	}
}
