import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui implements ActionListener{	
	
	private static JTextField UserText;
	private static JTextField WebsiteText;
	private static JPasswordField PW_Text;
	private static JLabel Response;
	public static String Code;
	
	private static String[] columns = {"Website","Username","Password"};
	public static DefaultTableModel model = new DefaultTableModel(columns,0);
	
	public static JTable Password_List;

	private static EntryWriter passlist = new EntryWriter();
	
	public static void run(){
		
		passlist.NewDocument();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(850,400);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JMenuBar menubar= new JMenuBar();
		menubar.setBounds(0, 0, 850, 20);
		panel.add(menubar);
		
		JMenu menu = new JMenu("File");
		menubar.add(menu);
		
		JMenuItem m1= new JMenuItem("RESET");
		JMenuItem m2= new JMenuItem("Change Saved Password");
		
		menu.add(m1);
		menu.add(m2);
		
		m1.addActionListener(new gui());
		m2.addActionListener(new gui());
		
		JLabel title = new JLabel("Password Manager");
		title.setBounds(70, 21, 300, 90);
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		panel.add(title);
		
		
		JLabel label = new JLabel("Website Name");
		label.setBounds(30, 122, 90, 30);
		panel.add(label);
		
		WebsiteText =  new JTextField(15);
		WebsiteText.setBounds(120,125, 165,25);
		panel.add(WebsiteText);
		
		JLabel User_Label = new JLabel("Username");
		User_Label.setBounds(52, 152, 90, 30);
		panel.add(User_Label);
		
		UserText =  new JTextField(15);
		UserText.setBounds(120,155, 165,25);
		panel.add(UserText);
		
		JLabel PW_Label = new JLabel("Password");
		PW_Label.setBounds(52, 182, 90, 30);
		panel.add(PW_Label);
		
		PW_Text =  new JPasswordField(50);
		PW_Text.setBounds(120,185, 165,25);
		panel.add(PW_Text);
		
		JButton Submit = new JButton("Submit");
		Submit.setBounds(120, 225, 90, 30);
		Submit.addActionListener(new gui());
		panel.add(Submit);
		
		Response = new JLabel();
		Response.setBounds(110,240,170,90);
		panel.add(Response);
		
		Password_List = new JTable(model) {
			private static final long serialVersionUID=1L;
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		
		passlist.init_table();
		passlist.FileReader();
		JScrollPane scroll = new JScrollPane(Password_List);
		Password_List.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		scroll.setBounds(315,35,500,300);

		panel.add(scroll);
		
		frame.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("RESET") && passlist.RESET()==0){

			passlist.NewDocument();
			passlist.FileReader();
			Response.setText(Code);

		}
		
		if(e.getActionCommand().equals("Change Saved Password")) {
			//Test
			passlist.passwordChange();
			
		}

		if(e.getActionCommand().equals("Submit"))
		{
			String website = WebsiteText.getText();
			String username = UserText.getText();
			String password = new String(PW_Text.getPassword());	
			
			if(website.isEmpty()||username.isEmpty()||password.isEmpty()) {
				Response.setText("Blank Field Detected");
			}else{
				passlist.FileWriter(website, username, password);
				passlist.add_row(website, username, password);
				Response.setText(Code);
			}
		}
	}	
}