
public class SortingObject 
{
	private long length, comparisons, movements;
	long totalTime;
	public SortingObject() 
	{
		length = 0;
		comparisons = 0;
		movements = 0;
		totalTime = 0;
	}
	
	public SortingObject(long length, long comparisons, long movements, long totalTime) 
	{
		this.length = length;
		this.comparisons = comparisons;
		this.movements = movements;
		this.totalTime = totalTime;
	}
	
	public long getLength()
	{
		return length;
	}
	
	public long getComparisons()
	{
		return comparisons;
	}
	
	public long getMovements() 
	{
		return movements;
	}
	
	public long getTotalTime() 
	{
		return totalTime;
	}
	
}
