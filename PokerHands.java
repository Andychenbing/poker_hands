package projecteuler51to60;
import java.awt.peer.SystemTrayPeer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

class Card{
	public final int rank;
	public final int suit;
	public Card(int rank,int suit){
		if(rank<0 || rank>= 13 ||suit< 0||suit>= 4)
			throw new IllegalAccessError();
		this.rank= rank;
		this.suit= suit;
	}
	public Card(String str){
		this("23456789TJQKA".indexOf(str.charAt(0)), "SHCD".indexOf(str.charAt(1)));  //牌的大小和类型
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;
		Card other = (Card)obj;
		return rank == other.rank && suit == other.suit;
	}


	public int hashCode() {
		return rank * 4 + suit;
	}

}

class p54{
	void solve0(){
		String filenameString="poker.txt";
		readTxtFile(filenameString);
	}
	int Judge(String[] twoManCard){
		Card[] player1 = new Card[5];
		Card[] player2 = new Card[5];

		for(int i=0;i<5;i++){
			player1[i] = new Card(twoManCard[i+0]);
			player2[i] = new Card(twoManCard[i+5]);
		}
		if (getScore(player1) > getScore(player2))
			return 1;
		return 0;
	}
	int getScore(Card[] hand){
		if(hand.length !=5)
			throw new IllegalArgumentException();
		int[] rankCounts = new int[13];
		int flushSuit = hand[0].suit;
		for(Card card:hand){
			rankCounts[card.rank]++;
			if(card.suit!=flushSuit)
				flushSuit = -1;
		}
		int[] rankCountHist = new int[6];
		for(int i=0;i<rankCounts.length;i++)
			rankCountHist[rankCounts[i]]++;
		int bestCards = get5FrequnetHighestCards(rankCounts, rankCountHist);
		int straightHishRank = getStraighHighRank(rankCounts);
		if(flushSuit!=-1)System.out.println("ok");
		// Straight flush
		if(straightHishRank!=-1 && flushSuit!=-1)          return 8<<20|straightHishRank;
			// Four of a kind
		else if(rankCountHist[4]==1)                     return 7<<20|bestCards;
			// Full house
		else if(rankCountHist[3]==1&&rankCountHist[2]==1)return 6<<20|bestCards;
			// Flush
		else if(flushSuit!=-1)                             return 5<<20|bestCards;
			// Straight
		else if(straightHishRank!=-1)                     return 4<<20|straightHishRank;
			// Three of a kind
		else if(rankCountHist[3]==1)                     return 3<<20|bestCards;
			// Two pairs
		else if(rankCountHist[2]==2)                     return 2<<20|bestCards;
			// One pair
		else if(rankCountHist[2]==1)                     return 1<<20|bestCards;
			// High card
		else                                              return 0<<20|bestCards;
	}
	int get5FrequnetHighestCards(int[] ranks,int[] ranksHist)  {
		int result = 0;
		int count =0;
		for(int i=ranksHist.length-1;i>=0;i--){
			for(int j=ranks.length-1;j>=0;j--){
				if(ranks[j]==i){
					for(int k=0;k<i&&count<5;k++,count++)
						result=result<<4|j;
				}
			}
		}

		return result;
	}
	int getStraighHighRank(int[] ranks){
		outer:
		for(int i=ranks.length -1;i>=3;i--){
			for(int j=0;j<5;j++){
				if(ranks[(i-j+13)%13] ==0)
					continue outer;
			}
			return i;
		}
		return -1;
	}

	void readTxtFile(String filename){
		File fl =new File(filename);
		int res=0;
		FileReader reader=null;
		try {
			reader = new FileReader(fl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(reader);
		String line="";
		try {
			while((line=br.readLine())!=null){
				String[] twoManCard = line.split(" ");
				res+=Judge(twoManCard);

			}
		} catch (IOException e) {
			System.out.println("no file");
		}
		System.out.println(res);
	}


}
public class Poker_hands {
	public static void main(String[] args){
		long begin= System.currentTimeMillis();
		new p54().solve0();
		long end = System.currentTimeMillis();
		long Time = end - begin;
	}

}