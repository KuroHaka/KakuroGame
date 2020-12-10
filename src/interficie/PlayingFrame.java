package interficie;

import domini.tauler.TaulerComencat;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.NoSuchFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import persistencia.Dades;
import domini.tauler.casella.*;
import domini.partida.Partida;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlayingFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    String usuari; /* THIS SHOULD NOT BE HERE <3 */
    Partida partida; /* THIS SHOULD NOT BE HERE <3 */
    TaulerComencat tc;  /* THIS SHOULD NOT BE HERE <3 */
    
    String[][] tauler;
    int timestamp;
    
    public PlayingFrame(ControladoraInterficie pres, Object[] params) {
        this.ctrl_interficie = pres;
        
        // Coses xaxis
        
        this.tauler = (String[][]) params[0];
        this.timestamp = (int) params[1];
        
        /* 'tauler' Està en format : 
        
            - blanca valor  4
            - blanca buida  ?
            - negra coses   4\3  (columna\fila) * si no té valor hi ha un espai: ' '. Exemple: "4\ "
            - negra buida   *
        
        */
        
        // Proof of work (Se'n pot anar a la mierder)
        
        popupTauler();
        initComponents();
    }
    
    public PlayingFrame() {
        
        /* THIS SHOULD NOT BE HERE <3 */
        /**/
        /**/ String txt = "";
        /**/ try {
        /**/     txt = Dades.carregaArxiu("dades/enunciats/123.txt");
        /**/ } catch (NoSuchFileException ex) {
        /**/     Logger.getLogger(PlayFrame.class.getName()).log(Level.SEVERE, null, ex);
        /**/ }
        /**/ tc = new TaulerComencat(txt);
        /**/
        /* END */
        tauler = tc.toFormatInterficie();
        
        this.ctrl_interficie = null;
        popupTauler();
        initComponents();
    }

    public void inicia(String usuari, String arxiuPartida){
        // Nothing here :)
    }
    
    private void popupTauler() {
        String tauler_string = "";
        for (String fila[] : tauler) {for (String elem : fila) tauler_string += (elem + " | ") ; tauler_string += "\n";}
        JOptionPane.showMessageDialog(this, "\n [ Playing ]\n" + tauler_string, "Proof of work", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void addCasellaNegra(JPanel panel, String x){
        JLabel jLabel = new JLabel();
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        if(!x.equals(" \\ "))
            jLabel.setText(x);
        
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(jLabel);
    }
    
    private void addCasellaBlanca(JPanel panel, String v){
        JTextField field = new JTextField();
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(new Font("Arial", Font.PLAIN, 20));
        
        if(!v.equals("?")){
            field.setText(String.valueOf(v));
        }
        
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE ||c== KeyEvent.VK_DELETE)){
                    e.consume();
                }
                else if(c == KeyEvent.VK_0){
                    e.consume();
                }
                else
                    field.setText("");
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        panel.add(field);
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pause = new javax.swing.JButton();
        save = new javax.swing.JButton();
        crono = new javax.swing.JLabel();
        hint = new javax.swing.JButton();
        solve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kakuro ~ Jugant");

        jPanel1.setPreferredSize(new java.awt.Dimension(1030, 640));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        jPanel2.setLayout(new java.awt.GridLayout(0, 5));

        pause.setText("pause");
        jPanel2.add(pause);

        save.setText("save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel2.add(save);

        crono.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        crono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        crono.setText("00:00:00");
        jPanel2.add(crono);

        hint.setText("hint");
        jPanel2.add(hint);

        solve.setText("solve");
        jPanel2.add(solve);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setLayout(new GridLayout(tauler.length,tauler[0].length,1,1));

        // ADD TO GRID
        for (String fila[] : tauler) {
            for (String elem : fila){
                if(elem.contains("\\")){
                    addCasellaNegra(jPanel1,elem);
                }
                else{
                    addCasellaBlanca(jPanel1, elem);
                }
            }
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed
    
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
            java.util.logging.Logger.getLogger(PlayingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayingFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel crono;
    private javax.swing.JButton hint;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton pause;
    private javax.swing.JButton save;
    private javax.swing.JButton solve;
    // End of variables declaration//GEN-END:variables
}