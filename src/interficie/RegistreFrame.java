package interficie;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class RegistreFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    public RegistreFrame(ControladoraInterficie pres) {
        this.ctrl_interficie = pres;
        initComponents();
    }
    
    // Per testing només
    public RegistreFrame() {
        this.ctrl_interficie = null;
        initComponents();
    }
    
    public void inicia() {
        this.jLabel_Incorrecte.setVisible(false);
        System.out.print("(RegFrame) iniciar()...");
        listenerQuanTanques();
        System.out.println("Done.");
    
    }
    
    public void reset() {
        this.jLabel_Incorrecte.setVisible(false);
        this.jTextUsuari.setText("");
        this.jPassword.setText("");
    }
    
    private void listenerQuanTanques(){
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ctrl_interficie.registre.setVisible(false);
                ctrl_interficie.login.setVisible(true);
                reset();
                //ctrl_interficie.login.setEnabled(true);
                System.out.println("(RegistreFrame) S'ha tancat amb la creu. Fent coses...");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonRegistrar = new javax.swing.JButton();
        jTextUsuari = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jLabel_Incorrecte = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Kakuro ~ Registrar-se");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Nou Usuari");

        jLabel2.setText("Mot de Pas");
        jLabel2.setToolTipText("");

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jLabel_Incorrecte.setForeground(new java.awt.Color(255, 51, 51));
        jLabel_Incorrecte.setText("Cal emplenar tots els camps!");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 2, 14)); // NOI18N
        jLabel3.setText("Registrar-se");

        jButton1.setText("Ja tinc Compte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextUsuari)
                    .addComponent(jLabel_Incorrecte, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Incorrecte)
                .addGap(18, 18, 18)
                .addComponent(jButtonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        
        String usuari = this.jTextUsuari.getText();
        String passwd = new String (this.jPassword.getPassword());
        if ("".equals(usuari) || "".equals(passwd)){
            this.jLabel_Incorrecte.setVisible(true);
            System.out.println("(RegistreFrame) Usuari o Password buits");
            return;
        }
        boolean correcte = ctrl_interficie.ctrl_domini.registrarUsuari(usuari, passwd);
        if (correcte){
            JOptionPane.showMessageDialog(this, "S'ha registrat correctament l'usuari " + usuari + ".", "Ep! I diu...", JOptionPane.INFORMATION_MESSAGE);
            ctrl_interficie.login.setUsuariPassword(usuari ,passwd);
            this.setVisible(false);
            reset();
        } else {
            JOptionPane.showMessageDialog(this, "No s'ha pogut registrar l'usuari " + usuari + ":\n Ja existeix.", "Ep! I diu...", JOptionPane.WARNING_MESSAGE);
            reset();
        }
        ctrl_interficie.login.setEnabled(true);
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ctrl_interficie.login.setEnabled(true);
        this.setVisible(false);
        ctrl_interficie.login.setVisible(true);
        reset();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RegistreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistreFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_Incorrecte;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTextUsuari;
    // End of variables declaration//GEN-END:variables
}
