package br.com.reinan.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.reinan.cm.execao.ExplosaoExeption;

public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean minado;
	private boolean aberto;
	private boolean marcado;
	
	public Campo(int linha , int coluna ){
		this.coluna = coluna;
		this.linha = linha;
	}
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean mesmaLinha = linha != vizinho.linha;  
		boolean mesmaColuna = coluna != vizinho.coluna;  
		boolean diagonal = mesmaLinha && mesmaColuna;   
		
		int deltaLinha = Math.abs(linha -vizinho.linha) ;
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaFinal = deltaLinha + deltaColuna;
		
		if(deltaFinal == 1 && !diagonal ) {
			vizinhos.add(vizinho);
			return true;
		}else if(deltaFinal == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {			
			return false;
		}
	}
	
	void alternarMarcado() {
		if(!aberto) {
			this.marcado = !marcado;
		}
	}
	
	boolean vizinhosSeguro() {
		boolean seguro  = vizinhos.stream().allMatch(v -> !v.minado);
		return seguro;
	}
	boolean abrir() {
		
		if(!aberto && !marcado) {
			aberto = true;
			if(minado) {
				throw new ExplosaoExeption();
			}
			if(vizinhosSeguro()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}else {
			return false;			
		}
	}
	boolean objetivoAlcancado() {
		boolean desvendado = aberto && !minado;
		boolean protegido = aberto && minado && marcado;
		
		return protegido || desvendado;
	}
	long minasNaVIzinhanca() {
		 long quantidadeVizinhosMinados =vizinhos.stream()
				 .filter(v -> v.minado)
				 .count();
		 return quantidadeVizinhosMinados;
	}
	void reiniciar() {
		this.minado = false;
		this.marcado = false;
		this.aberto = false;
	}
	public String toString() {
		if(minado) {
			return "Explodiu mané";
		}else if(marcado) {
			return "marcado";
		}else if(aberto && minasNaVIzinhanca()> 0) {
			return Long.toString(minasNaVIzinhanca());
		}else if(aberto) {
			return "Aberto e zero minas na vizinhanca";
		}else {
			return "fechado";
		}
		
	}
	public boolean isMinado() {
		return minado;
	}

	public void setMinado(boolean minado) {
		this.minado = minado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	

}