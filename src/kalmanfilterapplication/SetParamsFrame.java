/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalmanfilterapplication;

import java.awt.HeadlessException;

/**
 *
 * @author NK
 */

public class SetParamsFrame extends javax.swing.JFrame {

    private VisualizationFrame VFrame;
    
    /**
     * Creates new form SetParamsFrame
     */
    
    public SetParamsFrame() {
        initComponents();
    }

    public SetParamsFrame(VisualizationFrame VFrame, Double s1, Double s2, Double f,boolean fileLoaded) throws HeadlessException {
        initComponents(); // initialize the SetParamsFrame's form
        
        this.VFrame = VFrame; // set the VisualizationFrame that created this SetParamsFrame
        // if a file hasn't already loaded to R,Z arrays then hide OkFilterButton
        if(!fileLoaded){
            OkAndFilterButton.setVisible(false);
        }
        // fill the form if there are values
        try{
            txt1.setText(s1.toString());
            txt2.setText(s2.toString());
            txt3.setText(f.toString());
        }
        catch(NullPointerException ex){
            //Logger.getLogger(VisualizationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        OkAndCloseButton = new javax.swing.JButton();
        OkAndFilterButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Set Kalman Filter Parameter");
        setResizable(false);

        Label1.setText("System noise sdv");

        Label2.setText("Measurement noise sdv");

        Label3.setText("φ parameter");

        OkAndCloseButton.setText("OK&Close");
        OkAndCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkAndCloseButtonActionPerformed(evt);
            }
        });

        OkAndFilterButton.setText("OK&Filter");
        OkAndFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkAndFilterButtonActionPerformed(evt);
            }
        });

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(OkAndCloseButton)
                        .addGap(44, 44, 44)
                        .addComponent(OkAndFilterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(CloseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Label3)
                            .addComponent(Label2)
                            .addComponent(Label1))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label2)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label3)
                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkAndFilterButton)
                    .addComponent(CloseButton)
                    .addComponent(OkAndCloseButton))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void OkAndFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkAndFilterButtonActionPerformed
        // set s1,s2 and f parameters from SetParamsFrame form
        VFrame.setS1(Double.parseDouble(txt1.getText()));
        VFrame.setS2(Double.parseDouble(txt2.getText()));
        VFrame.setF(Double.parseDouble(txt3.getText()));
        // do filter calculations
        VFrame.FilterCalculations();
        // do eukleidian distance calculations
        VFrame.EukleidianDistanceCalculations();
        // do visualization of a linear chart in the VisualizationPanel of VisualizationFrame
        VFrame.Visualization();
        
        // unhide VisualizationFrame
        VFrame.setVisible(true);
        // close only SetParamsFrame
        this.dispose();
    }//GEN-LAST:event_OkAndFilterButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        VFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void OkAndCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkAndCloseButtonActionPerformed
        VFrame.setS1(Double.parseDouble(txt1.getText()));
        VFrame.setS2(Double.parseDouble(txt2.getText()));
        VFrame.setF(Double.parseDouble(txt3.getText()));
        
        VFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_OkAndCloseButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SetParamsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetParamsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetParamsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetParamsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SetParamsFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label3;
    private javax.swing.JButton OkAndCloseButton;
    private javax.swing.JButton OkAndFilterButton;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    // End of variables declaration//GEN-END:variables
}
