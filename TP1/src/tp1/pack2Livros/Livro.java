package tp1.pack2Livros;

import java.util.Arrays;

/**
 * Classe que deverá suportar um livro
 */
public class Livro {

    // Título do livro
    private String titulo;

    // número de páginas
    private int numPaginas;

    // preço do livro
    private float preco;

    // array de autores, este array não deve ter nulls
    private String[] autores;

    /**
     * Deve criar um novo livro com os dados recebidos. O título não deve ser
     * null nem vazio. O número de páginas não pode ser menor que 1. O preço não
     * pode ser negativo. O array de autores não deve conter nem nulls e deve
     * conter pelo menos um autor válido. Não pode haver repetições dos nomes
     * dos autores, considera-se os nomes sem os espaços extra (ver
     * removeExtraSpaces). Este método deve utilizar os métodos auxiliares
     * existentes. Em caso de nome inválido deve lançar uma excepção de
     * IllegalArgumentException com a indicação do erro ocorrido
     * 
     * @param String titulo
     * @param int numPaginas
     * @param float preco
     * @param String[] autores
     */
    public Livro(String titulo, int numPaginas, float preco, String[] autores) {

        // título
        if (titulo == null || titulo.length() == 0)
            throw new IllegalArgumentException("O titulo tem de ter pelo menos um caracter");
        this.titulo = titulo;
        //Num paginas
        if(numPaginas<0){
            throw new IllegalArgumentException("O número de páginas não pode ser negativo");
        }
        this.numPaginas=numPaginas;
        //Preço
        if(preco<0){
            throw new IllegalArgumentException("O valor do livro não pode ser negativo");
        }
        this.preco=preco;
        //Autores
        if(!validarNomes(autores)){
            throw new IllegalArgumentException("O array de autores não é valido");
        }
        if (haRepeticoes(autores)){
            throw new IllegalArgumentException("O array de autores não pode ter autores repetidos");
        }
        this.autores=autores;

    }

    /**
     * Devolve o título do livro
     * 
     * @return String titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Devolve o número de páginas do livro
     * 
     * @return int numPaginas
     */
    public int getNumPaginas() {
        return this.numPaginas;
    }

    /**
     * Devolve o preço do livro
     * 
     * @return float preco
     */
    public float getPreco() {
        return this.preco;
    }

    /**
     * Devolve uma cópia do array de autores do livro
     * 
     * @return String[] autores
     */
    public String[] getAutores() {
        return Arrays.copyOf(this.autores,autores.length);
    }

    /**
     * Deve devolver true se o array conter apenas nomes válidos. Um nome é
     * válido se conter pelo menos uma letra (Character.isLetter) e só conter
     * letras e espaços (Character.isWhitespace). Deve chamar validarNome.
     * 
     * @param String[] nomes
     * @return boolean flag
     */
    public static boolean validarNomes(String[] nomes) {
        boolean flag=true;
        for (int i=0;i<nomes.length;i++){
            if (validarNome(nomes[i])){
                flag=true;
            }
            else{
                flag=false;
            }
        }
        return flag;
    }

    /**
     * Um nome válido se não for null e não conter pelo menos uma letra
     * (Character.isLetter) e só conter letras e espaços
     * (Character.isWhitespace)
     * 
     * @param String nome
     * @return boolean flag
     */
    public static boolean validarNome(String nome) {
        boolean flag=true;
        for (int i=0;i<nome.length();i++){
            if (Character.isLetter(nome.charAt(i))|| Character.isWhitespace(nome.charAt(i))){
                flag=true;
            }
            else {
                flag=false;
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
     * 
     * @param String nome
     * @return String nome
     */
    public static String removeExtraSpaces(String nome) {
        nome.trim();
        nome.replaceAll("\\s+"," ");
        return nome;
    }

    /**
     * Método que verifica se há elementos repetidos. O array recebido não
     * contém nulls.
     * 
     * @param String[] elems
     * @return boolean 
     */
    public static boolean haRepeticoes(String[] elems) {
        for (int i=0;i<elems.length;i++){
            for (int j=0;j<elems.length;j++){
                if (j!=i && elems[i].equals(elems[j])){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devolve true se o autor recebido existe como autor do livro. O nome
     * recebido não contém espaços extra.
     * 
     * @param String autorNome
     * @return boolean
     */
    public boolean contemAutor(String autorNome) {
        for (int i=0;i<autores.length;i++){
            if (autores[i].equals(autorNome)){
                return true;
            }
        }
        return false;
    }

    /**
     * Devolve uma string com a informação do livro (ver outputs desejados)
     * 
     * @return String
     */
    public String toString() {
        return this.titulo + ", " + this.numPaginas + "p " + this.preco + "$ " + Arrays.toString(this.autores);
    }

    /**
     * Deve mostrar na consola a informação do livro precedida do prefixo
     */
    public void print(String prefix) {
        System.out.println(prefix+this);
    }

    /**
     * O Livro recebido é igual se tiver o mesmo título que o título do livro
     * corrente
     * 
     * @param Livro l
     * @return boolean
     */
    public boolean equals(Livro l) {
        return titulo.equals(l.getTitulo());
    }

    /**
     * main
     */
    public static void main(String[] args) {

        // constructor e toString
        Livro l = new Livro("Viagem aos Himalaias", 340, 12.3f, new String[]{"João Mendonça", "Mário Andrade"});
        System.out.println("Livro -> " + l);
        l.print("");
        l.print("-> ");
        System.out.println();

        // contém autor
        String autorNome = "Mário Andrade";
        System.out.println("Livro com o autor " + autorNome + "? -> " + l.contemAutor(autorNome));
        autorNome = "Mário Zambujal";
        System.out.println("Livro com o autor " + autorNome + "? -> " + l.contemAutor(autorNome));
        System.out.println();

        // equals
        System.out.println("Livro: " + l);
        System.out.println("equals Livro: " + l);
        System.out.println(" -> " + l.equals(l));

        Livro l2 = new Livro("Viagem aos Himalaias", 100, 10.3f, new String[]{"Vitor Záspara"});
        System.out.println("Livro: " + l);
        System.out.println("equals Livro: " + l2);
        System.out.println(" -> " + l.equals(l2));
        System.out.println();

        // testes que dão excepção - mostra-se a excepção

        // livro lx1
        System.out.println("Livro lx1: ");
        try {
            Livro lx1 = new Livro("Viagem aos Himalaias", -1, 12.3f, new String[]{"João Mendonça", "Mário Andrade"});
            System.out.println("Livro lx1: " + lx1);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        System.out.println();

        // livro lx2
        System.out.println("Livro lx2: ");
        try {
            Livro lx2 = new Livro("Viagem aos Himalaias", 200, -12.3f,
                    new String[]{"João Mendonça", "Mário Andrade"});
            System.out.println("Livro lx2: " + lx2);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        System.out.println();

        // livro lx3
        System.out.println("Livro lx3: ");
        try {
            Livro lx3 = new Livro(null, 200, -12.3f, new String[]{"João Mendonça", "Mário Andrade"});
            System.out.println("Livro lx3: " + lx3);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        System.out.println();

        // livro lx4
        System.out.println("Livro lx4: ");
        try {
            Livro lx4 = new Livro("Viagem aos Himalaias", 200, 12.3f,
                    new String[]{"João Mendonça", "Mário Andrade", "João Mendonça"});
            System.out.println("Livro lx4: " + lx4);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}

