//The class that stores all of the cards and is responisble for anything to do with the deck.
public class Cards
{
    //A 2D array that has the names of all the 52 cards stored in the Assets/Card Images folder as images.
    //It represents a standard deck of 52 cards.
    private String[][] deck = new String[][]
    {
        {"2_of_clubs", "3_of_clubs", "4_of_clubs", "5_of_clubs", "6_of_clubs", "7_of_clubs", "8_of_clubs", "9_of_clubs", "10_of_clubs", "jack_of_clubs2", "queen_of_clubs2", "king_of_clubs2", "ace_of_clubs"},
        {"2_of_diamonds", "3_of_diamonds", "4_of_diamonds", "5_of_diamonds", "6_of_diamonds", "7_of_diamonds", "8_of_diamonds", "9_of_diamonds", "10_of_diamonds", "jack_of_diamonds2", "queen_of_diamonds2", "king_of_diamonds2", "ace_of_diamonds"},
        {"2_of_hearts", "3_of_hearts", "4_of_hearts", "5_of_hearts", "6_of_hearts", "7_of_hearts", "8_of_hearts", "9_of_hearts", "10_of_hearts", "jack_of_hearts2", "queen_of_hearts2", "king_of_hearts2", "ace_of_hearts"},
        {"2_of_spades", "3_of_spades", "4_of_spades", "5_of_spades", "6_of_spades", "7_of_spades", "8_of_spades", "9_of_spades", "10_of_spades", "jack_of_spades2", "queen_of_spades2", "king_of_spades2", "ace_of_spades2"}
    };

    //When called it returns a random name of a card from the deck.
    public String drawRandomCard()
    {
        return deck[(int)(Math.random()*(4))][(int)(Math.random()*(13))];
    }

    //When called it returns the value of a card acording to the rules of Blackjack
    //Numbered cards equal to their number, picture cards equal to 10, and ace is 11 unless the total value is above 21 in which case it's 1.
    public int getCardValue(String cardName)
    {
        String temp = cardName.substring(0, cardName.indexOf("_"));
        //Chacks if the String is a number and if so it returns the number.
        try
        {
            return Integer.parseInt(temp);
        }
        //If the String is a word then it returns 10 if the String equals to jack, queen, or king, else retuns 11.
        catch (Exception e)
        {
            if (temp.equals("jack") || temp.equals("queen") || temp.equals("king"))
            {
                return 10;
            }
            else
            {
                return 11;
            }
        }
    }
}
