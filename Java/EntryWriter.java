import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;

public class EntryWriter extends gui{
	
	private static String SafePath = "./Passwords.txt";
	File document = new File(SafePath);
	
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
				FileWriter entry = new FileWriter(SafePath,true);
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
	
	//This one presents values to gui
	public void FileReader() {
		try {
			Scanner Reader = new Scanner(document);
			
			
			String Website = "";
			String Username = "";
			String Password = "";
			
			int Entry_Col = 0;
			
			while (Reader.hasNextLine()) {
				String Entry = Reader.nextLine();

				if(Entry.startsWith("Entry")){
					continue;
				}
				
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

		if(Choice==0) {
			if(document.delete()) {
				System.out.println("Deleted");
			}else {
				System.out.println("Not Deleted");
			}
			model.setRowCount(0);
			Code="All Entries Deleted";
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

	public void passwordChange() {
			try{
			Scanner reader = new Scanner(document);
			
			

			LineNumberReader line_num = new LineNumberReader(new FileReader(document));
			String l;
			int password_num = 0; 
			int found = 0;

			String website = JOptionPane.showInputDialog("Which Website?");

			while((l = line_num.readLine()) != null ){
				
				Scanner s = new Scanner(l);
				String key = reader.nextLine();

				if(key.contains(website)){
					System.out.println(line_num.getLineNumber());
					password_num = (line_num.getLineNumber() + 2);
					s.close();
					found = 1;
					break;
				}


			}

			if(found==0){
				JOptionPane.showMessageDialog(null,"No Website found!");

			}else{

				String Password = JOptionPane.showInputDialog("What is the new password?");

				Path path = Paths.get(SafePath);
				List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
				lines.set(password_num-1,Password);
				Files.write(path,lines,StandardCharsets.UTF_8);

				reader.close();
				line_num.close();

			}
		}
			catch(FileNotFoundException e) {
				System.out.println("No File Found");
			}

			catch(IOException e){
				System.out.println("cringe");
			}


		model.setRowCount(0);
		FileReader();

	}
}