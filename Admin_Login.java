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
    private String username, password; 
    private JTextField txtUsername, txtPassword;
    private JButton cmdCancel;
    private JButton cmdSubmit;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private Admin_Login admin_login;

    public Admin_Login() 
    {
        admin_login = this;

        setTitle("PAJ: ADMIN LOGIN");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        // to display Application Header
        JLabel apHeader = new JLabel("PAJ: ADMIN LOGIN");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        pnlDisplay.add(apHeader);

        pnlDisplay.add(new JLabel("Username:"));
        txtUsername = new JTextField(30);
        pnlDisplay.add(txtUsername);

        pnlDisplay.add(new JLabel("Password:"));
        txtPassword = new JTextField(10);
        pnlDisplay.add(txtPassword);

        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(Color.green);
        pnlCommand.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setBackground(Color.red);
        pnlCommand.add(cmdCancel);

        pnlDisplay.setLayout(new GridLayout(34, 1));
        setPreferredSize(new Dimension(1620, 1080));

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
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
                if ((username.equals("PAJ")) && (password.equals("PAJADMIN")))
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
        }
    }
}
