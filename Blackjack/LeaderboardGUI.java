import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//This is the code that is responsible for the leaderboard and its behaviors.
public class LeaderboardGUI extends JFrame
{    
    private String[] names = new String[5];
    private int[] scores = new int[5];
    private JLabel backgroundImage;
    private JLabel title;
    private JTextArea leaderboard1;
    private JTextArea leaderboard2;

    //The LeaderboardGUI constructor.
    public LeaderboardGUI()
    {
        setTopScores();
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        ImageIcon logo = new ImageIcon("Assets/Logo.png");
        ImageIcon background = new ImageIcon(new ImageIcon("Assets/Background.jpg").getImage().getScaledInstance(420, 300, Image.SCALE_DEFAULT));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);

        this.setTitle("Leaderboard");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(420, 300);
        this.setLocation((gd.getDisplayMode().getWidth()-420)/2, (gd.getDisplayMode().getHeight()-300)/2);
        this.getContentPane().setBackground(new Color(0, 100, 0));
        this.setResizable(false);

        title = new JLabel();
        title.setBounds(0, 10, 420, 50);
        title.setText("<html><div style='text-align: center;'>LEADERBOARD</html>");
        title.setFont(new Font("Constantia", Font.BOLD, 35));
        title.setForeground(new Color (245, 245, 245));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        leaderboard1 = new JTextArea();
        leaderboard1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        leaderboard1.setBounds(10, 60, 180, 340);
        leaderboard1.setText("1. " + names[0] + "\r\n2. " + names[1] + "\r\n3. " + names[2] + "\r\n4. " + names[3] + "\r\n5. " + names[4]);
        leaderboard1.setFont(new Font("Constantia", Font.BOLD, 30));
        leaderboard1.setForeground(new Color (245, 245, 245));
        leaderboard1.setOpaque(false);
        leaderboard1.setEditable(false);
        
        leaderboard2 = new JTextArea();
        leaderboard2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        leaderboard2.setBounds(210, 60, 180, 340);
        leaderboard2.setText("$" + scores[0] + "\r\n$" + scores[1] + "\r\n$" + scores[2] + "\r\n$" + scores[3] + "\r\n$" + scores[4]);
        leaderboard2.setFont(new Font("Constantia", Font.BOLD, 30));
        leaderboard2.setForeground(new Color (245, 245, 245));
        leaderboard2.setOpaque(false);
        leaderboard2.setEditable(false);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(background);
        backgroundImage.setBounds(0, 0, 420, 300);

        this.setVisible(true);
        this.add(title);
        this.add(leaderboard1);
        this.add(leaderboard2);
        this.add(backgroundImage);

        //When the leaderboard window closes, it sets the leaderboardStatus to false to allow new leaderboard windows to be opened again.
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                TransferClass.leaderboardStatus = false;
            }
        }
        );
    }

    //When it is called the arrays scores and names get populated by saves with the highest scores.
    public void setTopScores()
    {
        try
        {
            TransferClass.savesList = Files.readAllLines(Paths.get("Saves.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
        }
        for (String save : TransferClass.savesList)
        {
            if (save.indexOf("*") == -1)
            {
                for (int x = 0; x < 5; x++)
                {
                    if (Integer.parseInt(save.substring(save.indexOf("_")+1, save.indexOf("^"))) > scores[x])
                    {
                        for (int y = 4; y > x; y--)
                        {
                            scores[y] = scores[y-1];
                            names[y] = names[y-1];
                        }
                        scores[x] = Integer.parseInt(save.substring(save.indexOf("_")+1, save.indexOf("^")));
                        names[x] = save.substring(0, save.indexOf("_"));
                        break;
                    }
                }
            }
        }
    }
}