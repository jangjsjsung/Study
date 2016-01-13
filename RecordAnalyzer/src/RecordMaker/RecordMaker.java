package RecordMaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


class UserNumber{	
	protected String userNumber;
}

class SingleUserStatus extends UserNumber  {
	 
	public int rowNumber;
	public boolean useCheck = true;
	 
	SingleUserStatus(int rowNumber,String userNumber,boolean useCheck){
		this.rowNumber=rowNumber; 
		this.userNumber = userNumber;
		this.useCheck = useCheck;
	 }
}

class SingleUserInformation extends UserNumber  {
	
	protected int score;
	
	 SingleUserInformation(String userNumber){
		 this.score = getScore();
		 this.userNumber = userNumber; 
	 }
	 
	 SingleUserInformation(String userNumber,int score){
		 this.score = score;
		 this.userNumber = userNumber; 
	 }
	 
	 public int getScore() {
		 Random random = new Random();
		 int score =random.nextInt(100)+1;			 
		 return score;
	 }	   
}


class CreatingUserNumber{
	
	public int limitCount;
	public SingleUserStatus [] fullListUserNumbers;
	
	CreatingUserNumber(int limitCount,String filePathName){
		
		this.limitCount =limitCount;
		fullListUserNumbers = new SingleUserStatus[limitCount];
		
		int i =0;
		int iRowIdx = 0;
		String userNumber = "";
		 
		for(i=0 ;i<limitCount;i++){	
			
			 userNumber = String.format("NT%05d", i+1);			 
			 iRowIdx = i;
			 
			 fullListUserNumbers[i]  = new SingleUserStatus(iRowIdx,userNumber,true);						 						 
		}		
		
		readFile(filePathName);
	}
	
	public void readFile(String filePathName){
		
		File userNumbeTableFile=new File(filePathName);
		
		if (userNumbeTableFile.exists()) { 
			boolean result=userNumbeTableFile.isFile();
			
			if (result) {				
				
				BufferedReader bufferedReader = null;
				try {
					bufferedReader = new BufferedReader(new FileReader(userNumbeTableFile));
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
							
				String sLine = null;
				String userNumber ="";										
				
				try {
					while( (sLine =bufferedReader.readLine()) != null ){								
						
						userNumber= sLine.split(" ")[0].replace("NT", "");
						
						int rowNumber= Integer.valueOf(userNumber.replace("NT", "")).intValue()-1;						
						
						SingleUserStatus listUserNumber= fullListUserNumbers[rowNumber];
						
						setUseFlag(listUserNumber);											
						limitCount--;							
																
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
		
			}
		}else{
			 try {
				boolean result = userNumbeTableFile.createNewFile();
				if(result){
				   
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getRandomUserNumber(){		
		
		if(limitCount<1){
			System.out.println("NTID");
			return "";
		}		
		
		Random random = new Random();
		  
		int rowNumber =random.nextInt(limitCount);		 
			
		 
		String userNumber =randomUserNumber(rowNumber);		
		
		limitCount--;		
		return userNumber;
	}
	
	 public String randomUserNumber(int rowNumber){ 
		
		String userNumber = "";
		int i=0;
		for(SingleUserStatus each  :fullListUserNumbers){
			
			if(each.useCheck){
				
				if(i==rowNumber){
					userNumber= each.userNumber;
				
					SingleUserStatus listUserNumber= fullListUserNumbers[each.rowNumber];
					setUseFlag(listUserNumber);
				
					break;
				}
				i++;								
			}			
		}				
		 return userNumber;
	 }
		
	
	 public void print(){
		 System.out.println("======결과============================");  
		 for (SingleUserStatus each  :fullListUserNumbers){
	        System.out.println(each .userNumber);  
	    }
		 System.out.println("==================================");  
	 }
	
	 static void setUseFlag(SingleUserStatus listUserNumber){
		 listUserNumber.useCheck = false;
	       
	   }	
}


class CreateRecordTable{ 
	
	protected int recordCount =0;
	public SingleUserInformation [] recordTableList;
	
	CreateRecordTable(int limitCount){	
		recordTableList = new SingleUserInformation[limitCount];
	}
	
	public void addRecord(String userNumber){		
		
		recordTableList[recordCount]  = new SingleUserInformation(userNumber);			
		recordCount++;
	}	
	
	 public void saveFile(String filePathName){
		 
		 try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePathName, true));
			
			for(SingleUserInformation each  : recordTableList ){
				bufferedWriter.write( each .userNumber+" " +each .score+ "\n");
			}		
			
			bufferedWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }
	
	 public void print(){
		 System.out.println("===========생성 결과=============");  
		 for (SingleUserInformation each :recordTableList){
	        System.out.println(each.userNumber +" " +each.score);  
	     }
		 System.out.println("=================================");  
	 }
}


public class RecordMaker {
	static final int maxRecord = 100000;	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		int addCount = Integer.valueOf(args[0]).intValue();
		String filePathName=args[1];
			
		
		CreatingUserNumber userNumberTable = new CreatingUserNumber(maxRecord,filePathName);	
		
		CreateRecordTable recordTable = new CreateRecordTable(addCount);
				
		for(int i =0; i <addCount; i++){
			
			String userNumber =userNumberTable.getRandomUserNumber();
			
			if(!userNumber.equals("")){
				recordTable.addRecord(userNumber);
				
			}else{
				System.out.println("생성오류"); 
				break;				
			}
			
		}		
	
		recordTable.saveFile(filePathName);		
		
		recordTable.print();

	
	}

}
