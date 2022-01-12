import java.io.*;
import java.util.*;
import javax.swing.*;

public class EntryWriter extends gui{
	
	File document = new File("C:\\Users\\Kevin\\Desktop\\Test\\Passwords.txt");
	
	public void NewDocument() {
				
		try {
			if(document.createNewFile()) {
				System.err.println("Wrote File");
			}else {
				System.err.println("File Exists");
			}	
		} catch(IOException e) {
			System.out.print("Error Detected");
		}
	}
	
	public void FileWriter(String Web,String User, String Pass) {
		try{	
				FileWriter entry = new FileWriter("C:\\\\Users\\\\Kevin\\\\Desktop\\\\Test\\\\Passwords.txt",true);
				entry.write(Web+"\n");
				entry.write(User+"\n");
				entry.write(Pass+"\n");
				entry.close();
				System.out.println("Entry Written");
				Code="Entry Written";
			}
		catch(IOException e) {
			System.out.print("Error Detected");
		}
	}
	
	public void FileReader() {
		try {
			Scanner Reader = new Scanner(document);
			
			String Website = "";
			String Username = "";
			String Password = "";
			
			int Entry_Col = 0;
			
			while (Reader.hasNextLine()) {
				String Entry = Reader.nextLine();
				if(Entry_Col==0) {
					Website = Entry;
					Entry_Col=1;
				}else if(Entry_Col==1) {
					Username = Entry;
					Entry_Col=2;
				}else if(Entry_Col==2) {
					Password = Entry;
					String[] Previous = {Website,Username,Password};
					model.addRow(Previous);
					Entry_Col=0;
				}
			}
			Reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("No File Found");
		}
	}
	
	public int RESET() {
		Object[] options = {"Yes","No"};
		
		int Choice = JOptionPane.showOptionDialog(null,"Are you sure you want to delete all entries?",
				"Delete all entries?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
				null,options,options[1]);
		;
		if(Choice==0) {
			if(document.delete()) {
				System.out.println("Deleted");
			}else {
				System.out.println("Not Deleted");
			}
			model.setRowCount(0);
			Code="Reset All Entries";
		}
		return Choice;
	}
	// Table Class
	public void init_table()
	{
		Password_List.getTableHeader().setReorderingAllowed(false);
	}
	
	public void add_row(String Web,String User, String Pass) 
	{
		String [] NewEntry= { Web,User,Pass};
		model.addRow(NewEntry);
		Code="Entry Written";
	}
}