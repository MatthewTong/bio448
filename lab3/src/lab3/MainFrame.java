/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Juicy
 */
public class MainFrame extends javax.swing.JFrame {
    private File f1;
    private File f2;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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

        genButton = new javax.swing.JButton();
        fileOneText = new javax.swing.JTextField();
        fileTwoText = new javax.swing.JTextField();
        fileOneButton = new javax.swing.JButton();
        fileTwoButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        genButton.setText("Generate");
        genButton.setAlignmentX(0.5F);
        genButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genButtonActionPerformed(evt);
            }
        });

        fileOneText.setText("GTF FILE");

        fileTwoText.setText("FASTA FILE");

        fileOneButton.setText("Select File");
        fileOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOneButtonActionPerformed(evt);
            }
        });

        fileTwoButton.setText("Select File");
        fileTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileTwoButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("GTF File 1:");

        jLabel2.setText("FASTA File 2:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fileTwoText, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(fileOneText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileOneButton)
                            .addComponent(fileTwoButton)))
                    .addComponent(genButton, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileOneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileOneButton)
                    .addComponent(jLabel1))
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileTwoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileTwoButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(genButton)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genButtonActionPerformed
        File file1 = f1;
        File file2 = f2;
        if (file1 != null && file2 != null) {
            Lab3 lab = new Lab3();
            InfoHolder i = lab.start(file1, file2);
            writeToFile(file1.getName(), i);
        }
    }//GEN-LAST:event_genButtonActionPerformed

    private void fileOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOneButtonActionPerformed
        FileChooser fileChooser = new FileChooser(fileOneText, this, 1);
    }//GEN-LAST:event_fileOneButtonActionPerformed

    private void fileTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileTwoButtonActionPerformed
        FileChooser fileChooser = new FileChooser(fileTwoText, this, 2);
    }//GEN-LAST:event_fileTwoButtonActionPerformed

    public void setFile(File f, int deg) {
        if (f != null) {
            if (deg == 1) {
                f1 = f;
            }
            else if (deg == 2) {
                f2 = f;
            }
        }
    }
    
    private void writeToFile(String name, InfoHolder i) {
        String n = (name.split("\\."))[0];
        try {
            PrintWriter writer = new PrintWriter(n + ".csv", "UTF-8");
            writer.print("Avg. Gene Size, Number of Genes, Total KBP, Gene Density, "
             + "Number of Nucleotides, Amount of Nucleotides per Gene\n");
            writer.print(i.avgGene + ", " + i.numGenes + ", " + i.totalBP + ", " 
             + i.geneDensity + ", " + i.numNucleotides + ", " + i.numNucleotidesGene + "\n");
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fileOneButton;
    private javax.swing.JTextField fileOneText;
    private javax.swing.JButton fileTwoButton;
    private javax.swing.JTextField fileTwoText;
    private javax.swing.JButton genButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
