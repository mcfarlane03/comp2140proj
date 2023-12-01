package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Edit_Assigned_Jobs extends JFrame {


    private JTextField jobDescField;    
    private JTextField driverIDField;
    private JTextField truckIDField;
    private JTextField loadField;
    private JTextField destinationField;
    private JTextField deptTimeField;
    private JTextField retTimeField;
    private JPanel logoPanel;


    private ArrayList<Driver> driverList;
    private ArrayList<Truck> truckList;
    private ArrayList<Job> jobList;
    private Job job;

    private int jobID;
    private String jobDesc;
    private String deptime;
    private String rettime;
    private String location;


    public Edit_Assigned_Jobs() {

        JComboBox<String> jobComboBox;

        setTitle("Edit Assigned Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setBackground(new Color(0xa7dbd8));
        setBounds(300, 90, 800, 500);
        setLayout(new BorderLayout());


        driverList = loadDrivers("resources//Drivers.txt");
        truckList = loadTrucks("resources//Trucks.txt");
        jobList = loadJobs("resources//Jobs.txt");

        jobComboBox = new JComboBox<>();
        driverIDField = new JTextField();
        truckIDField = new JTextField();
        loadField = new JTextField();
        destinationField = new JTextField();
        deptTimeField = new JTextField();
        retTimeField = new JTextField();
        jobDescField = new JTextField();

        JFrame frame1 = new JFrame();
        frame1.setTitle("Select Job");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(400, 100);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(new BorderLayout());

        JButton saveButton = new JButton();
        JButton cancelButton = new JButton();

        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        logoPanel = new JPanel();
        logoPanel.setLayout(new GridLayout(2, 1));
        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        logoPanel.setBackground(new Color(0xa7dbd8));
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, JLabel.CENTER);
        JLabel apHeader = new JLabel("EDIT ASSIGNED JOBS",SwingConstants.CENTER);
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        logoPanel.add(label);
        logoPanel.add(apHeader);
        logoPanel.setSize(375, 100);
        logoPanel.setLocation(200, 50);
        add(logoPanel, BorderLayout.NORTH);




        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2));

        JPanel panel2 = new JPanel(new GridLayout(1, 2));

        JLabel jobLabel = new JLabel("Job:");
        panel1.add(jobLabel);

        // Create the job description label and text field
        //JLabel jobDescriptionLabel = new JLabel("Job Description:");
        jobComboBox.addItem("Select a Job to Edit");
        jobComboBox.setForeground(Color.GRAY);
        for (Job job : jobList) {
            jobComboBox.addItem("Job" + job.getJobID() + " " + job.getJobDescript());
        }
        


        JButton cancelButtonA = new JButton("Cancel");
        cancelButtonA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window
                frame1.setVisible(false);
                new Menu();
            }
        });
        

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window

                if(jobComboBox.getSelectedItem().toString().equals("Select a Job to Edit")){
                    JOptionPane.showMessageDialog(Edit_Assigned_Jobs.this, "Please select a job to edit!");
                    return;
                }

                frame1.setVisible(false);

                jobID = Integer.parseInt(jobComboBox.getSelectedItem().toString().split(" ")[0].replace("Job", ""));

                for (Job j : jobList) {
                    if (j.getJobID() == jobID) {
                        job = j;
                        break;
                    }
                }

                
                jobDescField.setText(job.getJobDescript());
                driverIDField.setText(Integer.toString(job.getDriver().getID()));
                truckIDField.setText(job.getTruck().getID());
                loadField.setText(Integer.toString(job.getLoad()));
                destinationField.setText(job.getDestination());
                deptTimeField.setText(job.getDeptTime());
                retTimeField.setText(job.getRetTime());


                add(mainPanel, BorderLayout.CENTER);

                // Add the button panel to the frame
                add(buttonPanel, BorderLayout.SOUTH);

                Edit_Assigned_Jobs.this.setVisible(true);


                
            }
        });

        panel1.add(jobComboBox);
        panel2.add(editButton);
        panel2.add(cancelButtonA);

        frame1.add(panel1,  BorderLayout.NORTH);
        frame1.add(panel2, BorderLayout.SOUTH);
        frame1.setVisible(true);


        // Create the main panel
        // JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xa7dbd8));
        mainPanel.setLayout(new GridLayout(7, 2, 10, 10));


        
        JLabel jobDescLabel= new JLabel("Job Description:");
        mainPanel.add(jobDescLabel);
        mainPanel.add(jobDescField);

        // Create the driver ID label and text field
        JLabel driverIDLabel = new JLabel("Driver ID:");
        driverIDField = new JTextField();
        mainPanel.add(driverIDLabel);
        mainPanel.add(driverIDField);

        // Create the truck ID label and text field
        JLabel truckIDLabel = new JLabel("Truck ID:");
        truckIDField = new JTextField();
        mainPanel.add(truckIDLabel);
        mainPanel.add(truckIDField);

        // Create the load label and text field
        JLabel loadLabel = new JLabel("Load:");
        loadField = new JTextField();
        mainPanel.add(loadLabel);
        mainPanel.add(loadField);

        // Create the destination label and text field
        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();
        mainPanel.add(destinationLabel);
        mainPanel.add(destinationField);

        // Create the departure time label and text field
        JLabel deptTimeLabel = new JLabel("Departure Time:");
        deptTimeField = new JTextField();
        mainPanel.add(deptTimeLabel);
        mainPanel.add(deptTimeField);

        // Create the return time label and text field
        JLabel retTimeLabel = new JLabel("Return Time:");
        retTimeField = new JTextField();
        mainPanel.add(retTimeLabel);
        mainPanel.add(retTimeField);

        // Create the button panel
        buttonPanel.setBackground(new Color(0xa7dbd8));
        buttonPanel.setLayout(new FlowLayout());

        // Create the save button
        saveButton = new JButton("Save");
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the edited job information

                job = null;
                int driverID = 0;
                int load = 0;


                jobID = Integer.parseInt(jobComboBox.getSelectedItem().toString().split(" ")[0].replace("Job", ""));
                jobDesc = jobComboBox.getSelectedItem().toString().split(" ")[1].replace(":", "");
                String truckID = truckIDField.getText();

                

                if(driverIDField.getText().equals("") ){
                    driverID = 0;
                } else {
                    driverID = Integer.parseInt(driverIDField.getText());
                }                

                if(loadField.getText().equals("")){
                    load = 0;
                } else {
                    load = Integer.parseInt(loadField.getText());
                }

                jobDesc = jobDescField.getText();
                location = destinationField.getText();
                deptime = deptTimeField.getText();
                rettime = retTimeField.getText();

                for (Job j : jobList) {
                    if (j.getJobID() == jobID) {
                        job = j;
                        jobList.remove(j);
                        break;
                    }
                }

                if(!driverIDField.getText().equals("")){
                    for (Driver d : driverList) {
                        if (d.getID() == driverID) {
                            job.setDriver(d);
                            break;
                        }
                    }
                }

                if(!truckIDField.getText().equals("")){
                    for (Truck t : truckList) {
                        if (t.getID().equals(truckID)) {
                            job.setTruck(t);
                            break;
                        }
                    }
                }

                if(!jobDescField.getText().equals("")){
                    job.setJobDescript(jobDesc);
                }

                if(!loadField.getText().equals("")){
                    job.setLoad(load);
                }

                if(!destinationField.getText().equals("")){
                    job.setDestination(location);
                }

                if(!deptTimeField.getText().equals("")){
                    job.setDeptTime(deptime);
                }

                if(!retTimeField.getText().equals("")){
                    job.setRetTime(rettime);
                }

                jobList.add(job);
                
                try 
                {
                    FileWriter writer = new FileWriter("resources//Jobs.txt");
                    for (Job j : jobList) 
                    {
                        writer.write(j.getJobID() + "," +   j.getJobDescript() + "," + j.getDriver().getID() + "," + j.getTruck().getID() + "," + j.getLoad() + "," + j.getDestination() + "," + j.getDeptTime() + "," + j.getRetTime() + "\n");
                    }
                    writer.close();
                } 
                catch (Exception ioe) 
                {
                    System.out.println("Error writing to file");
                }

                JOptionPane.showMessageDialog(Edit_Assigned_Jobs.this, "Job information saved successfully!");
                new Menu();
                dispose();
            }
        });
        saveButton.setBackground(new Color(0xf38630));
        buttonPanel.add(saveButton);

        // Create the cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window
                new Menu();
                dispose();
            }
        });
        cancelButton.setBackground(Color.RED);
        buttonPanel.add(cancelButton); 

        // Add the main panel to the frame
        

        //setVisible(true);
    }


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

    private ArrayList<Truck> loadTrucks(String tfile){
        Scanner tscan = null;
        ArrayList<Truck> trucks = new ArrayList<Truck>();

        try{
            tscan = new Scanner(new File(tfile));
            while (tscan.hasNext()) 
            {
                String [] nextLine = tscan.nextLine().split(",");
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
            System.out.println("Truck File not found");
        }

        return trucks;
    }

    //method for loading the jobs from the file
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Edit_Assigned_Jobs();
            }
        });
    }
}
