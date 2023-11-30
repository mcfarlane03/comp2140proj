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


public class Add_Driver extends JFrame 
{
    private Driver driver;
    private String fname, sname, gender, tphone, email, ltype;
    private int age, ID, lclass;
    private JTextField txtfname, txtsname, txttphone, txtemail, txtltype, txtage, txtID, txtlclass;
    private JRadioButton fbutton, mbutton;
    private JButton cmdSubmit, cmdCancel;
    private Menu menu;
    private JPanel pCommand, pDisplay;
    private Add_Driver add_driver;
    private ArrayList<Driver> driverlist = new ArrayList<Driver>();


    public Add_Driver() 
    {
        add_driver = this;
        setTitle("PAJ: ADD DRIVER");
        pCommand = new JPanel();
        pDisplay = new JPanel();
        setBounds(300, 90, 800, 500);
        driverlist = loadDrivers("Text Files//Drivers.txt");

        // to display Application Header
        
        pDisplay.setBackground(new Color(0xa7dbd8));
        pCommand.setBackground(new Color(0xa7dbd8));
        setForeground(new Color(0xa7dbd8));
        File imageFile = new File("A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
    
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        label.setForeground(new Color(0x79b5ac));
        add(label, BorderLayout.NORTH);
        label.setBounds(300, 90, 350,100);
        

        pDisplay.add(new JLabel("First Name:"));
        txtfname = new JTextField(30);
        pDisplay.add(txtfname);
    
        pDisplay.add(new JLabel("Surname:"));
        txtsname = new JTextField(30);
        pDisplay.add(txtsname);

        // to add Buttons to select gender
        pDisplay.add(new JLabel("Sex:"));
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fbutton = new JRadioButton("Female");
        fbutton.setHorizontalAlignment(SwingConstants.LEFT);
        mbutton = new JRadioButton("Male");
        mbutton.setHorizontalAlignment(SwingConstants.LEFT);
        pnl.setBackground(new Color(0xa7dbd8));
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(mbutton);
        bgroup.add(fbutton);
        pnl.add(mbutton);
        pnl.add(fbutton);
        pDisplay.add(pnl);

        pDisplay.add(new JLabel("Telephone Number: (example: 8761234567)"));
        txttphone = new JTextField(30);
        pDisplay.add(txttphone);

        pDisplay.add(new JLabel("Email:"));
        txtemail = new JTextField(30);
        pDisplay.add(txtemail);

        pDisplay.add(new JLabel("license Type:"));
        txtltype = new JTextField(30);
        pDisplay.add(txtltype);

        pDisplay.add(new JLabel("Age:"));
        txtage = new JTextField(30);
        pDisplay.add(txtage);

        pDisplay.add(new JLabel("ID:"));
        txtID = new JTextField(9);
        pDisplay.add(txtID);

        pDisplay.add(new JLabel("Weight Class:"));
        txtlclass = new JTextField(3);
        pDisplay.add(txtlclass);

        cmdSubmit = new JButton("Submit");
        cmdSubmit.setBackground(Color.green);
        pCommand.add(cmdSubmit);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setBackground(Color.red);
        pCommand.add(cmdCancel);

        pDisplay.setLayout(new GridLayout(10, 2));
        

        add(pDisplay, BorderLayout.CENTER);
        add(pCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        fbutton.addActionListener(new ButtonListener());
        mbutton.addActionListener(new ButtonListener());
        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }
    
    private class ButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == fbutton) 
            {
                gender = "female";
            } 
            else 
            {
                gender = "male";
            }
        }
    }

    private ArrayList<Driver> loadDrivers(String dfile) 
    {
        Scanner scanner = null;
        ArrayList<Driver> driverlist = new ArrayList<Driver>();
        try 
        {
            scanner = new Scanner(new File(dfile));
            while (scanner.hasNext()) 
            {
                String[] nextLine = scanner.nextLine().split(" ");
                String fname = nextLine[0];
                String sname = nextLine[1];
                String gender = nextLine[2];
                String tphone = nextLine[3];
                String email = nextLine[4];
                String ltype = nextLine[5];
                int age = Integer.parseInt(nextLine[6]);
                int ID = Integer.parseInt(nextLine[7]);
                int lclass = Integer.parseInt(nextLine[8]);
                Driver driver = new Driver(fname, sname, gender, tphone, email, ltype, age, ID, lclass);
                driverlist.add(driver);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return driverlist;
    }

    public void addDriver(Driver driver) 
    {
        try
        {
            // creates a file named stud_records.txt
            File file = new File("Text Files//Drivers.txt");

            // needed in order to handle appending data to file if file already exists:
            FileWriter fileWriter = new FileWriter(file, file.exists());
            BufferedWriter output = new BufferedWriter(fileWriter);

            for (Driver d : driverlist)
            {
                if (d.compareTo(driver) == 0)
                {
                    JOptionPane.showMessageDialog(null, "Driver already exists");
                    System.exit(0);
                }
            }
            output.write(driver.toString());

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
                fname = txtfname.getText();
                sname = txtsname.getText();
                email = txtemail.getText();
                tphone = txttphone.getText();
                email = txtemail.getText();
                ltype = txtltype.getText();
                age = Integer.parseInt(txtage.getText());
                ID = Integer.parseInt(txtID.getText());
                lclass = Integer.parseInt(txtlclass.getText());
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
                    driver = new Driver(fname, sname, gender, tphone, email, ltype, age, ID, lclass);   
                    addDriver(driver);
                }
            }
            add_driver.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            dispose();
            new Menu();
        }

    }
    public static void main(String[] args) 
    {
        Add_Driver add_driver = new Add_Driver();
    }   
}
