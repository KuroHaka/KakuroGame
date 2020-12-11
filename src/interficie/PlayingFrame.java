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
import domini.partida.Partida;
import domini.tauler.casella.Casella;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PlayingFrame extends javax.swing.JFrame {

    private ControladoraInterficie ctrl_interficie;
    private String usuari; /* THIS SHOULD NOT BE HERE <3 */
    private Partida partida; /* THIS SHOULD NOT BE HERE <3 */
    private TaulerComencat tc;  /* THIS SHOULD NOT BE HERE <3 */
    private Timer t;
    private boolean running;
    private String[][] tauler;
    private int timestamp;
    private int casellesDisponibles;
    private List<JTextField> casellesBlanques = new ArrayList();
    private boolean colorCambiat;
    private ActionListener actCrono = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timestamp++;
            actualitzaCrono();
        }
    };
    
    public PlayingFrame(ControladoraInterficie pres, Object[] params) {
        this.ctrl_interficie = pres;
        
        // Coses xaxis
        
        this.tauler = (String[][]) params[0];
        this.timestamp = (int) params[1];
        
        // popupTauler();
        
        casellesDisponibles = 0;
        initComponents();
        t = new Timer(1000, actCrono);
        running = true;
        colorCambiat = false;
        t.start();
    }
    
    public PlayingFrame() {
        
        /* THIS SHOULD NOT BE HERE <3 */
        /**/
        /**/ String txt = "";
        /**/ try {
        /**/     txt = Dades.carregaArxiu("dades/enunciats/334.txt");
        /**/ } catch (NoSuchFileException ex) {
        /**/     Logger.getLogger(PlayFrame.class.getName()).log(Level.SEVERE, null, ex);
        /**/ }
        /**/ tc = new TaulerComencat(txt);
        /**/
        /* END */
        
        tauler = tc.toFormatInterficie();
        timestamp = 0;
        this.ctrl_interficie = null;
        casellesDisponibles = 0;
        initComponents();
        t = new Timer(1000, actCrono);
        running = true;
        colorCambiat = false;
        popupTauler();
        t.start();
    }

    public void inicia(String usuari, String arxiuPartida){
        // Nothing here :)
    }
    
    private void actualitzaCrono(){
        int seconds = timestamp;
        long hours = TimeUnit.SECONDS.toHours(seconds);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
        String tot = "";
        boolean first = true;
        crono.setText(String.format("%02d", hours)+":"+String.format("%02d", minute)+":"+String.format("%02d", second));
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
    
    private void addCasellaBlanca(JPanel panel, String v, int x, int y){
        JTextField field = new JTextField();
        casellesBlanques.add(field);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(new Font("Arial", Font.PLAIN, 20));
        
        if(!v.equals("?")){
            field.setText(String.valueOf(v));
        }
        else
            casellesDisponibles++;
        
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
                else if(c == KeyEvent.VK_BACK_SPACE && tauler[y][x].equals("?")){
                    e.consume();
                }
                else if(c == KeyEvent.VK_BACK_SPACE && !tauler[y][x].equals("?")){
                    field.setText("");
                    tauler[y][x] = "?";
                    casellesDisponibles++;
                }
                else{
                    if(tauler[y][x].equals("?")){
                        casellesDisponibles--;
                    }
                    field.setText("");
                    tauler[y][x] = String.valueOf(e.getKeyChar());
                }
                if(casellesDisponibles == 0){
                    comprobarSolucio(field, String.valueOf(e.getKeyChar()));
                }
                else if(colorCambiat){
                    for (JTextField casella : casellesBlanques)
                        casella.setBackground(Color.WHITE);
                }
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
    
    private void comprobarSolucio(JTextField jf, String lastValue){
        jf.setText(lastValue);
        if(true/*COMPROVA SOLUCIO*/){
            t.stop();
            for (JTextField casella : casellesBlanques){
                casella.setBackground(Color.GREEN);
            }
            Object[] opcions = { "Si", "No"};
            int opt = JOptionPane.showOptionDialog(null, "HAS COMPLETAT EL KAKURO EN: "+this.crono.getText()+"\nVols enregistrar-la al rànking?" , "ENHORABONA",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, opcions, opcions[1]);
            partidaAcabada(opt);
        }
        else{
            for (JTextField casella : casellesBlanques)
                casella.setBackground(Color.RED);
        }
        colorCambiat = true;
    }
    
    private void partidaAcabada(int opt){
        ctrl_interficie.acabaPartida(opt==0,timestamp);
        this.setVisible(false);
        ctrl_interficie.inici.reset();
        ctrl_interficie.inici.setVisible(true);
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

        setTitle("Kakuro ~ Jugant");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });
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
        hint.setEnabled(false);
        jPanel2.add(hint);

        solve.setText("solve");
        solve.setEnabled(false);
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
        for (int y = 0; y < tauler.length; y++ ) {
            for (int x = 0; x < tauler[0].length; x++){
                if(tauler[y][x].contains("\\")){
                    addCasellaNegra(jPanel1,tauler[y][x]);
                }
                else{
                    addCasellaBlanca(jPanel1, tauler[y][x], x, y);
                }
            }
        }

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        t.stop();
        if(ctrl_interficie.guardaPartida(timestamp, tauler)){
            JOptionPane.showMessageDialog(this, "\nNotificació\n" , "S'ha guardat correctament", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "\nNotificació\n" , "No s'ha pogut guardar", JOptionPane.INFORMATION_MESSAGE);
        }
        t.start();
        
    }//GEN-LAST:event_saveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("(PlayingFrame) S'ha tancat amb la creu. Fent coses...");
        ctrl_interficie.playing.setVisible(false);
        ctrl_interficie.inici.reset();
        ctrl_interficie.inici.setVisible(true);
        
        // guarda coses ();
    }//GEN-LAST:event_formWindowClosing

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        // TODO add your handling code here:
        if(running){
            t.stop();
            this.pause.setText("resume");
        }
        else{
            t.start();
            this.pause.setText("pause");
        }
        running = !running;
        
    }//GEN-LAST:event_pauseActionPerformed
    
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