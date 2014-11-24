import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
//Group is small class that allows the creation of a group class to hold  Users
//Part of the Composite pattern

public class Group extends DefaultMutableTreeNode implements UserComponent{
	
	private String ID;
	private String name;
	private List<UserComponent> members = new LinkedList();
	private String identifer = "group";
	public Group(String name,int x){
		this.name = name;
		this.ID = name + x;
	}
	public void add(UserComponent uc){
		members.add(uc);
	}
	public String getName(){
		return name;
	}
	public String getIdentifier(){
		return identifer;
	}
	public String toString(){
		return name;
	}
}
