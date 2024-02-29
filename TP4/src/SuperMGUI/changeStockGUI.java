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

public class changeStockGUI extends JFrame {
    
    public static void changeStock(String util) throws ParserConfigurationException, SAXException, IOException{
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));


        JFrame menu =new JFrame("Alterar Stock");
        menu.setSize(512,512);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);

        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512, 512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        menu.setContentPane(image);
        menu.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     


        JLabel text = new JLabel("Alterar Stock");
        text.setBounds(200, 155, 512, 25);
        text.setForeground(new Color(0, 0, 255));
        bibPanel.add(text);

        JLabel nome = new JLabel("Nome");
        nome.setBounds(120,195,512,25);
        nome.setForeground(new Color(255, 0, 0));
        JTextField nomeCaixa = new JTextField(25);
        nomeCaixa.setBounds(194, 195, 195, 25); 
        bibPanel.add(nome);
        bibPanel.add(nomeCaixa);

        JLabel stock = new JLabel("Novo Stock");
        stock.setBounds(120,235,512,25);
        stock.setForeground(new Color(255, 0, 0));
        JTextField stockCaixa = new JTextField(25);
        stockCaixa.setBounds(194, 235, 195, 25); 
        bibPanel.add(stock);
        bibPanel.add(stockCaixa);

        JLabel errado = new JLabel("");
        errado.setBounds(230,275,300,25);
        errado.setForeground(new Color(0,0,255));
        bibPanel.add(errado);

        JButton trocar = new JButton("Alterar");
        trocar.setBounds(120, 275, 100, 25);
        trocar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                String nome = nomeCaixa.getText();
                int stock = Integer.parseInt(stockCaixa.getText());
                if(stock==0){
                    if(xmlReader.removeProduto(document, nome)){
                        menu.dispose();
                        MenuGUI.menu(util);
                    }
                    else{
                        errado.setText("Produto não existente");
                    }
                }
                else{
                    if(xmlReader.changeStock(document, nome, stock)){
                        menu.dispose();
                        MenuGUI.menu(util);
                    }
                    else{
                        errado.setText("Produto não existente");
                    }
                }
            }
        });
        trocar.setBackground(new Color(0, 0, 255));
        trocar.setForeground(new Color(255, 255, 255));
        bibPanel.add(trocar);
        menu.add(bibPanel);
        menu.setVisible(true);
    }

    
}
