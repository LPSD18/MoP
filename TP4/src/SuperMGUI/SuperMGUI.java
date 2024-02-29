package SuperMGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SuperMGUI extends JFrame {
    
    private static String User = "Diogo";
    private static String Pass = "123456";
    // private static char[] Pass = {'1','2','3','4','5','6'};

    public static void loginGUI() {
        JFrame inicio = new JFrame("Bem vindo");
        inicio.setSize(512, 512);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bibPanel = new JPanel(null);
        bibPanel.setOpaque(false);
        bibPanel.setSize(512, 512);

        JLabel image = new JLabel(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg"));
        inicio.setContentPane(image);
        inicio.setIconImage(new ImageIcon("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/images/Supermarkt.jpg").getImage());        

        JLabel text = new JLabel("Inicie a sessão");
        text.setBounds(200, 155, 512, 25);
        text.setForeground(new Color(0,0, 0));
        bibPanel.add(text);

        JLabel utilizador = new JLabel("Utilizador");
        utilizador.setBounds(133, 195, 512, 25);
        utilizador.setForeground(new Color(255, 0, 0));
        JTextField utilCaixa = new JTextField(25);
        utilCaixa.setBounds(194, 195, 165, 25);
        bibPanel.add(utilizador);
        bibPanel.add(utilCaixa);

        JLabel password = new JLabel("Password");
        password.setBounds(133, 235, 512, 25);
        password.setForeground(new Color(255, 0, 0));
        JTextField passCaixa = new JPasswordField(25);
        passCaixa.setBounds(194, 235, 165, 25);
        bibPanel.add(password);
        bibPanel.add(passCaixa);

        JLabel NomeWrong = new JLabel("");
        NomeWrong.setBounds(200, 175, 512, 25);
        NomeWrong.setForeground(new Color(0, 0, 0));
        bibPanel.add(NomeWrong);

        JLabel PassWrong = new JLabel("");
        PassWrong.setBounds(200, 215, 512, 25);
        PassWrong.setForeground(new Color(0, 0, 0));
        bibPanel.add(PassWrong);

        JButton entrar = new JButton("Entrar");
        entrar.setBounds(133, 275, 70, 25);
        entrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String util = utilCaixa.getText();
                String palavrapasse = passCaixa.getText();
                if (util.equals("") && palavrapasse.equals("")) {
                    return;
                } else if (util.equals(User) && palavrapasse.equals(Pass)) {
                    inicio.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            MenuGUI.menu(util);
                        }
                    });
                }
                else if(!util.equals(User)){
                    NomeWrong.setText("Nome de utilizador incorreto");
                    PassWrong.setText("");
                }
                else if(!palavrapasse.equals(Pass)){
                    NomeWrong.setText("");
                    PassWrong.setText("Palavra passe incorreta");
                }
            }
        });
        entrar.setBackground(new Color(0, 255, 0));
        entrar.setForeground(new Color(0, 0, 0));
        bibPanel.add(entrar);

        inicio.add(bibPanel);
        inicio.setVisible(true);
    }

    public static void main(String[] args){
        loginGUI();
    }

}
