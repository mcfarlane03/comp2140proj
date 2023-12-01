package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.ProviderMismatchException;

public class Application extends JFrame
{
    private JButton cmdCancel;
    private JButton cmdAdmin_Login;
    private Application app;
    private File imageFile; 
    private Container c;

    public Application() 
    {
        super("PAJ TRUCK REGISTRATION SYSTEM");
        
        
        c = getContentPane();
        c.setLayout(null);

        app = this;

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    

        setBounds(300, 90, 900, 600);
      

        // JLabel header = new JLabel("PORT AUTHORITY OF JAMAICA",SwingConstants.CENTER);
        // header.setFont(new Font("Felix Titling", Font.BOLD, 20));
        // header.setForeground(Color.white);
        // panelDisplay.add(header, BorderLayout.NORTH);
        
       
        imageFile = new File("resources//A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(260, 85, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        label.setSize(260, 85);
        label.setLocation(300, 10);
        c.add(label);

        JLabel subHeader2 = new JLabel("TRUCK REGISTRATION SYSTEM",SwingConstants.CENTER);
        subHeader2.setFont(new Font("Georgia", Font.BOLD, 30));
        subHeader2.setForeground(Color.black);
        subHeader2.setSize(600, 50);
        subHeader2.setLocation(150, 100);
        c.add(subHeader2, BorderLayout.NORTH);

        File truck = new File("resources//Truck.gif");
        ImageIcon icon2 = new ImageIcon(truck.getAbsolutePath());
        Image img2 = icon2.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon2 = new ImageIcon(img2);
        JLabel label2 = new JLabel(icon2);
        label2.setSize(400, 300);
        label2.setLocation(250, 100);
        c.add(label2);

        
        

        // command buttons
        cmdAdmin_Login = new JButton("Log-in");
        cmdAdmin_Login.setSize(100, 30);
        cmdAdmin_Login.setLocation(300, 450);
        cmdAdmin_Login.setFocusable(false);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setSize(100, 30);
        cmdCancel.setLocation(500, 450);
        cmdCancel.setFocusable(false);

        
        
        c.add(cmdAdmin_Login);
        c.add(cmdCancel);
        
        cmdAdmin_Login.addActionListener(new Admin_LoginButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());


        // to group the second header and the command buttons so it aligns better on the frame
        // Box vBox = Box.createVerticalBox();
        // vBox.add(Box.createVerticalGlue());
        // vBox.add(subHeader);    
        // subHeader.setAlignmentX(CENTER_ALIGNMENT);
        // vBox.add(Box.createVerticalGlue());                                                                                             
        // vBox.add(panelCommand);
        // vBox.setAlignmentX(CENTER_ALIGNMENT);
         
        // add(vBox, BorderLayout.SOUTH);

      
      c.setBackground(Color.white);
        setVisible(true);
    }

    
    private class Admin_LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        //    code to show list
            app.setVisible(false);
            new Admin_Login();
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new Application();
        
    }
}
