//This class is responsible for various calculations related to the victory conditions.
public class WinCalculator
{    
    private int personScore;
    private int computerScore;
    
    //When called it compares the personScore to the computerScore and returns an integer between 1-3 (inclusive) that represent which is greater and thus who won.
    public int getResult()
    {
        if (personScore > computerScore)
        {
            return 1;
        }
        else if (personScore < computerScore)
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }

    //When called it adds the integer cardValue to the personScore.
    public void addToPersonScore(int cardValue)
    {
        personScore+=cardValue;
    }

    //When called it adds the integer cardValue to the computerScore.
    public void addToComputerScore(int cardValue)
    {
        computerScore+=cardValue;
    }

    //When called it subtracts the integer cardValue from the personScore.
    public void subtractFromPersonScore()
    {
        personScore-=10;
    }

    //When called it subtracts the integer cardValue from the computerScore.
    public void subtractFromComputerScore()
    {
        computerScore-=10;
    }

    //When called it returns the personScore.
    public int getPersonScore()
    {
        return personScore;
    }
    
    //When called it returns the computerScore.
    public int getComputerScore()
    {
        return computerScore;
    }

    //When called it sets both the personScore and the computerScore to 0.
    public void reset()
    {
        personScore=0;
        computerScore=0;
    }
}
