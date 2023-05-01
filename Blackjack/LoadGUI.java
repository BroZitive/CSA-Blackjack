import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//This is the code that is responsible for the LoadGUI, loading saves, and its other behaviors.
public class LoadGUI extends JFrame
{
    private JLabel nameLabel;
    private JTextField nameInput;
    private JButton loadButton;
    private JLabel backgroundImage;

    private Balance balance = new Balance();

    //The LoadGUI constructor.
    public LoadGUI()
    {        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        ImageIcon logo = new ImageIcon("Assets/Logo.png");
        ImageIcon background = new ImageIcon(new ImageIcon("Assets/Background.jpg").getImage().getScaledInstance(420, 300, Image.SCALE_DEFAULT));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);

        this.setTitle("Load");
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

        loadButton = new JButton();
        loadButton.setBounds((420-200)/2, 140, 200, 60);
        loadButton.setText("LOAD");
        loadButton.setFont(new Font("Constantia", Font.BOLD, 30));
        loadButton.setHorizontalTextPosition(JButton.CENTER);
        loadButton.setVerticalTextPosition(JButton.CENTER);
        loadButton.setFocusable(false);
        loadButton.setForeground(new Color(50, 50, 50));
        loadButton.setBackground(new Color(245, 245, 245));
        loadButton.setBorder(BorderFactory.createEtchedBorder());

        backgroundImage = new JLabel();
        backgroundImage.setIcon(background);
        backgroundImage.setBounds(0, 0, 420, 300);

        this.setVisible(true);
        this.add(nameLabel);
        this.add(nameInput);
        this.add(loadButton);
        this.add(backgroundImage);

        //When the loadButton is pressed a save line with the same name is taken from the Save.txt file and all of its contents.
        //All of the save lines are organized based on their names for the ease of using binary search.
        loadButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks to make sure that the savesList is not empty.
                if(TransferClass.savesList.size() > 0)
                {   
                    int numOfSaves = TransferClass.savesList.size();
                    //Organizes all of the items in the savesList acording to their text before the "_".
                    for (int x = 0; x < numOfSaves; x++)
                    {
                        for (int y = x + 1; y < numOfSaves; y++)
                        {
                            if (TransferClass.savesList.get(x).substring(0, TransferClass.savesList.get(x).indexOf("_")).compareTo(TransferClass.savesList.get(y).substring(0, TransferClass.savesList.get(y).indexOf("_"))) > 0)
                            {
                                TransferClass.savesList.set(y, TransferClass.savesList.set(x, TransferClass.savesList.get(y)));
                            }
                        }
                    }
                    
                    int start = 0;
                    int end = TransferClass.savesList.size() - 1;
                    //Runs a binary search to find the needed save and load its information into the probgram.
                    while (start <= end)
                    {
                        int middle = (start + end) / 2;
                        int result = nameInput.getText().compareTo(TransferClass.savesList.get(middle).substring(0, TransferClass.savesList.get(middle).indexOf("_")));
                        if (result == 0)
                        {
                            TransferClass.currentSave = TransferClass.savesList.get(middle);
                            balance.setBalance(Integer.parseInt(TransferClass.currentSave.substring(TransferClass.currentSave.indexOf("_") + 1, TransferClass.currentSave.indexOf("^"))));
                            if (TransferClass.currentSave.substring(TransferClass.savesList.get(middle).indexOf("^")+1).equals("*"))
                            {
                                TransferClass.cheatsEnabled = true;
                            }
                            else
                            {
                                TransferClass.cheatsEnabled = false;
                            }
                            TransferClass.saveAsTextFile();
                            GUI.resetAfterLoad();
                            TransferClass.loadStatus = false;
                            dispose();
                            break;
                        }
                        else if (result < 0)
                        {
                            end = middle - 1;
                        }
                        else
                        {
                            start = middle + 1;
                        }
                    }
                }
            }
        }
        );
        
        //When the load window closes, it sets the loadStatus to false to allow new load windows to be opened again.
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                TransferClass.loadStatus = false;
            }
        }
        );
    }
}