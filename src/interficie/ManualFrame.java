/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interficie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author zsc27
 */
public class ManualFrame extends javax.swing.JFrame {
    private ControladoraInterficie ci;
    private ColorsPalet color = new ColorsPalet();
    private ArrayList<JPanel> caselles;
    private String[][] tauler;
    private int x;
    private int y;
    private JPanel grid;

    /**
     * Creates new form KakuroManual
     */
    public ManualFrame(){
    }
    public ManualFrame(ControladoraInterficie ci, int f, int c) {
        this.ci = ci;
        y = f;
        x = c;
        tauler = new String[y][x];
        caselles = new ArrayList<>();
        initComponents();
        grid = new JPanel(new GridLayout(y,x,3,3));
        getContentPane().setLayout(new BorderLayout());
        
        for(int i = 0; i < x*y ; i++){
            JPanel frame = new JPanel(new BorderLayout());
            tauler[i/x][i%x]=" \\ ";
            addCasella(frame, i);
            caselles.add(frame);
        }
        JButton save = new JButton("SAVE");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ci.presentaManualFrame(tauler);
            }
        });
        getContentPane().add(grid,BorderLayout.CENTER);
        getContentPane().add(save,BorderLayout.SOUTH);
    }
    
    private void addCasella(JPanel frame, int index){
        addCasellaNegra(frame, index);
        grid.add(frame);
        frame.setVisible(true);
        grid.setVisible(true);
        
    }
    private void addCasellaBlanca(JPanel frame, int index){
        frame.removeAll();
        frame.revalidate();
        JTextField v = new JTextField(2);
        v.setHorizontalAlignment(JTextField.CENTER);
        v.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                tauler[index/x][index%x]=""+e.getKeyChar();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        frame.add(v,BorderLayout.CENTER);
        JButton swap = new JButton("blanca");
        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCasellaNegra(frame, index);
                tauler[index/x][index%x]=" \\ ";
                printTauler();
            }
        });
        frame.add(swap,BorderLayout.SOUTH);
        frame.setBackground(Color.WHITE);
    }
    private void addCasellaNegra(JPanel frame, int index){
        frame.removeAll();
        frame.revalidate();
        JTextField v = new JTextField(2);
        v.setHorizontalAlignment(JTextField.CENTER);
        v.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String[] split = tauler[index/x][index%x].split("\\\\");
                String value = v.getText()+e.getKeyChar()+"\\"+split[1];
                tauler[index/x][index%x] = value;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        JTextField h = new JTextField(2);
        h.setHorizontalAlignment(JTextField.CENTER);
        h.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String[] split = tauler[index/x][index%x].split("\\\\");
                String value = split[0]+"\\"+h.getText()+e.getKeyChar();
                tauler[index/x][index%x] = value;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        JLabel separator = new JLabel("\\");
        separator.setFont(new Font("Serif", Font.BOLD, 18));
        separator.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(v,BorderLayout.WEST);
        frame.add(h,BorderLayout.EAST); 
        frame.add(separator,BorderLayout.CENTER);
        JButton swap = new JButton("negra");
        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCasellaBlanca(frame, index);
                tauler[index/x][index%x]="?";
                printTauler();
            }
        });
        
        frame.add(swap,BorderLayout.SOUTH);
        frame.setBackground(color.getEnfasi_Fosc());
    }
    
    private void printTauler(){
        for(String[]a : tauler ){
            for(String b: a){
                System.out.print(b+",");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1030, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ManualFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManualFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManualFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManualFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManualFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
