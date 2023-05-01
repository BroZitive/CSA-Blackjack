import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The main GUI code that creates the forms for the user to interact with and calls for methods in other classes to do calculations.
public class GUI extends JFrame
{    
    private Timer timer;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static Balance balance = new Balance();
    private static Player totalHands = new Player();
    private static Player personHand = new Person();
    private static Player computerHand = new Computer();
    public static Person p = (Person)personHand;
    public static Computer c = (Computer)computerHand;
    private static WinCalculator winCalculator = new WinCalculator();
    private static Cards deck = new Cards();
    private static int betAmount;

    private static JLabel titleLabelAndResult;
    private static JButton startButton;
    private static JLabel balanceLabel;
    private static JLabel moneyAvailable;
    private static JTextField inputAmount;
    private static JButton betButton;
    private static JButton addOneButton;
    private static JButton addFiveButton;
    private static JButton addTwentyfiveButton;
    private static JButton addHundredButton;
    private static JButton subtractOneButton;
    private static JButton subtractFiveButton;
    private static JButton subtractTwentyfiveButton;
    private static JButton subtractHundredButton;
    private static JButton quitButton;
    private static JLabel dealerCardOne;
    private static JLabel dealerCardTwo;
    private static JLabel dealerCardThree;
    private static JLabel dealerCardFour;
    private static JLabel dealerCardFive;
    private static JLabel dealerCardSix;
    private static JLabel playerCardOne;
    private static JLabel playerCardTwo;
    private static JLabel playerCardThree;
    private static JLabel playerCardFour;
    private static JLabel playerCardFive;
    private static JLabel playerCardSix;
    private static JButton hitButton;
    private static JButton standButton;
    private static JButton doubleButton;
    private static JButton playAgainButton;
    private static JLabel backgroundImage;
    private static JMenuBar menuBar;
    private static JMenu gameSave;
    private static JMenu devMode;
    private static JMenuItem save;
    private static JMenuItem load;
    private static JMenuItem enableDevMode;
    private static JMenuItem changeBalance;
    private static JButton leaderboard;

    //The main GUI constructor.
    public GUI()
    {
        TransferClass.generateSavesList();
        
        ImageIcon logo = new ImageIcon("Assets/Logo.png");
        ImageIcon background = new ImageIcon("Assets/Background.jpg");
        ImageIcon back = new ImageIcon(new ImageIcon("Assets/Card Images/card back red.png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT));
        
        this.setTitle("Blackjack");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(570, 820);
        this.setLocation((gd.getDisplayMode().getWidth()-570)/2, (gd.getDisplayMode().getHeight()-820)/2);
        this.getContentPane().setBackground(new Color(0, 100, 0));
        this.setResizable(false);

        generateMenu();
        this.setJMenuBar(menuBar);
        
        //Window 1 items.
        
        titleLabelAndResult = new JLabel();
        titleLabelAndResult.setBounds((550-400)/2, 150, 400, 250);
        titleLabelAndResult.setText("<html><div style='text-align: center;'>BLACK<br/>JACK</html>");
        titleLabelAndResult.setFont(new Font("Constantia", Font.BOLD, 100));
        titleLabelAndResult.setForeground(new Color (245, 245, 245));
        titleLabelAndResult.setHorizontalAlignment(JLabel.CENTER);
        titleLabelAndResult.setVerticalAlignment(JLabel.CENTER);

        startButton = new JButton();
        startButton.setBounds((550-300)/2, 450, 300, 100);
        startButton.setText("BEGIN");
        startButton.setFont(new Font("Constantia", Font.BOLD, 50));
        startButton.setHorizontalTextPosition(JButton.CENTER);
        startButton.setVerticalTextPosition(JButton.CENTER);
        startButton.setFocusable(false);
        startButton.setForeground(new Color(50, 50, 50));
        startButton.setBackground(new Color(245, 245, 245));
        startButton.setBorder(BorderFactory.createEtchedBorder());

        //Window 2 items.

        balanceLabel = new JLabel();
        balanceLabel.setBounds((550-300)/2, 50, 300, 150);
        balanceLabel.setText("BALANCE:");
        balanceLabel.setFont(new Font("Constantia", Font.BOLD, 50));
        balanceLabel.setForeground(new Color (245, 245, 245));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        balanceLabel.setVerticalAlignment(JLabel.CENTER);
        balanceLabel.setVisible(false);

        moneyAvailable = new JLabel();
        moneyAvailable.setBounds(0, 175, 550, 200);
        moneyAvailable.setText("$" + balance.getBalance());
        moneyAvailable.setFont(new Font("Constantia", Font.BOLD, 150));
        moneyAvailable.setForeground(new Color (245, 245, 245));
        moneyAvailable.setHorizontalAlignment(JLabel.CENTER);
        moneyAvailable.setVerticalAlignment(JLabel.CENTER);
        moneyAvailable.setVisible(false);
        
        inputAmount = new JTextField();
        inputAmount.setBounds((550-200)/2, 375, 200, 100);
        inputAmount.setText("0");
        inputAmount.setFont(new Font("Constantia", Font.BOLD, 75));
        inputAmount.setHorizontalAlignment(JTextField.CENTER);
        inputAmount.setVisible(false);

        betButton = new JButton();
        betButton.setBounds((550-120)/2, 510, 120, 60);
        betButton.setText("BET");
        betButton.setFont(new Font("Constantia", Font.BOLD, 40));
        betButton.setHorizontalTextPosition(JButton.CENTER);
        betButton.setVerticalTextPosition(JButton.CENTER);
        betButton.setFocusable(false);
        betButton.setForeground(new Color(50, 50, 50));
        betButton.setBackground(new Color(245, 245, 245));
        betButton.setBorder(BorderFactory.createEtchedBorder());
        betButton.setVisible(false);

        addOneButton = new JButton();
        addOneButton.setBounds((550-80)/2+125, 510, 80, 60);
        addOneButton.setText("+1");
        addOneButton.setFont(new Font("Constantia", Font.BOLD, 35));
        addOneButton.setHorizontalTextPosition(JButton.CENTER);
        addOneButton.setVerticalTextPosition(JButton.CENTER);
        addOneButton.setFocusable(false);
        addOneButton.setForeground(new Color(50, 50, 50));
        addOneButton.setBackground(new Color(145, 245, 145));
        addOneButton.setBorder(BorderFactory.createEtchedBorder());
        addOneButton.setVisible(false);
        
        addFiveButton = new JButton();
        addFiveButton.setBounds((550-80)/2+225, 510, 80, 60);
        addFiveButton.setText("+5");
        addFiveButton.setFont(new Font("Constantia", Font.BOLD, 35));
        addFiveButton.setHorizontalTextPosition(JButton.CENTER);
        addFiveButton.setVerticalTextPosition(JButton.CENTER);
        addFiveButton.setFocusable(false);
        addFiveButton.setForeground(new Color(50, 50, 50));
        addFiveButton.setBackground(new Color(145, 245, 145));
        addFiveButton.setBorder(BorderFactory.createEtchedBorder());
        addFiveButton.setVisible(false);

        addTwentyfiveButton = new JButton();
        addTwentyfiveButton.setBounds((550-80)/2+125, 600, 80, 60);
        addTwentyfiveButton.setText("+25");
        addTwentyfiveButton.setFont(new Font("Constantia", Font.BOLD, 35));
        addTwentyfiveButton.setHorizontalTextPosition(JButton.CENTER);
        addTwentyfiveButton.setVerticalTextPosition(JButton.CENTER);
        addTwentyfiveButton.setFocusable(false);
        addTwentyfiveButton.setForeground(new Color(50, 50, 50));
        addTwentyfiveButton.setBackground(new Color(145, 245, 145));
        addTwentyfiveButton.setBorder(BorderFactory.createEtchedBorder());
        addTwentyfiveButton.setVisible(false);

        addHundredButton = new JButton();
        addHundredButton.setBounds((550-80)/2+225, 600, 80, 60);
        addHundredButton.setText("+100");
        addHundredButton.setFont(new Font("Constantia", Font.BOLD, 35));
        addHundredButton.setHorizontalTextPosition(JButton.CENTER);
        addHundredButton.setVerticalTextPosition(JButton.CENTER);
        addHundredButton.setFocusable(false);
        addHundredButton.setForeground(new Color(50, 50, 50));
        addHundredButton.setBackground(new Color(145, 245, 145));
        addHundredButton.setBorder(BorderFactory.createEtchedBorder());
        addHundredButton.setVisible(false);

        subtractOneButton = new JButton();
        subtractOneButton.setBounds((550-80)/2-125, 510, 80, 60);
        subtractOneButton.setText("-1");
        subtractOneButton.setFont(new Font("Constantia", Font.BOLD, 35));
        subtractOneButton.setHorizontalTextPosition(JButton.CENTER);
        subtractOneButton.setVerticalTextPosition(JButton.CENTER);
        subtractOneButton.setFocusable(false);
        subtractOneButton.setForeground(new Color(50, 50, 50));
        subtractOneButton.setBackground(new Color(245, 145, 145));
        subtractOneButton.setBorder(BorderFactory.createEtchedBorder());
        subtractOneButton.setVisible(false);

        subtractFiveButton = new JButton();
        subtractFiveButton.setBounds((550-80)/2-225, 510, 80, 60);
        subtractFiveButton.setText("-5");
        subtractFiveButton.setFont(new Font("Constantia", Font.BOLD, 35));
        subtractFiveButton.setHorizontalTextPosition(JButton.CENTER);
        subtractFiveButton.setVerticalTextPosition(JButton.CENTER);
        subtractFiveButton.setFocusable(false);
        subtractFiveButton.setForeground(new Color(50, 50, 50));
        subtractFiveButton.setBackground(new Color(245, 145, 145));
        subtractFiveButton.setBorder(BorderFactory.createEtchedBorder());
        subtractFiveButton.setVisible(false);

        subtractTwentyfiveButton = new JButton();
        subtractTwentyfiveButton.setBounds((550-80)/2-125, 600, 80, 60);
        subtractTwentyfiveButton.setText("-25");
        subtractTwentyfiveButton.setFont(new Font("Constantia", Font.BOLD, 35));
        subtractTwentyfiveButton.setHorizontalTextPosition(JButton.CENTER);
        subtractTwentyfiveButton.setVerticalTextPosition(JButton.CENTER);
        subtractTwentyfiveButton.setFocusable(false);
        subtractTwentyfiveButton.setForeground(new Color(50, 50, 50));
        subtractTwentyfiveButton.setBackground(new Color(245, 145, 145));
        subtractTwentyfiveButton.setBorder(BorderFactory.createEtchedBorder());
        subtractTwentyfiveButton.setVisible(false);

        subtractHundredButton = new JButton();
        subtractHundredButton.setBounds((550-80)/2-225, 600, 80, 60);
        subtractHundredButton.setText("-100");
        subtractHundredButton.setFont(new Font("Constantia", Font.BOLD, 35));
        subtractHundredButton.setHorizontalTextPosition(JButton.CENTER);
        subtractHundredButton.setVerticalTextPosition(JButton.CENTER);
        subtractHundredButton.setFocusable(false);
        subtractHundredButton.setForeground(new Color(50, 50, 50));
        subtractHundredButton.setBackground(new Color(245, 145, 145));
        subtractHundredButton.setBorder(BorderFactory.createEtchedBorder());
        subtractHundredButton.setVisible(false);

        quitButton = new JButton();
        quitButton.setBounds((550-120)/2, 600, 120, 60);
        quitButton.setText("QUIT");
        quitButton.setFont(new Font("Constantia", Font.BOLD, 40));
        quitButton.setHorizontalTextPosition(JButton.CENTER);
        quitButton.setVerticalTextPosition(JButton.CENTER);
        quitButton.setFocusable(false);
        quitButton.setForeground(new Color(50, 50, 50));
        quitButton.setBackground(new Color(245, 245, 245));
        quitButton.setBorder(BorderFactory.createEtchedBorder());
        quitButton.setVisible(false);

        //Window 3 items.
        
        dealerCardOne = new JLabel();
        dealerCardOne.setBounds((550-100)/2-60, (820-140)/2-150, 100, 140);

        dealerCardTwo = new JLabel();
        dealerCardTwo.setBounds((550-100)/2+60, (820-140)/2-150, 100, 140);

        dealerCardThree = new JLabel();
        dealerCardThree.setBounds((550-100)/2+120, (820-140)/2-150, 100, 140);

        dealerCardFour = new JLabel();
        dealerCardFour.setBounds((550-100)/2, (820-140)/2-300, 100, 140);

        dealerCardFive = new JLabel();
        dealerCardFive.setBounds((550-100)/2+60, (820-140)/2-300, 100, 140);

        dealerCardSix = new JLabel();
        dealerCardSix.setBounds((550-100)/2+120, (820-140)/2-300, 100, 140);

        playerCardOne = new JLabel();
        playerCardOne.setBounds((550-100)/2-60, (820-140)/2+30, 100, 140);

        playerCardTwo = new JLabel();
        playerCardTwo.setBounds((550-100)/2+60, (820-140)/2+30, 100, 140);

        playerCardThree = new JLabel();
        playerCardThree.setBounds((550-100)/2+120, (820-140)/2+30, 100, 140);

        playerCardFour = new JLabel();
        playerCardFour.setBounds((550-100)/2, (820-140)/2+180, 100, 140);

        playerCardFive = new JLabel();
        playerCardFive.setBounds((550-100)/2+60, (820-140)/2+180, 100, 140);

        playerCardSix = new JLabel();
        playerCardSix.setBounds((550-100)/2+120, (820-140)/2+180, 100, 140);
        
        hitButton = new JButton();
        hitButton.setBounds((550-150)/2-175, 675, 150, 60);
        hitButton.setText("HIT");
        hitButton.setFont(new Font("Constantia", Font.BOLD, 30));
        hitButton.setHorizontalTextPosition(JButton.CENTER);
        hitButton.setVerticalTextPosition(JButton.CENTER);
        hitButton.setFocusable(false);
        hitButton.setForeground(new Color(50, 50, 50));
        hitButton.setBackground(new Color(245, 245, 245));
        hitButton.setBorder(BorderFactory.createEtchedBorder());
        hitButton.setVisible(false);

        standButton = new JButton();
        standButton.setBounds((550-150)/2, 675, 150, 60);
        standButton.setText("STAND");
        standButton.setFont(new Font("Constantia", Font.BOLD, 30));
        standButton.setHorizontalTextPosition(JButton.CENTER);
        standButton.setVerticalTextPosition(JButton.CENTER);
        standButton.setFocusable(false);
        standButton.setForeground(new Color(50, 50, 50));
        standButton.setBackground(new Color(245, 245, 245));
        standButton.setBorder(BorderFactory.createEtchedBorder());
        standButton.setVisible(false);

        doubleButton = new JButton();
        doubleButton.setBounds((550-150)/2+175, 675, 150, 60);
        doubleButton.setText("DOUBLE");
        doubleButton.setFont(new Font("Constantia", Font.BOLD, 30));
        doubleButton.setHorizontalTextPosition(JButton.CENTER);
        doubleButton.setVerticalTextPosition(JButton.CENTER);
        doubleButton.setFocusable(false);
        doubleButton.setForeground(new Color(50, 50, 50));
        doubleButton.setBackground(new Color(245, 245, 245));
        doubleButton.setBorder(BorderFactory.createEtchedBorder());
        doubleButton.setVisible(false);

        playAgainButton = new JButton();
        playAgainButton.setBounds((550-220)/2+120, 675, 220, 60);
        playAgainButton.setText("PLAY AGAIN");
        playAgainButton.setFont(new Font("Constantia", Font.BOLD, 30));
        playAgainButton.setHorizontalTextPosition(JButton.CENTER);
        playAgainButton.setVerticalTextPosition(JButton.CENTER);
        playAgainButton.setFocusable(false);
        playAgainButton.setForeground(new Color(50, 50, 50));
        playAgainButton.setBackground(new Color(145, 245, 145));
        playAgainButton.setBorder(BorderFactory.createEtchedBorder());
        playAgainButton.setVisible(false);

        //Constant items.
        
        backgroundImage = new JLabel();
        backgroundImage.setIcon(background);
        backgroundImage.setBounds(0, 0, 570, 820);
        
        //Adding all of the items to the GUI.
        this.setVisible(true);
        this.add(titleLabelAndResult);
        this.add(startButton);
        this.add(balanceLabel);
        this.add(moneyAvailable);
        this.add(inputAmount);
        this.add(betButton);
        this.add(addOneButton);
        this.add(addFiveButton);
        this.add(addTwentyfiveButton);
        this.add(addHundredButton);
        this.add(subtractOneButton);
        this.add(subtractFiveButton);
        this.add(subtractTwentyfiveButton);
        this.add(subtractHundredButton);
        this.add(quitButton);
        this.add(dealerCardOne);
        this.add(dealerCardTwo);
        this.add(dealerCardThree);
        this.add(dealerCardFour);
        this.add(dealerCardFive);
        this.add(dealerCardSix);
        this.add(playerCardOne);
        this.add(playerCardTwo);
        this.add(playerCardThree);
        this.add(playerCardFour);
        this.add(playerCardFive);
        this.add(playerCardSix);
        this.add(hitButton);
        this.add(standButton);
        this.add(doubleButton);
        this.add(playAgainButton);
        this.add(backgroundImage);
        
        //When the startButton is pressed the window changes by enabling or disabling items on the GUI.
        //startButton sends you to the second screen.
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                titleLabelAndResult.setVisible(false);
                startButton.setVisible(false);
                balanceLabel.setVisible(true);
                moneyAvailable.setVisible(true);
                inputAmount.setVisible(true);
                betButton.setVisible(true);
                addOneButton.setVisible(true);
                addFiveButton.setVisible(true);
                addTwentyfiveButton.setVisible(true);
                addHundredButton.setVisible(true);
                subtractOneButton.setVisible(true);
                subtractFiveButton.setVisible(true);
                subtractTwentyfiveButton.setVisible(true);
                subtractHundredButton.setVisible(true);
                quitButton.setVisible(true);
            }
        }
        );

        //When the quitButton is pressed the window changes by enabling or disabling items on the GUI.
        //quitButton sends you to the first screen.
        quitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {                
                //Checks if any cards have been drawn yet and if so calls for the resetTable method.
                if(totalHands.getNumberOfCards()>0)
                {
                    resetTable();
                }
                
                titleLabelAndResult.setVisible(true);
                startButton.setVisible(true);
                balanceLabel.setVisible(false);
                moneyAvailable.setVisible(false);
                inputAmount.setVisible(false);
                betButton.setVisible(false);
                addOneButton.setVisible(false);
                addFiveButton.setVisible(false);
                addTwentyfiveButton.setVisible(false);
                addHundredButton.setVisible(false);
                subtractOneButton.setVisible(false);
                subtractFiveButton.setVisible(false);
                subtractTwentyfiveButton.setVisible(false);
                subtractHundredButton.setVisible(false);
                quitButton.setVisible(false);
                hitButton.setVisible(false);
                standButton.setVisible(false);
                doubleButton.setVisible(false);
                playAgainButton.setVisible(false);
            }
        }
        );

        //When the playAgainButton is pressed the window changes by enabling or disabling items on the GUI.
        //playAgainButton sends you to the second screen.
        playAgainButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if any cards have been drawn yet and if so calls for the resetTable method.
                if(totalHands.getNumberOfCards()>0)
                {
                    resetTable();
                }

                titleLabelAndResult.setVisible(false);
                startButton.setVisible(false);
                balanceLabel.setVisible(true);
                moneyAvailable.setVisible(true);
                inputAmount.setVisible(true);
                betButton.setVisible(true);
                addOneButton.setVisible(true);
                addFiveButton.setVisible(true);
                addTwentyfiveButton.setVisible(true);
                addHundredButton.setVisible(true);
                subtractOneButton.setVisible(true);
                subtractFiveButton.setVisible(true);
                subtractTwentyfiveButton.setVisible(true);
                subtractHundredButton.setVisible(true);
                quitButton.setVisible(true);
                playAgainButton.setVisible(false);
            }
        }
        );

        //When addOneButton is pressed the input value that appears in the inputAmount JTextField increases by 1.
        addOneButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to increase the value in the inputAmount JTextField by 1.
                try
                {
                    inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())+1));
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 1.
                catch (Exception e)
                {
                    inputAmount.setText("1");
                }
            }
        }
        );

        //When addFiveButton is pressed the input value that appears in the inputAmount JTextField increases by 5.
        addFiveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to increase the value in the inputAmount JTextField by 5.
                try
                {
                    inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())+5));
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 5.
                catch (Exception e)
                {
                    inputAmount.setText("5");
                }
            }
        }
        );

        //When addTwentyFiveButton is pressed the input value that appears in the inputAmount JTextField increases by 25.
        addTwentyfiveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to increase the value in the inputAmount JTextField by 25.
                try
                {
                    inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())+25));
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 25.
                catch (Exception e)
                {
                    inputAmount.setText("25");
                }
            }
        }
        );

        //When addHundredButton is pressed the input value that appears in the inputAmount JTextField increases by 100.
        addHundredButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to increase the value in the inputAmount JTextField by 100.
                try
                {
                    inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())+100));
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 100.
                catch (Exception e)
                {
                    inputAmount.setText("100");
                }
            }
        }
        );

        //When subtractOneButton is pressed the input value that appears in the inputAmount JTextField decreased by 1.
        subtractOneButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to decreasee the value in the inputAmount JTextField by 1.
                try
                {
                    //Checks if the value in the inputAmount JTextField is grater or equals to 1 and if so subtracts 1 from the value in the inputAmount JTextField.
                    if(Integer.parseInt(inputAmount.getText()) >= 1)
                    {
                        inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())-1));
                    }
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 0.
                catch (Exception e)
                {
                    inputAmount.setText("0");
                }
            }
        }
        );

        //When subtractFiveButton is pressed the input value that appears in the inputAmount JTextField decreased by 5.
        subtractFiveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to decreasee the value in the inputAmount JTextField by 5.
                try
                {
                    //Checks if the value in the inputAmount JTextField is grater or equals to 5 and if so subtracts 5 from the value in the inputAmount JTextField.
                    if(Integer.parseInt(inputAmount.getText()) >= 5)
                    {
                        inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())-5));
                    }
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 0.
                catch (Exception e)
                {
                    inputAmount.setText("0");
                }
            }
        }
        );

        //When subtractTwentyFiveButton is pressed the input value that appears in the inputAmount JTextField decreased by 25.
        subtractTwentyfiveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to decreasee the value in the inputAmount JTextField by 25.
                try
                {
                    //Checks if the value in the inputAmount JTextField is grater or equals to 25 and if so subtracts 25 from the value in the inputAmount JTextField.
                    if(Integer.parseInt(inputAmount.getText()) >= 25)
                    {
                        inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())-25));
                    }
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 0.
                catch (Exception e)
                {
                    inputAmount.setText("0");
                }
            }
        }
        );

        //When subtractHundredButton is pressed the input value that appears in the inputAmount JTextField decreased by 100.
        subtractHundredButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to decreasee the value in the inputAmount JTextField by 100.
                try
                {
                    //Checks if the value in the inputAmount JTextField is grater or equals to 100 and if so subtracts 100 from the value in the inputAmount JTextField.
                    if(Integer.parseInt(inputAmount.getText()) >= 100)
                    {
                        inputAmount.setText(Integer.toString(Integer.parseInt(inputAmount.getText())-100));
                    }
                }
                //If the attempt failed then the value in the inputAmount JTextField is set to 0.
                catch (Exception e)
                {
                    inputAmount.setText("0");
                }
            }
        }
        );
        
        //When the betButton is pressed the window changes by enabling or disabling items on the GUI.
        //playAgainButton sends you to the third screen if you have enough money to play.
        betButton.addActionListener(new ActionListener()
        {   
            public void actionPerformed(ActionEvent evt)
            {
                //Attempts to set the betAmount to an integer of 1 or the value of the inputAmount JTextField.
                try
                {
                    Integer.parseInt(inputAmount.getText());
                    //Checks if the player has enough money to play the game.
                    if(balance.getBalance() > 0)
                    {
                        //Sets the betAmount to 1 or the value of the inputAmount JTextField.
                        if(Integer.parseInt(inputAmount.getText()) <= 0)
                        {
                            betAmount = 1;
                        }
                        else if(Integer.parseInt(inputAmount.getText()) >= balance.getBalance())
                        {
                            betAmount = balance.getBalance();
                        }
                        else
                        {
                            betAmount = Integer.parseInt(inputAmount.getText());
                        }
                
                        c.addToHand();
                        dealerCardOne.setIcon(back);
                        dealerCardOne.setVisible(true);
                        winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                        dealerCardTwo.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + c.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                        dealerCardTwo.setVisible(true);
                        winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                        playerCardOne.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                        playerCardOne.setVisible(true);
                        winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
                        playerCardTwo.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                        playerCardTwo.setVisible(true);
                        winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
                
                        balanceLabel.setVisible(false);
                        moneyAvailable.setVisible(false);
                        inputAmount.setVisible(false);
                        betButton.setVisible(false);
                        addOneButton.setVisible(false);
                        addFiveButton.setVisible(false);
                        addTwentyfiveButton.setVisible(false);
                        addHundredButton.setVisible(false);
                        subtractOneButton.setVisible(false);
                        subtractFiveButton.setVisible(false);
                        subtractTwentyfiveButton.setVisible(false);
                        subtractHundredButton.setVisible(false);
                        quitButton.setVisible(false);
                        hitButton.setVisible(true);
                        standButton.setVisible(true);
                        doubleButton.setVisible(true);

                        hitButton.setEnabled(true);
                        standButton.setEnabled(true);
                        doubleButton.setEnabled(true);
                    }
                }
                //If the attempt failed then the code does nothing until the value of the inputAmount JTextField is valid.
                catch (Exception e)
                {
                }
            }
        }
        );

        //When the hitButton is pressed a card is added to the player's hand and the code checks if the player is busted.
        hitButton.addActionListener(new ActionListener()
        {            
            public void actionPerformed(ActionEvent evt)
            {
                personTurn();
                busted();
            }
        }
        );

        //When the standButton is pressed the turn is passed to the computer.
        standButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                computerTurn();
            }
        }
        );

        //When the doubleButton is pressed a card is added to the player's hand, betAmount doubles, the hit and double buttons get disabled, and the code checks if the player is busted.
        doubleButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                betAmount*=2;
                hitButton.setEnabled(false);
                doubleButton.setEnabled(false);
                personTurn();
                busted();
            }
        }
        );

        //When the leaderboard is selected, a new leaderboard window pops up with the scores of the save files that didn't enable the DevMode.
        leaderboard.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if a leaderboard window is already open, and if not, opens a new one.
                if (TransferClass.leaderboardStatus==false)
                {
                    TransferClass.leaderboardStatus=true;
                    new LeaderboardGUI();
                }
            }
        }
        );

        //When the save option is selected, a new save window pops up with an option to save your current progress.
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if a save window is already open, and if not, opens a new one.
                if (TransferClass.saveStatus==false)
                {
                    TransferClass.saveStatus=true;
                    new SaveGUI();
                }
            }
        }
        );

        //When the load option is seleted, a new load window pops up with an option to load your progress from another save.
        load.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if a load window is already open, and if not, opens a new one.
                if (TransferClass.loadStatus==false)
                {
                    TransferClass.loadStatus=true;
                    new LoadGUI();
                }
            }
        }
        );

        //When the enableDevMode option is seleted, a new Developer Mode window pops up with an option to enable devMode and cheats.
        enableDevMode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if a Developer Mode window is already open, and if not, opens a new one.
                if (TransferClass.devModeStatus == false)
                {
                    TransferClass.devModeStatus = true;
                    new DevModeGUI();
                }
            }
        }
        );

        //When the changeBalance option is seleted, a new Developer Mode window pops up with an option to change your balance.
        changeBalance.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Checks if a Developer Mode window is already open, and if not, opens a new one.
                if (TransferClass.devModeStatus == false)
                {
                    TransferClass.devModeStatus = true;
                    new DevModeGUI();
                }
            }
        }
        );
    }

    //Generates a menuBar at the top of the main Blackjack window.
    public void generateMenu()
    {
		menuBar = new JMenuBar();

		gameSave = new JMenu("Game");
		devMode = new JMenu("DevMode");

		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
        enableDevMode = new JMenuItem("Enable Dev Mode");
        changeBalance = new JMenuItem("Change Balance");
        changeBalance.setEnabled(false);
        leaderboard = new JButton("Leader Board");
        leaderboard.setFocusable(false);
        leaderboard.setBorder(null);
        leaderboard.setBackground(new Color(245, 245, 245));

		gameSave.add(save);
        gameSave.add(load);
		devMode.add(enableDevMode);
        devMode.addSeparator();
        devMode.add(changeBalance);

		menuBar.add(gameSave);
		menuBar.add(devMode);
        menuBar.add(leaderboard);
	}

    //When called runs a computer turn and controls the appearance of the computer's hand in the main Blackjack window.
    public void computerTurn()
    {
        dealerCardOne.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + totalHands.getCardAtDraw(0) + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
        ActionListener al = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (computerHand.getNumberOfCards()==2 && winCalculator.getComputerScore()<=winCalculator.getPersonScore() && winCalculator.getComputerScore()<21)
                {
                    dealerCardThree.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + c.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                    dealerCardThree.setVisible(true);
                    winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                    dealerCardOne.setBounds(dealerCardOne.getX()-60, dealerCardOne.getY(), 100, 140);
                    dealerCardTwo.setBounds(dealerCardTwo.getX()-60, dealerCardTwo.getY(), 100, 140);
                }
                else if (computerHand.getNumberOfCards()==3 && winCalculator.getComputerScore()<=winCalculator.getPersonScore() && winCalculator.getComputerScore()<21)
                {
                    dealerCardFour.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + c.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                    dealerCardFour.setVisible(true);
                    winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                }
                else if (computerHand.getNumberOfCards()==4 && winCalculator.getComputerScore()<=winCalculator.getPersonScore() && winCalculator.getComputerScore()<21)
                {
                    dealerCardFive.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + c.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                    dealerCardFive.setVisible(true);
                    winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                    dealerCardFour.setBounds(dealerCardFour.getX()-60, dealerCardFour.getY(), 100, 140);
                }
                else if (computerHand.getNumberOfCards()==5 && winCalculator.getComputerScore()<=winCalculator.getPersonScore() && winCalculator.getComputerScore()<21)
                {
                    dealerCardSix.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + c.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
                    dealerCardSix.setVisible(true);
                    winCalculator.addToComputerScore(deck.getCardValue(c.getHand().get(computerHand.getNumberOfCards()-1)));
                    dealerCardFour.setBounds(dealerCardFour.getX()-60, dealerCardFour.getY(), 100, 140);
                    dealerCardFive.setBounds(dealerCardFive.getX()-60, dealerCardFive.getY(), 100, 140);
                }
                while(winCalculator.getComputerScore()>21 && c.getNumberOfAces()>0)
                {
                    winCalculator.subtractFromComputerScore();
                    c.decreaseNumberOfAces();
                }
                if(computerHand.getNumberOfCards() == 6 || winCalculator.getComputerScore() > winCalculator.getPersonScore() || winCalculator.getComputerScore() == 21)
                {
                    timer.stop();//stop the task after done the work
                    if(winCalculator.getComputerScore()<=21)
                    {
                        endGame(winCalculator.getResult());
                        balance.changeBalance(winCalculator.getResult(), betAmount);
                        moneyAvailable.setText("$" + balance.getBalance());
                    }
                    else
                    {
                        endGame(1);
                        balance.changeBalance(1, betAmount);
                        moneyAvailable.setText("$" + balance.getBalance());
                    }
                }
            }
        };
        timer = new Timer(1000, al);//create the timer which calls the actionperformed method for every 1000 millisecond(1 second=1000 millisecond)
        timer.start();//start the task
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        doubleButton.setEnabled(false);
    }

    //When called determines which card in the player's hand goes where in the main Blackjack window, totals their value, and enables/disables the hit, stands, and double buttons.
    public void personTurn()
    {
        if(personHand.getNumberOfCards()==2)
        {
            playerCardThree.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
            playerCardThree.setVisible(true);
            winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
            playerCardOne.setBounds(playerCardOne.getX()-60, playerCardOne.getY(), 100, 140);
            playerCardTwo.setBounds(playerCardTwo.getX()-60, playerCardTwo.getY(), 100, 140);
        }
        else if(personHand.getNumberOfCards()==3)
        {
            playerCardFour.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
            playerCardFour.setVisible(true);
            winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
        }
        else if(personHand.getNumberOfCards()==4)
        {
            playerCardFive.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
            playerCardFive.setVisible(true);
            winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
            playerCardFour.setBounds(playerCardFour.getX()-60, playerCardFour.getY(), 100, 140);
        }
        else if(personHand.getNumberOfCards()==5)
        {
            playerCardSix.setIcon(new ImageIcon(new ImageIcon("Assets/Card Images/" + p.addToHand() + ".png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT)));
            playerCardSix.setVisible(true);
            winCalculator.addToPersonScore(deck.getCardValue(p.getHand().get(personHand.getNumberOfCards()-1)));
            playerCardFour.setBounds(playerCardFour.getX()-60, playerCardFour.getY(), 100, 140);
            playerCardFive.setBounds(playerCardFive.getX()-60, playerCardFive.getY(), 100, 140);
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
        }
        if(winCalculator.getPersonScore()>21 && p.getNumberOfAces()>0)
        {
            while(winCalculator.getPersonScore()>21 && p.getNumberOfAces()>0)
            {
                winCalculator.subtractFromPersonScore();
                p.decreaseNumberOfAces();
            }
        }
        else if(winCalculator.getPersonScore()>21)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            doubleButton.setEnabled(false);
        }
    }

    //When callet it checks If player's total cards value is greater than 21, and if so it ends the game by calling endgame() and decreases the balance because the player is busted.
    public void busted()
    {
        if (winCalculator.getPersonScore()>21)
        {
            endGame();
            balance.changeBalance(2, betAmount);
            moneyAvailable.setText("$" + balance.getBalance());
        }
    }

    //When called it repositions and sets visible/invisible some items in the main GUI window to represent the end of the game and shows different victory status labels dependin on the results of the game.
    public void endGame(int win)
    {
        quitButton.setBounds((550-220)/2-120, 675, 220, 60);
        quitButton.setBackground(new Color(245, 145, 145));
        titleLabelAndResult.setBounds((550-400)/2, (820-250)/2-65, 400, 250);
        //Decides what the victory label will be set to based on the results of the game.
        if(win==1)
        {
            titleLabelAndResult.setText("<html><div style='text-align: center;'>YOU<br/>WIN</html>");
            titleLabelAndResult.setForeground(new Color(255, 215, 0));
        }
        else if(win==3)
        {
            titleLabelAndResult.setText("<html><div style='text-align: center;'>STAND<br/>OFF</html>");
            titleLabelAndResult.setForeground(new Color(75, 75, 175));
        }
        else
        {
            titleLabelAndResult.setText("<html><div style='text-align: center;'>YOU<br/>LOSE</html>");
            titleLabelAndResult.setForeground(new Color(125, 125, 150));
        }
        
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        playAgainButton.setVisible(true);
        quitButton.setVisible(true);
        titleLabelAndResult.setVisible(true);
    }

    //When called it repositions and sets visible/invisible some items in the main GUI window to represent the end of the game and shows a large "BUSTED" label as a result.
    public void endGame()
    {
        quitButton.setBounds((550-220)/2-120, 675, 220, 60);
        quitButton.setBackground(new Color(245, 145, 145));
        titleLabelAndResult.setBounds((550-400)/2, (820-250)/2-65, 400, 250);
        titleLabelAndResult.setText("<html><div style='text-align: center;'>BUSTED</html>");
        titleLabelAndResult.setForeground(new Color(125, 125, 150));
        
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        playAgainButton.setVisible(true);
        quitButton.setVisible(true);
        titleLabelAndResult.setVisible(true);
    }

    //When called it resets everything to the state it was in before the last game.
    public static void resetTable()
    {
        winCalculator.reset();
        totalHands.resetDraws();
        
        quitButton.setBounds((550-120)/2, 600, 120, 60);
        quitButton.setBackground(new Color(245, 245, 245));
        titleLabelAndResult.setBounds((550-400)/2, 150, 400, 250);
        titleLabelAndResult.setText("<html><div style='text-align: center;'>BLACK<br/>JACK</html>");
        titleLabelAndResult.setForeground(new Color(245, 245, 245));

        dealerCardOne.setVisible(false);
        dealerCardTwo.setVisible(false);
        dealerCardThree.setVisible(false);
        dealerCardFour.setVisible(false);
        dealerCardFive.setVisible(false);
        dealerCardSix.setVisible(false);
        playerCardOne.setVisible(false);
        playerCardTwo.setVisible(false);
        playerCardThree.setVisible(false);
        playerCardFour.setVisible(false);
        playerCardFive.setVisible(false);
        playerCardSix.setVisible(false);

        dealerCardOne.setBounds((550-100)/2-60, (820-140)/2-150, 100, 140);
        dealerCardTwo.setBounds((550-100)/2+60, (820-140)/2-150, 100, 140);
        dealerCardThree.setBounds((550-100)/2+120, (820-140)/2-150, 100, 140);
        dealerCardFour.setBounds((550-100)/2, (820-140)/2-300, 100, 140);
        dealerCardFive.setBounds((550-100)/2+60, (820-140)/2-300, 100, 140);
        dealerCardSix.setBounds((550-100)/2+120, (820-140)/2-300, 100, 140);
        playerCardOne.setBounds((550-100)/2-60, (820-140)/2+30, 100, 140);
        playerCardTwo.setBounds((550-100)/2+60, (820-140)/2+30, 100, 140);
        playerCardThree.setBounds((550-100)/2+120, (820-140)/2+30, 100, 140);
        playerCardFour.setBounds((550-100)/2, (820-140)/2+180, 100, 140);
        playerCardFive.setBounds((550-100)/2+60, (820-140)/2+180, 100, 140);
        playerCardSix.setBounds((550-100)/2+120, (820-140)/2+180, 100, 140);
    }

    //When called it resets everything to the state that program was started in, except for the balance and moneyAvailable JLabel which will be the same as the balance of the loaded save.
    public static void resetAfterLoad()
    {
        resetTable();

        moneyAvailable.setText("$" + balance.getBalance());

        titleLabelAndResult.setVisible(true);
        startButton.setVisible(true);
        balanceLabel.setVisible(false);
        moneyAvailable.setVisible(false);
        inputAmount.setVisible(false);
        betButton.setVisible(false);
        addOneButton.setVisible(false);
        addFiveButton.setVisible(false);
        addTwentyfiveButton.setVisible(false);
        addHundredButton.setVisible(false);
        subtractOneButton.setVisible(false);
        subtractFiveButton.setVisible(false);
        subtractTwentyfiveButton.setVisible(false);
        subtractHundredButton.setVisible(false);
        quitButton.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        playAgainButton.setVisible(false);
    }

    //When called it determines whether the enableDevMode or changeBalance will be enabled.
    public static void enableCheats()
    {
        enableDevMode.setEnabled(!TransferClass.cheatsEnabled);
        changeBalance.setEnabled(TransferClass.cheatsEnabled);
    }

    //When called it updates the value of the moneyAvailable JLabel to the current balance.
    public static void updateBalance()
    {
        moneyAvailable.setText("$" + balance.getBalance());
    }
}