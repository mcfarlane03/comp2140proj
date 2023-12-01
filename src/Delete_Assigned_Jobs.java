package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Delete_Assigned_Jobs extends JFrame{
    private Driver driver;
    private Job job;
    private ArrayList<Job> jobList;
    private JComboBox<String> jobBox;
    private JButton cmdSubmit, cmdCancel;
    private Container c;
    private JPanel logoPanel = new JPanel();


    public Delete_Assigned_Jobs(){


        jobList = loadJobs("resources//Jobs.txt");
        c = getContentPane();
        c.setLayout(null);
        
        setTitle("Delete Assigned Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null);
        setBounds(300, 90, 800, 500);

        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        logoPanel.setBackground(new Color(0xa7dbd8));
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        logoPanel.add(label);
        logoPanel.setSize(375, 100);
        logoPanel.setLocation(200, 50);
        c.add(logoPanel);

        JLabel adminHeader = new JLabel("Delete Assigned Jobs"); 
        adminHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        adminHeader.setSize(300, 30);
        adminHeader.setLocation(300, 150);
        c.add(adminHeader);
        
        JLabel adminName = new JLabel("Job:");
        adminName.setFont(new Font("Arial", Font.PLAIN, 20));
        adminName.setSize(100, 20);
        adminName.setLocation(100, 200);
        c.add(adminName);

        jobBox = new JComboBox<String>();
        jobBox.addItem("Select Job");
        for (Job j : jobList) {
            jobBox.addItem(j.getJobDescript());
        }
        jobBox.setFont(new Font("Arial", Font.PLAIN, 15));
        jobBox.setSize(400, 30);
        jobBox.setLocation(200, 200);
        c.add(jobBox);
   


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

       



        c.setBackground(new Color(0xa7dbd8));
        setVisible(true);
         


        


    }

    private class CancelButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            new Menu();
        }
    }

    private class SubmitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(jobBox.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Please select a job");
                return;
            }
            int jobID = jobList.get(jobBox.getSelectedIndex() - 1).getJobID();
            deleteJob(jobID);
            JOptionPane.showMessageDialog(null, "Job deleted");
            dispose();
            new Menu().setVisible(true);
        }
    }

    private void deleteJob(int jobID){
        jobList = loadJobs("resources//Jobs.txt");
        for(Job j: jobList){
            if(j.getJobID() == jobID){
                jobList.remove(j);
                break;
            }
        }
        try{
            FileWriter writer = new FileWriter("resources//Jobs.txt");
                    for (Job j : jobList) 
                    {
                        writer.write(j.getJobID() + "," +   j.getJobDescript() + "," + j.getDriver().getID() + "," + j.getTruck().getID() + "," + j.getLoad() + "," + j.getDestination() + "," + j.getDeptTime() + "," + j.getRetTime() + "\n");
                    }
                    writer.close();
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    private ArrayList<Job> loadJobs(String filename) {
        ArrayList<Job> jobList = new ArrayList<>();
        ArrayList<Driver> driverList = loadDrivers("resources//Drivers.txt");
        ArrayList<Truck> truckList = loadTrucks("resources//Trucks.txt");
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

                if (Job.jobCount <= jobID){
                    Job.jobCount = jobID;
                }

                jobList.add(j);

            }


            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return jobList;
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

    private Job findJob(Driver driver, ArrayList<Job> jobList) {
        for (Job j : jobList) {
            if (j.getDriver().getID() == driver.getID()) {
                return j;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new Delete_Assigned_Jobs().setVisible(true);
    }
    
}
