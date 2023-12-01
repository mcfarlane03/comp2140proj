import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.table.DefaultTableModel;

import src.Driver;


public class DriverListing extends JFrame {

    
    private JButton sortButton;
    private JButton closeButton;
    private JButton addButton;
    private JTable table;

    private DefaultTableModel model;    //table model   
    protected ArrayList<Driver> driverList;
     
    //constructor
    public DriverListing() {

        setTitle("Driver Listing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        
        
        driverList = loadDrivers("resources//Drivers.txt");
        String[] columns = {"Full Name", "Gender", "Telephone", 
                            "Email", "License Type", "Age", "ID",
                            "License Class"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        showTable(driverList);

        sortButton = new JButton("Sort");
        closeButton = new JButton("Close");
        addButton = new JButton("Add Driver");
        


        closeButton.addActionListener(new closeButtonListener());
        sortButton.addActionListener(new sortButtonListener());
        addButton.addActionListener(new addButtonListener());

        JPanel buttonPanel = new JPanel();
        JPanel titlePanel = new JPanel();

        //sets the layout of the button panel and adds the buttons
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(closeButton);

        //sets the layout of the title panel and adds the title
        add(titlePanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    //method for showing the drivers in the table
    void showTable(ArrayList<Driver> driverList) {
        model.setRowCount(0);
        for (Driver driver : driverList) {
            addToTable(driver);
        }
    }

    //method for adding the drivers to the table
    public void addToTable(Driver driver) {
        String[] items = {  driver.getFname() + " "+ driver.getSname(),
                            driver.getGender(), driver.getTphone(), driver.getEmail(),
                            driver.getLtype(), Integer.toString(driver.getAge()),
                            Integer.toString(driver.getID()),Integer.toString(driver.getLclass())
                        };
        model.addRow(items);
    }


    //method for loading the drivers from the text file
    private ArrayList<Driver> loadDrivers(String dfile){
        Scanner dscan = null;
        ArrayList<Driver> drivers = new ArrayList<Driver>();

        try{
            dscan = new Scanner(new File(dfile));
            while (dscan.hasNext()) 
            {
                String [] nextLine = dscan.nextLine().split(",");
                String fname = nextLine[0];
                String lname = nextLine[1];
                String gender = nextLine[2];
                String tphone = nextLine[3];
                String email = nextLine[4];
                String ltype = nextLine[5];
                int age = Integer.parseInt(nextLine[6]);
                int ID = Integer.parseInt(nextLine[7]);
                int lclass = Integer.parseInt(nextLine[8]);

                Driver d = new Driver(fname, lname, gender, tphone, email, ltype, age, ID, lclass);
                drivers.add(d);

            }

            dscan.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File not found");
        }

        return drivers;
    }

    //method for sorting the drivers by surname
    private class sortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            //sorts the drivers by surname
            Collections.sort(driverList, new Comparator<Driver>() {
                public int compare(Driver d1, Driver d2) {
                    return d1.getSname().toUpperCase().compareTo(d2.getSname().toUpperCase()); //converts to uppercase to avoid case sensitivity
                }
            });
            //reload the table
            showTable(driverList);
        }
    }

    //method for adding a driver
    private class addButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            // new Add_Driver(DriverListing.this);


        }
    }

    //closes window upon clicking the close button
    private class closeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    //for debugging
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DriverListing frame = new DriverListing();
            frame.setVisible(true);
        });
    }
}
