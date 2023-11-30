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
import java.io.FileWriter;
import java.io.BufferedWriter;


public class Delete_Truck extends JFrame 
{
    private Truck truck;
    private String ID;
    private ArrayList<Truck> trucklist = new ArrayList<Truck>();

    private JTextField txtID;
    private JButton cmdSubmit, cmdCancel;

    private JPanel pCommand, pDisplay;
    private Delete_Truck delete_truck;

    public Delete_Truck () 
    {
        delete_truck = this;
        trucklist = loadTrucks("Text Files//Trucks.txt");

        setTitle("PAJ: DELETE TRUCK");
        pCommand = new JPanel();
        pDisplay = new JPanel();

        // to display Application Header
        JLabel apHeader = new JLabel("PAJ: DELETE TRUCK");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        pDisplay.add(apHeader);

        pDisplay.add(new JLabel("License Plate Number:"));
        txtID = new JTextField(9);
        pDisplay.add(txtID);

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

    private void remove(ArrayList<Truck> trucklist) 
    {
        String text_;
        for (int i = 0; i < trucklist.size(); i++) 
        {
            if (trucklist.get(i).getID().equals(ID))
            {
                trucklist.remove(i);
                try 
                {
                    File file = new File("Text Files//Trucks.txt");
                    file.delete();// deletes the existing file
                    File file_ = new File("Text Files//Trucks.txt"); // re creates it to be filled with the updates                                                      // information
                    FileWriter fileWriter = new FileWriter(file, file_.exists());
                    BufferedWriter output = new BufferedWriter(fileWriter);
                    for (Truck s_ : trucklist) 
                    {
                        text_ = s_.toString();
                        output.write(text_); // each record is written to the file
                        if (file_.exists())
                            output.newLine();
                    }
                    output.close();
                    fileWriter.close();
                } catch (NumberFormatException nfe) 
                {
                    System.out.println(nfe.getMessage());
                } 
                catch (IOException ioe) 
                {
                    System.out.println(ioe.getMessage());
                }
            }
        }
    }

    private class SubmitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            boolean noerror = false;
            try 
            {
                ID = txtID.getText();
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
                    remove(trucklist);
                }

            }
            delete_truck.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            delete_truck.setVisible(false);
        }
    }
}






