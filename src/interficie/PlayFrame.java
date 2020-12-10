/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interficie;

import domini.tauler.TaulerComencat;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import persistencia.Dades;

/**
 *
 * @author zsc27
 */
public class PlayFrame extends JFrame{
    JPanel panel = new JPanel();
    
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
    public PlayFrame(){
        super("PlayFrame");
        Container container = getContentPane();
        panel = new JPanel();
        //IMPORT TAULER
        String txt = "";
        try {
            txt = Dades.carregaArxiu("dades/enunciats/enunciat2.txt");
        } catch (NoSuchFileException ex) {
            Logger.getLogger(PlayFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TaulerComencat tc = new TaulerComencat(txt);
        
        //INIT GRID
        panel.setLayout(new GridLayout(tc.getDimY(),tc.getDimX(),1,1));
        
        // ADD TO GRID
        for (int y = 0; y < tc.getDimY(); y++) {
            for(int x = 0; x < tc.getDimX(); x++){
                if(tc.esBlanca(x, y)){
                    addCasellaBlanca(panel, ((CasellaBlanca)tc.getCasella(x, y)).getValor());
                }
                else{
                    addCasellaNegra(panel, ((CasellaNegra)tc.getCasella(x, y)).getColumna(),((CasellaNegra)tc.getCasella(x, y)).getFila());
                }
            }
        }
        container.add(panel,BorderLayout.CENTER);
        
        setSize(tc.getDimX()*70,tc.getDimY()*70);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        PlayFrame playFrame = new PlayFrame();
    }
}
