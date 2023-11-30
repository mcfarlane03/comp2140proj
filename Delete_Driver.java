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

public class Delete_Driver extends JFrame
{
    private Container c;  
    private Driver driver;
    private JPanel logoPanel;
    private int ID;
    private ArrayList<Driver> driverlist = new ArrayList<Driver>();

    private JTextField txtID;
    private JButton cmdSubmit, cmdCancel;

    private JPanel pCommand, pDisplay;
    private Delete_Driver delete_driver;

    public Delete_Driver() 
    {
        super("PAJ: DELETE DRIVER");
        delete_driver = this;
        driverlist = loadDrivers("Text Files//Drivers.txt");
        c = getContentPane();
        c.setLayout(null);
        //this.add_driver = add_driver;
       
       
        c = getContentPane();
        c.setLayout(null);
        pCommand = new JPanel();
        pDisplay = new JPanel();
        logoPanel = new JPanel();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setLayout(new GridLayout(3, 1));
       
        setBounds(300, 90, 800, 500);

      
      
   
        File imageFile = new File("A-Jamaica.png");
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
        
       
        JLabel adminName = new JLabel("Identification Number:");
        adminName.setFont(new Font("Arial", Font.PLAIN, 20));
        adminName.setSize(200, 20);
        adminName.setLocation(100, 250);
        c.add(adminName);

        txtID = new JTextField();
        txtID.setFont(new Font("Arial", Font.PLAIN, 15));
        txtID.setSize(400, 30);
        txtID.setLocation(300, 250);
        c.add(txtID);
        
        c.setBackground(new Color(0xa7dbd8));


    
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

       
        // pDisplay.setLayout(new GridLayout(34, 1));
        // setPreferredSize(new Dimension(1620, 1080));
       
        
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
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

    private void remove(ArrayList<Driver> driverlist) 
    {
        String text_;
        for (int i = 0; i < driverlist.size(); i++) 
        {
            if (driverlist.get(i).getID() == ID)
            {
                driverlist.remove(i);
                try 
                {
                    File file = new File("Text Files//Drivers.txt");
                    file.delete();// deletes the existing file
                    File file_ = new File("Text Files//Drivers.txt"); // re creates it to be filled with the updates                                                      // information
                    FileWriter fileWriter = new FileWriter(file, file_.exists());
                    BufferedWriter output = new BufferedWriter(fileWriter);
                    for (Driver s_ : driverlist) 
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
                ID = Integer.parseInt(txtID.getText());
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
                    remove(driverlist);
                }
            }
            delete_driver.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent actev) 
        {
            delete_driver.setVisible(false);
        }
    }
    public static void main(String[] args) 
    {
        Delete_Driver delete_driver = new Delete_Driver();
    }
}








