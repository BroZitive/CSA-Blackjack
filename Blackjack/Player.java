import java.util.ArrayList;

//This is the code that is responsible for various actions of all the players and by extension computers and persons.
public class Player
{
    private static ArrayList<String> totalDraws = new ArrayList<String>();
    private Cards deck = new Cards();

    //When called it draws a random card, checks if it's already been drawn, and if not it adds it to the totalDraws.
    public String playRandomCard()
    {
        String playedCard = deck.drawRandomCard();
        for (String previousCard : totalDraws)
        {
            if (playedCard.equals(previousCard))
            {
                return playRandomCard();
            }
        }
        totalDraws.add(playedCard);
        return playedCard;
    }

    //When called it retuns a specific card that is located in the totalDraws at the integer draw.
    public String getCardAtDraw(int draw)
    {
        return totalDraws.get(draw);
    }

    //When called it returns an integer representing the number of totalDraws by both the computer and the person.
    public int getNumberOfCards()
    {
        return totalDraws.size();
    }

    //When called it resets all of the draws and the number of aces that the computer and person had.
    public void resetDraws()
    {
        totalDraws.removeAll(totalDraws);
        GUI.p.resetDraws();
        GUI.c.resetDraws();
        GUI.p.resetAces();
        GUI.c.resetAces();
    }
}