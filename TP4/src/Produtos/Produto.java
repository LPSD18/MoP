package Produtos;


public abstract class Produto implements IProduto{
    /*
     * Atributo que todas as classes filhas irão ter
     */
    private String nome;
    private float preco;
    private int stock;
    /**
     * Método construtor da classe abstrata Produto
     * Recebe como parametro um nome
     * 
     * @param nome
     */
    public Produto(String nome,float preco, int stock){
        this.nome=nome;
        this.preco=preco;
        this.stock=stock;
    }
    /**
     * método getter do atributo nome
     * 
     * @return String
     */
    public String getNome(){
        return this.nome;
    }

    public float getPreco(){
        return this.preco;
    }

    public int getStock(){
        return this.stock;
    }


    /**
     * Retorna uma string com as informações do Produto
     * 
     */
    public String toString(){
        return "Produto: " + this.nome + "\n  " + "Preço: " + Float.toString(getPreco())
        + "\n  " + "Stock: " + Integer.toString(getStock());
    }

    /**
     * Escreve, para a consola, o prefixo seguido da String representativa do Produto
     * @param prefix
     */
    public String print(String prefix){
        System.out.println(prefix+this);
        String ref = prefix+this+"\n";
        return ref;
    }

    
}
