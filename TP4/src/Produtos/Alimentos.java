package Produtos;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Alimentos extends Produto {

    /**
     * Atributos necessários para a classe
     */
    private String validade;

    /**
     * Construtor da classe Alimentos
     * @param nome
     * @param preco
     * @param stock
     * @param validade
     */
    public Alimentos(String nome,float preco,int stock,String validade) {
        super(nome,preco,stock);
        this.validade=validade;
        
    }

   

    /**
     * Getter do atributo validade
     * @return String
     */
    public String getValidade(){
        return this.validade;
    }

    
    /**
     * Constroi um novo Produto(este sendo diretamente um Alimentos) a partir do nó nNode passado como parâmetro
     * 
     * @param nNode
     * @return
     */
    public static Alimentos build(Node nNode){
        // NodeList nodeList = nNode.getChildNodes();
        Alimentos alimentos =null;
        Element element = (Element) nNode;
        String nome = element.getElementsByTagName("Nome").item(0).getTextContent();
        float preco = Float.parseFloat(element.getElementsByTagName("Preco").item(0).getTextContent());
        int stock = Integer.parseInt(element.getElementsByTagName("Stock").item(0).getTextContent());
        String validade = element.getElementsByTagName("Validade").item(0).getTextContent();
        alimentos = new Alimentos(nome, preco, stock, validade);
        return alimentos;
    }

    /**
     * Devolve a string desejada para o terminal
     */
    public String toString(){
        return super.toString()
        + "\n  " + "Validade: " + (getValidade());
    }
    
}
