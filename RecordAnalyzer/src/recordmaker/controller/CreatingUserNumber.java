package recordmaker.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import recordmaker.model.SingleUserStatus;

public class CreatingUserNumber{
	
	public int limitCount;
	public SingleUserStatus [] fullListUserNumbers;
	
	public CreatingUserNumber(int limitCount,String filePathName){
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