package tp3.XML;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Classe que representa um Evento do tipo Festival.
 * 
 * @version 1.0
 * @author Docentes da Disciplina de Modelação e Programação, LEIM, Instituto
 *         Superior de Engenharia de Lisboa
 *
 */
public class Festival extends Evento {

	private static final int MAX_EVENTOS = 20;

	private Evento[] eventos = new Evento[MAX_EVENTOS];
	private int numEventos = 0;

	public Festival(String nome) {
		super(nome);
	}

	/**
	 * Devolve todos os bilhetes existentes no Festival (somando e devolvendo todos
	 * os bilhetes dos seus Eventos).
	 * 
	 * @return o número de bilhetes existentes no Festival.
	 */
	@Override
	public int getNumBilhetes() {
		int soma = 0;
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] != null)
				soma += eventos[i].getNumBilhetes();
		}
		return soma;
	}

	/**
	 * Retorna o número de actuaçõoes de um determinado artista.
	 * 
	 * @param o nome do artista.
	 * @Override
	 */
	@Override
	public int numActuacoes(String artista) {
		int soma = 0;
		for (Evento evento : eventos) {
			if (evento instanceof Espetaculo) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					if (evento.getArtistas()[i].equals(artista))
						soma++;
				}
			} else if (evento instanceof Festival) {
				evento.numActuacoes(artista);
			}
		}
		return soma;

	}

	/**
	 * Devolve uma string representativa do Festival. Nota: Ver o ficheiro
	 * OutputPretendido/OutputPretendido.txt
	 */
	public String toString() {
		return "Festival " + super.toString();
	}

	/**
	 * Devolve um array contendo todos, de forma não repetida, os nomes de todos os
	 * artistas quer irão actuar no Festival.
	 * 
	 * @return um array contendo os nomes dos artistas.
	 */
	@Override
	public String[] getArtistas() {
		List<String> lista = new ArrayList<String>();
		for (Evento evento : eventos) {

			if (evento instanceof Festival) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					for (int j = 0; j < lista.size(); j++) {
						if (evento.getArtistas()[i] != null && evento.getArtistas()[i] != lista.get(j)) {
							lista.add(evento.getArtistas()[i]);
						}
					}
				}

			} else if (evento instanceof Espetaculo) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					if (lista.size() > 0)
						for (int j = 0; j < lista.size(); j++) {
							if (evento.getArtistas()[i] != null && evento.getArtistas()[i] != lista.get(j)) {
								lista.add(evento.getArtistas()[i]);
							}
						}
					else
						lista.add(evento.getArtistas()[i]);
				}
			}
		}
		ArrayList<String> nova = new ArrayList<>();
		for (String str : lista) {
			if (!nova.contains(str)) {
				nova.add(str);
			}
		}
		String[] newArrray = new String[nova.size()];
		return nova.toArray(newArrray);

	}

	/**
	 * Retorna a profundidade maxima da "árvore" que contém Festivais dentro de
	 * Festivais. O próprio Festival não conta.
	 * 
	 * @return a profundidade máxima.
	 */
	public int getDeepFestival() {
		int profundidade = 0;
		for (Evento evento : eventos) {
			if (evento instanceof Festival) {
				profundidade++;
				int in = ((Festival) evento).getDeepFestival();
				if (in != 1)
					profundidade += in - 1;
			}
		}
		return profundidade;
	}

	/**
	 * Adiciona um novo Evento ao Festival, caso para nenhum dos artistas do novo
	 * evento se verifique que o seu número de atuações no novo evento (a adicionar)
	 * supere em mais de duas o número de atuações no festival corrente.
	 * 
	 * @param evento
	 * @return verdadeiro, se o novo Evento foi adicionado.
	 */
	public boolean addEvento(Evento evento) {
		if (evento == null) {
			return false;
		}
		boolean existe = false;
		for (int i = 0; i < eventos.length; i++) {
			// System.out.println(evento.getNome());
			// System.out.println(eventos[i].getNome());
			if (eventos[i] != null && evento.getNome() == eventos[i].getNome())
				existe = true;
		}
		if (!existe && evento.getNome() != null && evento.getNome().length() > 1) {
			for (int i = 0; i < eventos.length; i++) {
				if (eventos[i] == null) {
					eventos[i] = evento;
					numEventos++;
					//System.out.println(eventos);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Remove um evento em qualquer profundidade do Festival corrente.
	 * 
	 * @param nomeEvento nome do Evento a remover.
	 * @return verdadeiro, se o Evento foi removido.
	 */
	public boolean delEvento(String nomeEvento) {
		if (nomeEvento == null || nomeEvento.length() < 1)
			return false;
		for (int i = 0; i < eventos.length; i++) {
			if (nomeEvento == eventos[i].getNome())
				eventos[i] = null;
			return true;
		}

		return false;
	}

	/**
	 * Imprime na consola informações sobre o Festival. Nota: Ver o output
	 * pretendido em OutputPretendido/OutputPretendido.txt.
	 * 
	 * @param o prefixo para identar o Festival de acordo com a sua profundidade.
	 */
	public void print(String prefix) {

		System.out.println(prefix+this);
        for(Evento evento:eventos){
            if(evento==null)break;
            if(evento instanceof Festival)evento.print(" "+prefix);
            if(evento instanceof Espetaculo)System.out.println(" " + prefix+evento);
        }
	}

	/**
	 * Constroi um novo Festival a partir de um nó contendo as infomações lidas do
	 * documento XML.
	 * 
	 * @param nNode o nó associado ao Festival
	 * @return um novo Festival
	 */
	public static Festival build(Node nNode) {
		
		NodeList nodeList = nNode.getChildNodes();
		Festival festival = new Festival(nodeList.item(1).getTextContent());
		//System.out.println("Before For Loop ->  " +  festival.getNome());
		for (int i = 0; i < nodeList.getLength(); i++) {
			
			Node child = nodeList.item(i);
			//System.out.println("Inside For Loop ->  " +  child.getNodeName());
			if (child.getNodeName() == "Eventos") {
				//System.out.println("Inside Eventos ->  " +  child.getNodeName());
				NodeList childNodes = child.getChildNodes();
				
				for (int j = 0; j < childNodes.getLength(); j++) {
					//System.out.println("Inside For Eventos ->  " +  childNodes.item(j).getNodeName());
					if (childNodes.item(j).getNodeName() == "Espetaculo") {
						Espetaculo espetaculo = (Espetaculo) Espetaculo.build(childNodes.item(j));
						festival.addEvento(espetaculo);
						//System.out.println("Inside For Eventos ->  " +  childNodes.item(j).getNodeName());
					}
					if (childNodes.item(j).getNodeName() == "Festival") {
						Festival festChild = Festival.build(childNodes.item(j));
						festival.addEvento(festChild);
					}
				}
			}
		}
		return festival;
	}

	/**
	 * Cria um novo Elemento quer irá representar, no documento XML, o Festival
	 * associado ao Festival corrente.
	 * 
	 * @param doc o Documento que irá ser usado para gerar o novo Element.
	 */
	public Element createElement(Document doc) {
		// TODO
		Element festival = doc.createElement("Festival");

        /*de seguida criamos o elemento nome. neste elemento vamos criar um nó de texto
        * onde iremos usar o getNome, damos append do getNome ao nome e repetimos o
        * append mas desta vez fazemos append do nome ao festival*/
        Element nome = doc.createElement("Nome");
        nome.appendChild(doc.createTextNode(getNome()));
        festival.appendChild(nome);

        //criamos o elemento eventos que também será um elemento filho do festival, tal como o nome
        Element eEventos = doc.createElement("Eventos");
        festival.appendChild(eEventos);

        /*ciclo for para adicionar eventos ao festival, estes eventos podem ser
        * espetaculos os outros festivais*/
        for (int i = 0; i < numEventos; i++){
            if (this.eventos[i] != null){
                //se o evento i não for nulo vamos ver se o evento é um espetaculo ou um festival
                if (this.eventos[i] instanceof Espetaculo){
                    //se for um espetaculo adicionamos o mesmo ao festival
                    Element eEspetaculo = ((Espetaculo)this.eventos[i]).createElement(doc);
                    eEventos.appendChild(eEspetaculo);
                }
                else if (this.eventos[i] instanceof Festival){
                    //se for um festival adicionamos o mesmo ao festival
                    Element eFestival = ((Festival)this.eventos[i]).createElement(doc);
                    eEventos.appendChild(eFestival);
                }

            }
        }
        
        //por fim retornamos o festival acabado de criar
        return festival;
	}

	/**
	 * Método main que gera no output o que está no ficheiro
	 * OutputPretendido/OutputPretendido.txt e cria um novo documento
	 * XML/Eventos.xml, com a mesma estrutura que o documento
	 * OutputPretendido/Eventos.xml.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			File inputFile = new File("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP3/XML/BaseDados.xml");
			// File inputFile = new File("XML/BaseDados.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = "/BaseDados/Eventos/*";
			NodeList nList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);

			Node nNode = nList.item(0);
			Evento evento = Evento.build(nNode);
			if (evento != null)
				evento.print("");

			Festival fNovo = new Festival("Bollywood Music Festival");

			Espetaculo e1_1 = new Espetaculo("Suna Hai", "Sines", 500);
			e1_1.addArtista("Suna Hai");
			fNovo.addEvento(e1_1);

			Espetaculo e1_2 = new Espetaculo("Rait Zara", "Sines", 400);
			e1_2.addArtista("Rait Zara");
			fNovo.addEvento(e1_2);

			if (evento instanceof Festival) {

				Festival festival = (Festival) evento;
				festival.addEvento(fNovo);

				// root elements
				Document newDoc = dBuilder.newDocument();
				Element rootElement = newDoc.createElement("Eventos");

				rootElement.appendChild(festival.createElement(newDoc));

				newDoc.appendChild(rootElement);

				FileOutputStream output = new FileOutputStream("C:/Users/diogo/Desktop/LEIM/2Sem/MoP/2/Codigos/TP3/XML/Eventos.xml");
				writeXml(newDoc, output);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Escreve, para o OutputStream, o documento doc.
	 * 
	 * @param doc    o documento contendo os Elementos a gravar on ficheiro output
	 * @param output o ficheiro de saída.
	 */
	private static void writeXml(Document doc, OutputStream output) throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		// pretty print XML
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);

		transformer.transform(source, result);
	}

}