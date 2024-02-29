package xml;


import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Produtos.Alimentos;
import Produtos.Casa;
import Produtos.Categoria;
import Produtos.Limpeza;

public class xmlReader {
    
    /**
     * Este método é o substituto do método build na classe Categoria
     * Recebe um documento,este sendo um xml e um nome(o nome da categoria)
     * Com estes dois atributos pesquisa-se no xml pela nó Categoria com o nome desejado
     * Se este existir cria-se uma nodelist com os produtos desta categoria
     * utiliza-se a função build da classe desejada e adiciona-se esse produto à categoria
     * no final retorna-se a categoria com os produtos desta
     * @param doc
     * @param nome
     * @return
     */
    public static Categoria getCategoria(Document doc, String nome){

        Categoria categoria = null;
        NodeList categorias = doc.getElementsByTagName("Categoria");
        for(int i=0;i<categorias.getLength();i++){
            Element agora = (Element)categorias.item(i);
            String name = agora.getElementsByTagName("Nome").item(0).getTextContent();
            if(name.equals(nome)){
                categoria = new Categoria(name);
                NodeList produtolist = agora.getElementsByTagName("Produto");
                if(nome=="Alimentos"){
                    for(int j=0;j<produtolist.getLength();j++){
                        Alimentos produto = Alimentos.build(produtolist.item(j));
                        categoria.addProduto(produto);
                    }
                }
                else if(nome=="Casa"){
                    for(int j=0;j<produtolist.getLength();j++){
                        Casa produto = Casa.build(produtolist.item(j));
                        categoria.addProduto(produto);
                    }
                }
                else{
                    for(int j=0;j<produtolist.getLength();j++){
                        Limpeza produto = Limpeza.build(produtolist.item(j));
                        categoria.addProduto(produto);
                    }
                }
                return categoria;
            }
        }
        return null;
    }

    /**
     * Este método é utilizado para adicionar um novo produto à categoria com o nome Alimentos
     * recebe o produto e pesquisa o xml pela categoria com o nome Alimentos
     * Ao estar nesse ramo cria-se um novo elemento com os dados necessários para estar de acordo com
     * as regras estabelecidas pelo dtd
     * No final adiciona esse elemento ao xml
     * @param doc
     * @param produto
     */
    public static void addNewProdutoAlimento(Document doc,Alimentos produto){
        NodeList categorias = doc.getElementsByTagName("Categoria");
        Element eCategoria = null;
        for(int i=0;i<categorias.getLength();i++){
            Element help = (Element)categorias.item(i);
            String nome = help.getElementsByTagName("Nome").item(0).getTextContent();
            if(nome.equals("Alimentos")){
                eCategoria = help;
                break;
            }
        }
        if(eCategoria!=null){
            Element eProduto = doc.createElement("Produto");
            Element nome = doc.createElement("Nome");
            nome.appendChild(doc.createTextNode(produto.getNome()));
            eProduto.appendChild(nome);
            Element preco = doc.createElement("Preco");
            preco.appendChild(doc.createTextNode(Float.toString(produto.getPreco())));
            eProduto.appendChild(preco);
            Element stock = doc.createElement("Stock");
            stock.appendChild(doc.createTextNode(Integer.toString(produto.getStock())));
            eProduto.appendChild(stock);
            Element validade = doc.createElement("Validade");
            validade.appendChild(doc.createTextNode(produto.getValidade()));
            eProduto.appendChild(validade);
            eCategoria.appendChild(eProduto);
        }        
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método é utilizado para adicionar um novo produto à categoria com o nome Limpeza
     * recebe o produto e pesquisa o xml pela categoria com o nome Limpeza
     * Ao estar nesse ramo cria-se um novo elemento com os dados necessários para estar de acordo com
     * as regras estabelecidas pelo dtd
     * No final adiciona esse elemento ao xml
     * @param doc
     * @param produto
     */
    public static void addNewProdutoLimpeza(Document doc,Limpeza produto){
        NodeList categorias = doc.getElementsByTagName("Categoria");
        Element eCategoria = null;
        for(int i=0;i<categorias.getLength();i++){
            Element help = (Element)categorias.item(i);
            String nome = help.getElementsByTagName("Nome").item(0).getTextContent();
            if(nome.equals("Limpeza")){
                eCategoria = help;
                break;
            }
        }
        if(eCategoria!=null){
            Element eProduto = doc.createElement("Produto");
            Element nome = doc.createElement("Nome");
            nome.appendChild(doc.createTextNode(produto.getNome()));
            eProduto.appendChild(nome);
            Element preco = doc.createElement("Preco");
            preco.appendChild(doc.createTextNode(Float.toString(produto.getPreco())));
            eProduto.appendChild(preco);
            Element stock = doc.createElement("Stock");
            stock.appendChild(doc.createTextNode(Integer.toString(produto.getStock())));
            eProduto.appendChild(stock);
            Element alergias = doc.createElement("alergias");
            for(int i=0;i<produto.getAlergias().length;i++){
                Element alergia = doc.createElement("alergia");
                alergia.appendChild(doc.createTextNode(produto.getAlergias()[i]));
                alergias.appendChild(alergia);
            }
            eProduto.appendChild(alergias);
            
            eCategoria.appendChild(eProduto);
        }        
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método é utilizado para adicionar um novo produto à categoria com o nome Casa
     * recebe o produto e pesquisa o xml pela categoria com o nome Casa
     * Ao estar nesse ramo cria-se um novo elemento com os dados necessários para estar de acordo com
     * as regras estabelecidas pelo dtd
     * No final adiciona esse elemento ao xml
     * @param doc
     * @param produto
     */
    public static void addNewProdutoCasa(Document doc,Casa produto){
        NodeList categorias = doc.getElementsByTagName("Categoria");
        Element eCategoria = null;
        for(int i=0;i<categorias.getLength();i++){
            Element help = (Element)categorias.item(i);
            String nome = help.getElementsByTagName("Nome").item(0).getTextContent();
            if(nome.equals("Casa")){
                eCategoria = help;
                break;
            }
        }
        if(eCategoria!=null){
            Element eProduto = doc.createElement("Produto");
            Element nome = doc.createElement("Nome");
            nome.appendChild(doc.createTextNode(produto.getNome()));
            eProduto.appendChild(nome);
            Element preco = doc.createElement("Preco");
            preco.appendChild(doc.createTextNode(Float.toString(produto.getPreco())));
            eProduto.appendChild(preco);
            Element stock = doc.createElement("Stock");
            stock.appendChild(doc.createTextNode(Integer.toString(produto.getStock())));
            eProduto.appendChild(stock);
            Element tipo = doc.createElement("tipo");
            tipo.appendChild(doc.createTextNode(produto.getTipo()));
            eProduto.appendChild(tipo);
            eCategoria.appendChild(eProduto);
        }
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta função é utilizada para alterar o stock de certo produto
     * De inicio cria-se uma nodelist com todos os produtos existentes no xml
     * Pesquisa-se dentro do xml pelo nome do produto igual ao nome dado pelo utilizador
     * Se for encontrado cria-se um elemento com esse produto e os seus nós filhos
     * De seguida altera-se o valor do stock dentro desse produto
     * No final escreve-se o xml novamente com o novo valor do stock 
     * 
     * @param doc
     * @param nome
     * @param stock
     */
    public static boolean changeStock(Document doc,String nome,int stock){
        NodeList produtos = doc.getElementsByTagName("Produto");
        Element eProduto = null;
        boolean existe=false;
        for(int i=0;i<produtos.getLength();i++){
            Element help = (Element) produtos.item(i);
            String name = help.getElementsByTagName("Nome").item(0).getTextContent();
            if(name.equals(nome)){
                eProduto=help;
                existe=true;
                break;
            }
        }
        if(eProduto!=null){
            eProduto.getElementsByTagName("Stock").item(0).setTextContent(Integer.toString(stock));
        }
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
            transformer.transform(dom, result);
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return existe;
    }

    /**
     * Esta função é utilizada para remover um produto do xml
     * Recebe o documento xml e o nome do produto que se deseja retirar
     * Pesquisa pelo nodeList de produtos pelo que tem o mesmo nome pelo passado como atributo na função
     * Se existir um produto com esse nome é removido o elemento desse produto
     * No final escreve-se o xml 
     * 
     * @param doc
     * @param nome
     */
    public static boolean removeProduto(Document doc,String nome){
        NodeList produtos = doc.getElementsByTagName("Produto");
        Element eProduto = null;
        boolean existe= false;
        for(int i=0;i<produtos.getLength();i++){
            Element help = (Element)produtos.item(i);
            String name = help.getElementsByTagName("Nome").item(0).getTextContent();
            if(name.equals(nome)){
                eProduto=help;
                existe=true;
                break;
            }
        }
        if(eProduto!=null){
            Node categoria = eProduto.getParentNode();
            categoria.removeChild(eProduto);
            
            
        }
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP4/XML/SuperM.xml"));
            transformer.transform(dom, result);
            
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return existe;

    }
}
