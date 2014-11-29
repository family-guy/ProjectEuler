import java.util.*;
import java.io.*;
/**
 * Problem 54
 */

public class Problem54 {
	public static void main(String[] arg) {
		FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem54/p054_poker.txt", "read");
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to open file", e);
		}
		try {
			int nbPlayerOneWins = 0;
			while (true) {
				String str = file.read();
				if (str == null) break;
				String playerOneHand = getPlayerOneHandVals(str);
				String playerTwoHand = getPlayerTwoHandVals(str);
				int playerOneRank = rank(playerOneHand, getPlayerOneHandSuits(str));
				int playerTwoRank = rank(playerTwoHand, getPlayerTwoHandSuits(str));
				if (playerOneRank > playerTwoRank) nbPlayerOneWins++;
				else if (playerOneRank == playerTwoRank) {
					if (playerOneRank == 0) {
						if (tieBreaker(sortDesc(playerOneHand), sortDesc(playerTwoHand))) nbPlayerOneWins++;
					}
					if (playerOneRank == 1) {
						int a = valueOfPair(playerOneHand);
						int b = valueOfPair(playerTwoHand);
						if (a > b) nbPlayerOneWins++;
						if (a == b) {
							if (tieBreaker(sortDesc(playerOneHand), sortDesc(playerTwoHand))) nbPlayerOneWins++;
						}
					}
					if (playerOneRank == 2) {
						int a = valueOfTwoPair(playerOneHand);
						int b = valueOfTwoPair(playerTwoHand);
						if (a > b) nbPlayerOneWins++;
						if (a == b) {
							int[] playerOneFreq = freq(playerOneHand);
							int[] playerTwoFreq = freq(playerTwoHand);
							int c = 0; int d = 0;
							for (int i = 0; i < playerOneFreq.length; i++) {
								if (playerOneFreq[i] == 2 && i != a) c = i;
								if (playerTwoFreq[i] == 2 && i != b) d = i;
							}
							if (c > d) nbPlayerOneWins++;
							if (c == d) {
								if (tieBreaker(sortDesc(playerOneHand), sortDesc(playerTwoHand))) nbPlayerOneWins++;
							}
						}
					}
					if (playerOneRank == 3 || playerOneRank == 6) {
						int a = valueOfTriple(playerOneHand);
						int b = valueOfTriple(playerTwoHand);
						if (a > b) nbPlayerOneWins++;
					}
					if (playerOneRank == 7) {
						int a = valueOfFourOfAKind(playerOneHand);
						int b = valueOfFourOfAKind(playerTwoHand);
						if (a > b) nbPlayerOneWins++;
					}
					if (playerOneRank == 4 || playerOneRank == 5 || playerOneRank == 8) {
						if (tieBreaker(sortDesc(playerOneHand), sortDesc(playerTwoHand))) nbPlayerOneWins++;
					}
				}	 
			}
			System.out.println("The solution is " + nbPlayerOneWins);	
		}
  		catch (IOException e) {
  			throw new RuntimeException("Failed to read file", e);
  		}
	}
	
	/**
	* Helper function for returning the value of a certain ranking
	*/
	public static int valueAux(String hand, int n) {
		int[] freq = freq(hand); int i = 0;
		while (i < freq.length) {
			if (freq[i] == n) break;
			i++;
		}
		return i;
	}
	
	/**
	* Takes a hand of ranking 'one pair' and returns its value e.g. "JJ854" returns 11
	*/
	public static int valueOfPair(String hand) {
		return valueAux(hand, 2);
	}
	
	/**
	* Takes a hand of ranking 'two pair' and returns its value e.g. "JJ844" returns 11
	*/
	public static int valueOfTwoPair(String hand) {
		int[] freq = freq(hand); int i = freq.length - 1;
		while (i > -1) {
			if (freq[i] == 2) break;
			i--;
		}
		return i;
	}
	
	/**
	* Takes a hand of ranking 'three of a kind' or 'full house' and returns its value e.g. "JJJ54" returns 11
	*/
	public static int valueOfTriple(String hand) {
		return valueAux(hand, 3);
	}
	
	/**
	* Takes a hand of ranking 'four of a kind' and returns its value e.g. "JJJ4J" returns 11
	*/
	public static int valueOfFourOfAKind(String hand) {
		return valueAux(hand, 4);
	}

	/**
	* Takes a string representing card values and returns that string in descending order
	*/
	public static String sortDesc(String hand) {
		String order = "23456789TJQKA";
		if (hand.length() == 1) return hand;
		String lastCard = hand.substring(hand.length() - 1);
		int lastCardVal = order.indexOf(lastCard);
		String partialSolution = sortDesc(hand.substring(0, hand.length() - 1));
		int pos = 0;
		while (pos < partialSolution.length()) {
			if (lastCardVal >= order.indexOf(partialSolution.substring(pos, pos + 1))) break;
			pos++;
		}
		if (pos < partialSolution.length()) return partialSolution.substring(0, pos) + lastCard + partialSolution.substring(pos);
		return partialSolution.substring(0, pos) + lastCard;
	}
	
	/**
	* Takes two 'high card' hands sorted in descending order and returns true if player one wins, false otherwise
	*/
	public static boolean tieBreaker(String playerOneHand, String playerTwoHand) {
		String order = "23456789TJQKA";
		for (int i = 0; i < playerOneHand.length(); i++) {
			int playerOneVal = order.indexOf(playerOneHand.substring(i, i + 1));
			int playerTwoVal = order.indexOf(playerTwoHand.substring(i, i + 1));
			if (playerOneVal != playerTwoVal) return playerOneVal > playerTwoVal;
		}
		return false; // the function should never get here
	}
	
	/**
	* Helper function for getPlayerOneHandVals, getPlayerTwoHandVals, getPlayerOneHandSuits, getPlayerTwoHandSuits
	*/
	public static String getHandInfoAux(String str, int k) {
		String result = "";
		for (int i = 0; i < 5; i++) {
			int j = i * 3 + k;
			result += str.substring(j, j + 1);
		}
		return result;
	}
	
	/**
	* Returns a string representing the values in a hand of player 1
	*/
	public static String getPlayerOneHandVals(String str) {
		return getHandInfoAux(str, 0);
	}
	
	/**
	* Returns a string representing the values in a hand of player 2
	*/
	public static String getPlayerTwoHandVals(String str) {
		return getHandInfoAux(str, 15);
	}
	
	/**
	* Returns a string representing the suits in a hand of player 1
	*/
	public static String getPlayerOneHandSuits(String str) {
		return getHandInfoAux(str, 1);
	}
	
	/**
	* Returns a string representing the suits in a hand of player 2
	*/
	public static String getPlayerTwoHandSuits(String str) {
		return getHandInfoAux(str, 16);
	}
	
	/**
	* Takes a string of length 5 representing the suits of a hand e.g. "DDHCS" and returns whether all cards are of the same suit
	*/
	public static boolean sameSuit(String hand) {
		for (int i = 0; i < hand.length() - 1; i++) {
			if (!(hand.substring(i, i + 1)).equals(hand.substring(i + 1, i + 2))) return false;
		}
		return true;
	}
	
	/**
	* Takes a string of length 5 representing the values of a hand sorted in descending order e.g. "AJT53" and returns whether they are consecutive values
	*/
	public static boolean consecVal(String hand) {
		String order = "23456789TJQKA";
		for (int i = 0; i < hand.length() - 1; i++) {
			String str1 = hand.substring(i, i + 1);
			String str2 = hand.substring(i + 1, i + 2);
			int index1 = order.indexOf(str1); int index2 = order.indexOf(str2);
			if (index1 - index2 != 1) return false;
		}
		return true;
	}
	
	/**
	* Takes a string of length 5 representing the values of a hand e.g. "53AJT" and returns the frequencies of each possible value in that hand
	*/
	public static int[] freq(String hand) {
		int[] result = new int[15]; // array is zero indexed hence 15 elements are required
		String order = "23456789TJQKA";
		for (int i = 0; i < hand.length(); i++) {
			String str = hand.substring(i, i + 1);
			int index = order.indexOf(str) + 2;
			result[index]++;
		}
		return result;
	}
	
	/**
	* Takes an array of integers and returns the maximum
	*/
	public static int max(int[] T) {
		int max = T[0];
		for (int i = 1; i < T.length; i++) {
			if (T[i] > max) max = T[i];
		}
		return max;
	}
	
	/**
	* Takes the values and suits of a hand and returns its ranking
	*/
	public static int rank(String values, String suits) {
		if (consecVal(sortDesc(values)) && sameSuit(suits)) return 8; // straight flush
		else {
			int[] freq = freq(values);
			int maxFreq = max(freq);
			if (maxFreq == 4) return 7; // four of a kind
			else {
				if (sameSuit(suits)) return 5; // flush
				if (consecVal(sortDesc(values))) return 4; // straight
				if (maxFreq == 3) {
					int i = 0;
					while (i < freq.length) {
						if (freq[i] == 1) break;
						i++;
					}
					if (i == freq.length) return 6; // full house
					return 3; // three of a kind
				}
				if (maxFreq == 2) {
					int nbPairs = 0;
					for (int i = 0; i < freq.length; i++) {
						if (freq[i] == 2) nbPairs++;
					}
					if (nbPairs == 2) return 2; // two pairs
					return 1; // one pair
				}
				return 0; // high card
			}
		}
	}
}