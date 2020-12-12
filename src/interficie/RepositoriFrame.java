package interficie;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class RepositoriFrame extends javax.swing.JFrame {

    // Controladora
    
    ControladoraInterficie ctrl_interficie;
    
    // Atributs
    
    private Vector<String> llista_id_enunciats;
    ArrayList<Object[]> info_enunciats;
    private String usuari;
    
    // Constructores
    
    public RepositoriFrame(ControladoraInterficie pres) {
        this.ctrl_interficie = pres;
        initComponents();
    }
    
    public RepositoriFrame() {
        this.ctrl_interficie = null;
        initComponents();
    }
    
    // Inicialització
    
    public void inicia(String usu) {
        this.usuari = usu;
        llista_id_enunciats = ctrl_interficie.ctrl_domini.llista_id_enunciats(); //Vector<>();
        info_enunciats = ctrl_interficie.ctrl_domini.getLlistaInfoEnunciats();
        actualitzaJList();
        listenerQuanTanques();
    }
    
    public void actualitza () {
        inicia(this.usuari);
    }
    
    // Exit (sortir)
    
    private void tornaAlInici() {
        this.setVisible(false);
        ctrl_interficie.inici.setVisible(true);
    }
    
    private void listenerQuanTanques(){
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                tornaAlInici();
                System.out.println("(RepositoriFrame) S'ha tancat amb la creu. Fent coses...");
            }
        });
    }
    
    // Mètodes d'actualització de la llista
    
    private String dificultat(int d) {
            if (d == 0) return "Fàcil";
            if (d == 1) return "Difícil";
            if (d == 2) return "Expert";
            if (d == 3) return "Personalitzat";
            return "?"; // This should not happen
    }
    
    private void actualitzaJList() {
        Vector<String> show_list = new Vector<String>();
        //Object[] items = new Object[] {"id","owner","diff","bool","user","time"};
        for (Object[] items : info_enunciats) {
            String id = (String) items[0];
            String owner = (String) items[1];
            String dif = (String) items[2];
            String d = dificultat(Integer.parseInt(dif));
            boolean hiHaRecord = (((String)items[3]).equals("1"));
            
            String record = "";
            if (hiHaRecord){
                String userTop = (String) items[4];
                String timeTop = (String) items[5]; int time = Integer.parseInt(timeTop);
                record = "  ~  Rècord: "+userTop+" en "+ctrl_interficie.deTimestampAVerbose(time);
            }
            String linea = "["+id+"] By " + owner + ": " + d + record;
            show_list.add(linea);
        }
        this.jListEnunciats.setListData(show_list);
        // OLD: this.jListEnunciats.setListData(llista_id_enunciats);
    }
    
    // iniciar nova partida desde un enunciat
    
    private void novaPartida_Desde_EnunciatSeleccionat() {
        int index = this.jListEnunciats.getSelectedIndex();
        if (index == -1) return;
        String id_enunciat_seleccionat = llista_id_enunciats.get(index);
        ctrl_interficie.iniciaNovaPartidaDesdeRepositori(id_enunciat_seleccionat);
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListEnunciats = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButtonGenera = new javax.swing.JButton();

        setTitle("Kakuro ~ Repositori");

        jListEnunciats.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "?", "?", "?" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListEnunciats);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Repositori públic d'Enunciats");

        jButtonGenera.setText("Genera Partida");
        jButtonGenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGeneraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonGenera, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGenera, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGeneraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeneraActionPerformed
      
        novaPartida_Desde_EnunciatSeleccionat();

    }//GEN-LAST:event_jButtonGeneraActionPerformed

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
            java.util.logging.Logger.getLogger(RepositoriFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RepositoriFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RepositoriFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RepositoriFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RepositoriFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListEnunciats;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
