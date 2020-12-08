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
    
    String usuari;
    Partida partida;
    TaulerComencat tc;
    
    public PlayingFrame(ControladoraInterficie pres, Object[] params) {
        this.ctrl_interficie = pres;
        
        String[][] tauler = (String[][]) params[0];
        int timestamp = (int) params[1];
        
        /* 'tauler' Està en format : 
        
            - blanca valor  4
            - blanca buida  ?
            - negra coses   4\3  (columna\fila) * si no té valor hi ha un espai: ' '. Exemple: "4\ "
            - negra buida   *
        
        */
        
        // Proof of work
        String tauler_string = "";
        for (String fila[] : tauler) {for (String elem : fila) tauler_string += (elem + " | ") ; tauler_string += "\n";}
        JOptionPane.showMessageDialog(this, "\n [ Playing ]\n" + tauler_string, "Proof of work", JOptionPane.INFORMATION_MESSAGE);
        

        initComponents();
    }
    
    public PlayingFrame() {
        
        /* THIS SHOULD NOT BE HERE <3 */
        /**/
        /**/ String txt = "";
        /**/ try {
        /**/     txt = Dades.carregaArxiu("dades/enunciats/enunciat2.txt");
        /**/ } catch (NoSuchFileException ex) {
        /**/     Logger.getLogger(PlayFrame.class.getName()).log(Level.SEVERE, null, ex);
        /**/ }
        /**/ tc = new TaulerComencat(txt);
        /**/
        /* <3 */
        
        this.ctrl_interficie = null;
        initComponents();
    }

    public void inicia(String usuari, String arxiuPartida){
        this.usuari = usuari;
        //Object[] ret = ctrl_interficie.deFilenameAPartidaTimestampHash(arxiuPartida);
    }
    
    private void addCasellaNegra(JPanel panel, Integer x, Integer y){
        JLabel jLabel = new JLabel();
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        if(x!=null && y!=null)
            jLabel.setText(x+"\\"+y);
        else if(y!=null)
            jLabel.setText(" \\"+y);
        else if(x!=null)
            jLabel.setText(x+"\\");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(jLabel);
    }
    
    private void addCasellaBlanca(JPanel panel, Integer v){
        JTextField field = new JTextField();
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(new Font("Arial", Font.PLAIN, 20));
        if(v!=null){
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kakuro ~ Jugant");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 546, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setLayout(new GridLayout(tc.getDimY(),tc.getDimX(),1,1));

        // ADD TO GRID
        for (int y = 0; y < tc.getDimY(); y++) {
            for(int x = 0; x < tc.getDimX(); x++){
                if(tc.esBlanca(x, y)){
                    addCasellaBlanca(jPanel1, ((CasellaBlanca)tc.getCasella(x, y)).getValor());
                }
                else{
                    addCasellaNegra(jPanel1, ((CasellaNegra)tc.getCasella(x, y)).getColumna(),((CasellaNegra)tc.getCasella(x, y)).getFila());
                }
            }
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}