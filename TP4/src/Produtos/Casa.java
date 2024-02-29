package Produtos;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Casa extends Produto {

    /**
     * Atributos necessários para a classe
     */
    private String tipo;

    /**
     * Construtor da classe Casa
     * @param nome
     * @param preco
     * @param stock
     * @param tipo
     */
    public Casa(String nome,float preco,int stock,String tipo) {
        super(nome,preco,stock);
        this.tipo=tipo;
        
    }



    /**
     * Getter do atributo tipo
     * @return String
     */
    public String getTipo(){
        return this.tipo;
    }

    /**
     * Constroi um novo Produto(Casa) a partir do nó nNode passado como parâmetro
     * @param nNode
     * @return
     */
    public static Casa build(Node nNode){
        Casa casa =null;
        Element element = (Element) nNode;
        String nome = element.getElementsByTagName("Nome").item(0).getTextContent();
        float preco = Float.parseFloat(element.getElementsByTagName("Preco").item(0).getTextContent());
        int stock = Integer.parseInt(element.getElementsByTagName("Stock").item(0).getTextContent());
        String tipo = element.getElementsByTagName("tipo").item(0).getTextContent();

        casa = new Casa(nome, preco, stock, tipo);
        return casa;
    }

    /**
     * Devolve a string desejada para o terminal 
     */
    public String toString(){
        return super.toString() 
        + "\n  " + "Tipo: " + (getTipo());
    }
    
}
