package interficie;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RankingFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    Object[] rankings;
    
    public RankingFrame() {
        this.ctrl_interficie = null;
        initComponents();
    }
    
    public RankingFrame(ControladoraInterficie pres) {
        this.ctrl_interficie = pres;
        initComponents();
    }

    public void inicia() {
        listenerQuanTanques();
        actualitzaRankings();
        setDificultat(0); // Default shown: Fàcil
    }
    
    public void actualitzaRankings() {
        rankings = new Object[3];
        rankings[0] = ctrl_interficie.getRankings(0);
        rankings[1] = ctrl_interficie.getRankings(1);
        rankings[2] = ctrl_interficie.getRankings(2);
    }
    
    private void setDificultat(int dificultat) {
        
        Object[] ret = (Object[]) rankings[dificultat];// ctrl_interficie.getRankings(dificultat);
        String[] nomUser = (String[]) ret[0];
        String[] temps = (String[]) ret[1];
        String[] t_verbose = new String[3];
        
        int aux;
        aux=0; t_verbose[aux] = ((temps[aux]).equals("?")?"":"("+ctrl_interficie.deTimestampAVerbose((long)Integer.parseInt(temps[aux]))+")");
        ++aux; t_verbose[aux] = ((temps[aux]).equals("?")?"":"("+ctrl_interficie.deTimestampAVerbose((long)Integer.parseInt(temps[aux]))+")");
        ++aux; t_verbose[aux] = ((temps[aux]).equals("?")?"":"("+ctrl_interficie.deTimestampAVerbose((long)Integer.parseInt(temps[aux]))+")");
        
        aux=0; jLabelPrimer.setText("1. " + nomUser[aux] + " " + t_verbose[aux]);
        ++aux; jLabelSegon .setText("2. " + nomUser[aux] + " " + t_verbose[aux]);
        ++aux; jLabelTercer.setText("3. " + nomUser[aux] + " " + t_verbose[aux]);
    }
    
    public void reset() {
        int def = 0;
        jComboDifSelect.setSelectedIndex(def);
        setDificultat(def);
    }
    
    private void listenerQuanTanques(){
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ctrl_interficie.ranking.setVisible(false);
                ctrl_interficie.inici.setVisible(true);
                reset();
                //ctrl_interficie.login.setEnabled(true);
                System.out.println("(RankingFrame) S'ha tancat amb la creu. Fent coses...");
            }
        });
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboDifSelect = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabelPrimer = new javax.swing.JLabel();
        jLabelSegon = new javax.swing.JLabel();
        jLabelTercer = new javax.swing.JLabel();

        setTitle("Kakuto ~ Rànkings");
        setResizable(false);

        jComboDifSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboDifSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fàcil", "Difícil", "Expert" }));
        jComboDifSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboDifSelectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Rànkings");

        jLabelPrimer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPrimer.setText("1. ?");

        jLabelSegon.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabelSegon.setText("2. ?");

        jLabelTercer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTercer.setText("3. ?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jComboDifSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPrimer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSegon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTercer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboDifSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabelPrimer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSegon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTercer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboDifSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDifSelectActionPerformed
        setDificultat(jComboDifSelect.getSelectedIndex());
    }//GEN-LAST:event_jComboDifSelectActionPerformed

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
            java.util.logging.Logger.getLogger(RankingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RankingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RankingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RankingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RankingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboDifSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPrimer;
    private javax.swing.JLabel jLabelSegon;
    private javax.swing.JLabel jLabelTercer;
    // End of variables declaration//GEN-END:variables
}
