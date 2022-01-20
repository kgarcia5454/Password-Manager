import javax.swing.*;

public class PasswordManager{

    private static JPasswordField pf = new JPasswordField();
    private static Object [] options  = {"Submit", "Close"};
    private static int locked = 1;
    private static String title = "Enter Password";
    private static String pass = "Test";

    public static void main(String[] args){
        while(locked == 1){
            int okPressed = JOptionPane.showOptionDialog(null, pf, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);

            if (okPressed == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if(pass.equals(password)) {
                    locked = 0 ;
                    gui.run();

                    //run class
                }else{
                    title = "Wrong Password";
                }
            }

            if(okPressed == 1 || okPressed == -1){
                locked = 0;
            }
        }  
    }

}