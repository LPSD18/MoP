package SuperMGUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Produtos.Categoria;
import xml.xmlReader;

public class VerGUI extends JFrame {
    
    public static void escolherCategoria(String util){
        JFrame menu = new JFrame("Categorias");
        menu.setSize(512,512);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);

        GridLayout grid = new GridLayout(4, 1, 0, 40);
        JPanel panel = new JPanel(grid);
        panel.setBounds(98, 160, 300, 260);
        panel.setOpaque(false);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        menu.setContentPane(image);
        menu.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel texto = new JLabel("");
        texto.setText("Escolha a categoria");
        texto.setFont(new Font("Agency FB",Font.BOLD,40));
        texto.setBounds(128,50,512,40);
        texto.setForeground(new Color(0, 0, 0));

        JButton BAlimentos = new JButton("Alimentos");
        JButton BLimpeza = new JButton("Limpeza");
        JButton BCasa = new JButton("Casa");
        JButton BVoltar = new JButton("Voltar atrás");

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(BAlimentos);
        buttons.add(BLimpeza);
        buttons.add(BCasa);
        buttons.add(BVoltar);

        Font letra = new Font("Agency FB",Font.BOLD,35);
        for(JButton button:buttons){
            button.setBackground(new Color(255,255,255));
            button.setForeground(new Color(0,0,0));
            button.setFont(letra);
            panel.add(button);
        }

        BAlimentos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            verProdutos("Alimentos",util);
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

        BLimpeza.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            verProdutos("Limpeza",util);
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

        BCasa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            verProdutos("Casa",util);
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

        BVoltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        MenuGUI.menu(util);
                        
                    }
                });
            }
            
        });
        menu.add(texto);
        menu.add(panel,SwingConstants.CENTER);
        menu.setVisible(true);
    }


    public static void verProdutos(String categoria,String util) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
        JFrame menu = new JFrame("Alimentos");
        menu.setSize(512, 512);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        menu.setContentPane(image);
        menu.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JTextArea ps = new JTextArea("");
        ps.setBounds(10,30,500,500);
        ps.setOpaque(true);
        if(categoria.equals("Alimentos")){
            Categoria alimentos = xmlReader.getCategoria(document, "Alimentos");
            ps.append(alimentos.print(" "));
        }
        else if(categoria.equals("Limpeza")){
            Categoria limpeza = xmlReader.getCategoria(document, "Limpeza");
            ps.append(limpeza.print(" "));
        }
        else if(categoria.equals("Casa")){
            Categoria casa = xmlReader.getCategoria(document, "Casa");
            ps.append(casa.print(" "));
        }
        JButton button = new JButton("Voltar atrás");
        button.setBounds(300, 2, 180, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                menu.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run(){
                        escolherCategoria(util);
                    }
                });
            }
        });
        menu.add(ps);
        menu.add(button);
        menu.setVisible(true);
        

    }

    
}
