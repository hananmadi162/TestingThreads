import java.util.ArrayList;

public class ThreadCheckArray implements Runnable 
{
	private boolean flag;
	private boolean [] winArray;
	SharedData sd;
	ArrayList<Integer> array_of_numbers;
	int b;
	
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array_of_numbers = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array_of_numbers.size()];
	}
	
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array_of_numbers.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array_of_numbers.get(n-1))
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array_of_numbers.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}

	public void run() {
		if (array_of_numbers.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array_of_numbers.size()-1, b - array_of_numbers.get(array_of_numbers.size() - 1));
			else 
				rec(array_of_numbers.size() -1, b);
		if (array_of_numbers.size() == 1)
			if (b == array_of_numbers.get(0) && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array_of_numbers.size() - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
