package Produtos;

public class Categoria{

    /**
     * Atributo necessário para a classe
     */
    private String nome;
    private Produto[] produtos = new Produto[100];

    /**
     * Construtor da classe Categoria
     * @param nome
     */
    public Categoria(String nome) {
        this.nome=nome;
        
        
    }

    /**
     * Método utilizado para adicionar produtos ao array de produtos
     * @param produto
     * @return
     */
    public boolean addProduto(Produto produto){
        if(produto==null){
            return false;
        }
        boolean existe=false;
        for(int i=0;i<produtos.length;i++){
            if(produtos[i]!=null && produto.getNome() == produtos[i].getNome())
                existe=true;
        }
        if(!existe && produto.getNome()!=null && produto.getNome().length()>1){
            for(int i=0;i<produtos.length;i++){
                if(produtos[i]==null){
                    produtos[i]=produto;
                    return true;
                }
            }
        }
        return false;
    }

    public String getNome(){
        return this.nome;
    }

    /**
     * Devolve a string desejada para o terminal
     */
    public String toString(){
        return "Categoria " + getNome();
    }

    /**
     * Escreve para a consola, o prefixo seguido da String representativa do Produto(Categoria)
     * De seguida faz o mesmo dos produtos desta categoria
     */
    public String print(String prefix){
        System.out.println(prefix+this+"\n");
        for(Produto produto:produtos){
            if(produto==null)break;
            else{ 
                produto.print(" "+ prefix);
                System.out.println("");
            }
        }
        String ref = "";
        ref+=(prefix+this);
        for(Produto produto:produtos){
            if(produto==null)break;
            else{ 
                ref+= produto.print(" "+ prefix+"\n");
                ref+=("");
            }
        }
        return ref;
    }

    /**
     * Este método verifica se o nome de um produto é igual ou não ao nome de algum dos produtos 
     * existentes dentro da categoria 
     * @param name
     * @return 
     */
    public boolean equals(String name){
        for(int i=0;i<this.produtos.length;i++){
            if(produtos[i]!=null){
                if(produtos[i].getNome().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
     
}
