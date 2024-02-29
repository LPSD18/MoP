package pack2Festivais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe Festival, deve conter a descrição de um festival, com nome,
 * os espetaculos , festivais, artistas e número de bilhetes.
 * Deve utilizar herança para guardar os espetaculos e festivais num só array 
 */

public class Festival extends Evento {

    /**
     * Array de eventos, de espetaculos ou festivais,estes devem sempre encontrar-se
     * nos menores índices e pela ordem de registo
     * 10--> o número máximo de eventos no festival
     */
	private Evento[] eventos = new Evento[20];

    // número de eventos existentes
	private int numEventos = 0;


    /**
     * cada fesval deverá ter uma
     * capacidade para um máximo de 20 eventos, que podem ser Espetáculos e Festivais
     * 
     * @param nome
     */
	public Festival(String nome) {
		super(nome);
        if(eventos.length>20){
            throw new IllegalArgumentException("O array de eventos do festival não pode ser maior que 20 eventos");
        }
		
        }
    /**
     *devolve o número de bilhetes disponíveis da totalidade dos eventos do fesval
     *   
     */    
	@Override
	public int getNumBilhetes() {
		int soma = 0;
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] != null)
				soma += eventos[i].getNumBilhetes();
		}
		return soma;
	}
    /**
     * deve devolver um novo array só com os arstas registados e com essa dimensão, cada arsta só deve
       aparecer uma vez no array devolvido, sugere-se a ulização de uma List<String> (e ulizar contains e
       toArray(…))
     */
	@Override
	public String[] getArtistas() {
		List<String> lista = new ArrayList<String>();
		for (Evento evento : eventos) {
			
			if (evento instanceof Festival) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					for (int j = 0; j < lista.size(); j++) {
						if (evento.getArtistas()[i] != null && evento.getArtistas()[i] != lista.get(j)) {
							lista.add(evento.getArtistas()[i]);
						}
					}
				}

			} else if (evento instanceof Espetaculo) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					if(lista.size()>0)
					for (int j = 0; j < lista.size(); j++) {
						if (evento.getArtistas()[i] != null && evento.getArtistas()[i] != lista.get(j)) {
							lista.add(evento.getArtistas()[i]);
						}
					}
					else lista.add(evento.getArtistas()[i]);
				}
			}
		}
        ArrayList<String>nova = new ArrayList<>();
        for(String str :lista){
            if(!nova.contains(str)){
                nova.add(str);
            }
        }
		String[] newArrray = new String[nova.size()];
		return nova.toArray(newArrray);

	}
    /**
     * devolve o número de atuações, dentro do Fesval, do arsta recebido
     */
	@Override
	public int numAtuacoes(String artista) {
		int soma = 0;
		for (Evento evento : eventos) {
			if (evento instanceof Espetaculo) {
				for (int i = 0; i < evento.getArtistas().length; i++) {
					if (evento.getArtistas()[i].equals(artista))
						soma++;
				}
			} else if (evento instanceof Festival) {
				evento.numAtuacoes(artista);
			}
		}
		return soma;

	}
    /**
     * deve devolver “Fesval “ seguido do conteúdo idênco ao toString de Evento
     */
	public String toString() {
		String string = "Festival -> " + super.toString();
		for(Evento evento:eventos) {
			if(evento instanceof Festival ) {
			string += "\n"+evento.toString();
			}
			if(evento instanceof Espetaculo ) {
				string += "\n"+evento.toString();
			}
		}
		return string;
	}
    /**
     * devolve a profundidade máxima de fesvais dentro de fesvais do
    *  festival corrente (o próprio fesval não conta)
     * @return int
     */
	private int getDeepFestival() {
		int profundidade = 0;
		for (Evento evento : eventos) {
			if (evento instanceof Festival) {
				profundidade++;
				int in = ((Festival) evento).getDeepFestival();
				if (in != 1)
					profundidade += in - 1;
			}
		}
		return profundidade;
	}
    /**
     *  adiciona o evento, caso para nenhum
     *  dos arstas do novo evento se verifique que o seu número de atuações no novo evento (a
     *  adicionar) supere em mais de duas o número de atuações no fesval corrente
     * 
     * @param evento
     * @return boolean
     */
	public boolean addEvento(Evento evento) {
		if (evento == null) {
			return false;
		}
		boolean existe = false;
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] != null && evento.getNome() == eventos[i].getNome())
				existe = true;
		}
		if (!existe && evento.getNome() != null && evento.getNome().length() > 1) {
			for (int i = 0; i < eventos.length; i++) {
				if (eventos[i] == null) {
					eventos[i] = evento;
					numEventos++;
					return true;
				}
			}
		}
		return false;
	}
    /**
     * que deverá remover o evento, com o nome recebido, em qualquer profundidade do Fesval corrente e devolver
     * true se removeu. O array de eventos deve ser gerido tal que nas remoções não se realizem
     * deslocamentos (shis) dos eventos existentes, ou seja, o array poderá conter nulls entre elementos
     * 
     * @param nomeEvento
     * @return boolean
     */
	public boolean delEvento(String nomeEvento) {
		if (nomeEvento == null || nomeEvento.length() < 1)
			return false;
		for (int i = 0; i < eventos.length; i++) {
			if (nomeEvento == eventos[i].getNome())
				eventos[i] = null;
			return true;
		}

		return false;
	}
    /**
     * main
     * 
     */
	public static void main(String[] args) {
        //Construtor e toString

		Festival f1 = new Festival("TomorrowLand");
		Festival f2 = new Festival("YesterdayLand");
		Espetaculo e1 = new Espetaculo("MEO Marés Vivas", "Lisboa", 500);
		e1.addArtista("Eminem");
		e1.addArtista("Ludovico Einaudi");
        Espetaculo e2 = new Espetaculo("Noitada Azul", "Porto", 2000);
        e2.addArtista("Joana");
		e2.addArtista("Artur");
        e2.addArtista("Eminem");
		f1.addEvento(e1);
		f1.addEvento(f2);
        f1.addEvento(e2);
		System.out.println(f1);

        //Adição de um evento com um nome de outro já existente
        Espetaculo e3 = new Espetaculo("Noitada Azul", "Porto", 2000);
        e3.addArtista("Joana");
		e3.addArtista("Artur");
        boolean res = f1.addEvento(e3);
        System.out.println("adição novamente de Noitada Azul a f1 -> "+ res);
        
        // Profundidade do Festival f1
        System.out.println("Profundidade do Festival f1 ->" + f1.getDeepFestival());

        //Número de atuações de um artista
        System.out.println("Número de atuações de um artista->"+ f1.numAtuacoes("Eminem"));

        //getArtistas
        String[] artistas = f1.getArtistas();
        System.out.println("Artistas do festival f1->" + Arrays.toString(artistas));

		

	}

}