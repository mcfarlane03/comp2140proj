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
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class Delete_Truck extends JFrame 
{
    private Container c;  
    private JPanel logoPanel;
    private Truck truck;
    private String ID;
    private ArrayList<Truck> trucklist = new ArrayList<Truck>();

    private JTextField txtID;
    private JButton cmdSubmit, cmdCancel;

  
    private Delete_Truck delete_truck;

    public Delete_Truck () 
    {
        super("PAJ: DELETE TRUCK");
        delete_truck = this;
        trucklist = loadTrucks("resources//Trucks.txt");
        c = getContentPane();
        c.setLayout(null);

        
        logoPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(300, 90, 800, 500);

        File imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        logoPanel.setBackground(new Color(0xa7dbd8));
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, JLabel.CENTER);
        logoPanel.add(label);
        logoPanel.setSize(375, 100);
        logoPanel.setLocation(200, 50);
        c.add(logoPanel);


        // to display Application Header
        JLabel apHeader = new JLabel("DELETE TRUCK");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        apHeader.setSize(300, 30);
        apHeader.setLocation(300, 150);
        c.add(apHeader);

        JLabel adminName = new JLabel("License Plate Number:");
        adminName.setFont(new Font("Arial", Font.PLAIN, 20));
        adminName.setSize(250, 20);
        adminName.setLocation(100, 250);
        c.add(adminName);

        txtID = new JTextField();
        txtID.setFont(new Font("Arial", Font.PLAIN, 15));
        txtID.setSize(400, 30);
        txtID.setLocation(310, 250);
        c.add(txtID);

        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(new Color(0xf38630));
        cmdSubmit.setSize(100, 30);
        cmdSubmit.setLocation(250, 350);
        c.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setSize(100, 30);
        cmdCancel.setLocation(400, 350);
        cmdCancel.setBackground(Color.red);
        c.add(cmdCancel);

        c.setBackground(new Color(0xa7dbd8));
        
        
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
                String[] nextLine = scanner.nextLine().split(",");
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

    private int remove(ArrayList<Truck> trucklist) 
    {
        String text_;
        for (int i = 0; i < trucklist.size(); i++) 
        {
            if (trucklist.get(i).getID().equals(ID))
            {
                trucklist.remove(i);
                try 
                {
                    File file = new File("resources//Trucks.txt");
                    file.delete();// deletes the existing file
                    File file_ = new File("resources//Trucks.txt"); // re creates it to be filled with the updates                                                      // information
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
                return 0;
            }
        }
        return 1;
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
                    if (remove(trucklist) == 0) 
                    {
                        JOptionPane.showMessageDialog(null, "Truck deleted successfully");
                        new Menu();
                        dispose();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Truck not found");
                    }
                }

            }
            // delete_truck.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            new Menu();
            dispose();
        }
    }
    public static void main(String[] args) 
    {
        Delete_Truck delete_truck = new Delete_Truck();
    }
}






