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
    private JPanel panelCommand;
    private JPanel panelDisplay;
    private Application app;
    private File imageFile; 

    public Application() 
    {
        super("PAJ TRUCK REGISTRATION SYSTEM");
        
        panelCommand = new JPanel();
        panelDisplay = new JPanel();

        app = this;

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
    

        setBounds(300, 90, 900, 600);
        panelDisplay.setBackground(new Color(0xa7dbd8));
        panelCommand.setBackground(new Color(0xa7dbd8));
        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.setPreferredSize(new Dimension(400, 300));

        // JLabel header = new JLabel("PORT AUTHORITY OF JAMAICA",SwingConstants.CENTER);
        // header.setFont(new Font("Felix Titling", Font.BOLD, 20));
        // header.setForeground(Color.white);
        // panelDisplay.add(header, BorderLayout.NORTH);
        
        JLabel subHeader = new JLabel("PAJ TRUCK REGISTRATION SYSTEM",SwingConstants.LEFT);
        subHeader.setFont(new Font("Georgia", Font.BOLD, 30));
        
        imageFile = new File("A-Jamaica.png");
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        icon = new ImageIcon(imageFile.getAbsolutePath());
        Image img = icon.getImage().getScaledInstance(250, 75, Image.SCALE_SMOOTH);//Adjust Logo to fit in frame
        icon = new ImageIcon(img);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        panelDisplay.add(label);

        JLabel subHeader2 = new JLabel("PAJ TRUCK REGISTRATION SYSTEM",SwingConstants.CENTER);
        subHeader2.setFont(new Font("Helvetica", Font.BOLD, 30));
        subHeader2.setForeground(Color.white);
        panelDisplay.add(subHeader2, BorderLayout.NORTH);

        // command buttons
        cmdAdmin_Login = new JButton("Admin Login");
        cmdCancel = new JButton("Cancel");

        cmdAdmin_Login.setBackground(new Color(0xf38630));
        cmdCancel.setBackground(Color.red);
        
        panelCommand.add(cmdAdmin_Login);
        panelCommand.add(cmdCancel);
        
        cmdAdmin_Login.addActionListener(new Admin_LoginButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        add(panelDisplay, BorderLayout.NORTH);
        add(panelCommand, BorderLayout.SOUTH);

        // to group the second header and the command buttons so it aligns better on the frame
        // Box vBox = Box.createVerticalBox();
        // vBox.add(Box.createVerticalGlue());
        // vBox.add(subHeader);    
        // subHeader.setAlignmentX(CENTER_ALIGNMENT);
        // vBox.add(Box.createVerticalGlue());                                                                                             
        // vBox.add(panelCommand);
        // vBox.setAlignmentX(CENTER_ALIGNMENT);
         
        // add(vBox, BorderLayout.SOUTH);

      
        getContentPane().add(panelDisplay, BorderLayout.CENTER);
        getContentPane().add(panelCommand, BorderLayout.SOUTH);
 
        setVisible(true);
    }

    
    private class Admin_LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        //    code to show list
            app.setVisible(false);
            Admin_Login admin_login = new Admin_Login();
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        Application app = new Application();
        
    }
}
