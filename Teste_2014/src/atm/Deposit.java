package atm;

public class Deposit extends Transaction {
	public Deposit(ATM atm, Session session, Card card, int amount) {
		super(atm, session, card, amount);
	}
}
