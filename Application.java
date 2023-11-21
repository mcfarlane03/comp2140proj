import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.ProviderMismatchException;

public class Application extends JPanel 
{
    private JButton cmdCancel;
    private JButton cmdAdmin_Login;
    private JPanel panelCommand;
    private JPanel panelDisplay;
    private Application app;

    public Application() 
    {
        panelCommand = new JPanel();
        panelDisplay = new JPanel();

        JLabel header = new JLabel("PORT AUTHORITY OF JAMAICA (PAJ)",SwingConstants.CENTER);
        header.setFont(new Font("Georgia", Font.BOLD, 60));
        panelDisplay.add(header);

        JLabel subHeader = new JLabel("PAJ TRUCK REGISTRATION SYSTEM",SwingConstants.LEFT);
        subHeader.setFont(new Font("Georgia", Font.BOLD, 30));
        
        panelDisplay.setLayout(new GridLayout(1, 2));

        // command buttons
        cmdAdmin_Login = new JButton("Admin Login");
        cmdAdmin_Login.setBackground(Color.green);
        panelCommand.add(cmdAdmin_Login);

        cmdCancel = new JButton("Cancel");
        cmdCancel.setBackground(Color.red);
        panelCommand.add(cmdCancel);
        
        cmdAdmin_Login.addActionListener(new Admin_LoginButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        add(panelDisplay, BorderLayout.CENTER);

        // to group the second header and the command buttons so it aligns better on the frame
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(subHeader);    
        subHeader.setAlignmentX(CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalGlue());                                                                                             
        vBox.add(panelCommand);
        vBox.setAlignmentX(CENTER_ALIGNMENT);
         
        add(vBox, BorderLayout.SOUTH);
        setVisible(true);
        app = this;
    }

    // to display the main form
    public static void showApplication() {
        JFrame frame = new JFrame("PAJ TRUCK REGISTRATION SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Application app = new Application();
        // app.setOpaque(true);
        frame.setContentPane(app);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showApplication();
            }
        });
    }

    private class Admin_LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        //    code to show list
            Admin_Login admin_login = new Admin_Login();
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
