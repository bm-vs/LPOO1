public class Dragao {
	public int x;
	public int y;
	
	public Dragao(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void morre(Labirinto labirinto) {
		labirinto.lab[y][x] = ' ';
		labirinto.lab[labirinto.saida.y][labirinto.saida.x] = ' ';
	}
}
