package tp3.XML;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe que representa um Evento do tipo Espetaculo.
 * 
 * @version 1.0
 * @author Docentes da Disciplina de Modelação e Programação, LEIM, Instituto
 *         Superior de Engenharia de Lisboa
 *
 */
public class Espetaculo extends Evento {

	private static final int MAX_ARTISTAS = 10;
	private String[] artistas = new String[MAX_ARTISTAS];
	private int nArtistas = 0;
	private int numBilhetes;
	private String localidade;

	/**
	 * Constroi um novo Espetaculo
	 * 
	 * @param nome        nome do Espetaculo
	 * @param localidade  a localidade do Espetaculo
	 * @param numBilhetes o número de bilhetes disponíveis
	 */
	public Espetaculo(String nome, String localidade, int numBilhetes) {
		super(nome);
		/*
		 * if(nome==null||nome.length()<1) throw new
		 * IllegalArgumentException("O nome não é válido");
		 */

		if (localidade == null || localidade.length() < 1)
			throw new IllegalArgumentException("A localidade não é valida");
		this.localidade = localidade;

		if (numBilhetes < 1)
			throw new IllegalArgumentException("O número de bilhetes não pode ser menor que um");
		this.numBilhetes = numBilhetes;

		if (artistas.length > 10) {
			throw new IllegalArgumentException("O número de artistas não pode ser maior a 10");
		}
	}

	/**
	 * Informa se um determinado artista irá actuar no Espetaculo.
	 * 
	 * @return 1, se actuar e 0 caso contrário.
	 * @Override
	 */
	@Override
	public int numActuacoes(String artista) {
		int soma = 0;
		for (int i = 0; i < artistas.length; i++) {
			if (artistas[i] == (artista)) {
				soma++;
			}
		}
		return soma;

	}

	/**
	 * Permite adicionar un novo artista ao Espetaculo se o artista ainda não tem
	 * actuações e se o número máximo de artistas ainda não foi ultrapassado.
	 * 
	 * @param artista representa o novo artista
	 * @return verdadeiro, caso o artista tenha sido adicionado e falso caso
	 *         contrário.
	 */
	public boolean addArtista(String artista) {
		if (artista.equals(null))
			return false;

		for (int i = 0; i < artistas.length; i++) {
			if (artista.equals(this.artistas[i]))
				return false;
		}

		for (int i = 0; i < artistas.length; i++) {
			if (this.artistas[i] == null) {
				this.artistas[i] = artista;
				nArtistas++;
				return true;
			}
		}
		return false;

	}

	/**
	 * Devolve o número de bilhetes.
	 * 
	 * @Override
	 */
	@Override
	public int getNumBilhetes() {
		return numBilhetes;
	}

	/**
	 * Devolve uma cópia dos artistas que actuam no Espetaculo.
	 * 
	 * @Override
	 */
	@Override
	public String[] getArtistas() {
		ArrayList<String> novo = new ArrayList<>();
		for (int i = 0; i < artistas.length; i++) {
			if (artistas[i] != null) {
				nArtistas++;
				novo.add(artistas[i]);
			}
		}
		String[] go = new String[novo.size()];
		novo.toArray(go);

		return go;
	}

	/**
	 * Devolve a localidade do Espetaculo
	 * 
	 * @return a localidade.
	 */
	public String getLocalidade() {
		return this.localidade;
	}

	/**
	 * Devolve uma String a representar o Espetaculo. Nota: Ver o ficheiro
	 * OutputPretendido.txt
	 * 
	 * @Override
	 */
	public String toString() {
		return super.toString() + " em " + localidade;
	}

	/**
	 * Constroi um novo Evento a partir do objecto Node passado como parâmetro.
	 * 
	 * @param nNode
	 * @return um novo Evento
	 */
	public static Evento build(Node nNode) {
		NodeList nodeList = nNode.getChildNodes();
		Espetaculo espetaculo = null;
		
		Element element = (Element) nNode;
		String bilhetes = element.getAttribute("numBilhetes");
		String nome = element.getElementsByTagName("Nome").item(0).getTextContent();
		String localidade = element.getElementsByTagName("Localidade").item(0).getTextContent();
		espetaculo = new Espetaculo(nome, localidade, Integer.parseInt(bilhetes));
		NodeList artistas = element.getElementsByTagName("Artistas").item(0).getChildNodes();
		for(int i =  0; i< artistas.getLength();i++) {
			Node artista = artistas.item(i);
			if(artista.getNodeName() == "Artista") {
				espetaculo.addArtista(artista.getTextContent());
			}
		}
		
		return espetaculo;
	}

	/**
	 * Constroi um novo Element a partir do Espectaculo actual.
	 * 
	 * @param doc - o documento que irá gerar o novo Element
	 */
	public Element createElement(Document doc) {
		// TODO
		Element espetaculo = doc.createElement("Espetaculo");

        /*de seguida criamos o elemento nome. neste elemento vamos criar um nó de texto
         * onde iremos usar o getNome, damos append do getNome ao nome e repetimos o
         * append mas desta vez fazemos append do nome ao espetaculo*/
        Element nome = doc.createElement("Nome");
        nome.appendChild(doc.createTextNode(getNome()));
        espetaculo.appendChild(nome);

        /*repetimos o que foi realizado a cima para os artistas, localidade
        * e para o numero de bilhestes*/
        Element eArtistas = doc.createElement("Artistas");
        //neste caso estamos a criar um elemento self closing/empty
        espetaculo.appendChild(eArtistas);

        Element localidade = doc.createElement("Localidade");
        localidade.appendChild(doc.createTextNode(getLocalidade()));
        espetaculo.appendChild(localidade);

        espetaculo.setAttribute("numBilhetes" , Integer.toString(getNumBilhetes()));

        /*por fim vamos tratar do elemento "Artistas" que até agora é um elemento self
        * closing/empty, para tal começamos por criar um array de Strings chamado o metodo
        * getArtista para termos acesso ao artistas */
        String[] artistasTotal = getArtistas();
        //vamos um ciclo for para adicionar os artistas 1 a 1
        for (int i = 0; i < artistasTotal.length; i++){
            if (artistasTotal[i] != null){
                /*se o artista i não for null então criamos um elemento "Artista" e damos append
                * do do mesmo para o elemento*/
                Element eArtista = doc.createElement("Artista");
                eArtista.appendChild(doc.createTextNode(artistasTotal[i]));
                /*por fim damos append do elemento "Artista" ao elemento "Artistas" e deste modo
                * deixamos de ter o elemento "Artistas" self closing/empty*/
                eArtistas.appendChild(eArtista);
            }
        }
        //feito tudo isto, damos return do espetaculo em questão
        return espetaculo;
	}

}