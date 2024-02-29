package pack1ColeccoesComHeranca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Coleccao, deve conter a descrição de uma colecção, com título, os seus
 * livros, colecções e editores. Deve utilizar herança para guardar os livros e
 * as colecções num só array
 */
public class Coleccao extends Obra {
	// prefixo a colocar no início de cada print mais interno que o corrente
	public static final String GENERALPREFIX = "  ";

	// número máximo de obras de uma colecção
	private static int MAXOBRAS = 20;

	// Array de obras, de Livros ou Coleccções, em que estas devem encontrar-se
	// sempre nos menores índices e pela ordem de registo

	private Obra[] obras = new Obra[MAXOBRAS];

	// deverá conter sempre o número de obras na colecção
	private int numObras = 0;

	// Editores, tem as mesmas condicionantes que array de autores na classe
	// livro
	private String[] editores;

	/**
	 * Construtor; o título deve ser guardado e validado na clase obra; o array de
	 * editores devem ser pelo menos um e tem as mesmas restrições que os autores
	 * dos livros;
	 */
	public Coleccao(String titulo, String[] editores) {

		// titulo
		super(titulo);
		/*if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException("O título tem de ter pelo menos um caracter ");
		*/
		// editores
		if (editores.length < 1) {
			throw new IllegalArgumentException("É necessário ter pelo menos um editor no array");
		}
		if (!Livro.validarNomes(editores)) {
			throw new IllegalArgumentException("O array de editores não é valido");
		}
		if (Livro.haRepeticoes(editores)) {
			throw new IllegalArgumentException("O array de editores não pode ter nomes repetidos");
		}
		this.editores = editores;
	}

	/**
	 * Obtem o número total de páginas da colecção, páginas dos livros e das
	 * colecções
	 */
	public int getNumPaginas() {

		int soma = 0;
		for (int i = 0; i < obras.length; i++) {
			if (obras[i] != null)
				soma += obras[i].getNumPaginas();
		}
		return soma;

	}

	/**
	 * As colecções com mais de 5000 páginas nos seus livros directos têm um
	 * desconto de 20% nesses livros. As colecções em que o somatório de páginas das
	 * suas subcolecções directas seja igual ou superior ao quádruplo do nº de
	 * páginas da sua subcolecção directa com mais páginas deverão aplicar um
	 * desconto de 10% sobre os preços das suas subcolecções
	 */

	public float getPreco() {
		float soma = 0;
		if (this.getNumPaginas() > 5000) {
			for (int i = 0; i < obras.length; i++) {
				if (obras[i] != null)
					soma += obras[i].getPreco() * 0.8;
			}
		} else {
			for (int i = 0; i < obras.length; i++) {
				if (obras[i] != null)
					soma += obras[i].getPreco();
			}
		}
		return soma;

	}

	/**
	 * Adiciona uma obra à colecção se puder, se esta não for null e a colecção não
	 * ficar com obras com iguais no seu nível imediato. Deve utilizar o método
	 * getIndexOfLivro e getIndexOfColeccao
	 */
	public boolean addObra(Obra obra) {

		if (obra == null)
			return false;
		if (getIndexOfObra(obra.getTitulo()) == -1 && obra.getTitulo() != null && obra.getTitulo().length() > 1) {
			for (int i = 0; i < obras.length; i++) {
				if (obras[i] == null) {
					obras[i] = obra;
					numObras++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Devolve o index no array de obras onde estiver a obra com o nome pretendido.
	 * Devolve -1 caso não o encontre
	 */
	private int getIndexOfObra(String titulo) {

		for (int i = 0; i < obras.length; i++) {
			if (obras[i] != null && obras[i].getTitulo().equals(titulo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Remove do array a obra com o título igual ao título recebido. Devolve a obra
	 * removida ou null caso não tenha encontrado a obra. Deve-se utilizar o método
	 * getIndexOfLivro. Recorda-se que as obras ocupam sempre os menores índices, ou
	 * seja, não pode haver nulls entre elas.
	 */
	public Obra remObra(String titulo) {

		if (titulo == null || titulo.length() < 1)
			return null;

		int num = getIndexOfObra(titulo);
		if (num != -1) {
			Obra obraremovida = obras[num];
			numObras--;
			for (int i = 0; i < obras.length; i++) {
				if (i == num) {
					obras[i] = obras[i + 1];
				} else if (i > num) {
					obras[i - 1] = obras[i];
				}
			}
			return obraremovida;
		} else {
			return null;
		}

	}

	/**
	 * Remove todas as obras (livros ou colecções) dentro da obra corrente, que
	 * tenham um título igual ou título recebido. Devolve true se removeu pelo menos
	 * uma obra, ou false caso não tenha trealizado qualquer remoção. Deve utilizar
	 * os métodos remObra e remAllObra.
	 */
	public boolean remAllObra(String titulo) {
		// TODO
		Obra obra = null;
		for (int i = 0; i < obras.length; i++) {
			if (obras[i].getTitulo().equals(titulo))
				obra = remObra(titulo);
		}
		if (obra != null)
			return true;
		else
			return false;
	}

	/**
	 * Devolve o nº de obras de uma pessoa. Cada colecção deve contabilizar-se como
	 * uma obra para os editores.
	 */
	public int getNumObrasFromPerson(String autorEditor) {
		int num = 0;
		for (Obra obra : obras) {
			if (obra instanceof Livro) {
				if (((Livro) obra).contemAutor(autorEditor))
					num++;
			}
			if (obra instanceof Coleccao) {
				num += ((Coleccao) obra).getNumObrasFromPerson(autorEditor);
			}
		}
		for (String editor : editores)
			if (editor == autorEditor)
				num++;

		return num;
	}

	/**
	 * Deve devolver um novo array, sem repetições, com os livros de que o autor
	 * recebido é autor. O array devolvido não deve conter repetições, para excluir
	 * as repetições devem utilizar o método mergeWithoutRepetitions
	 */
	public Livro[] getLivrosComoAutor(String autorNome) {

		int size = 0;
		// Obter o size necessario do array
		for (Obra obra : obras) {
			if (obra instanceof Livro) {
				if (((Livro) obra).contemAutor(autorNome))
					size++;
			}
			if (obra instanceof Coleccao) {
				size += ((Coleccao) obra).getLivrosComoAutor(autorNome).length;
			}
		}
		Livro[] livros = new Livro[size];
		int index = 0;
		// Preencher o array
		for (Obra obra : obras) {
			if (obra instanceof Livro) {
				if (((Livro) obra).contemAutor(autorNome)) {
					livros[index] = (Livro) obra;
					index++;
				}
			}
			if (obra instanceof Coleccao) {
				Livro[] autores = ((Coleccao) obra).getLivrosComoAutor(autorNome);
				for (Livro livro : autores) {
					if (livro.contemAutor(autorNome)) {
						livros[index] = livro;
						index++;
					}
				}
			}
		}

		return mergeWithoutRepetitions(livros, livros);
	}

	/**
	 * Deve devolver um array, sem nulls, com todos os autores e editores existentes
	 * na colecção. O resultado não deve conter repetições. Deve utilizar o método
	 * mergeWithoutRepetitions
	 */
	public String[] getAutoresEditores() {
		String[] edit = editores;
		for(Obra obra : obras) {
			if(obra instanceof Coleccao)
				edit = mergeWithoutRepetitions(edit,((Coleccao) obra).getAutoresEditores());
			if(obra instanceof Livro)
				edit = mergeWithoutRepetitions(edit,((Livro) obra).getAutores());
		}
		return edit;
	}

	/**
	 * Método que recebendo dois arrays sem repetições devolve um novo array com
	 * todos os elementos dos arrays recebidos mas sem repetições
	 */
	private static String[] mergeWithoutRepetitions(String[] a1, String[] a2) {
		Set<String> setNaoDuplicado = new HashSet<>();
		setNaoDuplicado.addAll(Arrays.asList(a1));
		setNaoDuplicado.addAll(Arrays.asList(a2));
		String[] ArrayNaoDuplicado = new String[setNaoDuplicado.size()];
		setNaoDuplicado.toArray(ArrayNaoDuplicado);
		Arrays.sort(ArrayNaoDuplicado);
		return ArrayNaoDuplicado;
	}

	/**
	 * Método idêntico ao método anterior mas agora com arrays de livros
	 */
	private static Livro[] mergeWithoutRepetitions(Livro[] a1, Livro[] a2) {
		Set<Livro> setNaoDuplicado = new HashSet<>();
		setNaoDuplicado.addAll(Arrays.asList(a1));
		setNaoDuplicado.addAll(Arrays.asList(a2));
		Livro[] ArrayNaoDuplicado = new Livro[setNaoDuplicado.size()];
		setNaoDuplicado.toArray(ArrayNaoDuplicado);
		// Arrays.sort(ArrayNaoDuplicado);
		return ArrayNaoDuplicado;
	}

	/**
	 * Devolve o nº de livros dentro da colecção
	 */
	public int getNumLivros() {
		int soma = 0;
		for (Obra obra : obras) {
			if (obra instanceof Livro)
				soma++;
			else if (obra instanceof Coleccao)
				soma += ((Coleccao) obra).getNumLivros();

		}
		return soma;
	}

	/**
	 * Devolve o nº de colecções dentro da colecção
	 */
	public int getNumColeccoes() {
		int coleccoes = 0;
		for (Obra obra : obras) {
			if (obra instanceof Coleccao)
				coleccoes++;
		}
		return coleccoes;
	}

	/**
	 * Devolve a profundidada de máxima de uma colecção em termos de coleccões
	 * dentro de coleccções: uma colecção c1 com uma colecção c2 dentro, c1 deve
	 * devolver 2 e c2 deve devolver 1, independentemente do número do conteúdo de
	 * cada uma.
	 */
	public int getProfundidade() {
		int profundidade = 1;
		for (Obra obra : obras) {
			if (obra instanceof Coleccao) {
				profundidade++;
				int in = ((Coleccao) obra).getProfundidade(); // verificamos a profundidade da coleccao
				if (in != 1)
					profundidade += in - 1; // se for 1 ent a coleccao nao contem outras colecoes, se for 2 ent
											// encontrou 1,
											// se for 3 quer dizer que a outra colecao dentro da colecao tambem
											// encontrou
			}
		}
		return profundidade;
	}

	/**
	 * Duas colecções são iguais se tiverem o mesmo título e a mesma lista de
	 * editores. Deve utilizar o equals da classe Obra. Para verificar verificar se
	 * os editores são os mesmos devem utilizar o método mergeWithoutRepetitions
	 */
	public boolean equals(Object c) {
		// TODO
		return false;
	}

	/**
	 * Deve devolver uma string compatível com os outputs desejados
	 */
	public String toString() {
		return super.getTitulo() + ", " + getNumPaginas() + "p, " + getPreco() + "$, editores "
				+ Arrays.toString(editores) + ", com " + getNumLivros() + " livros, com " + getNumColeccoes()
				+ " coleccoes e com profundidade máxima de " + getProfundidade();
	}

	/**
	 * Mostra uma colecção segundo os outputs desejados. Deve utilizar o método
	 * print da classe Obra.
	 */
	public void print(String prefix) {
		System.out.println(prefix + this);
		for (Obra obra : obras) {
			if (obra == null) {
				break;
			}
			if (obra instanceof Coleccao)
				obra.print(prefix);
			if (obra instanceof Livro)
				System.out.println(GENERALPREFIX + obra);
		}
	}

	/**
	 * main
	 */
	public static void main(String[] args) {
		Livro l1 = new Livro("Viagem aos Himalaias", 340, 12.3f, new String[] { "João Mendonça", "Mário Andrade" });
		Livro l2 = new Livro("Viagem aos Pirinéus", 270, 11.5f, new String[] { "João Mendonça", "Júlio Pomar" });

		Coleccao c1 = new Coleccao("Primavera", new String[] { "João Mendonça", "Manuel Alfazema" });

		boolean res;

		res = c1.addObra(l1);
		res = c1.addObra(l2);
		System.out.println("c1 -> " + c1);
		c1.print("");
		System.out.println();

		// adicionar um livro com nome de outro já existente
		res = c1.addObra(l2);
		System.out.println("adição novamente de Viagem aos Pirinéus a c1 -> " + res);
		System.out.println("c1 -> " + c1);
		System.out.println();

		// Outra colecção
		Livro l21 = new Livro("Viagem aos Himalaias 2", 340, 12.3f, new String[] { "João Mendonça", "Mário Andrade" });
		Livro l22 = new Livro("Viagem aos Pirinéus 2", 270, 11.5f, new String[] { "João Mendonça", "Júlio Pomar" });

		Coleccao cx2 = new Coleccao("Outono", new String[] { "João Mendonça", "Manuel Antunes" });
		cx2.addObra(l21);
		cx2.addObra(l22);
		System.out.println("cx2 -> " + cx2);
		cx2.print("");
		System.out.println();

		// adicioná-la a c1
		c1.addObra(cx2);
		System.out.println("c1 após adição da colecção cx2 -> " + c1);
		c1.print("");
		System.out.println();

		// get editores autores
		String[] ae = c1.getAutoresEditores();
		System.out.println("Autores editores of c1 -> " + Arrays.toString(ae));
		System.out.println();

		// getNumObrasFromPerson
		String nome = "João Mendonça";
		int n = c1.getNumObrasFromPerson(nome);
		System.out.println("Nº de obras de " + nome + " -> " + n);
		System.out.println();

		// getLivrosComoAutor
		nome = "João Mendonça";
		Livro[] livros = c1.getLivrosComoAutor(nome);
		System.out.println("Livros de " + nome + " -> " + Arrays.toString(livros));
		System.out.println();
		System.out.println();

		// testes aos métodos getNumLivros, getNumColeccoes e getProfundidade
		c1.print("");
		System.out.println("Nº de livros na colecção " + c1.getTitulo() + " -> " + c1.getNumLivros());

		System.out.println("Nº de colecções dentro da colecção " + c1.getTitulo() + " -> " + c1.getNumColeccoes());

		System.out.println("Profundidade da colecção " + c1.getTitulo() + " -> " + c1.getProfundidade());
		System.out.println("Profundidade da colecção " + cx2.getTitulo() + " -> " + cx2.getProfundidade());
		System.out.println();

		// rem livro
		String nomeLivro = "Viagem aos Himalaias";
		Obra l = c1.remObra(nomeLivro);
		System.out.println("Remoção de " + nomeLivro + " -> " + l);
		c1.print("");

	}
}