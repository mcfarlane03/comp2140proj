package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class Assign_Jobs extends JFrame {
    private JTextField jobdescField, loadField, destinationField, departureTimeField, returnTimeField;
    private JButton closeButton, submitButton;
    private JComboBox<String> driverComboBox;
    private JComboBox<String> truckComboBox;

    private ArrayList<Job> jobList;
    private ArrayList<Driver> driverList;
    private ArrayList<Truck> truckList;
    private Driver driver;
    private Truck truck;

    public Assign_Jobs() {
        setTitle("Assign Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 90, 900, 600);
        setLocationRelativeTo(null);

        
        driverList = loadDrivers("resources//Drivers.txt");
        truckList = loadTrucks("resources//Trucks.txt");
        jobList = loadJobs("resources//Jobs.txt");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10));

        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridLayout(2,1));
        logoPanel.add(label);
        logoPanel.setSize(375, 100);
        logoPanel.setLocation(200, 50);
        logoPanel.setBackground(new Color(0xa7dbd8));
        add(logoPanel, BorderLayout.NORTH);


        JLabel subHeader2 = new JLabel("Assign Job",SwingConstants.CENTER);
        subHeader2.setFont(new Font("Georgia", Font.BOLD, 18));
        subHeader2.setForeground(Color.black);
        logoPanel.add(subHeader2, BorderLayout.NORTH);

        JLabel jobdescLabel = new JLabel("Job Description:");
        jobdescField = new JTextField();
        panel.add(jobdescLabel);
        panel.add(jobdescField);

        JLabel driverLabel = new JLabel("Driver:");
        driverComboBox = new JComboBox<>();
        driverComboBox.addItem("Select a Driver");
        for (Driver driver : driverList) {
            driverComboBox.addItem(driver.getFname() + " " + driver.getSname() + " (ID: " + driver.getID() + ")");
        }
        panel.add(driverLabel);
        panel.add(driverComboBox);

        JLabel truckLabel = new JLabel("Truck:");
        truckComboBox = new JComboBox<>();
        truckComboBox.addItem("Select a Truck");
        for (Truck truck : truckList) {
            truckComboBox.addItem(truck.getTruckdesc() + " "+ truck.getTtype() +" (ID: " + truck.getID() + ")");
        }
        panel.add(truckLabel);
        panel.add(truckComboBox);
        

        JLabel loadLabel = new JLabel("Load:");
        loadField = new JTextField();
        panel.add(loadLabel);
        panel.add(loadField);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();
        panel.add(destinationLabel);
        panel.add(destinationField);

        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureTimeField = new JTextField();
        panel.add(departureTimeLabel);
        panel.add(departureTimeField);

        JLabel returnTimeLabel = new JLabel("Return Time:");
        returnTimeField = new JTextField();
        panel.add(returnTimeLabel);
        panel.add(returnTimeField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        submitButton.setBackground(new Color(0xf38630));
        buttonPanel.add(submitButton);

        closeButton = new JButton("Close");
        closeButton.addActionListener(new CloseButtonListener());
        buttonPanel.add(closeButton);
        closeButton.setBackground(Color.red);
        setBackground(new Color(0xa7dbd8));
        buttonPanel.setBackground(new Color(0xa7dbd8));
        panel.setBackground(new Color(0xa7dbd8));
        add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addJob() {
        String jobdesc = jobdescField.getText();
        int driverId = Integer.parseInt(driverComboBox.getSelectedItem().toString().split(" ")[3].replace(")", ""));
        String truckId = truckComboBox.getSelectedItem().toString().split(" ")[3].replace(")", "");
        int load = Integer.parseInt(loadField.getText());
        String destination = destinationField.getText();
        String departureTime = departureTimeField.getText();
        String returnTime = returnTimeField.getText();


        // Data Validation
        if (jobdesc.equals("")) { 
            JOptionPane.showMessageDialog(null, "Please enter a Job Description."); 
        }

        else if (driverId == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a Driver ID.");
        }

        else if (truckId.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a Truck ID.");
        }

        else if (load == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a Load.");
        }

        else if (destination.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a Destination.");
        }

        else if (departureTime.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a Departure Time.");
        }

        else if (returnTime.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a Return Time.");
        }
        
        // If all data is valid, add the job to the file
        else {
            driver = findDriver(driverId);
            truck = findTruck(truckId);
            Job job = new Job(jobdesc, driver, truck, load, destination, departureTime, returnTime);


            try 
            {
                FileWriter fileWriter = new FileWriter("resources//Jobs.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(job.toString());
                bufferedWriter.newLine();

                bufferedWriter.close();
                fileWriter.close();

                JOptionPane.showMessageDialog(null, "Job added successfully.");
            } 
            
            catch (IOException e) 
            {
                JOptionPane.showMessageDialog(null, "Error writing to file.");
            }
        }

        
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {
            addJob();
        }
    }

    private Driver findDriver(int driverId) {
        for (Driver d : driverList) {
            if (d.getID() == driverId) {
                return d;
            }
        }
        return null;
    }

    private Truck findTruck(String truckId) {
        for (Truck t : truckList) {
            if (t.getID().equals(truckId)) {
                return t;
            }
        }
        return null;
    }
    
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {
            dispose();
            new Menu();
        }
    }

    private ArrayList<Driver> loadDrivers(String dfile){
        Scanner dscan = null;
        ArrayList<Driver> drivers = new ArrayList<Driver>();

        try{
            dscan = new Scanner(new File(dfile));
            while (dscan.hasNext()) 
            {
                String [] nextLine = dscan.nextLine().split(" ");
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

    private ArrayList<Truck> loadTrucks(String tfile){
        Scanner tscan = null;
        ArrayList<Truck> trucks = new ArrayList<Truck>();

        try{
            tscan = new Scanner(new File(tfile));
            while (tscan.hasNext()) 
            {
                String [] nextLine = tscan.nextLine().split(" ");
                String truckdesc = nextLine[0];
                String ttype = nextLine[1];
                String ID = nextLine[2];
                int wclass = Integer.parseInt(nextLine[3]);


                Truck t = new Truck(truckdesc, ttype, ID, wclass);
                trucks.add(t);

            }

            tscan.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        return trucks;
    }

    private ArrayList<Job> loadJobs(String filename) {
        ArrayList<Job> jobList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] jobDetails = line.split(",");
                int jobID = Integer.parseInt(jobDetails[0]);
                String jobDesc = jobDetails[1];
                int driverID = Integer.parseInt(jobDetails[2]);
                String truckID = jobDetails[3];
                int load = Integer.parseInt(jobDetails[4]);
                String destination = jobDetails[5];
                String deptime = jobDetails[6];
                String rettime = jobDetails[7];

                Driver driver = null;
                for (Driver d : driverList) {
                    if (d.getID() == driverID) {
                        driver = d;
                        break;
                    }
                }

                Truck truck = null;
                for (Truck t : truckList) {
                    if (t.getID().equals(truckID)) {
                        truck = t;
                        break;
                    }
                }

                if (driver == null || truck == null) {
                    System.out.println("Driver or truck not found");
                    continue;
                }

                Job j = new Job(jobDesc, driver, truck, load, destination, deptime, rettime);
                j.setJobID(jobID);

                if (Job.jobCount <= jobID) {
                    Job.jobCount = jobID;
                }

                jobList.add(j);
            }

            scanner.close();
        }
        
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        }
        return jobList;
    }


    public static void main(String[] args) {
        new Assign_Jobs();
    }
}
