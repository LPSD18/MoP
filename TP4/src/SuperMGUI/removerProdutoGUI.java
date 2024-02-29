package SuperMGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import xml.xmlReader;

public class removerProdutoGUI extends JFrame {
    
    public static void removeProduto(String util) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));

        JFrame menu = new JFrame("Remover um produto");
        menu.setSize(512,512);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);

        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512, 512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        menu.setContentPane(image);
        menu.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel text = new JLabel("Remover um produto");
        text.setBounds(200, 155, 512, 25);
        text.setForeground(new Color(0, 0, 255));
        bibPanel.add(text);

        JLabel nome = new JLabel("Nome");
        nome.setBounds(120, 195, 512, 25);
        nome.setForeground(new Color(255, 0, 0));
        JTextField nomeCaixa = new JTextField(25);
        nomeCaixa.setBounds(194, 195, 195, 25);
        bibPanel.add(nome);
        bibPanel.add(nomeCaixa);

        JLabel errado = new JLabel("");
        errado.setBounds(120,240,300,25);
        errado.setForeground(new Color(255, 0, 0));
        bibPanel.add(errado);

        JButton remover = new JButton("Remover");
        remover.setBounds(120,275,100,25);
        remover.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                String nome = nomeCaixa.getText();
                if(xmlReader.removeProduto(document, nome)){
                    menu.dispose();
                    MenuGUI.menu(util);
                }
                else{
                    errado.setText("Produto não existente");
                }
            }
        });
        remover.setBackground(new Color(0, 0, 255));
        remover.setForeground(new Color(255, 255, 255));
        bibPanel.add(remover);
        menu.add(bibPanel);
        menu.setVisible(true);
    }

}
