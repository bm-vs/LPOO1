
public class User {
	private String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object u) {
		User user = (User) u;
		
		if (user.getName() == name) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
}
