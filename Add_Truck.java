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
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Add_Truck extends JFrame 
{
    private Truck truck;
    private String truckdesc, ttype, ID;
    private int wclass;
    private JTextField txttruckdesc, txtttype, txtID, txtwclass;
    private JButton cmdSubmit, cmdCancel;
    private Menu menu;
    private JPanel pCommand, pDisplay;
    private Add_Truck add_truck;
    private ArrayList<Truck> trucklist = new ArrayList<Truck>();

    public Add_Truck () 
    {
        add_truck = this;
        setTitle("PAJ: ADD TRUCK");
        pCommand = new JPanel();
        pDisplay = new JPanel();

        trucklist = loadTrucks("Text Files//Trucks.txt");

        // to display Application Header
        JLabel apHeader = new JLabel("PAJ: ADD TRUCK");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        pDisplay.add(apHeader);

        pDisplay.add(new JLabel("Truck Description:"));
        txttruckdesc = new JTextField(30);
        pDisplay.add(txttruckdesc);

        pDisplay.add(new JLabel("Truck Type:"));
        txtttype = new JTextField(30);
        pDisplay.add(txtttype);

        pDisplay.add(new JLabel("License Plate Number:"));
        txtID = new JTextField(9);
        pDisplay.add(txtID);

        pDisplay.add(new JLabel("Weight Class:"));
        txtwclass = new JTextField(3);
        pDisplay.add(txtwclass);

        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(Color.green);
        pCommand.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setBackground(Color.red);
        pCommand.add(cmdCancel);

        pDisplay.setLayout(new GridLayout(34, 1));
        setPreferredSize(new Dimension(1620, 1080));

        add(pDisplay, BorderLayout.CENTER);
        add(pCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }
    
    private ArrayList<Truck> loadTrucks(String dfile) 
    {
        Scanner scanner = null;
        ArrayList<Truck> trucklist = new ArrayList<Truck>();
        try 
        {
            scanner = new Scanner(new File(dfile));
            while (scanner.hasNext()) 
            {
                String[] nextLine = scanner.nextLine().split(" ");
                String truckdesc = nextLine[0];
                String ttype = nextLine[1];
                String ID = nextLine[2];
                int wclass = Integer.parseInt(nextLine[3]);
                Truck truck = new Truck(truckdesc, ttype, ID, wclass);
                trucklist.add(truck);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return trucklist;
    }

    public void addTruck(Truck truck) 
    {
        try
        {
            // creates a file named stud_records.txt
            File file = new File("Text Files//Trucks.txt");

            // needed in order to handle appending data to file if file already exists:
            FileWriter fileWriter = new FileWriter(file, file.exists());
            BufferedWriter output = new BufferedWriter(fileWriter);

            for (Truck t : trucklist)
            {
                if (t.compareTo(truck) == true)
                {
                    JOptionPane.showMessageDialog(null, "Truck already exists");
                    System.exit(0);
                }
            }
            output.write(truck.toString());

            // adding new line for next record to be appended:
            if (file.exists())
                output.newLine();
                JOptionPane.showMessageDialog(null, "Your data has been recorded.");

            output.close();
            fileWriter.close();
        } 
        catch (NumberFormatException nfe) 
        {
            System.out.println(nfe.getMessage());
        }
        catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        }
    }

    private class SubmitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            boolean noerror = false;
            try 
            {
                truckdesc = txttruckdesc.getText();
                ttype = txtttype.getText();
                ID = txtID.getText();
                wclass = Integer.parseInt(txtwclass.getText());
                noerror = true;
            } catch (NumberFormatException numformerror) 
            {
                System.out.println(numformerror.getMessage());
            } 
            catch (ArrayIndexOutOfBoundsException indexouterror) 
            {
                System.out.println(indexouterror.getMessage());
            } 
            finally 
            {
                if (noerror == true) 
                {
                    truck = new Truck(truckdesc, ttype, ID, wclass);
                    addTruck(truck);
                }

            }
            add_truck.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            add_truck.setVisible(false);
        }
    }
}





















 



    



