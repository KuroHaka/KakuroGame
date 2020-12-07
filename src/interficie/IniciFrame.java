package interficie;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class IniciFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    String usuari = "<usuari>";
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
        llista_partides = ctrl_interficie.ctrl_domini.llistaPartidesUsuari(); //Vector<>();
        actualitzaJList();//this.jListPartides.setListData(llista_partides);
        // Seleccionar la primera partida per defecte
        if(llista_partides.size() > 0){
        this.jListPartides.setSelectedIndex(0);
        setPartidaSeleccionada(0);
        } else capSeleccionada();
    }
    
    private void capSeleccionada() {
        this.jLabel_id_partida.setText("");
        this.jLabel_timestamp.setText("");
    }
    
    private void actualitzaJList() {
        this.jListPartides.setListData(llista_partides);
    }
    
    private void setPartidaSeleccionada(int posicio){
        String partida_id = llista_partides.get(posicio);
        this.jLabel_id_partida.setText(partida_id);
        Object[] ret = ctrl_interficie.ctrl_domini.getInfoPartida(partida_id);
        this.jLabel_timestamp.setText("" + (int) ret[2]);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuari = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPartides = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_delete_partida = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_id_partida = new javax.swing.JLabel();
        jLabel_timestamp = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

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
        jListPartides.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListPartidesValueChanged(evt);
            }
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Partida Seleccionada");

        jButton_delete_partida.setText("Elimina Partida");
        jButton_delete_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_partidaActionPerformed(evt);
            }
        });

        jLabel4.setText("Id Partida:");

        jLabel5.setText("Timestamp:");

        jLabel6.setText("jLabel6");

        jLabel_id_partida.setText("jLabel7");

        jLabel_timestamp.setText("jLabel8");

        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUsuari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_id_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_timestamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelUsuari)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_id_partida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_timestamp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        
        String id_partida_seleccionada = llista_partides.get(index);
        
        Object[] ret = ctrl_interficie.ctrl_domini.getInfoPartida(id_partida_seleccionada);
        String nomEnunciat = (String) ret[0];
        String nomComencada = (String) ret[1];
        int timestamp = (int) ret[2];

        String verbose_timestamp = ctrl_interficie.deTimestampAVerbose(timestamp);
        
        // TODO Obrir partida
        
        JOptionPane.showMessageDialog(this, ""
                + "\n -- TODO --"
                + "\n [ Obrir partida ]\n"
                + "\n- Propietari: " + this.usuari + ""
                + "\n- Id de la partida: " + id_partida_seleccionada
                + "\n- Arxiu d'enunciat: " + nomEnunciat
                + "\n- Arxiu de partida: " + nomComencada
                + "\n- Cronòmetre: " + verbose_timestamp, "Informació Partida id=" + id_partida_seleccionada, JOptionPane.INFORMATION_MESSAGE);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // NOTHING TO DO HERE
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        ctrl_interficie.login.reset();
        this.setVisible(false);
        ctrl_interficie.login.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jListPartidesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListPartidesValueChanged
        setPartidaSeleccionada(this.jListPartides.getSelectedIndex());
    }//GEN-LAST:event_jListPartidesValueChanged

    private void jButton_delete_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete_partidaActionPerformed
        int index = this.jListPartides.getSelectedIndex();
        String id_partida = this.llista_partides.get(index);
        boolean ok = ctrl_interficie.ctrl_domini.borrarPartida(id_partida, this.usuari);
        if(ok) {
            llista_partides.remove(index);
            actualitzaJList();
            if(index > 0) {
                setPartidaSeleccionada(index - 1);
            }
            else {
                capSeleccionada();
                this.jListPartides.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_jButton_delete_partidaActionPerformed

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
    private javax.swing.JButton jButton_delete_partida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUsuari;
    private javax.swing.JLabel jLabel_id_partida;
    private javax.swing.JLabel jLabel_timestamp;
    private javax.swing.JList<String> jListPartides;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
