package recordmaker;

import recordmaker.controller.CreateRecordTable;
import recordmaker.controller.CreatingUserNumber;

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
