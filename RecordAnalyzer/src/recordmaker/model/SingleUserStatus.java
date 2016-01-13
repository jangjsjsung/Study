package recordmaker.model;

public class SingleUserStatus extends UserNumber  {
	 
	public int rowNumber;
	public boolean useCheck = true;
	 
	public SingleUserStatus(int rowNumber,String userNumber,boolean useCheck){
		this.rowNumber=rowNumber; 
		this.userNumber = userNumber;
		this.useCheck = useCheck;
	 }
}