//Interface to link User and Group (Composite Pattern)
public interface UserComponent {
	public void add(UserComponent uc);

	public String getName();
	public String getIdentifier();
	public String toString();
	public String getID();
	public long getCreationTime();
	public boolean check();
}
