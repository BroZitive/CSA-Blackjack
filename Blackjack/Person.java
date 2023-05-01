import java.util.ArrayList;

//This is the code responsible for most of the behaviors needed during the person turn.
public class Person extends Player
{
    private ArrayList<String> previousPersonDraws = new ArrayList<String>();
    private int numberOfPersonAces = 0;

    //When called it adds a card to the previousPersonDraws and the totalHand.
    public String addToHand()
    {
        previousPersonDraws.add(playRandomCard());
        if(previousPersonDraws.get(previousPersonDraws.size()-1).substring(0, (previousPersonDraws.get(previousPersonDraws.size()-1)).indexOf("_")).equals("ace"))
        {
            numberOfPersonAces++;
        }
        return previousPersonDraws.get(previousPersonDraws.size()-1);
    }

    //When called it returns the number of cards in the previousPersonDraws.
    public int getNumberOfCards()
    {
        return previousPersonDraws.size();
    }

    //When called it retuns the whole previousPersonDraws ArrayList.
    public ArrayList<String> getHand()
    {
        return previousPersonDraws;
    }

    //When called it decreases the numberOfPersonAces by 1.
    public void decreaseNumberOfAces()
    {
        numberOfPersonAces--;
    }

    //When called it increases the numberOfPersonAces by 1.
    public int getNumberOfAces()
    {
        return numberOfPersonAces;
    }

    //When called it resets the previousPersonDraws list to an empty arrayList.
    public void resetDraws()
    {
        previousPersonDraws.removeAll(previousPersonDraws);
    }

    //When called it resets the numberOfPersonAces to be equal to 0;
    public void resetAces()
    {
        numberOfPersonAces = 0;
    }
}