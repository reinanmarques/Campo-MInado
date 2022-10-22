package br.com.reinan.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.reinan.cm.execao.ExplosaoException;
import br.com.reinan.cm.execao.SairException;
import br.com.reinan.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {

        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
            	cicloDoJogo();
                System.out.println("Quer continuar?");
                String digito = entrada.nextLine();
                if ("n".equalsIgnoreCase(digito)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {

            System.out.println("Tchau!!");
        } finally {
            entrada.close();
        }

    }
   private  void cicloDoJogo() {
    	try {
    		while(!tabuleiro.objetivoAlcancado()) {
    			System.out.println(tabuleiro);
    			
    			String cordenadas = pegarDigitos("Digite (x,y): ");
    			
    			Iterator<Integer> xy = Arrays.stream(cordenadas.split(","))
    					.map(e -> Integer.parseInt(e.trim())).iterator();
    			String interacao = pegarDigitos("1 - Abrir ou 2 - (Des)Marcar: ");
    			
    			if("1".equalsIgnoreCase(interacao)) {
    				tabuleiro.abrir(xy.next(),xy.next());
    			}else if ("2".equalsIgnoreCase(interacao)) {
    				tabuleiro.alternarMarcacao(xy.next(), xy.next());
    			}else {
    				System.out.println("Opção Ivalida!! :(");
    			}
    		}
    		
    	}catch (ExplosaoException e) {
			System.out.println(tabuleiro + "\n Vodcê perdeu!!");
			
		}
    	
    }
    
    private String pegarDigitos(String texto ) {
    	System.out.println(texto);
    	String digitos = entrada.nextLine();
    	
    	if("sair".equalsIgnoreCase(digitos)) {
    		throw new SairException();
    	}
    	    	return digitos;
    }
    
    
    
    
    
    
    
    
    
    
}
