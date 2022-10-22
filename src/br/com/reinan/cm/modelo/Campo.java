package br.com.reinan.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.reinan.cm.execao.ExplosaoException;

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
	public boolean abrir() {
		
		if(!aberto && !marcado) {
			this.aberto = true;
			if(minado) {
				throw new ExplosaoException();
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
		boolean protegido = minado && marcado ;
		
		
		return protegido || desvendado ;
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
		if(minado && aberto) {
			return "*";
		}else if(marcado) {
			return "x";
		}else if(aberto && minasNaVIzinhanca()> 0) {
			return Long.toString(minasNaVIzinhanca());
		}else if(aberto) {
			return " ";
		}else {
			return "?";
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
