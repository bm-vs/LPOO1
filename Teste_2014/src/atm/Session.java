package atm;

import java.util.*;

public class Session implements Countable {
	private ATM atm;
	private ArrayList<Transaction> transactions;
	
	public Session(ATM atm) {
		this.atm = atm;
		transactions = new ArrayList<Transaction>();
	}
	
	public ATM getATM() {
		return atm;
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public int count() {
		return transactions.size();
	}
	
	@Override
	public boolean equals(Object s) {
		Session session = (Session) s;
		
		if (atm.getBank() == session.getATM().getBank() &&
				atm.getCity() == session.getATM().getCity() &&
				atm.getID() == session.getATM().getID()) {
			return true;
		}
		else {
			return false;
		}
	}
}
