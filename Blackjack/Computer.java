import java.util.ArrayList;

//This is the code responsible for most of the behaviors needed during the computer turn.
public class Computer extends Player
{
    private ArrayList<String> previousComputerDraws = new ArrayList<String>();
    private int numberOfComputerAces = 0;

    //When called it adds a card to the previousComputerDraws and the totalHand.
    public String addToHand()
    {
        previousComputerDraws.add(playRandomCard());
        if(previousComputerDraws.get(previousComputerDraws.size()-1).substring(0, (previousComputerDraws.get(previousComputerDraws.size()-1)).indexOf("_")).equals("ace"))
        {
            numberOfComputerAces++;
        }
        return previousComputerDraws.get(previousComputerDraws.size()-1);
    }

    //When called it returns the number of cards in the previousComputerDraws.
    public int getNumberOfCards()
    {
        return previousComputerDraws.size();
    }

    //When called it retuns the whole previousComputerDraws ArrayList.
    public ArrayList<String> getHand()
    {
        return previousComputerDraws;
    }

    //When called it decreases the numberOfComputerAces by 1.
    public void decreaseNumberOfAces()
    {
        numberOfComputerAces--;
    }

    //When called it increases the numberOfComputerAces by 1.
    public int getNumberOfAces()
    {
        return numberOfComputerAces;
    }

    //When called it resets the previousComputerDraws list to an empty arrayList.
    public void resetDraws()
    {
        previousComputerDraws.removeAll(previousComputerDraws);
    }

    //When called it resets the numberOfComputerAces to be equal to 0.
    public void resetAces()
    {
        numberOfComputerAces = 0;
    }
}
