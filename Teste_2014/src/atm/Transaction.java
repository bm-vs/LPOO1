package atm;

public class Transaction {
	protected ATM atm;
	protected Session session;
	protected Card card;
	protected double amount;
	
	public Transaction(ATM atm, Session session, Card card, int amount) {
		this.atm = atm;
		this.session = session;
		this.card = card;
		this.amount = amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount; 
	}
	
	public double getAmount() {
		return amount;
	}
}
