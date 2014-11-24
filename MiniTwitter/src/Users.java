import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

//NOTING I was supposed to use Observer pattern here, 
//but i noticed that both the observer and subject were
//both being implemented by the users class so i decied that 
//i would not need an interface for and that i could just use it like so.
//note part of Composite Pattern

public class Users extends DefaultMutableTreeNode implements UserComponent {
	private String ID;
	private String name;
	private List<Users> followers;
	private List<Users> following;
	private List<String> personalMessageRecord;
	private String message;
	private UserGUI ui;
	private String identifer = "user";
	private boolean changed;

	public Users(String name, int x) {
		this.name = name;
		this.ID = name + x;
		followers = new ArrayList<Users>();
		following = new ArrayList<Users>();
		personalMessageRecord = new LinkedList<String>();
		super.setAllowsChildren(false);

	}

	public String getName() {return name;}

	public String getID() {return ID;}

	public String getIdentifier() {return identifer;}

	public String toString() {return name;}

	public void setGUI(UserGUI g) {ui = g;}

	public UserGUI getGui() {return ui;}

	public List<String> getNF() {return personalMessageRecord;}
	//The below functions are what could be used in the Observer Pattern
	public void addFollowing(Users s) {following.add(s);s.followers.add(this);}

	public void post(String m) {
		message = m;
		personalMessageRecord.add(message);
		changed = true;
		notifyObservers();

	}

	public void notifyObservers() {
		List<Users> temp = null;
		if (!changed)
			return;
		temp = new ArrayList<Users>(followers);
		this.changed = false;
		for (Users s : temp)
			s.updates(this.message);

	}

	public void updates(String str) {
		String temp = str;
		if (temp != null || temp != "") {
			ui.setNF(name + ": " + temp);

		}

	}
	//This Function was added to satisfy the UserComponent interface
	//The add function was used to make sure i could correctly add to a linkedList for group
	@Override
	public void add(UserComponent uc) {
		// TODO Auto-generated method stub
	}

	public List<String> getPMR() {

		return personalMessageRecord;
	}

}
