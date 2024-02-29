package Produtos;


import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Limpeza extends Produto {

    /**
     * Atributos necessários para a classe
     */
    private String[] alergias;

    /**
     * Construtor da classe Limpeza
     * @param nome
     * @param preco
     * @param stock
     * @param alergias
     */
    public Limpeza(String nome,float preco,int stock,String[] alergias) {
        super(nome,preco,stock);
        this.alergias=alergias;
    }

    /**
     *Getter do atributo alergias
     * @return String[]
     */
    public String[] getAlergias(){
        return this.alergias;
    }

    /**
     * Constroi um novo Produto(Limpeza) a partir do nó nNode passado como parâmetro
     * @param nNode
     * @return
     */
    public static Limpeza build(Node nNode){
        // NodeList nodeList = nNode.getChildNodes();
        Limpeza limpeza = null;
        Element element = (Element)nNode;
        String nome = element.getElementsByTagName("Nome").item(0).getTextContent();
        float preco = Float.parseFloat(element.getElementsByTagName("Preco").item(0).getTextContent());
        int stock = Integer.parseInt(element.getElementsByTagName("Stock").item(0).getTextContent());
        NodeList alergias = element.getElementsByTagName("alergias").item(0).getChildNodes();
        ArrayList<String> allergies = new ArrayList<String>();
        for(int i=0;i<alergias.getLength();i++){
            Node alergia = alergias.item(i);
            if(alergia.getNodeName()=="alergia"){
                allergies.add(alergia.getTextContent());
            }
        }
        String[] novo = new String[allergies.size()];
        novo = allergies.toArray(novo);
        limpeza = new Limpeza(nome, preco, stock, novo);
        return limpeza;
    }

    /**
     * Devolve a string desejada para o terminal
     */
    public String toString(){
        return super.toString() 
        + "\n  " + "Alergias: " + Arrays.toString(getAlergias());
    }
    
}
