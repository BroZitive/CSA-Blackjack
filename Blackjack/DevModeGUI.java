import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//This is the code that is responsible for the DevModeGUI, marking the save with a "*" if cheated, changing the balance to a new value, and its other behaviors.
public class DevModeGUI extends JFrame
{
    private JLabel enableCheatsLabel;
    private JButton enableCheatsButton;
    private JButton changeBalanceButton;
    private JTextField inputNewBalance;
    private JLabel backgroundImage;

    private Balance balance = new Balance();

    //The DevModeGUI constructor.
    public DevModeGUI()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        ImageIcon logo = new ImageIcon("Assets/Logo.png");
        ImageIcon background = new ImageIcon(new ImageIcon("Assets/Background.jpg").getImage().getScaledInstance(420, 300, Image.SCALE_DEFAULT));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);

        this.setTitle("Developer Mode");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(420, 300);
        this.setLocation((gd.getDisplayMode().getWidth()-420)/2, (gd.getDisplayMode().getHeight()-300)/2);
        this.getContentPane().setBackground(new Color(0, 100, 0));
        this.setResizable(false);

        enableCheatsLabel = new JLabel();
        enableCheatsLabel.setBounds((420-380)/2, 30, 380, 100);
        enableCheatsLabel.setText("<html><div style='text-align: center;'>DO YOU WANT TO ENABLE THE DEVELOPER MODE?</html>");
        enableCheatsLabel.setFont(new Font("Constantia", Font.BOLD, 30));
        enableCheatsLabel.setForeground(new Color (245, 245, 245));
        enableCheatsLabel.setHorizontalAlignment(JLabel.CENTER);
        enableCheatsLabel.setVerticalAlignment(JLabel.CENTER);
        
        enableCheatsButton = new JButton();
        enableCheatsButton.setBounds((420-200)/2, 150, 200, 75);
        enableCheatsButton.setText("ENABLE");
        enableCheatsButton.setFont(new Font("Constantia", Font.BOLD, 30));
        enableCheatsButton.setHorizontalTextPosition(JButton.CENTER);
        enableCheatsButton.setVerticalTextPosition(JButton.CENTER);
        enableCheatsButton.setFocusable(false);
        enableCheatsButton.setForeground(new Color(50, 50, 50));
        enableCheatsButton.setBackground(new Color(245, 245, 245));
        enableCheatsButton.setBorder(BorderFactory.createEtchedBorder());

        changeBalanceButton = new JButton();
        changeBalanceButton.setBounds((420-200)/2, 140, 200, 60);
        changeBalanceButton.setText("UPDATE");
        changeBalanceButton.setFont(new Font("Constantia", Font.BOLD, 30));
        changeBalanceButton.setHorizontalTextPosition(JButton.CENTER);
        changeBalanceButton.setVerticalTextPosition(JButton.CENTER);
        changeBalanceButton.setFocusable(false);
        changeBalanceButton.setForeground(new Color(50, 50, 50));
        changeBalanceButton.setBackground(new Color(245, 245, 245));
        changeBalanceButton.setBorder(BorderFactory.createEtchedBorder());
        changeBalanceButton.setVisible(false);

        inputNewBalance = new JTextField();
        inputNewBalance.setBounds(160, 50, 220, 50);
        inputNewBalance.setFont(new Font("Constantia", Font.BOLD, 25));
        inputNewBalance.setHorizontalAlignment(JTextField.CENTER);
        inputNewBalance.setVisible(false);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(background);
        backgroundImage.setBounds(0, 0, 420, 300);

        //Checks if cheats are enabled, and if so then it will skip the first window and show the cheat right away.
        if (TransferClass.cheatsEnabled)
        {
            enabledCheatsWindow();
        }

        this.setVisible(true);
        this.add(enableCheatsLabel);
        this.add(enableCheatsButton);
        this.add(changeBalanceButton);
        this.add(inputNewBalance);
        this.add(backgroundImage);

        //When the enableCheatsButton is pressed the cheatsEnabled becomes true, if there are previous saves open then the currentSave will have a "*" at the end instead of a "/", and goes to the next window. 
        enableCheatsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                try
                {
                    if (TransferClass.currentSave.substring(TransferClass.currentSave.indexOf("^")+1).equals("/"))
                    {
                        TransferClass.currentSave = TransferClass.currentSave.substring(0, TransferClass.currentSave.length()) + "*";
                        TransferClass.cheatsEnabled = true;
                        GUI.enableCheats();
                        enabledCheatsWindow();
                    }
                }
                catch (Exception e)
                {
                    TransferClass.cheatsEnabled = true;
                    GUI.enableCheats();
                    enabledCheatsWindow();
                }
            }
        }
        );

        //When the changeBalanceButton is pressed the balance is set to a new balance from the inputNewBalance JTextField.
        changeBalanceButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Tries to change the balance if the inputNewBalance can be converted into an integer, otherwise it does nothing.
                try
                {
                    if (Integer.parseInt(inputNewBalance.getText()) > 0)
                    {
                        balance.setBalance(Integer.parseInt(inputNewBalance.getText()));
                        GUI.updateBalance();
                        TransferClass.devModeStatus = false;
                        dispose();
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
        );
        
        //When the Developer Mode window closes, it sets the devModeStatus to false to allow new Developer Mode windows to be opened again.
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                TransferClass.devModeStatus = false;
            }
        }
        );
    }

    //When called it enables all of the items needed for the second window.
    public void enabledCheatsWindow()
    {
        enableCheatsLabel.setBounds(10, 50, 150, 50);
        enableCheatsLabel.setText("BALANCE:");
        enableCheatsLabel.setFont(new Font("Constantia", Font.BOLD, 25));
        enableCheatsLabel.setForeground(new Color (245, 245, 245));
        enableCheatsLabel.setHorizontalAlignment(JLabel.CENTER);
        enableCheatsLabel.setVerticalAlignment(JLabel.CENTER);

        enableCheatsButton.setVisible(false);

        changeBalanceButton.setVisible(true);

        inputNewBalance.setVisible(true);
    }
}