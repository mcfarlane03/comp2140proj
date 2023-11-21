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

    public Menu() 
    {
        menu = this;

        panelCommand = new JPanel();
        panelDisplay = new JPanel(); 

        JLabel header = new JLabel("PORT AUTHORITY OF JAMAICA (PAJ)",SwingConstants.CENTER);
        header.setFont(new Font("Georgia", Font.BOLD, 36));
        panelDisplay.add(header);

        // to group crest and the school's name so it appears in one row

        JLabel subHeader = new JLabel("PORT AUTHORITY OF JAMAICA (PAJ)",SwingConstants.LEFT);
        subHeader.setFont(new Font("Georgia", Font.BOLD, 24));
        panelDisplay.add(subHeader);
        
        panelDisplay.setLayout(new GridLayout(1, 2));

        // command buttons
        cmdAdd_Driver = new JButton("Add Driver");
        cmdAdd_Driver.setBackground(Color.cyan);
        panelCommand.add(cmdAdd_Driver);
        cmdDelete_Driver = new JButton("Delete Driver");
        cmdDelete_Driver.setBackground(Color.yellow);
        panelCommand.add(cmdDelete_Driver);
        cmdAdd_Truck = new JButton("Add Truck");
        cmdAdd_Truck.setBackground(Color.orange);
        panelCommand.add(cmdAdd_Truck);
        cmdDelete_Truck = new JButton("Delete Truck");
        cmdDelete_Truck.setBackground(Color.blue);
        panelCommand.add(cmdDelete_Truck);
        cmdAssign_Jobs = new JButton("Assign Jobs");
        cmdAssign_Jobs.setBackground(Color.magenta);
        panelCommand.add(cmdAssign_Jobs);
        cmdEdit_Assigned_Jobs = new JButton("Edit Assigned Jobs");
        cmdEdit_Assigned_Jobs.setBackground(Color.lightGray);
        panelCommand.add(cmdEdit_Assigned_Jobs);
        cmdDelete_Assigned_Jobs = new JButton("Delete Assigned Jobs");
        cmdDelete_Assigned_Jobs.setBackground(Color.cyan);
        panelCommand.add(cmdDelete_Assigned_Jobs);
        cmdReport = new JButton("Driver Report");
        cmdReport.setBackground(Color.yellow);
        panelCommand.add(cmdReport);
        cmdEdit_Admin_Password = new JButton("Edit Admin Password");
        cmdEdit_Admin_Password.setBackground(Color.green);
        panelCommand.add(cmdEdit_Admin_Password);
        cmdAdmin_Logout = new JButton("Admin Logout");
        cmdAdmin_Logout.setBackground(Color.red);
        panelCommand.add(cmdAdmin_Logout);

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

        //panelDisplay.setLayout(new GridLayout(34, 1));
        setPreferredSize(new Dimension(1620, 1080));
        add(panelDisplay, BorderLayout.CENTER);

        // to group the second header and the command buttons so it aligns better on the frame
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(subHeader);    
        subHeader.setAlignmentX(CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalGlue());                                                                                             
        vBox.add(panelCommand);
        vBox.setAlignmentX(CENTER_ALIGNMENT);
         
        add(vBox, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class MenuButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent g) 
        {
            if (g.getSource() == cmdAdd_Driver)
            {
                Add_Driver add_driver = new Add_Driver();
            } 
            else if (g.getSource() == cmdDelete_Driver)
            {
                Delete_Driver delete_driver = new Delete_Driver();
            }
            else if (g.getSource() == cmdAdd_Truck)
            {
                Add_Truck add_truck = new Add_Truck();
            }
            else if (g.getSource() == cmdDelete_Truck)
            {
                Delete_Truck delete_truck = new Delete_Truck();
            }
        }
    }   
}





  





















