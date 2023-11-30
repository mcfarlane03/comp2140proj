import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;




public class Edit_Admin_Password extends JFrame{
    private JPanel panel;
    private JLabel lblOldPassword, lblNewPassword, lblConfirmPassword;
    private JTextField txtOldPassword, txtNewPassword, txtConfirmPassword;
    private JButton cmdSubmit, cmdCancel;
    private String oldPassword, newPassword, confirmPassword;
    private String[] adminInfo;

    public Edit_Admin_Password(){
        setTitle("Edit Admin Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        adminInfo = loadAdminInfo("Admin.txt");

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        lblOldPassword = new JLabel("Old Password:");
        panel.add(lblOldPassword);

        txtOldPassword = new JPasswordField();
        panel.add(txtOldPassword);

        lblNewPassword = new JLabel("New Password:");
        panel.add(lblNewPassword);

        txtNewPassword = new JPasswordField();
        panel.add(txtNewPassword);

        lblConfirmPassword = new JLabel("Confirm Password:");
        panel.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        panel.add(txtConfirmPassword);

        cmdSubmit = new JButton("Submit");
        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel = new JButton("Cancel");
        cmdCancel.addActionListener(new CancelButtonListener());

        panel.add(cmdSubmit);
        panel.add(cmdCancel);

        add(panel);
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            oldPassword = txtOldPassword.getText();
            newPassword = txtNewPassword.getText();
            confirmPassword = txtConfirmPassword.getText();

            if(oldPassword.equals(adminInfo[1]) && newPassword.equals(confirmPassword)){
                try{
                    PrintStream output = new PrintStream(new FileOutputStream("Admin.txt"));
                    output.println(newPassword);
                    output.close();
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
            Menu menu = new Menu();
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

    private void editAdminPassword(String filename, String newPassword){
        try{
            FileWriter writer = new FileWriter(filename);
            writer.write(newPassword);
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
