package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Admin_Login extends JFrame 
{
    private Container c;  
    private String username, password; 
    private JTextField txtUsername, txtPassword;
    private JButton cmdCancel;
    private JButton cmdSubmit;
   
    private Admin_Login admin_login;
    private JPanel logoPanel;
    private String[] adminInfo;

    public Admin_Login() 
    {
        super("ADMINSTRATOR LOG-IN");
        admin_login = this;
        c = getContentPane();
        c.setLayout(null);
        
        logoPanel = new JPanel();
        adminInfo = loadAdminInfo("resources//Admin.txt");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setLayout(new GridLayout(3, 1));
        setSize(400, 300);
        setBounds(300, 90, 800, 500);

       
        logoPanel.setBackground(new Color(0xa7dbd8));
      
        logoPanel.setPreferredSize(new Dimension(400, 100));
        
        // // to display Application Header
        // JLabel apHeader = new JLabel("PAJ: ADMIN LOGIN");
        // apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        // pnlDisplay.add(apHeader);
      
        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        logoPanel.setBackground(new Color(0xa7dbd8));
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        label.setSize(250, 75);
        label.setLocation(250, 35);
        c.add(label);
      
   
        JLabel adminName = new JLabel("Username:");
        adminName.setFont(new Font("Arial", Font.PLAIN, 20));
        adminName.setSize(100, 20);
        adminName.setLocation(150, 150);
        c.add(adminName);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUsername.setSize(400, 30);
        txtUsername.setLocation(250, 150);
        c.add(txtUsername);
        
        c.setBackground(new Color(0xa7dbd8));

        JLabel greetingLabel = new JLabel("Good Day Admin, Please Enter Your Username and Password to Continue");
        greetingLabel.setFont(new Font("Georgia", Font.ITALIC, 14));
        greetingLabel.setSize(600, 20);
        greetingLabel.setLocation(150, 120);
        c.add(greetingLabel);

        
        JLabel adminPassword = new JLabel("Password:");
        adminPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        adminPassword.setSize(100, 20);
        adminPassword.setLocation(150, 200);
        c.add(adminPassword);
 
        txtPassword = new JPasswordField(100);
        txtPassword.setSize(400, 30);
        txtPassword.setLocation(250, 200);
        c.add(txtPassword);

      

        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(new Color(0xf38630));
        cmdSubmit.setSize(100, 30);
        cmdSubmit.setLocation(250, 350);
        c.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setSize(100, 30);
        cmdCancel.setLocation(400, 350);
        cmdCancel.setBackground(Color.red);
        c.add(cmdCancel);

       
        // pnlDisplay.setLayout(new GridLayout(34, 1));
        // setPreferredSize(new Dimension(1620, 1080));
        
        getContentPane().add(logoPanel);
       
        
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }

    private class SubmitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent actev)
        {
            boolean noerror = false;
            try 
            {
                username = txtUsername.getText();
                password = txtPassword.getText();
                if ((username.equals(adminInfo[0])) && (password.equals(adminInfo[1])))
                {
                    noerror = true;
                }
            } catch (NumberFormatException numformerror) 
            {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) 
            {
                System.out.println(indexouterror.getMessage());
            } finally 
            {
                if (noerror == true)
                {
                    admin_login.setVisible(false);
                    Menu menu = new Menu();
                }
                else
                {
                    JOptionPane
                    .showMessageDialog(null,
                            "Your username or password is incorrect.",
                            "Try again!", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            admin_login.setVisible(false);
            Application app = new Application();
        }
    }


    private String[] loadAdminInfo(String filename) 
    {
        String[] adminInfo = new String[2];
        try 
        {
            Scanner input = new Scanner(new File(filename));
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] info = line.split(",");
                adminInfo[0] = info[0];
                adminInfo[1] = info[1];
            }
            input.close();
        } catch (FileNotFoundException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        return adminInfo;
    }

    public static void main(String[] args) 
    {
        Admin_Login admin_login = new Admin_Login();
    }
}
