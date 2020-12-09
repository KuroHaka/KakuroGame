package interficie;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class IniciFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    String usuari = "<usuari>";
    Vector<String> llista_partides; // llista partides de l'usuari simple: nom + temps
    
    Object[] conf_preferida; // Configuració per defecte de l'usuari
    // Configuracions diverses: numeroBlanquesEstablertes,numeroBlanques,Ncols(X),Nfiles(Y)
    Object[] conf_facil     = new Object[] {4, null, 5, 5};
    Object[] conf_dificil   = new Object[] {0, null, 8, 8};
    Object[] conf_expert    = new Object[] {0, null, 12, 12};
            
    
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
        actualitzaJList();
        
        // SET Configuracio Preferida Usuari
        conf_preferida = ctrl_interficie.ctrl_domini.getConfigPreferida(this.usuari);
        
        // Seleccionar la primera partida per defecte
        if(llista_partides.size() > 0){
        this.jListPartides.setSelectedIndex(0);
        preview_setPartidaSeleccionada(0);
        } else preview_capSeleccionada();
              
        // SET Dificultat : Personalitzada
        this.jComboBox_Dificultat.setSelectedItem(0); // Per defecte: "Personalitzat" Seleccionat
        set_configuracio_seleccionada(conf_preferida, true);
        
    }
    
    private void set_configuracio_seleccionada(Object[] conf, boolean es_personalitzat){

// [x] Check box de Numero Blanques
        // SELECTED
        jCheckBox1.setSelected((conf[1] != null));
        
        // ENABLED
        if(!es_personalitzat){
              jCheckBox1.setEnabled(false);
        }else jCheckBox1.setEnabled(true);
        
// Spinners
        //ENABLED
        this.jSpinner_blanquesValor.setEnabled(es_personalitzat);
        this.jSpinner_numCols.setEnabled(es_personalitzat);
        this.jSpinner_numFiles.setEnabled(es_personalitzat);
        
        if (jCheckBox1.isSelected())
            this.jSpinner_numBlanques.setEnabled(es_personalitzat);
        else 
            this.jSpinner_numBlanques.setEnabled(false);
        
        // VALORS
        this.jSpinner_blanquesValor.setValue(conf[0]);
        this.jSpinner_numCols.setValue(conf[2]);
        this.jSpinner_numFiles.setValue(conf[3]);
        
        if (jCheckBox1.isSelected())
            this.jSpinner_numBlanques.setValue(conf[1]); 
        else 
            this.jSpinner_numBlanques.setValue(-1);

    }
    
    private void preview_capSeleccionada() {
        this.jLabel_id_partida.setText("");
        this.jLabel_timestamp.setText("");
    }
    
    private void actualitzaJList() {
        this.jListPartides.setListData(llista_partides);
    }
    
    private void preview_setPartidaSeleccionada(int posicio){
        if (posicio < 0){ 
            preview_capSeleccionada();
            return;
        }
        String partida_id = llista_partides.get(posicio);
        this.jLabel_id_partida.setText(partida_id);
        Object[] ret = ctrl_interficie.ctrl_domini.getInfoPartida(partida_id);
        this.jLabel_timestamp.setText("" + (int) ret[2]);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuari = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Dificultat = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jSpinner_numBlanques = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jSpinner_numFiles = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jSpinner_numCols = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSpinner_blanquesValor = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPartides = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_id_partida = new javax.swing.JLabel();
        jLabel_timestamp = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton_delete_partida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kakuro ~ Principal");
        setResizable(false);

        jLabelUsuari.setText("Benvingut <usuari>");
        jLabelUsuari.setName(""); // NOI18N
        jLabelUsuari.setOpaque(true);

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

        jButton2.setText("Crea nova Partida [TODO]");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guarda com a Preferit [TODO]");
        jButton3.setToolTipText("");

        jButton4.setText("Repositori");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Rankings [TODO]");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Configuració preferida");

        jLabel8.setText("Dificultat");

        jComboBox_Dificultat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personalitzat", "Fàcil", "Difícil", "Expert" }));
        jComboBox_Dificultat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DificultatActionPerformed(evt);
            }
        });

        jLabel10.setText("# Caselles Blanques");

        jLabel11.setText("Número de Files");

        jLabel12.setText("Número Columnes");

        jLabel13.setText("Blanques amb Valor");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Dificultat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner_numBlanques, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jSpinner_numFiles, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinner_numCols)
                            .addComponent(jSpinner_blanquesValor))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_Dificultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_numFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_numCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_blanquesValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner_numBlanques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jCheckBox1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Les teves partides");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Partida Seleccionada");

        jLabel4.setText("Id Partida:");

        jLabel5.setText("Timestamp:");

        jLabel_id_partida.setText("jLabel7");

        jLabel_timestamp.setText("jLabel8");

        jLabel6.setText("jLabel6");

        jLabel9.setText("jLabel9");

        jButton1.setText("Carrega partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_delete_partida.setText("Elimina Partida");
        jButton_delete_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_partidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_id_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_timestamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_id_partida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_timestamp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUsuari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelUsuari)
                            .addComponent(jLabel2))
                        .addGap(17, 17, 17)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
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
        
        JOptionPane.showMessageDialog(this, ""
                + "\n -- TODO --"
                + "\n [ Obrir partida ]\n"
                + "\n- Propietari: " + this.usuari + ""
                + "\n- Id de la partida: " + id_partida_seleccionada
                + "\n- Arxiu d'enunciat: " + nomEnunciat
                + "\n- Arxiu de partida: " + nomComencada
                + "\n- Cronòmetre: " + verbose_timestamp, "Informació Partida id=" + id_partida_seleccionada, JOptionPane.INFORMATION_MESSAGE);
        
        // OBRIR PARTIDA
        ctrl_interficie.iniciaPartida(id_partida_seleccionada);
        
        
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
        preview_setPartidaSeleccionada(this.jListPartides.getSelectedIndex());
    }//GEN-LAST:event_jListPartidesValueChanged

    private void jButton_delete_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete_partidaActionPerformed
        
        Object[] opcions = { "Sí", "Cancel·la" };
        int opt = JOptionPane.showOptionDialog(null, "Segur que vols eliminar aquesta partida?", "Atenció",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, opcions, opcions[1]);
        System.out.println("(IniciFrame) Eliminar partida? " + opcions[opt]);
        if (opt != 0) return;
        
        int index = this.jListPartides.getSelectedIndex();
        String id_partida = this.llista_partides.get(index);
        boolean ok = ctrl_interficie.ctrl_domini.borrarPartida(id_partida, this.usuari);
        if(ok) {
            llista_partides.remove(index);
            actualitzaJList();
            preview_capSeleccionada();
            this.jListPartides.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_jButton_delete_partidaActionPerformed

    private void jComboBox_DificultatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DificultatActionPerformed
        
        String selected = (String) this.jComboBox_Dificultat.getSelectedItem();
        System.out.print("(IniciFrame) Dificultat seleccionada : " + selected + "... ");
        
        boolean es_personalitzat = (selected == "Personalitzat");
        
        Object [] conf;
        if (selected == "Personalitzat")    conf = conf_preferida;
        else if (selected == "Fàcil")       conf = conf_facil;
        else if (selected == "Difícil")     conf = conf_dificil;
        else if (selected == "Expert")      conf = conf_expert;
        else conf = conf_preferida; // This should never happen
        
        set_configuracio_seleccionada(conf, es_personalitzat);
        
        System.out.println("Done.");
    }//GEN-LAST:event_jComboBox_DificultatActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()){
            this.jSpinner_numBlanques.setEnabled(true);
            this.jSpinner_numBlanques.setValue(0);
            
        }
        else {
            this.jSpinner_numBlanques.setEnabled(false);
            this.jSpinner_numBlanques.setValue(-1);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int files = (int) this.jSpinner_numFiles.getValue();
        int cols = (int) this.jSpinner_numCols.getValue();
        int blanques_amb_valor = (int) this.jSpinner_blanquesValor.getValue();
        Integer blanques = jCheckBox1.isSelected() ? (int)this.jSpinner_numBlanques.getValue() : null;
        
        JOptionPane.showMessageDialog(this, ""
                + "\n -- TODO --"
                + "\n [ Crear Partida ]\n"
                + "\n Propietari: " + this.usuari + "\n"
                + "\n CONFIGURACIÓ: "
                + "\n- Num columnes(X): " + this.jSpinner_numCols.getValue()
                + "\n- Num files(Y): " + this.jSpinner_numFiles.getValue()
                + "\n- Num blanques amb valor: " + this.jSpinner_blanquesValor.getValue()
                + "\n- Num blanques: " + (jCheckBox1.isSelected() ? this.jSpinner_numBlanques.getValue() : "null")
                + "", "Creació d'una nova partida", JOptionPane.INFORMATION_MESSAGE);
        
        // GENERAR I OBRIR PARTIDA
        ctrl_interficie.generaAndIniciaNovaPartida(files, cols, blanques_amb_valor, blanques);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        // Obrir repositori
        ctrl_interficie.repo.inicia(this.usuari);
        this.setVisible(false);
        ctrl_interficie.repo.setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton_delete_partida;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox_Dificultat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUsuari;
    private javax.swing.JLabel jLabel_id_partida;
    private javax.swing.JLabel jLabel_timestamp;
    private javax.swing.JList<String> jListPartides;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_blanquesValor;
    private javax.swing.JSpinner jSpinner_numBlanques;
    private javax.swing.JSpinner jSpinner_numCols;
    private javax.swing.JSpinner jSpinner_numFiles;
    // End of variables declaration//GEN-END:variables
}
