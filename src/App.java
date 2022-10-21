import br.com.reinan.cm.modelo.Tabuleiro;
import br.com.reinan.cm.visao.TabuleiroConsole;

public class App {
public static void main(String[] args) {
	Tabuleiro tab = new Tabuleiro(4, 4, 4);
	TabuleiroConsole jogo = new TabuleiroConsole(tab);
}
}
