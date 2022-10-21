package br.com.reinan.cm.visao;

import java.util.Scanner;

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

}
