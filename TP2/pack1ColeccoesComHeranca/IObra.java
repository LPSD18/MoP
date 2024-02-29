package pack1ColeccoesComHeranca;

public interface IObra {
	public String getTitulo();
	public abstract int getNumPaginas();
	public abstract float getPreco();
	public void print(String prefix);
}