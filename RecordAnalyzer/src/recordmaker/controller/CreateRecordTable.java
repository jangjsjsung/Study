package recordmaker.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import recordmaker.model.SingleUserInformation;

public class CreateRecordTable{ 
	
	protected int recordCount =0;
	public SingleUserInformation [] recordTableList;
	
	public CreateRecordTable(int limitCount){	
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
