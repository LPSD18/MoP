package tp1.pack2Livros;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Coleccao, deve conter a descrição de uma colecção, com título, seus
 * livros e editores
 */
public class Coleccao {

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

    // Editores, tem as mesmas condicionantes que array de autores na classe
    // livro
    private String[] editores;

    /**
     * Construtor; o título tem de ter pelo menos um caracter que não seja um
     * espaço (Character.isWhitespace); o array de editores devem ser pelo menos
     * um e têm as mesmas restrições que os autores dos livros; o array de
     * livros deve conter os mesmos sempre nos menores índices
     * 
     * @param String   titulo
     * @param String[] editores
     */
    public Coleccao(String titulo, String[] editores) {
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

        // TODO
    }

    /**
     * Método para retornar titulo
     * 
     * @return String titulo
     */
    public String getTitulo() {
        // TODO
        return this.titulo;
    }

    /**
     * Obtem o número total de páginas da colecção
     * 
     * @return int soma
     */
    public int getNumPaginas() {
        // TODO
        int soma = 0;
        for (int i = 0; i < this.livros.length; i++) {
            if (livros[i] == null)
                break;
            soma += livros[i].getNumPaginas();
        }
        return soma;
    }

    /**
     * Devolve o preço da colecção tendo em conta que as colecções com 4 ou mais
     * livros têm um desconto de 20% nos livros que custam pelo menos 10 euros e
     * que têm mais de 200 páginas
     * 
     * @return float soma
     */
    public float getPreco() {
        // TODO
        // ESTÁ MAL
        float soma = 0;
        if (this.numLivros > 3) {
            for (int i = 0; i < this.livros.length; i++) {
                if (this.livros[i] != null) {
                    if (this.livros[i].getNumPaginas() > 200 && this.livros[i].getPreco() >= 10)
                        soma += (this.livros[i].getPreco() * 0.8f);
                    else
                        soma += this.livros[i].getPreco();
                }
            }
        } else {
            for (int i = 0; i < this.livros.length; i++) {
                if (this.livros[i] != null) {
                    // System.out.println("DEBUGGGGGG --> " + this.livros[i].getPreco());
                    soma += this.livros[i].getPreco();
                }
            }
        }
        return soma;
    }

    /**
     * Adiciona um livro se puder e este não seja null e a colecção não ficar
     * com livros iguais. Deve utilzar o método getIndexOfLivro.
     * 
     * @param Livro livro
     * @return boolean
     */
    public boolean addLivro(Livro livro) {
        // TODO
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
     * Devolve o index no array de livros onde estiver o livro com o nome
     * pretendido. Devolve -1 caso não o encontre
     * 
     * @param String titulo
     * @return int
     * 
     */
    private int getIndexOfLivro(String titulo) {
        // TODO
        for (int i = 0; i < this.livros.length; i++) {
            if (this.livros[i] != null && this.livros[i].getTitulo().equals(titulo)) {
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
        // TODO
        if (titulo == null || titulo.length() < 1) {
            return null;
        }
        int num = getIndexOfLivro(titulo);
        Livro livrorem = null;
        if (num != -1) {
            numLivros--;
            livrorem = livros[num];
            for (int i = 0; i < this.livros.length; i++) {
                
                if (i == num) {
                    this.livros[i] = this.livros[i + 1];
                } else {
                    this.livros[i - 1] = this.livros[i];
                }
            }
        } else {
            return null;
        }

        return livrorem;

    }

    /**
     * Devolve o nº de obras de uma pessoa. A colecção deve contabilizar-se como
     * uma obra para os editores.
     * 
     * @param String autorEditor
     * @return int
     */
    public int getNumObrasFromPerson(String autorEditor) {
        // TODO
        int soma = 0;
        for (int i = 0; i < editores.length; i++) {
            if (autorEditor.equals(editores[i])) {
                soma++;
            }
        }
        for (int i = 0; i < this.livros.length; i++) {
            if (this.livros[i] != null) {
                for (int s = 0; s < this.livros[i].getAutores().length; s++) {
                    if (autorEditor.equals(this.livros[i].getAutores()[s])) {
                        soma++;
                    }
                }
            }
        }
        return soma;
    }

    /**
     * Devolver um novo array (sem nulls) com os livros de que a pessoa recebida
     * é autor
     * 
     * @param String autorNome
     * @return Livro[]
     */
    public Livro[] getLivrosComoAutor(String autorNome) {
        // TODO
        int soma = 0;
        for (int i = 0; i < this.livros.length; i++) {
            if (this.livros[i] != null) {
                for (int j = 0; j < this.livros[i].getAutores().length; j++) {
                    if (this.livros[i].getAutores()[j].equals(autorNome)) {
                        soma++;
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

        return newArray;
    }

    /**
     * Deve devolver uma string compatível com os outputs desejados
     * 
     * @return String
     */
    public String toString() {
        // TODO
        return "Coleção " + this.titulo + ", editores " + Arrays.toString(editores) + ", " + this.numLivros + " livros, "
                + getNumPaginas() + "p " + getPreco() + "$";
    }

    /**
     * Deve devolver um array, sem nulls, com todos os autores e editores
     * existentes na colecção. O resultado não deve conter repetições. Deve
     * utilizar o método mergeWithoutRepetitions
     * 
     * @return String[]
     */
    public String[] getAutoresEditores() {
        // TODO
        String[] Array1;
        Array1 = editores;
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] == null) {
                break;
            }
            Array1 = mergeWithoutRepetitions(livros[i].getAutores(), Array1);
        }

        return Array1;
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
     * Devolve true caso a colecção recebida tenha o mesmo título e a mesma
     * lista de editores. Para verificar verificar se os editores são os mesmos
     * devem utilizar o método mergeWithoutRepetitions
     * 
     * @param Coleccao c
     * @return boolean
     */
    public boolean equals(Coleccao c) {
        // TODO
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
     * 
     */
    public void print(String prefix) {
        System.out.println(prefix + this);
        for (int i = 0; i < this.livros.length; i++) {
            if (livros[i] != null)
                livros[i].print(prefix);
        }
        // TODO
    }

    /**
     * main
     */
    public static void main(String[] args) {
        Livro l1 = new Livro("Viagem aos Himalaias", 340, 12.3f,
                new String[] { "João Mendonça", "Mário Andrade" });
        Livro l2 = new Livro("Viagem aos Pirinéus", 270, 11.5f,
                new String[] { "João Mendonça", "Júlio Pomar" });

        Coleccao c1 = new Coleccao("Primavera",
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

        // equals
        Coleccao c2 = new Coleccao("Primavera",
                new String[] { "João Mendonça", "Manuel Alfazema" });
        boolean same = c1.equals(c2);
        System.out.println("c2:");
        c2.print("");
        System.out.println("c1.equals(c2) -> " + same);
        System.out.println();

        Coleccao c3 = new Coleccao("Primavera",
                new String[] { "João Mendonça" });
        same = c1.equals(c3);
        System.out.println("c3:");
        c3.print("");
        System.out.println("c1.equals(c3) -> " + same);
    }
}