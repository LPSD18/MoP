package Super;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

public class SuperM {

    /**
     * Método main
     * @param args
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
        String user = "Diogo";
        String pass = "123456";
        Categoria alimentos = xmlReader.getCategoria(document, "Alimentos");
        Categoria limpeza = xmlReader.getCategoria(document, "Limpeza");
        Categoria casa = xmlReader.getCategoria(document, "Casa");
        Scanner s = new Scanner(System.in);
        System.out.println("Introduza o seu nome de utlizador");
        String username = s.nextLine();
        System.out.println("Introduza a sua password");
        String password = s.nextLine();
        while(!checkUser(username,password, user, pass)){
            username = s.nextLine();
            System.out.println("Agora a password");
            password = s.nextLine();
        }
        int escolha=100;
        System.out.println("Bem vindo " + username);
        while(escolha!=0){
            System.out.println("Que operação quer realizar?");
            System.out.println("1- Consultar produtos");
            System.out.println("2- Adicionar produtos");
            System.out.println("3- Alterar stock dos produtos");
            System.out.println("4- Remover um produto");
            System.out.println("0- Sair");
            escolha = s.nextInt();
            if(escolha==1){
                checkProdutos(alimentos,limpeza,casa,s);
            }
            else if(escolha==2){
                addProdutos(alimentos,limpeza,casa,s,document);
            }
            else if(escolha==3){
                changeStock(s,document);
            }
            else if(escolha==4){
                removerProduto(s, document);
            }
            else if(escolha!=0){
                System.out.println("Introduziu um número errado,\npor favor introduza um correto");
            }
            alimentos = xmlReader.getCategoria(document, "Alimentos");
            limpeza = xmlReader.getCategoria(document, "Limpeza");
            casa = xmlReader.getCategoria(document, "Casa");
            
        }        
        s.close();
    }

    /**
     * Método utilizado para a primeira opção da aplicação
     * Com o input dado pelo utilizador é utilizada a função print da classe Categoria para mostrar os produtos de certa categoria
     * @param alimentos
     * @param limpeza
     * @param casa
     * @param scan
     */
    private static void checkProdutos(Categoria alimentos,Categoria limpeza,Categoria casa,Scanner scan){
        
        System.out.println("Que categoria de produtos quer ver? \n 1-Alimentos \n 2-Limpeza \n 3-Casa \n 0-Voltar atrás");
        int num = scan.nextInt();
        while(num!=0){
            if(num==1){
                alimentos.print(" ");}
            else if(num==2){
                limpeza.print(" ");}
            else if(num==3){
                casa.print(" ");
            }
            System.out.println("Introduza o novo pedido");
            num = scan.nextInt();
        }
        
    }

    /**
     * Método utilizado para a segunda opção da aplicação
     * Com o input dado pelo utilizador sobre a categoria à qual o produto pertence,
     * é de seguida pedido os dados do produto que se deseja e utilizada a função addNewProduto(Nome de cat) com o documento e o novo produto
     * Antes de ser adicionado verifica-se que este produto já não existe no array da categoria desejada
     * @param alimentos
     * @param limpeza
     * @param casa
     * @param scan
     * @param doc
     */
    private static void addProdutos(Categoria alimentos,Categoria limpeza,Categoria casa,Scanner scan,Document doc){
        System.out.println("Em que categoria quer adicionar um produto? \n 1-Alimentos \n 2-Limpeza \n 3-Casa \n 0-Voltar atrás");
        int num = scan.nextInt();
        if(num==1){
            
            Alimentos novo = null;
            System.out.println("Introduza o nome do produto");
            String nome = scan.nextLine();
            while(nome.length()<1){
                nome=scan.nextLine();
            }
            System.out.println("Introduza o preço do produto");
            float preco =Float.parseFloat(scan.nextLine());
            System.out.println("Introduza o stock do produto");
            int stock = Integer.parseInt(scan.nextLine());
            System.out.println("Introduza a validade do produto");
            String validade = scan.nextLine();
            novo = new Alimentos(nome, preco, stock, validade);
            if(!alimentos.equals(novo.getNome())){
                xmlReader.addNewProdutoAlimento(doc, novo);
            }
            
        }
        else if(num==2){
            Limpeza novo = null;
            System.out.println("Introduza o nome do produto");
            String nome = scan.nextLine();
            while(nome.length()<1){
                nome=scan.nextLine();
            }
            System.out.println("Introduza o preço do produto");
            float preco =Float.parseFloat(scan.nextLine());
            System.out.println("Introduza o stock do produto");
            int stock = Integer.parseInt(scan.nextLine());
            System.out.println("Introduza o número de alergias do Produto");
            int numAlergias = scan.nextInt();
            ArrayList<String> alerg = new ArrayList<String>();
            for(int i=0;i<numAlergias;i++){
                String alergia=null;
                System.out.println("introduza a alergia");
                alergia = scan.nextLine();
                while(alergia.length()<1){
                    alergia=scan.nextLine();
                }
                alerg.add(alergia);
            }
            String[] dar = new String[alerg.size()];
            dar = alerg.toArray(dar);
            novo = new Limpeza(nome, preco, stock, dar);
            if(limpeza.equals(novo.getNome())==false){
                xmlReader.addNewProdutoLimpeza(doc, novo);
            }
            
        }
        else if(num==3){
            Casa novo = null;
            System.out.println("Introduza o nome do produto");
            String nome = scan.nextLine();
            while(nome.length()<1){
                nome=scan.nextLine();
            }
            System.out.println("Introduza o preço do produto");
            float preco =Float.parseFloat(scan.nextLine());
            System.out.println("Introduza o stock do produto");
            int stock = Integer.parseInt(scan.nextLine());
            System.out.println("Introduza o tipo do produto");
            String tipo = scan.nextLine();
            novo = new Casa(nome, preco, stock, tipo);
            if(!casa.equals(novo.getNome())){
                xmlReader.addNewProdutoCasa(doc, novo);
            }
        }
    }

    /**
     * Método utilizado para a terceira opção da aplicação
     * Recebe um input do utilizador com o nome do produto que se deseja alterar o stock e o valor
     * Com estes dois inputs é utilizado o método changeStock da classe xmlReader para a alteração do stock
     * 
     * @param scan
     * @param doc
     */
    private static void changeStock(Scanner scan,Document doc){
        System.out.println("Introduza o nome do produto que stock quer alterar");
        String nome = scan.nextLine();
        while(nome.length()<1){
            nome=scan.nextLine();
        }
        System.out.println("Agora introduza o novo valor de stock");
        int stock = scan.nextInt();
        xmlReader.changeStock(doc, nome, stock);
    }

    /**
     * Método utilizado para a quarta opção da aplicação
     * Recebe um input do utilizador com o nome do produto desejado e se esse nome for o correto 
     * é retirado o produto do xml
     * @param scan
     * @param doc
     */
    private static void removerProduto(Scanner scan,Document doc){
        System.out.println("Introduza o nome do produto que pretende remover");
        String nome = scan.nextLine();
        while(nome.length()<1){
            nome=scan.nextLine();
        }
        xmlReader.removeProduto(doc, nome);
    }





    private static boolean checkUser(String username,String password,String corretoUser,String corretoPass){
        if(username.equals(corretoUser)&& password.equals(corretoPass)){
            return true;
        }
        else if(!username.equals(corretoUser)){
            System.out.println("O username está errado, introduza o username e password correto");
            return false;
        }
        else if(!password.equals(corretoPass)){
            System.out.println("A pasword está errada, introduza o username e password correto");
            return false;
        }
        return false;
    }

    
}