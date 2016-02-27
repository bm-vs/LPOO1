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

	public void move(Labirinto labirinto) {
		int i = labirinto.rand.nextInt() % 4;

		if (i == 0) {
			if (labirinto.lab[y - 1][x] == ' ') {
				labirinto.lab[y][x] = ' ';
				y--;
				labirinto.lab[y][x] = 'D';
			}
			else if (labirinto.lab[y - 1][x] == 'E') {
				labirinto.lab[y][x] = ' ';
				y--;
				labirinto.lab[y][x] = 'F';
			}
		}

		if (i == 1) {
			if (labirinto.lab[y][x + 1] == ' ')

			{
				labirinto.lab[y][x] = ' ';
				x++;
				labirinto.lab[y][x] = 'D';
			}
			else if (labirinto.lab[y][x + 1] == 'E')

			{
				labirinto.lab[y][x] = ' ';
				x++;
				labirinto.lab[y][x] = 'F';
			}
		}

		if (i == 2) {
			if (labirinto.lab[y + 1][x] == ' ') {
				labirinto.lab[y][x] = ' ';
				y++;
				labirinto.lab[y][x] = 'D';
			
			}
			else if (labirinto.lab[y + 1][x] == 'E') {
				labirinto.lab[y][x] = ' ';
				y++;
				labirinto.lab[y][x] = 'F';
			}
		}

		if (i == 3) {
			if (labirinto.lab[y][x - 1] == ' ') {
				labirinto.lab[y][x] = ' ';
				x--;
				labirinto.lab[y][x] = 'D';
			}
			else if (labirinto.lab[y][x - 1] == 'E') {
				labirinto.lab[y][x] = ' ';
				x--;
				labirinto.lab[y][x] = 'F';
			}
		}
		
		
		if((labirinto.espada.y != y || labirinto.espada.x !=x) && labirinto.existeEspada)
		{
			labirinto.lab[labirinto.espada.y][labirinto.espada.x]='E';
		}
		
		
			
	}

}
