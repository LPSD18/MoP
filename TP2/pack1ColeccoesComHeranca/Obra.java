package pack1ColeccoesComHeranca;

public abstract class Obra implements IObra {

	private String titulo;

	public Obra(String titulo) {
		// título (valida título e guarda-o)
		if (titulo == null || titulo.length() < 1) {
			throw new IllegalArgumentException("O titulo não é valido");
		}
		this.titulo = titulo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public abstract int getNumPaginas();

	public abstract float getPreco();

	/**
	 * Deve devolver true se o array conter apenas nomes válidos. Um nome é válido
	 * se conter pelo menos uma letra (Character.isLetter) e só conter letras e
	 * espaços (Character.isWhitespace). Deve chamar validarNome.
	 */
	public static boolean validarNomes(String[] nomes) {
		boolean flag = true;
		for (int i = 0; i < nomes.length; i++) {
			if (validarNome(nomes[i])) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * Um nome válido se não for null e não conter pelo menos uma letra
	 * (Character.isLetter) e só conter letras e espaços (Character.isWhitespace)
	 */
	public static boolean validarNome(String nome) {
		boolean flag = true;
		for (int i = 0; i < nome.length(); i++) {
			if (Character.isLetter(nome.charAt(i)) || Character.isWhitespace(nome.charAt(i))) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * Recebe um nome já previamente validado, ou seja só com letras ou espaços.
	 * Deve devolver o mesmo nome mas sem espaços (utilizar trim e
	 * Character.isWhitespace) no início nem no fim e só com um espaço ' ' entre
	 * cada nome. Deve utilizar um StringBuilder para ir contendo o nome já
	 * corrigido
	 */
	public static String removeExtraSpaces(String nome) {
		nome.trim();
		nome.replaceAll("\\s+", " ");
		return nome;
	}

	/**
	 * Método que verifica se há elementos repetidos. O array recebido não contém
	 * nulls.
	 */
	public static boolean haRepeticoes(String[] elems) {
		for (int i = 0; i < elems.length; i++) {
			for (int j = 0; j < elems.length; j++) {
				if (j != i && elems[i].equals(elems[j])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Devolve uma string com a informação da obra (ver outputs desejados e método
	 * toString de Livro)
	 */
	public String toString() {
		return this.titulo + ", " + this.getNumPaginas() + "p " + this.getPreco() + "$";
	}

	/**
	 * Deve mostrar na consola a informação da obra precedida do prefixo
	 */
	public void print(String prefix) {
		System.out.println(prefix + this);
	}

	/**
	 * O Object recebido é igual, se não for null, se for uma obra e se tiver o
	 * mesmo título que o título da obra corrente
	 */
	public boolean equals(Object l) {

		if (l == null) {
			return false;
		}
		if (l instanceof Obra) {
			return true;
		}
		if (l instanceof Livro) {
			return true;
		}
		if (l instanceof Coleccao){
			return true; 
		}
		return false;
	}

}