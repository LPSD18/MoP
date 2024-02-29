package Produtos;

public interface IProduto {
    
    public String getNome();
    public float getPreco();
    public int getStock();
    public String toString();
    public String print(String prefix);
    
}
