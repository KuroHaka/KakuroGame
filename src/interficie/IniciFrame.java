package interficie;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc
 */
public class IniciFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    String usuari = "<usuari>";
    Vector<String> partides_raw; // llista partides de l'usuari amb el nom de l'arxiu
    Vector<String> llista_partides; // llista partides de l'usuari simple: nom + temps
    
    public IniciFrame(ControladoraInterficie pres) {
        this.ctrl_interficie = pres;
        initComponents();
    }
    
    // Testing only
    public IniciFrame() {
        this.ctrl_interficie = null;
        initComponents();
    }
    
    public void inicia(String usuari) {
        this.usuari = usuari;
        
        // SET Label NOM USUARI
        
        this.jLabelUsuari.setText("Benvingut " + this.usuari);
        
        // SET Llista de PARTIDES de l'Usuari
        
        partides_raw = ctrl_interficie.persist.llistaPartidesUsuari(this.usuari);
        llista_partides = new Vector<>();
        
        for (String p : partides_raw) {
            Object[] ret = ctrl_interficie.deFilenameAPartidaTimestampHash(p);
            llista_partides.add(ret[0] + " <" + ret[1] + ">");
        }
        this.jListPartides.setListData(llista_partides);
        
    }
    
    /*public Object[] deFilenameAPartidaTimestampHash(String f){
        String[] parts = f.split("@");
        String nom = parts[1];
        String timestamp = deTimestampAVerbose(Long.parseLong(parts[2]));
        String hash = parts[3];
        return new Object[] {nom, timestamp, hash};
    }
    
    public static String deTimestampAVerbose(long seconds) {
            int day = (int)TimeUnit.SECONDS.toDays(seconds);        
            long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
            long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
            long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
            String tot = "";
            boolean first = true;
            if (day != 0) {tot += (day + "d"); first=false;}
            if (hours != 0) { if(!first) tot += " "; tot += (hours + "h"); first=false;}
            if (minute != 0) { if(!first) tot += " "; tot += (minute + "m"); first=false;}
            if (second != 0) { if(!first) tot += " "; tot += (second + "s"); first=false;}
            if (first) return "no començada";
            return tot;

    }*/
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuari = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPartides = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kakuro ~ Principal");
        setResizable(false);

        jLabelUsuari.setText("Benvingut <usuari>");
        jLabelUsuari.setName(""); // NOI18N
        jLabelUsuari.setOpaque(true);

        jListPartides.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListPartides);

        jButton1.setText("Carrega partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Les teves partides");

        jLabel2.setForeground(new java.awt.Color(0, 0, 238));
        jLabel2.setText("logout");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(396, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUsuari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuari)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // Partida Seleccionada
        Integer index = this.jListPartides.getSelectedIndex();
        if (index == -1) return; // Cap seleccionat
        System.out.println("(IniciFrame) Index partida=" + index);
        
        String arxiu_selec = partides_raw.get(index);
        
        Object[] ret = ctrl_interficie.deFilenameAPartidaTimestampHash(arxiu_selec);
        String nom = (String) ret[0];
        String timestamp = (String) ret[1];
        String hash = (String) ret[2];
        
        // TODO Obrir partida
        JOptionPane.showMessageDialog(this, "\n -- TODO --\nObrir partida:\n\n- Usuari: " + this.usuari + "\n- Arxiu: " + arxiu_selec + "\n- Partida: " + nom + "\n- Cronòmetre: " + timestamp);
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // NOTHING TO DO HERE
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        ctrl_interficie.login.reset();
        this.setVisible(false);
        ctrl_interficie.login.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(IniciFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IniciFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IniciFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IniciFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelUsuari;
    private javax.swing.JList<String> jListPartides;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
