import java.util.Arrays;

public class DeckOfCardsTest {
   public static void printDealersCards(Card[] hand){
      System.out.printf("Dealer's hand:\n");

      for (int i = 0; i < hand.length; i++) {
         System.out.printf("%-20s\n", hand[i]);
      }
   }
   public static void makeABetterHand(Card[] hand, DeckOfCards myDeckOfCards){
      hand[0] = myDeckOfCards.dealCard();
      hand[1] = myDeckOfCards.dealCard();
      hand[2] = myDeckOfCards.dealCard();
   }
   // execute application
   public static void main(String[] args) {
      DeckOfCards myDeckOfCards = new DeckOfCards();
      myDeckOfCards.shuffle(); // place Cards in random order

      Card[] hand1 = new Card[5]; // store first hand
      Card[] hand2 = new Card[5]; // store second hand

      // get first five cards
      for (int i = 0; i < 5; i++) {
         hand1[i] = myDeckOfCards.dealCard(); // get next card
         hand2[i] = myDeckOfCards.dealCard(); // get next card
      }
      // hand 1 is the player's hand, the player will choose whether to hold or trade up to 3 cards
      // TODO: do this later

      printDealersCards(hand2);
      // hand2 is the dealers hand
      // find out what action the dealer should take with their hand
      makeABetterHand(hand2, myDeckOfCards);
      printDealersCards(hand2);

      // print hand1 and hand2
      System.out.printf("%-20s%-20s\n", "Your hand:", "Dealer's hand:");

      for (int i = 0; i < hand1.length; i++) {
         System.out.printf("%-20s%-20s\n", hand1[i], "Hidden card");
      }

      // display result
      System.out.println("\nHand Values:");
      myDeckOfCards.compareTwoHands(hand1, hand2); // compare two hands
   }

}