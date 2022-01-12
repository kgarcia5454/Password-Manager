import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui implements ActionListener
{	

	private static JMenuBar MenuBar;
	private static JMenu Menu;
	private static JTextField Usertext;
	private static JTextField WebsiteText;
	private static JPasswordField PW_Text;
	private static JLabel Response;
	public static String Code;
	
	//Password stuff can be moved
	private static int Unlocked=0;
	public static String input="Password";
	
	
	private static String[] columns = {"Website","Username","Password"};
	public static DefaultTableModel model = new DefaultTableModel(columns,0);
	
	public static JTable Password_List;
	
	private static EntryWriter PassList = new EntryWriter();
	
	public static void main(String[] args){
		PassList.NewDocument();
		
		JPasswordField pf = new JPasswordField();
		int ok_pressed = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (ok_pressed == JOptionPane.OK_OPTION) {
			String password = new String(pf.getPassword());
			if(input.equals(password)) {
			Unlocked=1;
		}
		System.err.println("You entered: " + password);
		
		}
		
		if(Unlocked==1) {
			JFrame frame = new JFrame();
			JPanel panel = new JPanel();
			
			frame.setSize(850,400);
			frame.setResizable(false);

			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(panel);
			
			panel.setLayout(null);
			
			MenuBar= new JMenuBar();
			MenuBar.setBounds(0, 0, 850, 20);
			panel.add(MenuBar);
			
			Menu = new JMenu("File");
			MenuBar.add(Menu);
			
			JMenuItem m1= new JMenuItem("RESET");
			JMenuItem m2= new JMenuItem("Change App Password");
			
			Menu.add(m1);
			Menu.add(m2);
			
			m1.addActionListener(new gui());
			m2.addActionListener(new gui());
			
			JLabel Title = new JLabel("Password Manager");
			Title.setBounds(70, 21, 300, 90);
			Title.setFont(new Font("Serif", Font.PLAIN, 25));
			panel.add(Title);
			
			
			JLabel label = new JLabel("Website Name");
			label.setBounds(30, 122, 90, 30);
			panel.add(label);
			
			WebsiteText =  new JTextField(15);
			WebsiteText.setBounds(120,125, 165,25);
			panel.add(WebsiteText);
			
			JLabel User_Label = new JLabel("Username");
			User_Label.setBounds(52, 152, 90, 30);
			panel.add(User_Label);
			
			Usertext =  new JTextField(15);
			Usertext.setBounds(120,155, 165,25);
			panel.add(Usertext);
			
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
			
			PassList.init_table();
			PassList.FileReader();
			JScrollPane scroll = new JScrollPane(Password_List);
			Password_List.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
			scroll.setBounds(315,35,500,300);
	
			panel.add(scroll);
			
			frame.setVisible(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		
		if(e.getActionCommand()=="RESET") {
			if(PassList.RESET()==0) {
				PassList.NewDocument();
				PassList.FileReader();
				Response.setText(Code);
				}
		}
		
		if(e.getActionCommand()=="Change App Password") {
			System.out.println("test");
		}
		
					
		if(e.getActionCommand()=="Submit")
		{
			String Website = WebsiteText.getText();
			String Username = Usertext.getText();
			String Password = new String(PW_Text.getPassword());	
			
			if(Website.isEmpty()||Username.isEmpty()||Password.isEmpty()) {
				System.out.println("Blank Field Detected");
				Code="Blank Field Detected";
				Response.setText(Code);
			}else{
				PassList.FileWriter(Website, Username, Password);
				PassList.add_row(Website, Username, Password);
				Response.setText(Code);
			}
		}
	}
	
	
	
	
}