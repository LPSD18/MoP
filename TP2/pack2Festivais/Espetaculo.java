package pack2Festivais;

import java.util.ArrayList;

/**
 * Classe que deverá suportar um Espetaculo
 */
public class Espetaculo extends Evento{
    //Array de artistas do espetáculo
    private String[] artistas = new String[10];
    //número de artistas participantes do espetáculo
    private int nArtistas=0;
    // número de bilhetes
    private int numBilhetes;
    //localidade do Espetaculo
    private String localidade;

    /**
     * deverá ter a capacidade para um máximo de 10 arstas
     * @param nome
     * @param localidade
     * @param numBilhetes
     */
    public Espetaculo(String nome, String localidade, int numBilhetes) {
        super(nome);
        /*if(nome==null||nome.length()<1)
            throw new IllegalArgumentException("O nome não é válido");*/
        
        if(localidade==null||localidade.length()<1)
            throw new IllegalArgumentException("A localidade não é valida");
        this.localidade=localidade;

        if(numBilhetes<1)
            throw new IllegalArgumentException("O número de bilhetes não pode ser menor que um");
        this.numBilhetes=numBilhetes;

        if(artistas.length>10){
            throw new IllegalArgumentException("O número de artistas não pode ser maior a 10");
        }
    }

    /**
     * devolve o número de bilhetes disponíveis da totalidade dos eventos do fesval
     */
    @Override
    public int getNumBilhetes() {
        return numBilhetes;
    }

    /**
     *  deve devolver um novo array só com os arstas registados e com essa dimensão, cada arsta só deve
     *  aparecer uma vez no array devolvido, sugere-se a ulização de uma List<String> (e ulizar contains e
     *  toArray(…))
     */
    @Override
    public String[] getArtistas() {
        ArrayList<String>novo = new ArrayList<>();
        for(int i=0;i<artistas.length;i++){
            if(artistas[i]!=null){
                nArtistas++;
                novo.add(artistas[i]);
            }
        }
        String[] go = new String[novo.size()];
        novo.toArray(go);
        
        
        
        return go;
    }

    /**
     * devolve o número de atuações do arsta recebido (um ou zero)
     */
    @Override
    public int numAtuacoes(String artista) {
        int soma=0;
        for (int i=0;i<artistas.length;i++){
            if(artistas[i]==(artista)){
                soma++;
            }
        }
        return soma;
        
    }
    /**
     * adiciona o artista, caso ele já não esteja registado no espetáculo
     * @param artista
     * @return boolean
     */
    public boolean addArtista(String artista){
        if(artista.equals(null)) return false;

        for(int i=0;i<artistas.length;i++){
            if(artista.equals(this.artistas[i])) return false;
        }

        for(int i=0;i<artistas.length;i++){
            if(this.artistas[i]==null){
                this.artistas[i]=artista;
                nArtistas++;
                return true;
            }
        }
        return false;

    }
    /**
     * deve devolver um conteúdo idênco ao toString de Evento seguido de “ em Lisboa”, para o caso em que o
     * espetáculo se realize na localidade de lisboa
     */
    public String toString(){
        return super.toString()+ " em " + localidade;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args){

        //Construtor e toString
        Espetaculo e = new Espetaculo("MEO Marés Vivas", "Lisboa", 500);
        e.addArtista("Eminem");
        e.addArtista("Ludovico Einaudi");
        System.out.println("Espetaculo-> "+e);
        System.out.println();

        //Número de atuações 
        String artista="Eminem";
        System.out.println("Número de atuações do artista " + artista + "?-> " + e.numAtuacoes(artista));
        artista = "Drake";
        System.out.println("Número de atuações do artista " + artista + "?-> " + e.numAtuacoes(artista));

        //Testes que dão excepção

        //Espetáculo Ex1 
        System.out.println("Espetáculo Ex1: ");
        try{
            Espetaculo Ex1 = new Espetaculo("", "Lisboa",500);
            System.out.println(Ex1);
        }
        catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }

        //Espetáculo Ex2
        System.out.println("Espetáculo Ex2: ");
        try{
            Espetaculo Ex2 = new Espetaculo("MEO Sudoeste", "",500);
            System.out.println(Ex2);
        }
        catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }

        //Espetáculo Ex3 
        System.out.println("Espetáculo Ex3: ");
        try{
            Espetaculo Ex3 = new Espetaculo("MEO Sudoeste", "Lisboa",0);
            System.out.println(Ex3);
        }
        catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }
    
}
