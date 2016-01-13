package RecordAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Calculation {
	
	
	public String fileName ="";
	public String result = "";
	public String commandTyp = "";
	public int fileReadLineCount =0;
	
	
	Calculation(String fileName){
		this.fileName = fileName;		
	}	
	
	
	public void calculationCommand (String commandTyp ){
		
		this.commandTyp =commandTyp;
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sLine = null;		
		
		int su1 =-1;			
		
		try {
			while( (sLine = bufferedReader.readLine()) != null ){								
				
				int su2 = Integer.valueOf(sLine.split(" ")[1]).intValue();							
								
				switch (commandTyp) {				
				
				case "min":					
					su1 = min( su1, su2);
					break;
				case "max":
					su1 = max( su1, su2);
					break;
				case "avg":
					su1 = sum( su1, su2);
					break;
				
				}
				
				this.fileReadLineCount++;			
													
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if(commandTyp.equals("avg")){
			
			this.result = String.valueOf((float)su1/fileReadLineCount);	
			
		}else{
			this.result = String.valueOf(su1);
		}
				
	}	
	 public int min(int su1, int su2) {		 
		 int min;
		 
		 if(su1== -1) {
			 su1 = su2;			 
		 }			 
		 
		 if(su1<=su2) {
			 min =su1;			 
		 }else {
			 min =su2;			 
		 }		 
		 return min;
	 }
	 
	 public int max(int su1, int su2) {
		 
		 int max;
		 
		 if(su1>=su2){
			 max =su1;			 
		 }else{
			 max =su2;			 
		 }		 
		 return max;	
	 }	 
	 
	 public int sum(int su1, int su2) {	 

		 if(su1== -1){
			 su1 = 0;			 
		 }
		 return su1+su2;
	 } 
	 
	 public void print(){
		 System.out.println("===========결과 ("+this.commandTyp +")========");
		 System.out.println("Result:" + this.result);  		 
		 System.out.println("============================");  
	 }	 
}


public class RecordAnalyzer {		

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		if (args.length != 2) {
			throw new IllegalArgumentException();
		}
		
		String commandArg  = (args[0]);
		String fileName=args[1];
		
		Calculation command  = new Calculation(fileName);			
		
		command .calculationCommand(commandArg );				
		command .print();		
		
	}
}
