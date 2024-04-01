package backend;
import java.util.ArrayList;
import java.util.Iterator;


public class Accounts {
	
	private static boolean status;
	private static User user;
	private static ArrayList<User> users;

	public Accounts() {
		Accounts.users = DataProcessing.loadUserInfo();
		Accounts.status = false;
		Accounts.user = null;
	}
	
	public static User getUser() {
		return user;
	}
	
	public static boolean getStatus() {
		return status;
	}
	
	public static boolean logIn(String name) {
		if (status == false && exist(name) == true) {
			status = true;
			user = find(name);
			return true;
		}
		else return false;
	}
	
    public static boolean logOut() {
    	if (status == false) return false;
    	else {
    		save();
    		status = false;
    		user = null;
    		return true;
    	}
    }
    
    public static void save() {
    	DataProcessing.updateUsers(users);
    	return;
    }
    
    public static boolean create(User person) {
    	if (exist(person.getEmail())) {
    		return false;
    	}
    	else {
    		users.add(person);
    		return true;
    	}
    }
	
	public static boolean exist(String name) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			if (name.equals(it.next().getEmail())) return true;
		}
		return false;
	}
	
	public static User find(String name) {
		User person;
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			person = it.next();
			if (name.equals(person.getEmail())) return person;
		}
		return null;
	}

}