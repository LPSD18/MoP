package tp1.pack3Coleccoes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import tp1.pack2Livros.Livro;

//import tp1.pack2Livros.Livro;

/**
 * Classe Coleccao, deve conter a descrição de uma colecção, com título, os seus
 * livros, colecções e editores
 */
public class ColeccaoPt3 {
	// número máximo de obras de uma colecção
	private static int MAXOBRAS = 20;

	// prefixo usual
	public static final String GENERALPREFIX = "  ";

	// título da colecção
	private String titulo;

	// Array de livros, em que estas encontram-se sempre nos menores índices e
	// pela ordem de registo
	private Livro[] livros = new Livro[MAXOBRAS];

	// deverá conter sempre o número de livros na colecção
	private int numLivros = 0;

	// array de colecções, estas devem ocupar sempre os menores índices
	private ColeccaoPt3[] coleccoes = new ColeccaoPt3[MAXOBRAS];

	// deverá conter sempre o número de colecções dentro da colecção
	private int numColeccoes = 0;

	// Editores, tem as mesmas condicionantes que array de autores na classe
	// livro
	private String[] editores;

	/**
	 * Construtor; o título tem de ter pelo menos um caracter que não seja um
	 * espaço (Character.isWhitespace); o array de editores devem ser pelo menos
	 * um e têm as mesmas restrições que os autores dos livros;
	 * 
	 * @param String   titulo
	 * @param String[] editores
	 */
	public ColeccaoPt3(String titulo, String[] editores) {
		// titulo
		if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException(
					"O titulo tem de ter pelo menos um caracter");
		this.titulo = titulo;
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
	 * Método para retornar titulo
	 * 
	 * @return String titulo
	 */
	public String getTitulo() {
		
		return this.titulo;
	}

	/**
	 * Obtem o número total de páginas da colecção, páginas dos livros e das
	 * colecções
	 * 
	 * @return int soma
	 */
	public int getNumPaginas() {
		
		int soma = 0;
		for (int i = 0; i < coleccoes.length; i++) {
			if (this.coleccoes[i] != null)
				soma += coleccoes[i].getNumPaginas();
		}
		for (int i = 0; i < livros.length; i++) {
			if (this.livros[i] != null)
				soma += livros[i].getNumPaginas();
		}
		return soma;
	}

	/**
	 * As colecções com mais de 5000 páginas nos seus livros directos têm um
	 * desconto de 20% nesses livros. As colecções em que o somatório de páginas
	 * das suas subcolecções directas seja igual ou superior ao quádruplo do nº
	 * de páginas da sua subcolecção directa com mais páginas deverão aplicar um
	 * desconto de 10% sobre os preços das suas subcolecções
	 * 
	 * @return float soma
	 */
	public float getPreco() {
		
		float soma = 0;

		for (int i = 0; i < coleccoes.length; i++) {

			if (coleccoes[i] != null && coleccoes[i].getNumPaginas() > 5000) {
				for (int s = 0; s < coleccoes[i].livros.length; s++) {
					if (coleccoes[i].livros[s] != null) {
						soma += coleccoes[i].livros[s].getPreco() * 0.8;
						// System.out.println("Preço com Desconto -> " +
						// coleccoes[i].livros[s].getPreco() * 0.8);
					}
				}
			} else {

				if (coleccoes[i] != null) {
					for (int s = 0; s < coleccoes[i].livros.length; s++) {
						if (coleccoes[i].livros[s] != null) {
							soma += coleccoes[i].livros[s].getPreco();
							// System.out.println("Preço sem Desconto -> " +
							// coleccoes[i].livros[s].getPreco());
						}
					}
				}
			}
		}
		if (this.getNumPaginas() > 5000) {
			for (int i = 0; i < livros.length; i++) {
				if (livros[i] != null)
					soma += livros[i].getPreco() * 0.8;
			}
		} else {
			for (int i = 0; i < livros.length; i++) {
				if (livros[i] != null)
					soma += livros[i].getPreco();
			}
		}
		return soma;

	}

	/**
	 * Adiciona um livro à colecção se puder e este não seja null e a colecção
	 * não ficar com livros iguais ao nível imediato da colecção. Deve utilzar o
	 * método getIndexOfLivro e getIndexOfColeccao
	 * 
	 * @param Livro livro
	 * @return boolean
	 */
	public boolean addLivro(Livro livro) {
		
		if (livro == null)
			return false;
		if (getIndexOfLivro(livro.getTitulo()) == -1 && livro.getTitulo() != null && livro.getTitulo().length() > 1) {
			for (int i = 0; i < this.livros.length; i++) {
				if (this.livros[i] == null) {
					this.livros[i] = livro;
					numLivros++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Adiciona uma colecção à colecção se puder, esta não seja null e a
	 * colecção não ficar com obras imediatas com títulos repetidos. Deve
	 * utilizar o método getIndexOfLivro e getIndexOfColeccao
	 * 
	 * @param ColeccaoPt3 col
	 * @return boolean
	 */
	public boolean addColeccao(ColeccaoPt3 col) {
		
		if (col == null)
			return false;
		if (getIndexOfColeccao(col.getTitulo()) == -1 && col.getTitulo() != null && col.getTitulo().length() > 1) {
			for (int i = 0; i < this.coleccoes.length; i++) {
				if (this.coleccoes[i] == null) {
					this.coleccoes[i] = col;
					numColeccoes++;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Devolve o index no array de livros onde estiver o livro com o nome
	 * pretendido. Devolve -1 caso não o encontre
	 * 
	 * @param String titulo
	 * @return int
	 */
	private int getIndexOfLivro(String titulo) {
		
		for (int i = 0; i < this.livros.length; i++) {
			if (this.livros[i] != null && this.livros[i].getTitulo().equals(titulo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Devolve o index no array de colecções onde estiver a colecção com o nome
	 * pretendido. Devolve -1 caso não o encontre
	 * 
	 * @param String titulo
	 * @return int
	 */
	private int getIndexOfColeccao(String titulo) {
	
		for (int i = 0; i < this.coleccoes.length; i++) {
			if (this.coleccoes[i] != null && this.coleccoes[i].getTitulo().equals(titulo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Remove do array o livro com o título igual ao título recebido. Devolve o
	 * livro removido ou null caso não tenha encontrado o livro. Deve-se
	 * utilizar o método getIndexOfLivro. Recorda-se que os livros devem ocupar
	 * sempre os menores índices, ou seja, não pode haver nulls entre os livros
	 * 
	 * @param String titulo
	 * @return Livro
	 */
	public Livro remLivro(String titulo) {
		
		if (titulo == null || titulo.length() < 1) {
			return null;
		}
		int num = getIndexOfLivro(titulo);
		if (num != -1) {
			Livro rLivro = this.livros[num];
			numLivros--;
			for (int i = 0; i < this.livros.length; i++) {
				if (i == num) {
					this.livros[i] = this.livros[i + 1];
				} else if (i > num) {
					this.livros[i - 1] = this.livros[i];
				}
			}
			return rLivro;
		} else {
			return null;
		}
	}

	/**
	 * Remove do array de colecções a colecção com o título igual ao título
	 * recebido. Devolve a colecção removida ou null caso não tenha encontrado.
	 * Deve-se utilizar o método getIndexOfColeccao. Recorda-se que as colecções
	 * devem ocupar sempre os menores índices, ou seja, não pode haver nulls
	 * entre elas
	 * 
	 * @param String titulo
	 * @return ColeccaoPt3
	 */
	public ColeccaoPt3 remColeccao(String titulo) {
		
		if (titulo == null || titulo.length() < 1)
			return null;
		int num = getIndexOfColeccao(titulo);
		if (num != -1) {
			numColeccoes--;
			for (int i = 0; i < this.coleccoes.length; i++) {
				if (i < num)
					this.coleccoes[i] = this.coleccoes[i];
				else if (i == num)
					this.coleccoes[i] = this.coleccoes[i + 1];
				else
					this.coleccoes[i - 1] = this.coleccoes[i];
			}
		} else {
			return null;
		}
		return this.coleccoes[num];
	}

	/**
	 * Devolve o nº de obras de uma pessoa. Cada colecção deve contabilizar-se
	 * como uma obra para os editores.
	 * 
	 * @param String autorEditor
	 * @return int
	 */
	public int getNumObrasFromPerson(String autorEditor) {
		
		int soma = 0;
		for (int i = 0; i < editores.length; i++) {
			if (editores[i] != null && editores[i].equals(autorEditor))
				soma++;
		}
		for (int i = 0; i < this.coleccoes.length; i++) {
			if (this.coleccoes[i] != null) {
				for (int j = 0; j < this.coleccoes[i].getAutoresEditores().length; j++) {
					if (this.coleccoes[i].getAutoresEditores()[j].equals(autorEditor))
						soma++;
				}
				for (int j = 0; j < this.coleccoes[i].livros.length; j++) {
					if (this.coleccoes[i].livros[j] != null && this.coleccoes[i].livros[j].getAutores() != null) {
						for (int s = 0; s < this.coleccoes[i].livros[j].getAutores().length; s++) {
							if (this.coleccoes[i].livros[j].getAutores()[s].equals(autorEditor))
								soma++;

						}
					}
				}
			}
		}
		for (int i = 0; i < this.livros.length; i++) {
			if (this.livros[i] != null) {
				for (int j = 0; j < this.livros[i].getAutores().length; j++) {
					if (this.livros[i].getAutores()[j].equals(autorEditor))
						soma++;
				}
			}
		}
		return soma;
	}

	/**
	 * Devolver um novo array (sem nulls) com os livros de que a pessoa recebida
	 * é autor. Não deve conter repetições, para excluir as repetições devem
	 * utilizar o método mergeWithoutRepetitions
	 * 
	 * @param String autorNome
	 * @return Livro[]
	 */
	public Livro[] getLivrosComoAutor(String autorNome) {
		
		int soma = 0;
		for (int i = 0; i < livros.length; i++) {
			if (livros[i] != null) {
				for (int j = 0; j < livros[i].getAutores().length; j++) {
					if (livros[i].getAutores()[j].equals(autorNome)) {
						soma++;
					}
				}
			}
		}
		for (int i = 0; i < coleccoes.length; i++) {
			if (coleccoes[i] != null) {
				for (int s = 0; s < coleccoes[i].livros.length; s++) {
					if (coleccoes[i].livros[s] != null) {
						for (int j = 0; j < coleccoes[i].livros[s].getAutores().length; j++) {
							if (coleccoes[i].livros[s].getAutores()[j].equals(autorNome)) {
								// System.out.println(coleccoes[i].livros[s]);
								soma++;
							}

						}
					}
				}
			}
		}
		Livro[] newArray = new Livro[soma];
		int x = 0;
		for (int i = 0; i < this.livros.length; i++) {
			if (this.livros[i] != null) {
				for (int j = 0; j < this.livros[i].getAutores().length; j++) {
					if (this.livros[i].getAutores()[j].equals(autorNome)) {
						newArray[x] = this.livros[i];
						x++;
					}
				}
			}
		}
		for (int i = 0; i < coleccoes.length; i++) {
			if (coleccoes[i] != null) {
				for (int j = 0; j < coleccoes[i].livros.length; j++) {
					if (coleccoes[i].livros[j] != null) {
						for (int s = 0; s < coleccoes[i].livros[j].getAutores().length; s++) {
							if (coleccoes[i].livros[j].getAutores()[s].equals(autorNome)) {
								newArray[x] = coleccoes[i].livros[j];
								x++;
							}
						}
					}
				}
			}
		}
		return newArray;

	}

	/**
	 * Deve devolver uma string compatível com os outputs desejados
	 * 
	 * @return String
	 */
	public String toString() {
		
		return "Coleção " + this.titulo + ", editores " + Arrays.toString(editores) + ", " + this.numLivros
				+ " livros, " + getNumPaginas() + "p " + getPreco() + "$";
	}

	/**
	 * Deve devolver um array, sem nulls, com todos os autores e editores
	 * existentes na colecção. O resultado não deve conter repetições. Deve
	 * utilizar o método mergeWithoutRepetitions
	 * 
	 * @return String[]
	 */
	public String[] getAutoresEditores() {
		
		String[] array1;
		array1 = editores;
		for (int i = 0; i < coleccoes.length; i++) {
			if (coleccoes[i] == null) {
				break;
			}
			array1 = mergeWithoutRepetitions(coleccoes[i].editores, array1);
			for (int s = 0; s < coleccoes[i].livros.length; s++) {
				if (coleccoes[i].livros[s] == null)
					break;
				array1 = mergeWithoutRepetitions(coleccoes[i].livros[s].getAutores(), array1);
			}
		}
		for (int i = 0; i < livros.length; i++) {
			if (livros[i] == null)
				break;
			array1 = mergeWithoutRepetitions(livros[i].getAutores(), array1);
		}
		return array1;
	}

	/**
	 * Método que recebendo dois arrays sem repetições devolve um novo array com
	 * todos os elementos dos arrays recebidos mas sem repetições
	 * 
	 * @param String[] a1
	 * @param String[] a2
	 * @return String[]
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
	 * 
	 * @param Livro[] a1
	 * @param Livro[] a2
	 * @return Livro[]
	 */
	private static Livro[] mergeWithoutRepetitions(Livro[] a1, Livro[] a2) {
		
		Set<Livro> setNaoDuplicado = new HashSet<>();
		setNaoDuplicado.addAll(Arrays.asList(a1));
		setNaoDuplicado.addAll(Arrays.asList(a2));
		Livro[] ArrayNaoDuplicado = new Livro[setNaoDuplicado.size()];
		setNaoDuplicado.toArray(ArrayNaoDuplicado);
		Arrays.sort(ArrayNaoDuplicado);
		return ArrayNaoDuplicado;
	}

	/**
	 * Devolve true caso a colecção recebida tenha o mesmo título e a mesma
	 * lista de editores. Para verificar verificar se os editores são os mesmos
	 * devem utilizar o método mergeWithoutRepetitions
	 * 
	 * @param Coleccao c
	 * @return boolean
	 */
	public boolean equals(ColeccaoPt3 c) {
		
		if (c.getTitulo() != titulo) {
			return false;
		}
		if (this.editores.length != c.editores.length)
			return false;
		for (int i = 0; i < c.editores.length; i++) {
			if (this.editores[i] != c.editores[i]) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Mostra uma colecção segundo os outputs desejados
	 * 
	 * @param String prefix
	 */
	public void print(String prefix) {
		
		System.out.println(prefix + this);
		for (int i = 0; i < this.livros.length; i++) {
			if (this.livros[i] == null) {
				break;
			}
			System.out.println(GENERALPREFIX + livros[i]);
		}
		for (int i = 0; i < coleccoes.length; i++) {
			if (coleccoes[i] == null) {
				break;
			}
			System.out.println(GENERALPREFIX + coleccoes[i]);
			for (int s = 0; s < coleccoes[i].livros.length; s++) {
				if (coleccoes[i].livros[s] == null) {
					break;
				}
				System.out.println(GENERALPREFIX + GENERALPREFIX + coleccoes[i].livros[s]);
			}
		}
	}

	/**
	 * main
	 */
	public static void main(String[] args) {
		Livro l1 = new Livro("Viagem aos Himalaias", 340, 12.3f,
				new String[] { "João Mendonça", "Mário Andrade" });
		Livro l2 = new Livro("Viagem aos Pirinéus", 270, 11.5f,
				new String[] { "João Mendonça", "Júlio Pomar" });

		ColeccaoPt3 c1 = new ColeccaoPt3("Primavera",
				new String[] { "João Mendonça", "Manuel Alfazema" });

		boolean res;

		res = c1.addLivro(l1);
		res = c1.addLivro(l2);
		System.out.println("c1 -> " + c1);
		c1.print("");
		System.out.println();

		// adicionar um livro com nome de outro já existente
		res = c1.addLivro(l2);
		System.out.println(
				"adição novamente de Viagem aos Pirinéus a c1 -> " + res);
		System.out.println("c1 -> " + c1);
		System.out.println();

		// Outra colecção
		Livro l21 = new Livro("Viagem aos Himalaias 2", 340, 12.3f,
				new String[] { "João Mendonça", "Mário Andrade" });
		Livro l22 = new Livro("Viagem aos Pirinéus 2", 270, 11.5f,
				new String[] { "João Mendonça", "Júlio Pomar" });

		ColeccaoPt3 cx2 = new ColeccaoPt3("Outono",
				new String[] { "João Mendonça", "Manuel Antunes" });
		cx2.addLivro(l21);
		cx2.addLivro(l22);
		System.out.println("cx2 -> " + cx2);
		cx2.print("");
		System.out.println();

		// adicioná-la a c1
		c1.addColeccao(cx2);
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
		Livro[] obras = c1.getLivrosComoAutor(nome);
		System.out
				.println("Livros de " + nome + " -> " + Arrays.toString(obras));
		System.out.println();

		// rem livro
		String nomeLivro = "Viagem aos Himalaias";
		Livro l = c1.remLivro(nomeLivro);
		System.out.println("Remoção de " + nomeLivro + " -> " + l);
		c1.print("");
		System.out.println();
	}
}