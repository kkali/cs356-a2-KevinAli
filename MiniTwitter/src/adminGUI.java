import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
//This is the adminGUI, thie class will create the admin GUI, the admin GUI used the Singeton Design.
//There are 4 other functions in this class which help the functionality of the class it self(not including initComponets, getInstance)
//There are 2 getters which retrieve the user and group array list which will be used in the userGUI class
//there are also 2 add functions that are used in the admin  to add groups and users to the Jtree
//The way i created a pseudo unique IDs is every new user,i append an integer to the end of the name which will be its ID, same with group.

public class adminGUI extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField group;
	private JButton addUser;
	private JButton addGroup;
	private JButton viewUser;
	private JButton userTotal;
	private JButton totalGroup;
	private JButton totalMessage;
	private JButton percentage;
	private JTree tree;
	private static adminGUI instance = null;
	private JScrollPane scrollPane;
	public List<Users> allUser = new ArrayList<Users>();
	public List<Group> allGroup = new ArrayList<Group>();
	//Note using a HashMap would better 
	private int userC = 0;//used to create pseudo unique ID
	private int groupC = 0;//used to create psedo unique ID
	

	private adminGUI() {
		initComponents();
	}

	public static adminGUI getInstance() {
		if (instance == null) {
			instance = new adminGUI();
		}
		return instance;
	}

	private void initComponents() {
		setType(Type.UTILITY);
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		user = new JTextField();
		user.setBounds(10, 11, 86, 20);
		contentPane.add(user);
		user.setColumns(10);

		group = new JTextField();
		group.setBounds(10, 35, 86, 20);
		contentPane.add(group);
		group.setColumns(10);

		addUser = new JButton("Add User");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addUSER();
			}
		});
		addUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addUser.setBounds(106, 10, 89, 23);
		contentPane.add(addUser);

		addGroup = new JButton("Add Group");
		addGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addGROUP();
			}
		});
		addGroup.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addGroup.setBounds(106, 34, 89, 23);
		contentPane.add(addGroup);

		viewUser = new JButton("View User Panel");
		viewUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		// THIS PART OF THE CODE WILL LISTEN TO THE BUTTON, and open upa userGUI
		// for that user
		viewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				UserComponent uc = (UserComponent) selectedNode.getUserObject();
				if (uc.getIdentifier().compareTo("user") == 0) {

				}
				((Users) uc).getGui().setVisible(true);

			}
		});
		viewUser.setBounds(7, 65, 188, 23);
		contentPane.add(viewUser);

		userTotal = new JButton("Total user");
		userTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(),
						userC+" Users Registered");
			}
		});
		userTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		userTotal.setBounds(10, 171, 96, 29);
		contentPane.add(userTotal);

		totalGroup = new JButton("Group Total");
		totalGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(),
						groupC+" Registered");}}
		);
		totalGroup.setFont(new Font("Tahoma", Font.PLAIN, 10));
		totalGroup.setBounds(116, 171, 96, 29);
		contentPane.add(totalGroup);

		totalMessage = new JButton("Total message");
		totalMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int temp=0;
				for(int i=0; i<allUser.size();i++){
					temp += allUser.get(i).getPMR().size();
				}
				JOptionPane.showMessageDialog(new JFrame(),
						temp+" message sent");
				
			}
		});
		totalMessage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		totalMessage.setBounds(10, 210, 96, 29);
		contentPane.add(totalMessage);

		percentage = new JButton("Percentage");
		percentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double c=0;
				double max=0;
				for(int i=0; i<allUser.size();i++){
					List<String> temp =allUser.get(i).getPMR();
					max += allUser.get(i).getPMR().size();
					for (int j=0;j<temp.size();j++){
						String s =temp.get(j);
						if(s.contains("good")||s.contains("excellent")||s.contains("great")){
							c++;
						}
					}
				}
				DecimalFormat df = new DecimalFormat("#.##");
				c=((c/max)*100);
				JOptionPane.showMessageDialog(new JFrame(),
						df.format(c) +" is the percantage of positive tweets");
				
			}
		});
		percentage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		percentage.setBounds(116, 210, 96, 29);
		contentPane.add(percentage);

		tree = new JTree();
		Group root = new Group("Root", 0);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(root);
		temp.setUserObject(root);
		tree.setModel(new DefaultTreeModel(temp));
		tree.setBounds(253, 13, 205, 220);
		contentPane.add(tree);

		scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(253, 11, 205, 220);
		contentPane.add(scrollPane);
	}
	//get userList
	public List<Users> getUList() {
		return allUser;
	}
	//get Group List
	public List<Group> getGLISt() {
		return allGroup;
	}

	// Will Add a group to the tree being displayed in the JTREE, if a node is
	// selected and it is not a user it will create a pseude group which will
	// allow children to be put into the group unlike users
	private void addGROUP() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		UserComponent uc = new Group(group.getText(), groupC);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(uc);
		temp.setUserObject(uc);
		Group gg;

		if (selectedNode == null || selectedNode.isRoot()) {
			selectedNode = (DefaultMutableTreeNode) model.getRoot();
			if (!group.getText().trim().equals("")) {
				selectedNode.add(temp);
				model.reload();
			}
		} else {
			if (!group.getText().trim().equals("")) {
				model.insertNodeInto(temp, selectedNode,
						selectedNode.getChildCount());
				model.reload();
			}
		}
		gg = (Group) selectedNode.getUserObject();
		allGroup.add((Group) uc);
		groupC++;
		gg.add(uc);

	}

	// Will add a user to the tree if nothing is selected default is node
	//the user will not allow another user to be added as a child
	private void addUSER() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		UserComponent uc = new Users(user.getText(), userC);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(uc);
		temp.setAllowsChildren(false);
		temp.setUserObject(uc);
		UserGUI g = new UserGUI((Users) uc);
		((Users) uc).setGUI(g);
		Group gg;

		if (selectedNode == null || selectedNode.isRoot()) {

			selectedNode = (DefaultMutableTreeNode) model.getRoot();
			if (!user.getText().trim().equals("")) {
				selectedNode.add(temp);
				model.reload();
			}
		} else {
			if (!user.getText().trim().equals("")) {
				model.insertNodeInto(temp, selectedNode,
						selectedNode.getChildCount());
				model.reload();
			}
		}
		gg = (Group) selectedNode.getUserObject();
		allUser.add((Users) uc);
		userC++;
		gg.add(uc);

	}
}
