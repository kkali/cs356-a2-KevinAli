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
	private long idx;
	public Group(String name){
		this.name = name;
		this.idx = System.currentTimeMillis();
		this.ID = name + idx%1000;
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
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return idx;
	}
	public boolean check(){
	    if(ID != null){
	        for(int i = 0; i < ID.length(); i++){
	            if(Character.isWhitespace(ID.charAt(i))){
	                return true;
	            }
	        }
	    }
	    return false;
	}
	public String getID() {
		return ID;
	}

}
