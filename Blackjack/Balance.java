//This class is responsible for any changes and calculation related to the player's game balance.
public class Balance
{
    //Initial balance when starting a new game.
    private static int balance  = 100;

    //When called it returns the current balance.
    public int getBalance()
    {
        return balance;
    }
    
    //When called it increases or decreases the balance depending on whether the game was won or lost.
    public void changeBalance(int win, int betAmount)
    {
        if (win==1)
        {
            balance += betAmount;
        }
        else if (win==2)
        {
            balance -= betAmount;
        }
    }

    //When called it sets the balance to whatever integer is inputted into the savedBalance.
    public void setBalance(int savedBalance)
    {
        balance = savedBalance;
    }
}
