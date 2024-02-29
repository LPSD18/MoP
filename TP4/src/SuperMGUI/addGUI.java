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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Produtos.Alimentos;
import Produtos.Casa;
import Produtos.Categoria;
import Produtos.Limpeza;
import xml.xmlReader;

public class addGUI extends JFrame {

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
                            addAlimento(util);
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
                            addLimpeza(util);
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
                            addCasa(util);
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

    public static void addAlimento(String util) throws ParserConfigurationException, SAXException, IOException{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));

        JFrame inicio = new JFrame("Adicionar um alimento");
        inicio.setSize(512,512);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512,512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        inicio.setContentPane(image);
        inicio.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel text = new JLabel("Adicionar um alimento");
        text.setBounds(200,55,512,25);
        text.setForeground(new Color(0,0,255));
        bibPanel.add(text);


        JLabel nome = new JLabel("Nome");
        nome.setBounds(133,95,512,25);
        nome.setForeground(new Color(255,0,0));
        JTextField nomeCaixa = new JTextField(25);
        nomeCaixa.setBounds(194,95,165,25);
        bibPanel.add(nome);
        bibPanel.add(nomeCaixa);

        JLabel preco = new JLabel("Preço");
        preco.setBounds(133,135,512,25);
        preco.setForeground(new Color(255,0,0));
        JTextField precoCaixa = new JTextField(25);
        precoCaixa.setBounds(194,135,165,25);
        bibPanel.add(preco);
        bibPanel.add(precoCaixa);

        JLabel stock = new JLabel("Stock");
        stock.setBounds(133,175,512,25);
        stock.setForeground(new Color(255,0,0));
        JTextField stockCaixa = new JTextField(25);
        stockCaixa.setBounds(194,175,165,25);
        bibPanel.add(stock);
        bibPanel.add(stockCaixa);

        JLabel validade = new JLabel("Validade");
        validade.setBounds(133,215,512,25);
        validade.setForeground(new Color(255,0,0));
        JTextField validadeCaixa = new JTextField(25);
        validadeCaixa.setBounds(194,215,165,25);
        bibPanel.add(validade);
        bibPanel.add(validadeCaixa);
        
        JLabel errado = new JLabel("");
        errado.setBounds(133,285,512,25);
        errado.setForeground(new Color(0,0,0));
        bibPanel.add(errado);

        JButton adicButton = new JButton("Adicionar");
        adicButton.setBounds(133, 255, 100, 25);
        adicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String nome=nomeCaixa.getText();
                float preco = Float.parseFloat(precoCaixa.getText());
                int stock = Integer.parseInt(stockCaixa.getText());
                String validade = validadeCaixa.getText();
                Alimentos novo = new Alimentos(nome, preco, stock, validade);
                Categoria alimentos = xmlReader.getCategoria(document, "Alimentos");
                if(!alimentos.equals(novo.getNome())){
                    xmlReader.addNewProdutoAlimento(document, novo);
                    inicio.dispose();
                    MenuGUI.menu(util);
                }
                else{
                    errado.setText("Está a tentar adicionar um produto existente");
                }
            }
        });
        adicButton.setBackground(new Color(0,255,0));
        adicButton.setForeground(new Color(0,0,0));
        bibPanel.add(adicButton);
        inicio.add(bibPanel);
        inicio.setVisible(true);


    }
    
    public static void addLimpeza(String util) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));

        JFrame inicio = new JFrame("Adicionar um produto de limpeza");
        inicio.setSize(512,512);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512,512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        inicio.setContentPane(image);
        inicio.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel text = new JLabel("Adicionar um alimento");
        text.setBounds(200,55,512,25);
        text.setForeground(new Color(0,0,255));
        bibPanel.add(text);

        JLabel nome = new JLabel("Nome");
        nome.setBounds(133,95,512,25);
        nome.setForeground(new Color(255,0,0));
        JTextField nomeCaixa = new JTextField(25);
        nomeCaixa.setBounds(194,95,165,25);
        bibPanel.add(nome);
        bibPanel.add(nomeCaixa);

        JLabel preco = new JLabel("Preço");
        preco.setBounds(133,135,512,25);
        preco.setForeground(new Color(255,0,0));
        JTextField precoCaixa = new JTextField(25);
        precoCaixa.setBounds(194,135,165,25);
        bibPanel.add(preco);
        bibPanel.add(precoCaixa);

        JLabel stock = new JLabel("Stock");
        stock.setBounds(133,175,512,25);
        stock.setForeground(new Color(255,0,0));
        JTextField stockCaixa = new JTextField(25);
        stockCaixa.setBounds(194,175,165,25);
        bibPanel.add(stock);
        bibPanel.add(stockCaixa);

        JLabel validade = new JLabel("Alergias");
        validade.setBounds(133,215,512,25);
        validade.setForeground(new Color(0,0,0));
        JTextField validadeCaixa = new JTextField(25);
        validadeCaixa.setBounds(194,215,165,25);
        bibPanel.add(validade);
        bibPanel.add(validadeCaixa);

        JLabel errado = new JLabel("");
        errado.setBounds(133,285,512,25);
        errado.setForeground(new Color(255,0,0));
        bibPanel.add(errado);

        JButton adicButton = new JButton("Adicionar");
        adicButton.setBounds(133, 255, 100, 25);
        adicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String nome=nomeCaixa.getText();
                float preco = Float.parseFloat(precoCaixa.getText());
                int stock = Integer.parseInt(stockCaixa.getText());
                String alergiasString = validadeCaixa.getText();
                alergiasString = alergiasString.replaceAll("\\s+", "");
                String[] alergias = alergiasString.split(",");
                Limpeza novo = new Limpeza(nome, preco, stock, alergias);
                Categoria limpeza = xmlReader.getCategoria(document, "Limpeza");
                if(!limpeza.equals(novo.getNome())){
                    xmlReader.addNewProdutoLimpeza(document, novo);
                    inicio.dispose();
                    MenuGUI.menu(util);
                }
                else{
                    errado.setText("Está a tentar adicionar um produto existente");
                }
            }
        });
        adicButton.setBackground(new Color(0,255,0));
        adicButton.setForeground(new Color(0,0,0));
        bibPanel.add(adicButton);
        inicio.add(bibPanel);
        inicio.setVisible(true);
    }

    public static void addCasa(String util) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));

        JFrame inicio = new JFrame("Adicionar um produto de casa");
        inicio.setSize(512,512);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512,512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        inicio.setContentPane(image);
        inicio.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());     

        JLabel text = new JLabel("Adicionar um produto de casa");
        text.setBounds(200,55,512,25);
        text.setForeground(new Color(0,0,255));
        bibPanel.add(text);

        JLabel nome = new JLabel("Nome");
        nome.setBounds(133,95,512,25);
        nome.setForeground(new Color(255,0,0));
        JTextField nomeCaixa = new JTextField(25);
        nomeCaixa.setBounds(194,95,165,25);
        bibPanel.add(nome);
        bibPanel.add(nomeCaixa);

        JLabel preco = new JLabel("Preço");
        preco.setBounds(133,135,512,25);
        preco.setForeground(new Color(255,0,0));
        JTextField precoCaixa = new JTextField(25);
        precoCaixa.setBounds(194,135,165,25);
        bibPanel.add(preco);
        bibPanel.add(precoCaixa);

        JLabel stock = new JLabel("Stock");
        stock.setBounds(133,175,512,25);
        stock.setForeground(new Color(255,0,0));
        JTextField stockCaixa = new JTextField(25);
        stockCaixa.setBounds(194,175,165,25);
        bibPanel.add(stock);
        bibPanel.add(stockCaixa);

        JLabel validade = new JLabel("Tipo");
        validade.setBounds(133,215,512,25);
        validade.setForeground(new Color(255,0,0));
        JTextField tipoCaixa = new JTextField(25);
        tipoCaixa.setBounds(194,215,165,25);
        bibPanel.add(validade);
        bibPanel.add(tipoCaixa);

        JLabel errado = new JLabel("");
        errado.setBounds(133,285,512,25);
        errado.setForeground(new Color(0,0,0));
        bibPanel.add(errado);

        JButton adicButton = new JButton("Adicionar");
        adicButton.setBounds(133, 255, 100, 100);
        adicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String nome=nomeCaixa.getText();
                float preco = Float.parseFloat(precoCaixa.getText());
                int stock = Integer.parseInt(stockCaixa.getText());
                String tipo = tipoCaixa.getText();
                Casa novo = new Casa(nome, preco, stock, tipo);
                Categoria casa = xmlReader.getCategoria(document, "Casa");
                if(!casa.equals(novo.getNome())){
                    xmlReader.addNewProdutoCasa(document, novo);
                    inicio.dispose();
                    MenuGUI.menu(util);
                }
                else{
                    errado.setText("Está a tentar adicionar um produto existente");
                }

            }
        });
        adicButton.setBackground(new Color(0,255,0));
        adicButton.setForeground(new Color(0,0,0));
        bibPanel.add(adicButton);
        inicio.add(bibPanel);
        inicio.setVisible(true);
    }
    
}
