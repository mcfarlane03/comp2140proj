package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;




public class Edit_Admin_Password extends JFrame{
  
    private JLabel lblOldPassword, lblNewPassword, lblConfirmPassword;
    private JTextField txtOldPassword, txtNewPassword, txtConfirmPassword;
    private JButton cmdSubmit, cmdCancel;
    private String oldPassword, newPassword, confirmPassword;
    private String[] adminInfo;
    private Container c;
    private JPanel logoPanel = new JPanel();

    public Edit_Admin_Password(){
        setTitle("Edit Admin Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(300, 90, 800, 500);

        adminInfo = loadAdminInfo("resources//Admin.txt");

        c = getContentPane();
        c.setLayout(null);

        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        logoPanel.setBackground(new Color(0xa7dbd8));
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        logoPanel.add(label);
        JLabel apHeader = new JLabel("EDIT ADMIN PASSWORD");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        apHeader.setSize(300, 30);
        apHeader.setLocation(260, 100);
        c.add(apHeader);
        logoPanel.setSize(375, 100);
        logoPanel.setLocation(200, 10);
        c.add(logoPanel);
        
        lblOldPassword = new JLabel("Old Password:");
        lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        lblOldPassword.setSize(140, 20);
        lblOldPassword.setLocation(100, 150);
        c.add(lblOldPassword);

        txtOldPassword = new JPasswordField();
        txtOldPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtOldPassword.setSize(400, 30);
        txtOldPassword.setLocation(275, 150);
        c.add(txtOldPassword);

        lblNewPassword = new JLabel("New Password:");
        lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewPassword.setSize(150, 20);
        lblNewPassword.setLocation(100, 200);
        c.add(lblNewPassword);


        txtNewPassword = new JTextField();
        txtNewPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNewPassword.setSize(400, 30);
        txtNewPassword.setLocation(275, 200);
        c.add(txtNewPassword);

        lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        lblConfirmPassword.setSize(190, 20);
        lblConfirmPassword.setLocation(100, 250);
        c.add(lblConfirmPassword);
      
        txtConfirmPassword = new JTextField();
        txtConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtConfirmPassword.setSize(400, 30);
        txtConfirmPassword.setLocation(275, 250);
        c.add(txtConfirmPassword);
    
        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(new Color(0xf38630));
        cmdSubmit.setSize(100, 30);
        cmdSubmit.setLocation(250, 350);
        cmdSubmit.addActionListener(new SubmitButtonListener());
        c.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setSize(100, 30);
        cmdCancel.setLocation(400, 350);
        cmdCancel.setBackground(Color.red);
        cmdCancel.addActionListener(new CancelButtonListener());
        c.add(cmdCancel);

        c.add(cmdSubmit);
        c.add(cmdCancel);
        c.setBackground(new Color(0xa7dbd8));

        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            oldPassword = txtOldPassword.getText();
            newPassword = txtNewPassword.getText();
            confirmPassword = txtConfirmPassword.getText();

            if(oldPassword.equals(adminInfo[1]) && newPassword.equals(confirmPassword)){
                try{
                    FileWriter writer = new FileWriter("resources//Admin.txt");    
                    writer.write(adminInfo[0] + "," + newPassword.toUpperCase());
                    writer.close();
                }
                catch(Exception ex){
                    System.out.println("Error: " + ex.getMessage());
                }
                dispose();
                Menu menu = new Menu();
            }
            else{
                System.out.println("Error: Passwords do not match");
            }
        }
    }

    private class CancelButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            new Menu();
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

    private void editAdminPassword(String filename, String newPassword, String[] adminInfo){
        try{
            FileWriter writer = new FileWriter(filename);
            writer.write(adminInfo[0] + "," + newPassword);
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args){
         new Edit_Admin_Password();
    }
}
