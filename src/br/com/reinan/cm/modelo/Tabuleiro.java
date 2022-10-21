package br.com.reinan.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.reinan.cm.execao.ExplosaoException;

public class Tabuleiro {

		private int linhas;
		private int colunas;
		private int minas;
		
		private final List<Campo> campos = new ArrayList<>();
		
		public Tabuleiro(int linhas, int colunas , int minas) {
			this.linhas = linhas;
			this.colunas = colunas;
			this.minas = minas;
			
			gerarCampos();
			associarVizinhos();
			distribuirMinas();
			
		}
		public void abrir(int linha , int coluna) {
		  try {  campos.parallelStream()
		    .filter(c -> c.getLinha() == linha && c.getColuna()== coluna)
		    .findFirst()
		    .ifPresent(c -> c.abrir());
		  }catch (ExplosaoException e) {
            campos.forEach(c -> c.setAberto(true));
        }
		}
		public void alternarMarcacao(int linha , int coluna) {
		  campos.parallelStream()
		  .filter(c -> c.getLinha() ==linha && c.getColuna() == coluna)
		  .findFirst()
		  .ifPresent(c -> c.alternarMarcado());
		}

		private void gerarCampos() {
			for( int i = 0; i < linhas; i++) {
				for(int j = 0; j < colunas; j++) {
					campos.add(new Campo(i,j)) ;
				}
			}
		}
		private void associarVizinhos() {
			
			for (Campo campo : campos) {
				for (Campo vizinhos : campos) {
					campo.adicionarVizinho(vizinhos);
				}
			}
		}
		private void distribuirMinas() {
		    long minasArmadas = 0;
	        Predicate<Campo> minado = c -> c.isMinado();
	        
	        do {
	            int aleatorio = (int) (Math.random() * campos.size());
	            campos.get(aleatorio).setMinado(true);
	            minasArmadas = campos.stream().filter(minado).count();
	        } while(minasArmadas < minas);
		}
		boolean objetivoAlcancado() {
			return campos.stream().allMatch(c -> c.objetivoAlcancado());
		}
		public void reiniciar() {
			campos.stream().forEach(c -> c.reiniciar());
			distribuirMinas();
		}
		public String toString() {
		    StringBuilder str = new StringBuilder();
	        
	        int index = 0;
	        for (int l = 0; l < linhas; l++) {
	            str.append(" ");
	            for (int c = 0; c < colunas; c++) {
	                str.append(" ");             
	                str.append(campos.get(index));
	                str.append(" ");
	                index++;
	            }
	            str.append("\n");
	        }
	        
	        return str.toString();
		}
		
		
}
