public class Heroi {
	public int x;
	public int y;
	public char arma;
	
	public Heroi(int x, int y) {
		this.x = x;
		this.y = y;
		this.arma = 'H';
	}
	
	public boolean move(String tecla, Labirinto labirinto) {		
		if (tecla.equals("w")) {
			if (labirinto.lab[y - 1][x] == ' ' || labirinto.lab[y - 1][x] == 'E') {
				labirinto.lab[y][x] = ' ';
				y --;
				labirinto.lab[y][x] = arma;
			
				return true;
			}
		}
		else if (tecla.equals("a")) {
			if (labirinto.lab[y][x - 1] == ' ' || labirinto.lab[y][x - 1] == 'E') {
				labirinto.lab[y][x] = ' ';
				x --;
				labirinto.lab[y][x] = arma;
				
				return true;
			}
		}
		else if (tecla.equals("d")) {
			if (labirinto.lab[y][x + 1] == ' ' || labirinto.lab[y][x + 1] == 'E') {
				labirinto.lab[y][x] = ' ';
				x ++;
				labirinto.lab[y][x] = arma;
				
				return true;
			}
		}
		else if (tecla.equals("s")) {
			if (labirinto.lab[y + 1][x] == ' ' || labirinto.lab[y + 1][x] == 'E') {
				labirinto.lab[y][x] = ' ';
				y ++;
				labirinto.lab[y][x] = arma;
				
				return true;
			}
		}
		
		return false;
	}
	
	public int lutardragao(Dragao dragao) {
		if (dragao.x == x) {
			if (dragao.y == y - 1 || dragao.y == y + 1) {
				if (arma == 'H') {
					return 1;
				}
				else if (arma == 'A') {
					return 2;
				}
			}
		}
		if (dragao.y == y) {
			if (dragao.x == x - 1 || dragao.x == x + 1) {
				if (arma == 'H') {
					return 1;
				}
				else if (arma == 'A') {
					return 2;
				}
			}
		}
		
		return 0;
	}
	
	public boolean apanha(Labirinto.Espada espada, Labirinto labirinto) {
		if (espada.x == x && espada.y == y) {
			arma = 'A';
			labirinto.lab[y][x] = arma;
			
			return true;
		}
		
		return false;
	}
}
