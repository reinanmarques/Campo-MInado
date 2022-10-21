package br.com.reinan.cm.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.reinan.cm.execao.ExplosaoExeption;

public class CampoTest {
	Campo campo;
	@BeforeEach
	void instancia() {
	campo =new Campo(3,3);
	}
	@Test
	void testeSeForAceito() {
		Campo vizinho = new Campo(2,3);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeSeNaoPuderSerVizinho() {
		Campo vizinho = new Campo(1,1);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	@Test
	void testeDiagonal() {
		Campo vizinho = new Campo(2,2);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeAlterarMarcacao() {
		campo.setMarcado(true);
		
		campo.alternarMarcado();
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeNaoAbrir() {
		campo.setAberto(true);
		campo.setMinado(false);
		boolean aberto =campo.abrir();
		assertFalse(aberto);
	}
	@Test
	void testeExplosao() {
		campo.setMinado(true);
		
		assertThrows(ExplosaoExeption.class, () -> {
			campo.abrir();
		});
	}
	@Test
	void testeSimAbrir() {
		boolean aberto = campo.abrir();
		assertTrue(aberto);
	}
	@Test
	void testeVizinhosSeguro() {
		Campo vizinho = new Campo(2,2);
		Campo vizinho2 = new Campo(3,2);
		campo.adicionarVizinho(vizinho);
		campo.adicionarVizinho(vizinho2);
		assertTrue(campo.vizinhosSeguro());
	}
	@Test
	void testeAbrirVizinhosSeguro() {
		Campo vizinho = new Campo(2,2);
		Campo vizinho2 = new Campo(3,2);
		campo.adicionarVizinho(vizinho);
		campo.adicionarVizinho(vizinho2);	
		assertTrue(campo.abrir());
	}
	@Test
	void testeObjetivoAlcancadoDesvendado() {
		campo.setAberto(true);
		assertTrue(campo.objetivoAlcancado());
	}
	@Test
	void testeObjetivoAlcancadoProtegido() {
		campo.setAberto(true);
		campo.setMarcado(true);
		campo.setMinado(true);
		assertTrue(campo.objetivoAlcancado());
	}
	@Test
	void testeminasNaVIzinhanca1mina() {
		Campo vizinho = new Campo(2,2);
		Campo vizinho2 = new Campo(3,2);
		vizinho.setMinado(true);
		campo.adicionarVizinho(vizinho);
		campo.adicionarVizinho(vizinho2);	
		assertEquals(1, campo.minasNaVIzinhanca());
	}
	@Test
	void testeminasNaVIzinhanca2mIina() {
		Campo vizinho = new Campo(2,2);
		Campo vizinho2 = new Campo(3,2);
		vizinho.setMinado(true);
		vizinho2.setMinado(true);
		campo.adicionarVizinho(vizinho);
		campo.adicionarVizinho(vizinho2);	
		assertEquals(2, campo.minasNaVIzinhanca());
	}
	@Test
	void testeReiniciar() {
		campo.setAberto(true);
		campo.setMinado(true);
		campo.setMarcado(true);
		campo.reiniciar();
		assertFalse(campo.isMinado() && campo.isMarcado() && campo.isAberto());
	}
	@Test
	void testeToStringFechado() {
		
		Assert.assertEquals("?",campo.toString());
	}
	@Test
	void testeToStringMinado() {
		campo.setMinado(true);
		
		Assert.assertEquals("*",campo.toString());
	}
	@Test
	void testeToStringNaoMinado() {
		campo.setAberto(true);
		Assert.assertEquals(" ",campo.toString());
	}
	@Test
	void testeToStringMarcado() {
		campo.setMarcado(true);
		Assert.assertEquals("x",campo.toString());
	}
	@Test
	void testeToStringNaoMinadoMasComVizinhancaPerigosa() {
		campo.setAberto(true);
		Campo vizinho = new Campo(2,2);
		Campo vizinho2 = new Campo(3,2);
		vizinho.setMinado(true);
		vizinho2.setMinado(true);
		campo.adicionarVizinho(vizinho);
		campo.adicionarVizinho(vizinho2);	
		String nDeVizinhosPerigos = campo.toString();
		assertEquals("2", nDeVizinhosPerigos);
	}
	
}
	