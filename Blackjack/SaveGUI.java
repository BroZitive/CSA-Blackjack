import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//This is the code that is responsible for the saveGUI, its behaviors, and saves.
public class SaveGUI extends JFrame
{
    private JLabel nameLabel;
    private JTextField nameInput;
    private JButton saveButton;
    private JLabel backgroundImage;

    private Balance balance = new Balance();

    //The SaveGUI constructor.
    public SaveGUI()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        ImageIcon logo = new ImageIcon("Assets/Logo.png");
        ImageIcon background = new ImageIcon(new ImageIcon("Assets/Background.jpg").getImage().getScaledInstance(420, 300, Image.SCALE_DEFAULT));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);

        this.setTitle("Save");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(420, 300);
        this.setLocation((gd.getDisplayMode().getWidth()-420)/2, (gd.getDisplayMode().getHeight()-300)/2);
        this.getContentPane().setBackground(new Color(0, 100, 0));
        this.setResizable(false);

        nameLabel = new JLabel();
        nameLabel.setBounds(10, 50, 100, 50);
        nameLabel.setText("NAME:");
        nameLabel.setFont(new Font("Constantia", Font.BOLD, 25));
        nameLabel.setForeground(new Color (245, 245, 245));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);

        nameInput = new JTextField();
        nameInput.setBounds(110, 50, 270, 50);
        nameInput.setFont(new Font("Constantia", Font.BOLD, 25));
        nameInput.setHorizontalAlignment(JTextField.CENTER);

        saveButton = new JButton();
        saveButton.setBounds((420-200)/2, 140, 200, 60);
        saveButton.setText("SAVE");
        saveButton.setFont(new Font("Constantia", Font.BOLD, 30));
        saveButton.setHorizontalTextPosition(JButton.CENTER);
        saveButton.setVerticalTextPosition(JButton.CENTER);
        saveButton.setFocusable(false);
        saveButton.setForeground(new Color(50, 50, 50));
        saveButton.setBackground(new Color(245, 245, 245));
        saveButton.setBorder(BorderFactory.createEtchedBorder());

        backgroundImage = new JLabel();
        backgroundImage.setIcon(background);
        backgroundImage.setBounds(0, 0, 420, 300);

        this.setVisible(true);
        this.add(nameLabel);
        this.add(nameInput);
        this.add(saveButton);
        this.add(backgroundImage);

        //When the saveButton is pressed new save line that stores the save line name, balance, and cheats status is added to the Saves.txt or an existing one with the same name is edited to match the new save.
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks to make sure that the inputted name does not contain "_", "^", "*", or "/", and is not empty.
                if(!(nameInput.getText().equals("")) && nameInput.getText().indexOf("_") == -1 && nameInput.getText().indexOf("^") == -1 && nameInput.getText().indexOf("*") == -1 && nameInput.getText().indexOf("/") == -1)
                {
                    boolean repeatedName = false;
                    int repeatedNamePosition = 0;
                    //Determines if the saveList is empty, and if not it checks for the presence of the name inputted by the user in the saveList and if it finds it sets the repeatedName to true.
                    if (TransferClass.savesList.size() > 0)
                    {
                        while (repeatedNamePosition < TransferClass.savesList.size())
                        {
                            if (TransferClass.savesList.get(repeatedNamePosition).substring(0, TransferClass.savesList.get(repeatedNamePosition).indexOf("_")).equals(nameInput.getText()))
                            {
                                repeatedName = true;
                                break;
                            }
                            repeatedNamePosition++;
                        }
                    }
                    
                    //Checks if the cheats are enabled and if so adds a "*" to the end of the save line, else adds a "/".
                    if (TransferClass.cheatsEnabled)
                    {
                        TransferClass.currentSave = nameInput.getText() + "_" + balance.getBalance() + "^*";
                    }
                    else
                    {
                        TransferClass.currentSave = nameInput.getText() + "_" + balance.getBalance() + "^/";
                    }

                    //Decides whether to replace an existing save line or to add a new one.
                    if(repeatedName)
                    {
                        TransferClass.savesList.set(repeatedNamePosition, TransferClass.currentSave);
                    }
                    else
                    {
                        TransferClass.savesList.add(TransferClass.currentSave);
                    }
                    
                    TransferClass.saveAsTextFile();
                    TransferClass.saveStatus = false;
                    dispose();
                }
            }
        }
        );

        //When the save window closes, it sets the saveStatus to false to allow new save windows to be opened again.
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                TransferClass.saveStatus = false;
            }
        }
        );
    }
}