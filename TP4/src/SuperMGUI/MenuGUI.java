package SuperMGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.util.ArrayList;


public class MenuGUI extends JFrame{

    public static void menu(String util){
        JFrame menuT = new JFrame("Bem vindo");
        menuT.setSize(512,512);
        menuT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuT.setLayout(null);

        GridLayout grid = new GridLayout(4, 1, 0, 40);
        JPanel panel = new JPanel(grid);
        panel.setBounds(98,160,300,260);
        panel.setOpaque(false);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        menuT.setContentPane(image);
        menuT.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel texto = new JLabel("");
        texto.setText("Bem vindo " + util);
        texto.setFont(new Font("Agency FB",Font.BOLD,40));
        texto.setBounds(128,050,512,50);
        texto.setForeground(new Color(0,0,0));

        
        JButton BVer = new JButton("Verificar Produtos");
        JButton BAdd = new JButton("Adicionar Produto");
        JButton BRem = new JButton("Remover Produto");
        JButton BStock = new JButton("Alterar Stock");

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(BVer);
        buttons.add(BAdd);
        buttons.add(BStock);
        buttons.add(BRem);

        Font letra = new Font("Agency FB",Font.BOLD,35);
        for (JButton button:buttons){
            button.setBackground(new Color(255,255,255));
            button.setForeground(new Color(0,0,0));
            button.setFont(letra);
            panel.add(button);
        }

        BVer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuT.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        VerGUI.escolherCategoria(util);
                    }
                });
            }
            
        });

        BAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuT.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        addGUI.escolherCategoria(util);
                    }
                });
            }
            
        });

        BStock.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuT.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            changeStockGUI.changeStock(util);
                        } catch (ParserConfigurationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (SAXException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
            
        });
        
        BRem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuT.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            removerProdutoGUI.removeProduto(util);
                        } catch (ParserConfigurationException | SAXException | IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
            
        });
        menuT.add(texto);
        menuT.add(panel,SwingConstants.CENTER);
        menuT.setVisible(true);

        
    }


    public static void main(String[] args){
        menu("Diogo");

    }

}