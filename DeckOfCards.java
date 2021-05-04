import java.util.Random;
import java.util.Arrays;

public class DeckOfCards {
   private static final String[] faces = 
      {"Ace", "Deuce", "Three", "Four", "Five", "Six", 
        "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
   private static final String[] suits = 
      {"Hearts", "Diamonds", "Clubs", "Spades"};

   // random number generator
   private static final Random randomNumbers = new Random(); 

   private static final int NUMBER_OF_CARDS = 52; // constant number of cards
   private static final int ONEPAIR = 2;
   private static final int TWOPAIR = 4;
   private static final int THREEKIND = 6;
   private static final int STRAIGHT = 8;
   private static final int FULLHOUSE = 10;
   private static final int FLUSH = 12;
   private static final int FOURKIND = 14;
   private static final int STRAIGHTFLUSH = 16;

   private Card[] deck; // array of Card objects
   private int currentCard; // the index of the next Card to be dealt
   private boolean straightHand1, straightHand2, pair1, pair2;
   private int hand1Value, hand2Value;

   // constructor fills deck of cards
   public DeckOfCards() {
      deck = new Card[NUMBER_OF_CARDS]; // create array of Card objects
      currentCard = 0; // initialize currentCard 

      // populate deck with Card objects
      for (int count = 0; count < deck.length; count++)  {
         deck[count] = new Card(faces[count % 13], suits[count / 13]);
      }
   } 

   // shuffle deck of cards with one-pass algorithm
   public void shuffle() {
      currentCard = 0; // reinitialize currentCard

      // for each card, pick another random card and swap them
      for (int first = 0; first < deck.length; first++)  {
         int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
         Card temp = deck[first];        
         deck[first] = deck[second];   
         deck[second] = temp;            
      } 
   } 

   // deal one card
   public Card dealCard() {
      // determine whether cards remain to be dealt
      if (currentCard < deck.length) {
         return deck[currentCard++]; // return current Card in array
      }
      else {        
         return null; // return null to indicate that all cards were dealt
      }
   } 

   // tally the number of each face card in hand
   private int[] totalHand(Card hand[]) {
      int[] numbers = new int[faces.length]; // store number of face

      // initialize all elements of numbers to zero
      for (int i = 0; i < 13; i++) {
         numbers[i] = 0;
      }

      // compare each card in the hand to each element in the faces array
      for (int h = 0; h < hand.length; h++) {
         for (int f = 0; f < 13; f++) {
            if (hand[h].getFace() == faces[f]) {
               ++numbers[f];
            }
         } 
      } 

      return numbers;
   } 

   // determine if hand contains pairs
   public void pairs(Card[] hand1, Card[] hand2) {
      int numberPairs1 = 0; // number of pairs in hand1
      int numberPairs2 = 0; // number of pairs in hand2
      int highest1 = 0; // highest number of pair in hand1
      int highest2 = 0; // highest number of pair in hand2
      int[] numbers1 = totalHand(hand1); // tally the number of each 
      int[] numbers2 = totalHand(hand2); // face in hand1 and hand2

      // count number of pairs in hands
      for (int k = 0; k < faces.length; k++) {
         // pair found in hand 1
         if (numbers1[k] == 2) {
            pair1 = true;

            // store highest pair
            if (k == 0) {
               highest1 = 13; // special value for ace
            }

            if (k > highest1) {
               highest1 = k;
            }

            ++numberPairs1;
         } 

         // pair found in hand 2
         if (numbers2[k] == 2) {
            pair2 = true;

            // store highest pair
            if (k == 0) {
               highest2 = 13; // special value for ace
            }

            if (k > highest2) {
               highest2 = k;
            }

            ++numberPairs2;
         } 
      } 

      // evaluate number of pairs in each hand
      if (numberPairs1 == 1) {
         hand1Value = ONEPAIR;
      }
      else if (numberPairs1 == 2) {
         hand1Value = TWOPAIR;
      }

      if (numberPairs2 == 1) {
         hand2Value = ONEPAIR;
      }
      else if (numberPairs2 == 2) {
         hand2Value = TWOPAIR;
      }

      if (highest1 > highest2) {
         ++hand1Value;
      }
      else if (highest2 > highest1) {
         ++hand2Value;
      }
   }

   // determine if hand contains a three of a kind
   public void threeOfAKind(Card[] hand1, Card[] hand2) {
      int tripletValue1 = 0; // highest triplet value in hand1
      int tripletValue2 = 0; // highest triplet value in hand2
      boolean flag1 = false;
      boolean flag2 = false;
      int[] numbers1 = totalHand(hand1); // tally the number of each 
      int[] numbers2 = totalHand(hand2); // face in hand1 and hand2

      // check for three of a kind
      for (int k = 0; k < faces.length; k++) {
         // three of a kind found in hand 1
         if (numbers1[k] == 3) {
            hand1Value = THREEKIND;
            flag1 = true;

            // store value of triplet
            if (k == 0) {
               tripletValue1 = 13; // special value for ace
            }

            if (k > tripletValue1) {
               tripletValue1 = k;
            }

            if (pair1 == true) {
               hand1Value = FULLHOUSE;
            }
         } 

         // three of a kind found in hand 2
         if (numbers2[k] == 3) {
            hand2Value = THREEKIND;
            flag2 = true;

            // store value of triplet
            if (k == 0) {
               tripletValue2 = 13;   // special value for ace
            }

            if (k > tripletValue2) {
               tripletValue2 = k;
            }

            if (pair2 == true) {
               hand2Value = FULLHOUSE;
            }
         } 
      } 

      // both hands have three of a kind,
      // determine which triplet is higher in value
      if (flag1 == true && flag2 == true) {
         if (tripletValue1 > tripletValue2) {
            ++hand1Value;
         }
         else if (tripletValue1 < tripletValue2) {
            ++hand2Value;
         }
      } 
   } 

   // determine if hand contains a four of a kind
   public void fourOfAKind(Card[] hand1, Card[] hand2) {
      int highest1 = 0;
      int highest2 = 0;
      boolean flag1 = false;
      boolean flag2 = false;
      int[] numbers1 = totalHand(hand1); // tally the number of each
      int[] numbers2 = totalHand(hand2); // face in hand1 and hand2

      // check for four of a kind
      for (int k = 0; k < faces.length; k++) {
         // hand 1
         if (numbers1[k] == 4) {
            hand1Value = FOURKIND;
            flag1 = true;

            if (k == 0) {
               highest1 = 13; // special value for ace
            }

            if (k > highest1) {
               highest1 = k;
            }
         } 

         // hand 2
         if (numbers2[k] == 4) {
            hand2Value = FOURKIND;
            flag2 = true;

            if (k == 0) {
               highest2 = 13;   // special value for ace
            }

            if (k > highest2) {
               highest2 = k;
            }
         } 
      } 

      // if both hands contain four of a kind, determine which is higher
      if (flag1 == true && flag2 == true) {
         if (highest1 > highest2) {
            ++hand1Value;
         }
         else if (highest1 < highest2) {
            ++hand2Value;
         }
      } 
   } 

   // determine if hand contains a flush
   public void flush(Card[] hand1, Card[] hand2) {
      String hand1Suit = hand1[0].getSuit();
      String hand2Suit = hand2[0].getSuit();
      boolean flag1 = true, flag2 = true;

      // check hand1
      for (int s = 1; s < hand1.length && flag1 == true; s++) {
         if (hand1[s].getSuit() != hand1Suit) {
            flag1 = false;   // not a flush
         }
      } 

      // check hand2
      for (int s = 1; s < hand2.length && flag2 == true; s++) {
         if (hand2[s].getSuit() != hand2Suit) {
            flag2 = false;   // not a flush
         }
      } 

      // hand 1 is a flush
      if (flag1 == true) {
         hand1Value = FLUSH;

         // straight flush
         if (straightHand1 == true) {
            hand1Value = STRAIGHTFLUSH;
         }
      } 

      // hand 2 is a flush
      if (flag2 == true) {
         hand2Value = FLUSH;

         // straight flush
         if (straightHand2 == true) {
            hand2Value = STRAIGHTFLUSH;
         }
      } 
   } 

   // determine if hand contains a straight
   public void straight(Card[] hand1, Card[] hand2) {
      int[] locations1 = new int[5];
      int[] locations2 = new int[5];
      int value;
      int[] numbers1 = totalHand(hand1); // tally the number of each 
      int[] numbers2 = totalHand(hand2); // face in hand1 and hand2

      // check each card in both hands
      for (int y = 0, z = 0; y < numbers1.length; y++) {
         if (numbers1[y] == 1) {
            locations1[z++] = y;
         }
      } 

      for (int y = 0, z = 0; y < numbers2.length; y++) {
         if (numbers1[y] == 1) {
            locations1[z++] = y;
         }
      } 

      int faceValue = locations1[0];
      boolean flag1 = true, flag2 = true;

      if (faceValue == 0) { // special case, faceValue is Ace
         faceValue = 13;
  
         for (int m = locations1.length - 1; m >= 1; m--) {
            if (faceValue != locations1[m] + 1) {
               return; // not a straight
            }
            else {
               faceValue = locations1[m];
            }
         } 
      } 
      else {
         for (int m = 1; m < locations1.length; m++) {
            if (faceValue != locations1[m] - 1) {
               return; // not a straight
            }
            else {
               faceValue = locations1[m];
            }
         } 
      } 

      faceValue = locations2[0];

      if (faceValue == 0) { // special case, faceValue is Ace
         faceValue = 13;
  
         for (int m = locations2.length - 1; m >= 1; m--) {
            if (faceValue != locations2[m] + 1) {
               return; // not a straight
            }
            else {
               faceValue = locations2[m];
            }
         } 
      } 
      else {
         for (int m = 1; m < locations2.length; m++) {
            if (faceValue != locations2[m] - 1) {
               return; // not a straight
            }
            else {
               faceValue = locations2[m];
            }
         } 
      } 

      int highest1 = 0;
      int highest2 = 0;

      // hand 1 is a straight
      if (flag1 == true) {
         straightHand1 = true;
         hand1Value = STRAIGHT;

         if (locations1[0] != 0) {
            highest1 = locations1[4];
         }
         else {
            highest1 = 13;
         }
      } 

      // hand 2 is a straight
      if (flag2 == true) {
         straightHand2 = true;
         hand2Value = STRAIGHT;

         if (locations2[0] != 0) {
            highest2 = locations2[4];
         }
         else {
            highest2 = 13;
         }
      } 

      // if both hands contain straights,
      // determine which is higher
      if (straightHand1 == true && straightHand2 == true) {
         if (highest1 > highest2) {
            ++hand1Value;
         }
         else if (highest2 > highest1) {
            ++hand2Value;
         }
      } 
   }

   // compare two hands
   public void compareTwoHands(Card[] hand1, Card[] hand2) {
      // calculate contents of the two hand
      pairs(hand1, hand2);
      threeOfAKind(hand1, hand2);
      fourOfAKind(hand1, hand2);
      straight(hand1, hand2);
      flush(hand1, hand2);
      displayHandValues(); // display hand values

      int[] numbers1 = totalHand(hand1); // tally the number of each 
      int[] numbers2 = totalHand(hand2); // face in hand1 and hand2
      int highestValue1 = 0;
      int highestValue2 = 0;

      // calculate highest value in hand1
      if (numbers1[0] > 0) { // hand1 contains Ace
         highestValue1 = 13;
      }
      else {
         for (int i = 1; i < numbers1.length; i++) {
            if (numbers1[i] > 0) {
               highestValue1 = i;
            }
        }
      }

      // calculate highest value in hand2
      if (numbers2[0] > 0) { // hand2 contains Ace
         highestValue2 = 13;
      }
      else {
        for (int i = 1; i < numbers2.length; i++) {
            if (numbers2[i] > 0) {
               highestValue2 = i;
            }
        }
      } 

      /* compare and display result
      if (hand1Value > hand2Value) {
         System.out.println("\nResult: left hand is better");
      }
      else if (hand1Value < hand2Value) {
         System.out.println("\nResult: right hand is better");
      }
      else {
         // test for the highest card
         if (highestValue1 > highestValue2) {
            System.out.println("\nResult: left hand is better");
         }
         else if (highestValue1 < highestValue2) {
            System.out.println("\nResult: right hand is better");
         }
         else {
            System.out.println("\nResult: they are equal");
         }
      } */
   }

   // display hand values
   public void displayHandValues() {
      String[] handValue = {"none", "none"};
      int value = hand1Value;

      for (int i = 0; i < 2; i++) {
         if (i == 1) {
            value = hand2Value;
         }

         switch (value) {
            case 2: case 3:
               handValue[i] = "One Pair";
               break;
            case 4: case 5:
               handValue[i] = "Two Pair";
               break;
            case 6: case 7:
               handValue[i] = "Three of a Kind";
               break;
            case 8: case 9:
               handValue[i] = "Straight";
               break;
            case 10: case 11:
               handValue[i] = "Full House";
               break;
            case 12: case 13:
               handValue[i] = "Flush";
               break;
            case 14: case 15:
               handValue[i] = "Four of a Kind";
               break;
            case 16:
               handValue[i] = "Straight Flush";
               break;
         }
      }


      }

      System.out.printf("%-20s", handValue[0]);
      System.out.printf("%-20s\n", "Hidden value");

   }
}