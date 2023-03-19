import java.util.ArrayList;

public class SharedData 
{
	private ArrayList<Integer> array_of_numbers;
	private boolean [] winArray;
	private boolean flag;
	private final int b;
	
	public SharedData(ArrayList<Integer> array, int b) {
		
		this.array_of_numbers = array;
		this.b = b;
	}

	public boolean[] getWinArray() 
	{
		return winArray;
	}

	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}

	public ArrayList<Integer> getArray() 
	{
		return array_of_numbers;
	}

	public int getB() 
	{
		return b;
	}

	public boolean getFlag() 
	{
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
