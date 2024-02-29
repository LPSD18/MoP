package pack2Festivais;

import java.util.Arrays;

public abstract class Evento {

   private String nome;

    /**
     * Construtor
     * 
     * @param String nome
     */

   public Evento(String nome){

      if(nome.length()<1 || nome==null)
         throw new IllegalArgumentException("O nome não é válido");
      this.nome=nome;
         
     }
     /**
      * 
      * @return String
      */
     public String getNome(){
        return nome;
     }

     public abstract int getNumBilhetes();

     public abstract String[] getArtistas();

     public abstract int numAtuacoes(String artista);

     /**
      * Devolve uma string com a informação do evento (ver outputs desejados e método toString de Espetaculo)
      * 
      * @return String
      */
     public String toString(){

         return this.nome + " com " + getNumBilhetes() + " bilhetes e com os artistas " + Arrays.toString(getArtistas());
     }
    
}
