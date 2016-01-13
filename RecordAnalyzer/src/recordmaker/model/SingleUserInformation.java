package recordmaker.model;

import java.util.Random;

public class SingleUserInformation extends UserNumber  {
	
	public int score;
	
	 public SingleUserInformation(String userNumber){
		 this.score = getScore();
		 this.userNumber = userNumber; 
	 }
	 
	 public SingleUserInformation(String userNumber,int score){
		 this.score = score;
		 this.userNumber = userNumber; 
	 }
	 
	 public int getScore() {
		 Random random = new Random();
		 int score =random.nextInt(100)+1;			 
		 return score;
	 }	   
}