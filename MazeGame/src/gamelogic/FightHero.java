package gamelogic;

/**
 * Exception class.
 * <p>
 * For when an a dragon moves to a place adjacent to the hero.
 * </p>
 */
public class FightHero extends Exception {
	private static final long serialVersionUID = 1L;
	
	private boolean hero_dies;
	
	public FightHero(boolean hero_dies) {
		this.hero_dies = hero_dies;
	}
	
	public boolean getHeroDies() {
		return hero_dies;
	}
}
