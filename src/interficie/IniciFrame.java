package interficie;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class IniciFrame extends javax.swing.JFrame {

    ControladoraInterficie ctrl_interficie;
    
    String usuari = "<usuari>";
    Vector<String> llista_partides; // llista partides de l'usuari simple: nom + temps
    ArrayList<Object[]> info_partides;
    
    Object[] conf_preferida; // ConfiguraciĆ³ per defecte de l'usuari
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
    
    public void reset() {
        actualitzaJList();
        // SET Dificultat : Personalitzada
        this.jComboBox_Dificultat.setSelectedIndex(0); // Per defecte: "Personalitzat" Seleccionat
        set_configuracio_seleccionada(conf_preferida, true);
    }
    
    public void inicia(String usuari) {
        this.usuari = usuari;
        
        // SET Label NOM USUARI
        this.jLabelUsuari.setText("Benvingut " + this.usuari);
        
        jPanelConfig.setVisible(false);
        
        // SET Llista de PARTIDES de l'Usuari
        llista_partides = ctrl_interficie.ctrl_domini.llistaPartidesUsuari(); //Vector<>();
        info_partides = ctrl_interficie.ctrl_domini.getLlistaInfoPartides();
        actualitzaJList();
        
        // SET Configuracio Preferida Usuari
        conf_preferida = ctrl_interficie.ctrl_domini.getConfigPreferida(this.usuari);
        
        // Seleccionar la primera partida per defecte
        if(llista_partides.size() > 0){
            this.jListPartides.setSelectedIndex(0);
            preview_setPartidaSeleccionada(0);
        } else preview_capSeleccionada();
              
        // SET Dificultat : Personalitzada
        this.jComboBox_Dificultat.setSelectedIndex(0); // Per defecte: "Personalitzat" Seleccionat
        set_configuracio_seleccionada(conf_preferida, true);
        
    }
    
    private void set_configuracio_seleccionada(Object[] conf, boolean es_personalitzat){

// [x] Check box de Numero Blanques
        // SELECTED
        jCheckBox1.setSelected((Integer) conf[1] != null);
        
        // ENABLED
        if(!es_personalitzat){
              jCheckBox1.setEnabled(false);
        }else{
            jCheckBox1.setEnabled(true);
        }
        
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
        this.jLabelArxiuGuardat.setText("");
        this.jLabelEnunciat.setText("");
    }
    
    private void preview_setPartidaSeleccionada(int posicio){
        if (posicio < 0){ 
            preview_capSeleccionada();
            return;
        }
        //String partida_id = llista_partides.get(posicio);
        Object[] ret = info_partides.get(posicio);//ctrl_interficie.ctrl_domini.getInfoPartida(partida_id);
        this.jLabel_id_partida.setText((String) ret[1]);
        this.jLabelArxiuGuardat.setText("comencada/" + (String) ret[1] + ".txt");
        this.jLabel_timestamp.setText("" + ctrl_interficie.deTimestampAVerbose((int) ret[2]));
        this.jLabelEnunciat.setText((String) ret[0]);
    }
    
    private String dificultat(int d) {
            if (d == 0) return "FĆ cil";
            if (d == 1) return "DifĆ­cil";
            if (d == 2) return "Expert";
            return "Personalitzada";
    }
    
    private void actualitzaJList() {
        llista_partides = ctrl_interficie.ctrl_domini.llistaPartidesUsuari(); //Vector<>();
        info_partides = ctrl_interficie.ctrl_domini.getLlistaInfoPartides();
        Vector<String> show_list = new Vector<String>();
        //{elemsPartida[1], elemsPartida[0], Integer.parseInt(elemsPartida[2]), elemsPartida[3]};

        for (Object[] items : info_partides) {
            String id_enunciat = (String) items[0];
            String id = (String) items[1];
            int time = (int) items[2];
            String dif = (String) items[3];
            String d = dificultat(Integer.parseInt(dif));
            String linea = "["+id+"] "+d+"  ~  "+ctrl_interficie.deTimestampAVerbose(time);
            show_list.add(linea);
        }
        this.jListPartides.setListData(show_list);
        //OLD: this.jListPartides.setListData(llista_partides);
    }
    
    private int getDificultatSeleccionada () {
        String selected = (String) this.jComboBox_Dificultat.getSelectedItem();
        
        if (selected == "Personalitzat")    return 3;
        else if (selected == "FĆ cil")       return 0;
        else if (selected == "DifĆ­cil")     return 1;
        else if (selected == "Expert")      return 2;
        return -1;
    }
    
    private void popup (String msg) {
        JOptionPane.showMessageDialog(this, msg , "Impossibilitat de generar", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean check (int lim, int f, int c, int b, double ben) {
        return (f+c > lim && b > Math.round((f-1)*(c-1)*ben));
    }
    private boolean comprovaIdoneitatParametres(int files, int cols, int blanques_amb_valor, Integer blanques){
        String errors = "";
        
        if (files < 2) errors += "- El nĆŗmero de files ha de ser mĆ­nim 2.\n";
        if (cols < 2) errors += "- El nĆŗmero de columnes ha de ser mĆ­nim 2.\n";
        if ((files > 15 && cols > 15) || files + cols > 30) errors += "- Les dimensions del tauler sĆ³n massa altes. Proveu un 15x15.\n";
        
        if (blanques_amb_valor < 0) errors += "- NĆŗmero de blanques amb valor ha de ser major de 0.\n";
        if (blanques != null && blanques_amb_valor >= blanques ) errors += "- NĆŗmero de blanques amb valor ha de ser menor.\n";
        if (blanques == null && blanques_amb_valor >= (files-1)*(cols-1)*(0.54) ) errors += "- NĆŗmero de blanques amb valor ha de ser menor.\n";
        
        if (blanques != null && blanques < 0) errors += "- El nĆŗmero de blanques totals ha de ser positiu!\n";
        
        if (blanques != null) {
        
            boolean nope = false;
            //if (files+cols > 28 && blanques > Math.round((files-1)*(cols-1)*0.55)) errors += "\n";
            if      (check(28, files, cols, blanques, 0.55)) nope = true;
            else if (check(26, files, cols, blanques, 0.6)) nope = true;
            else if (check(24, files, cols, blanques, 0.6)) nope = true;
            else if (check(22, files, cols, blanques, 0.65)) nope = true;
            else if (check(20, files, cols, blanques, 0.65)) nope = true;
            else if (check(18, files, cols, blanques, 0.7)) nope = true;
            else if (check(16, files, cols, blanques, 0.8)) nope = true;

            else if (blanques > Math.round((files-1)*(cols-1)*0.85)) nope = true;

            if (nope) errors += "- El nĆŗmero de blanques totals ha de ser mĆ©s petit!\n";
        
        }
            // 1 <= blanques <= (files-1)*(cols-1)*
            /*
                benchmark
            
            15x15 - (107) 0.55
            14x14 - (101) 0.6
            13x13 - (86) 0.6
            12x12 - (78) 0.65
            11x11 - (67) 0.65
            10x10 - (56) 0.7
            9x9   - (51) 0.8
            8x8   - (41) 0.85
            7x7   - (31) 0.87
            
            */
        
        if (!errors.equals("")) popup (errors);
        else return true;
        return false;
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuari = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonRepo = new javax.swing.JButton();
        jButtonRankings = new javax.swing.JButton();
        jPanelConfig = new javax.swing.JPanel();
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPartides = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelArxiuGuardat = new javax.swing.JLabel();
        jLabel_timestamp = new javax.swing.JLabel();
        jLabel_id_partida = new javax.swing.JLabel();
        jButton_delete_partida = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabelEnunciat = new javax.swing.JLabel();
        jButtonNovaPartida = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButtonProposa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kakuro ~ Principal");
        setResizable(false);

        jLabelUsuari.setText("Benvingut ?");
        jLabelUsuari.setToolTipText("");
        jLabelUsuari.setName(""); // NOI18N
        jLabelUsuari.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
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

        jButtonRepo.setText("Repositori");
        jButtonRepo.setToolTipText("");
        jButtonRepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRepoActionPerformed(evt);
            }
        });

        jButtonRankings.setText("Rankings");
        jButtonRankings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRankingsActionPerformed(evt);
            }
        });

        jPanelConfig.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Configura la Partida");

        jLabel8.setText("Dificultat");

        jComboBox_Dificultat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personalitzat", "FĆ cil", "DifĆ­cil", "Expert" }));
        jComboBox_Dificultat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DificultatActionPerformed(evt);
            }
        });

        jLabel10.setText("Blanques totals");

        jSpinner_numBlanques.setModel(new javax.swing.SpinnerNumberModel(0, -1, 170, 1));

        jLabel11.setText("NĆŗmero de Files");

        jSpinner_numFiles.setModel(new javax.swing.SpinnerNumberModel(0, 0, 28, 1));

        jLabel12.setText("NĆŗmero Columnes");

        jSpinner_numCols.setModel(new javax.swing.SpinnerNumberModel(0, 0, 28, 1));

        jLabel13.setText("Blanques amb Valor");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jSpinner_blanquesValor.setModel(new javax.swing.SpinnerNumberModel(0, 0, 170, 1));

        jButton2.setText("Genera nova Partida");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guarda com a Preferida");
        jButton3.setToolTipText("");
        jButton3.setActionCommand("Guarda configuraciĆ³ Preferida");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfigLayout = new javax.swing.GroupLayout(jPanelConfig);
        jPanelConfig.setLayout(jPanelConfigLayout);
        jPanelConfigLayout.setHorizontalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Dificultat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelConfigLayout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner_numBlanques, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jSpinner_numFiles, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinner_numCols)
                            .addComponent(jSpinner_blanquesValor))))
                .addContainerGap())
        );
        jPanelConfigLayout.setVerticalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_Dificultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_numFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_numCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_blanquesValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner_numBlanques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jListPartides.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "?", "?", "?", "?" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListPartides.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListPartidesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListPartides);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Les teves partides");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Partida Seleccionada");

        jLabel4.setText("ID de la Partida:");

        jLabel5.setText("Temps emprat:");
        jLabel5.setToolTipText("");

        jLabel6.setText("Arxiu de guardat:");

        jLabelArxiuGuardat.setText("?");

        jLabel_timestamp.setText("?");

        jLabel_id_partida.setText("?");

        jButton_delete_partida.setText("Elimina Partida");
        jButton_delete_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_partidaActionPerformed(evt);
            }
        });

        jButton1.setText("Carrega partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Enunciat repositori:");

        jLabelEnunciat.setText("?");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelArxiuGuardat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_timestamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEnunciat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_id_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_id_partida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_timestamp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelArxiuGuardat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelEnunciat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_delete_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonNovaPartida.setText("Nova Partida");
        jButtonNovaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaPartidaActionPerformed(evt);
            }
        });

        jButtonProposa.setText("Proposa Kakuro Arxiu");
        jButtonProposa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProposaActionPerformed(evt);
            }
        });

        jButton4.setText("Proposa Kakuro Manual");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRepo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonProposa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jButtonNovaPartida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jButtonRankings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabelUsuari)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelUsuari))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonRepo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRankings)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonProposa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNovaPartida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
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
        
        /*JOptionPane.showMessageDialog(this, ""
                + "\n -- TODO --"
                + "\n [ Obrir partida ]\n"
                + "\n- Propietari: " + this.usuari + ""
                + "\n- Id de la partida: " + id_partida_seleccionada
                + "\n- Arxiu d'enunciat: " + nomEnunciat
                + "\n- Arxiu de partida: " + nomComencada
                + "\n- CronĆ²metre: " + verbose_timestamp, "InformaciĆ³ Partida id=" + id_partida_seleccionada, JOptionPane.INFORMATION_MESSAGE);
        */
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
        
        int index = this.jListPartides.getSelectedIndex();
        if (index == -1) return;
        
        Object[] opcions = { "SĆ­", "CancelĀ·la" };
        int opt = JOptionPane.showOptionDialog(null, "Segur que vols eliminar aquesta partida?", "AtenciĆ³",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, opcions, opcions[1]);
        System.out.println("(IniciFrame) Eliminar partida? " + opcions[opt]);
        if (opt != 0) return;
        
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
        else if (selected == "FĆ cil")       conf = conf_facil;
        else if (selected == "DifĆ­cil")     conf = conf_dificil;
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
        int dificultat = getDificultatSeleccionada();
        
        /*JOptionPane.showMessageDialog(this, ""
                + "\n -- TODO --"
                + "\n [ Crear Partida ]\n"
                + "\n Propietari: " + this.usuari + "\n"
                + "\n CONFIGURACIĆ: "
                + "\n- Num columnes(X): " + this.jSpinner_numCols.getValue()
                + "\n- Num files(Y): " + this.jSpinner_numFiles.getValue()
                + "\n- Num blanques amb valor: " + this.jSpinner_blanquesValor.getValue()
                + "\n- Num blanques: " + (jCheckBox1.isSelected() ? this.jSpinner_numBlanques.getValue() : "null")
                + "", "CreaciĆ³ d'una nova partida", JOptionPane.INFORMATION_MESSAGE);
        */
        boolean idoni = comprovaIdoneitatParametres(files, cols, blanques_amb_valor, blanques);
        if (!idoni) return;
        
        // GENERAR I OBRIR PARTIDA
        ctrl_interficie.generaAndIniciaNovaPartida(files, cols, blanques_amb_valor, blanques, dificultat);
        actualitzaJList();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonRepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRepoActionPerformed
        
        // Obrir repositori
        ctrl_interficie.repo.inicia(this.usuari);
        this.setVisible(false);
        ctrl_interficie.repo.setVisible(true);
        
    }//GEN-LAST:event_jButtonRepoActionPerformed

    private void jButtonNovaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaPartidaActionPerformed
        
        jPanelConfig.setVisible(!jPanelConfig.isVisible());
        
        
    }//GEN-LAST:event_jButtonNovaPartidaActionPerformed

    private void jButtonRankingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRankingsActionPerformed
        
        // Obrir ranking
        ctrl_interficie.ranking.inicia();
        this.setVisible(false);
        ctrl_interficie.ranking.setVisible(true);
        
    }//GEN-LAST:event_jButtonRankingsActionPerformed

    private void jButtonProposaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProposaActionPerformed
        
        JFileChooser chooser = new JFileChooser();
        File f = null;
        int ret = chooser.showOpenDialog( null );
        
        if( ret == JFileChooser.APPROVE_OPTION ) {
               f = chooser.getSelectedFile() ;
        }
        if(f == null) return;
        String path = f.getPath();
        System.out.println("(IniciFrame) Presenta kakuro. Path = " + path);
        
        ctrl_interficie.iniciaNovaPartidaDesdeArxiu(path);

    }//GEN-LAST:event_jButtonProposaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /*
        
        public boolean setConfiguracio(String usuari, String[] config) {
        // numeroBlanquesEstablertes, numeroBlanques, dimX, dimY
        
        */
        int files = (int) this.jSpinner_numFiles.getValue();
        int cols = (int) this.jSpinner_numCols.getValue();
        int blanques_amb_valor = (int) this.jSpinner_blanquesValor.getValue();
        Integer blanques = jCheckBox1.isSelected() ? (int)this.jSpinner_numBlanques.getValue() : -1;
        
        //ctrl_interficie.setConfigPreferida(files, cols, blanques_amb_valor, blanques);
        //conf_preferida = new Object[] {files, cols, blanques_amb_valor, blanques};
        
        ctrl_interficie.setConfigPreferida(blanques_amb_valor, blanques, cols, files);
        conf_preferida = new Object[] {blanques_amb_valor, blanques, cols, files};
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        //JTextField Jfiles = new JTextField(5);
        //JTextField Jcolumnes = new JTextField(5);
        JSpinner Jfiles = new JSpinner(new SpinnerNumberModel(2, 2, 15, 1));
        JSpinner Jcolumnes = new JSpinner(new SpinnerNumberModel(2, 2, 15, 1));
        //Jfiles.set
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("files:"));
        myPanel.add(Jfiles);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("columnes:"));
        myPanel.add(Jcolumnes);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
            "NĆŗmero de files i columnes", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          System.out.println("(Popup) files: " + Jfiles.getValue());
          System.out.println("(Popup) columnes: " + Jcolumnes.getValue());
          ctrl_interficie.iniciaManualFrame((int)Jfiles.getValue(), (int)Jcolumnes.getValue());
        }
        
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
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonNovaPartida;
    private javax.swing.JButton jButtonProposa;
    private javax.swing.JButton jButtonRankings;
    private javax.swing.JButton jButtonRepo;
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
    private javax.swing.JLabel jLabelArxiuGuardat;
    private javax.swing.JLabel jLabelEnunciat;
    private javax.swing.JLabel jLabelUsuari;
    private javax.swing.JLabel jLabel_id_partida;
    private javax.swing.JLabel jLabel_timestamp;
    private javax.swing.JList<String> jListPartides;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelConfig;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_blanquesValor;
    private javax.swing.JSpinner jSpinner_numBlanques;
    private javax.swing.JSpinner jSpinner_numCols;
    private javax.swing.JSpinner jSpinner_numFiles;
    // End of variables declaration//GEN-END:variables
}
