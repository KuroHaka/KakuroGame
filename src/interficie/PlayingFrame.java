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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int cronocont;
    private ColorsPalet colorsPalet = new ColorsPalet();
    private Image img;
    
    private ActionListener actCrono = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cronocont>=10){
                timestamp++;
                actualitzaCrono();
                cronocont=0;
            }
            cronocont++;
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
        t = new Timer(100, actCrono);
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
        t = new Timer(10, actCrono);
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
        jLabel.setBackground(colorsPalet.getEnfasi_Fosc());
        jLabel.setForeground(colorsPalet.getText_Clar());
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        if(!x.equals(" \\ "))
            jLabel.setText(x);
        
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(jLabel);
    }
    
    private void addCasellaBlanca(JPanel panel, String v, int x, int y){
        JTextField field = new JTextField();
        field.setBackground(colorsPalet.getFons_Clar());
        field.setForeground(colorsPalet.getText_Fosc());
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
                    for (JTextField casella : casellesBlanques){
                        casella.setBackground(colorsPalet.getFons_Clar());
                        casella.setForeground(colorsPalet.getText_Fosc());
                    }
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
        if(ctrl_interficie.esSolucio(tauler)){
            jf.setText(lastValue);
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
            for (JTextField casella : casellesBlanques){
                casella.setBackground(Color.RED);
                casella.setForeground(colorsPalet.getText_Clar());
            }
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

        jPanel2 = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        toolbar = new javax.swing.JPanel();
        pause = new javax.swing.JButton();
        save = new javax.swing.JButton();
        crono = new javax.swing.JLabel();
        hint = new javax.swing.JButton();
        solve = new javax.swing.JButton();

        setTitle("Kakuro ~ Jugant");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        gamePanel.setPreferredSize(new java.awt.Dimension(1030, 640));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        pause.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_play_48px.png"))); // NOI18N
        pause.setBorder(null);
        pause.setBorderPainted(false);
        pause.setContentAreaFilled(false);
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_save_52px.png"))); // NOI18N
        save.setToolTipText("");
        save.setBorder(null);
        save.setBorderPainted(false);
        save.setContentAreaFilled(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        crono.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        crono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        crono.setText("00:00:00");

        hint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_idea_48px.png"))); // NOI18N
        hint.setBorder(null);
        hint.setBorderPainted(false);
        hint.setContentAreaFilled(false);
        hint.setEnabled(false);
        hint.setFocusable(false);
        hint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintActionPerformed(evt);
            }
        });

        solve.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        solve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_davinci_resolve_48px.png"))); // NOI18N
        solve.setBorder(null);
        solve.setBorderPainted(false);
        solve.setContentAreaFilled(false);
        solve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toolbarLayout = new javax.swing.GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
            toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarLayout.createSequentialGroup()
                .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(crono, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solve, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        toolbarLayout.setVerticalGroup(
            toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarLayout.createSequentialGroup()
                .addGroup(toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crono, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solve, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gamePanel.setLayout(new GridLayout(tauler.length,tauler[0].length,1,1));

        // ADD TO GRID
        for (int y = 0; y < tauler.length; y++ ) {
            for (int x = 0; x < tauler[0].length; x++){
                if(tauler[y][x].contains("\\")){
                    addCasellaNegra(gamePanel,tauler[y][x]);
                }
                else{
                    addCasellaBlanca(gamePanel, tauler[y][x], x, y);
                }
            }
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        
        if(ctrl_interficie.guardaPartida(timestamp, tauler)){
            JOptionPane.showMessageDialog(this, "\nNotificació\n" , "S'ha guardat correctament", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "\nNotificació\n" , "No s'ha pogut guardar", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
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
            gamePanel.setVisible(false);
            t.stop();
            pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_pause_48px.png")));
        }
        else{
            t.start();
            gamePanel.setVisible(true);
            pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interficie/icones/icons8_play_48px.png")));
        }
        running = !running;
        
    }//GEN-LAST:event_pauseActionPerformed

    private void solveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveActionPerformed
        // TODO add your handling code here:
        this.tauler = ctrl_interficie.getSolucio();
        this.gamePanel.removeAll();
        for (int y = 0; y < tauler.length; y++ ) {
            for (int x = 0; x < tauler[0].length; x++){
                if(tauler[y][x].contains("\\")){
                    addCasellaNegra(gamePanel,tauler[y][x]);
                }
                else{
                    addCasellaBlanca(gamePanel, tauler[y][x], x, y);
                }
            }
        }
        toolbar.setVisible(false);
    }//GEN-LAST:event_solveActionPerformed

    private void hintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_hintActionPerformed
    
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
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton hint;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton pause;
    private javax.swing.JButton save;
    private javax.swing.JButton solve;
    private javax.swing.JPanel toolbar;
    // End of variables declaration//GEN-END:variables
}