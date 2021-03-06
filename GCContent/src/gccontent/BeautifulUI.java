/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gccontent;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 *
 * @author mtong01
 */
public class BeautifulUI extends javax.swing.JFrame {
    private ArrayList<InfoHolder> list;
    /**
     * Creates new form BeautifulUI
     */
    public BeautifulUI() {
        list = new ArrayList<InfoHolder>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submitButton = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        windowSizeUI = new javax.swing.JSpinner();
        stepSizeUI = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jFileChooser1.setControlButtonsAreShown(false);
        jFileChooser1.setDragEnabled(true);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        windowSizeUI.setModel(new javax.swing.SpinnerNumberModel());

        stepSizeUI.setModel(new javax.swing.SpinnerNumberModel());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        jLabel1.setText("Window Size");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 30));

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        jLabel2.setText("Step Size");
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 30));

        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(windowSizeUI, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stepSizeUI, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(windowSizeUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepSizeUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(submitButton))
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        console.setText("");
        int windowSize = (Integer)windowSizeUI.getValue();
        int stepSize = (Integer)stepSizeUI.getValue();
        
        if (windowSize == 0) {
            windowSize = 100;
            console.append("Adjusted Window Size to 100 due to it having a 0 value\n");
        }
        
        if (stepSize == 0) {
            stepSize = 100;
            console.append("Adjusted Step Size to 100 due to it having a 0 value\n");
        }
        File file = jFileChooser1.getSelectedFile();
        System.out.println(windowSize);
        System.out.println(stepSize);
        System.out.println(file.getName());
        
        try {
            Scanner in = new Scanner(file);
            
            String sequence = "";
            
            while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.startsWith(">")) continue;
                    sequence += line;
            }
            console.append(gcFrames(sequence, windowSize, stepSize));
            writeToFile(file.getName());
            list.clear();
            
            /*DecimalFormat myFormatter = new DecimalFormat("###.##");
            String output = myFormatter.format(percent);

            console.append(output);*/
        }
        catch(FileNotFoundException e) {
            console.append("File was not found\n");
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void writeToFile(String name) {
        String n = (name.split("\\."))[0];
        try {
            PrintWriter writer = new PrintWriter(n + ".csv", "UTF-8");
            writer.print("Starting Position of Window, %GC, # of N's\n");
            for (InfoHolder i : list) {
                writer.print(i.pos + ", " + i.gcContent + ", " + i.nCount + "\n");
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
            console.append(e.toString());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus 2000 look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BeautifulUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeautifulUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeautifulUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeautifulUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeautifulUI().setVisible(true);
            }
        });
    }
    
    public float gcContentWindow(String s, int index, int width) {
	    if (s.length() == 0) {
		return 0.0f;
	    }

	    int l = 0;
	    int gcCount = 0;
	    char[] dna = s.toCharArray();
	    for (int i = 0; i < width && index + i < s.length(); i++) {
		char c = dna[index + i];
		if (c == 'G' || c == 'C') {
		    ++gcCount;
		}
		++l;
	    }
	    return gcCount / (float)l;
	}
    
    public int nContentWindow(String s, int index, int width) {
	    if (s.length() == 0) {
		return 0;
	    }

	    int l = 0;
	    int gcCount = 0;
	    char[] dna = s.toCharArray();
	    for (int i = 0; i < width && index + i < s.length(); i++) {
		char c = dna[index + i];
		if (c == 'N') {
		    ++gcCount;
		}
		++l;
	    }
	    return gcCount;
	}

	public String gcFrames(String s, int windowWidth, int step) {
            s = s.toUpperCase();
	    int len = s.length();
	    StringBuilder result = new StringBuilder("position    percent_content    number_of_n's\n");

	    int start = 0;

	    while (len > 0) {
		float gcContent = gcContentWindow(s, start, windowWidth);
                int numNs = nContentWindow(s, start, windowWidth);
                InfoHolder info = new InfoHolder(start, toPercentage(gcContent), numNs);
                list.add(info);
		result.append(start).append("\t").append(toPercent(gcContent)).append("\t").append(numNs).append("\n");
//		result.append(gcContent).append("|");
		start += step;
		len -= step;
	    }
	    
	    return result.toString();
	}
        
        private String toPercent(double value) {
            return String.format("%.2f", 100d * value) + '%';
        }
        
        private double toPercentage(double value) {
            String s = String.format("%.2f", 100d * value);
            double d = Double.parseDouble(s);
            return d;
        }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner stepSizeUI;
    private javax.swing.JButton submitButton;
    private javax.swing.JSpinner windowSizeUI;
    // End of variables declaration//GEN-END:variables
}

class InfoHolder {
    public int pos;
    public double gcContent;
    public int nCount;
    
    public InfoHolder(int p, double f, int n) {
        pos = p;
        gcContent = f;
        nCount = n;
    }
}