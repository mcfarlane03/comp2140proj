package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Flow;
import java.io.File;

public class Menu extends JFrame 
{
    private JButton cmdAdd_Driver;
    private JButton cmdDelete_Driver;
    private JButton cmdAdd_Truck;
    private JButton cmdDelete_Truck;
    private JButton cmdAssign_Jobs;
    private JButton cmdEdit_Assigned_Jobs;
    private JButton cmdDelete_Assigned_Jobs;
    private JButton cmdReport;
    private JButton cmdEdit_Admin_Password;
    private JButton cmdAdmin_Logout;
    private JPanel panelCommand;
    private JPanel panelDisplay;
    private Menu menu;
    private File imageFile; 

    public Menu() 
    {
        super("Main Menu (Admin)");
        menu = this;

        panelCommand = new JPanel();
        panelDisplay = new JPanel(); 
        panelDisplay.setLayout(new GridLayout(1, 2));
        // panelCommand.setLayout(new GridLayout(34, 25));
        panelCommand.setBounds(300, 90, 350,100);
        panelDisplay.setSize(400,100);
        panelCommand.setBackground(new Color(0x79b5ac));
        panelDisplay.setBackground(new Color(0xa7dbd8));
        

      
        // GridLayout grid = new GridLayout(4, 3, 10, 10);
        // panelCommand.setLayout(grid);

        JLabel header = new JLabel("MAIN MENU",SwingConstants.CENTER);
        header.setFont(new Font("Georgia", Font.BOLD, 25));
        panelDisplay.add(header);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        panelDisplay.add(label);
        
        // to group crest and the school's name so it appears in one row

        // JLabel subHeader = new JLabel("PORT AUTHORITY OF JAMAICA (PAJ)",SwingConstants.LEFT);
        // subHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        // panelDisplay.add(subHeader);
        
        panelDisplay.setLayout(new GridLayout(2, 2));

        // command buttons
        cmdAdd_Driver = new JButton("Add Driver");
        cmdAdd_Driver.setBackground(new Color(0xf38630));
        panelCommand.add(cmdAdd_Driver);
        cmdDelete_Driver = new JButton("Delete Driver");
        cmdDelete_Driver.setBackground(new Color(0xf38630));
        panelCommand.add(cmdDelete_Driver);
        cmdAdd_Truck = new JButton("Add Truck");
        cmdAdd_Truck.setBackground(new Color(0xf38630));
        panelCommand.add(cmdAdd_Truck);
        cmdDelete_Truck = new JButton("Delete Truck");
        cmdDelete_Truck.setBackground(new Color(0xf38630));
        panelCommand.add(cmdDelete_Truck);
        cmdAssign_Jobs = new JButton("Assign Jobs");
        cmdAssign_Jobs.setBackground(new Color(0xf38630));
        panelCommand.add(cmdAssign_Jobs);
        cmdEdit_Assigned_Jobs = new JButton("Edit Assigned Jobs");
        cmdEdit_Assigned_Jobs.setBackground(new Color(0xf38630));
        panelCommand.add(cmdEdit_Assigned_Jobs);
        cmdDelete_Assigned_Jobs = new JButton("Delete Assigned Jobs");
        cmdDelete_Assigned_Jobs.setBackground(new Color(0xf38630));
        panelCommand.add(cmdDelete_Assigned_Jobs);
        cmdReport = new JButton("Driver Report");
        cmdReport.setBackground(new Color(0xf38630));
        panelCommand.add(cmdReport);
        cmdEdit_Admin_Password = new JButton("Edit Admin Password");
        cmdEdit_Admin_Password.setBackground(new Color(0xf38630));
        panelCommand.add(cmdEdit_Admin_Password);
        cmdAdmin_Logout = new JButton("Admin Logout");
        cmdAdmin_Logout.setBackground(Color.red);
       

        cmdAdd_Driver.addActionListener(new MenuButtonListener());
        cmdDelete_Driver.addActionListener(new MenuButtonListener());
        cmdAdd_Truck.addActionListener(new MenuButtonListener());
        cmdDelete_Truck.addActionListener(new MenuButtonListener());
        cmdAssign_Jobs.addActionListener(new MenuButtonListener());
        cmdEdit_Assigned_Jobs.addActionListener(new MenuButtonListener());
        cmdDelete_Assigned_Jobs.addActionListener(new MenuButtonListener());
        cmdReport.addActionListener(new MenuButtonListener());
        cmdEdit_Admin_Password.addActionListener(new MenuButtonListener());
        cmdAdmin_Logout.addActionListener(new MenuButtonListener());

        // panelCommand.setLayout(new GridLayout(34, 1));

        // add(subHeader, BorderLayout.NORTH);
        panelCommand.setLayout(new GridLayout(3, 3, 10, 10));
        add(panelCommand, BorderLayout.CENTER);
        add(panelDisplay, BorderLayout.NORTH);
        JPanel LEFTpanel = new JPanel();
        LEFTpanel.setBackground(new Color(0xa7dbd8));
        add(LEFTpanel, BorderLayout.WEST);
        JPanel RIGHTpanel = new JPanel();
        RIGHTpanel.setBackground(new Color(0xa7dbd8));
        add(RIGHTpanel, BorderLayout.EAST);
        JPanel BOTTOMpanel = new JPanel();
        BOTTOMpanel.setBackground(new Color(0xa7dbd8));
        BOTTOMpanel.add(cmdAdmin_Logout);
        add(BOTTOMpanel, BorderLayout.SOUTH);

       
        // to group the second header and the command buttons so it aligns better on the frame
        // Box vBox = Box.createVerticalBox();
        // vBox.add(Box.createVerticalGlue());
        // vBox.add(subHeader);    
        // subHeader.setAlignmentX(CENTER_ALIGNMENT);
        // vBox.add(Box.createVerticalGlue());                                                                                             
        // vBox.add(panelCommand);
        // vBox.setAlignmentX(CENTER_ALIGNMENT);
         
        // add(vBox, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class MenuButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent g) 
        {
            if (g.getSource() == cmdAdd_Driver)
            {
                new Add_Driver();
                dispose();
            } 
            else if (g.getSource() == cmdDelete_Driver)
            {
                new Delete_Driver();
                dispose();
            }
            else if(g.getSource() == cmdAdd_Truck)
            {
                new Add_Truck();
                dispose();
            }
            else if (g.getSource() == cmdDelete_Truck)
            {
                new Delete_Truck();
                dispose();
            }
            else if (g.getSource() == cmdAssign_Jobs)
            {
                new Assign_Jobs();
                dispose();
            }
            else if (g.getSource() == cmdEdit_Assigned_Jobs)
            {
                new Edit_Assigned_Jobs();
                dispose();
            }
            else if (g.getSource() == cmdDelete_Assigned_Jobs)
            {
                new Delete_Assigned_Jobs();
                dispose();
            }
            else if (g.getSource() == cmdReport){
                JOptionPane.showMessageDialog(null, "Not yet implemented");
            }
            else if (g.getSource() == cmdEdit_Admin_Password){
                new Edit_Admin_Password();
                dispose();
            }
            else if (g.getSource() == cmdAdmin_Logout){
                dispose();
                new Application();
            }
        }

    }   
    public static void main(String[] args) 
    {
        Menu menu = new Menu();
    }
}





  





















