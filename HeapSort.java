
class Heap
{
	private java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
	private long comparisons, movements;
	public Heap()
	{}
	public Heap(Integer[] objects)
	{
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}
	
	public void add(Integer temp2)
	{
		list.add(temp2);
		int currentIndex = list.size() - 1;
		
		while (currentIndex > 0)
		{
			int parentIndex = (currentIndex - 1) / 2;
			comparisons++;
			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0)
			{
				movements++;
				Integer temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			}
			else
				break;
			
			currentIndex = parentIndex;
		}
	}
	
	public Integer remove()
	{
		if(list.size() == 0) return null;
		
		Integer removedObject = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		int currentIndex = 0;
		while (currentIndex < list.size())
		{
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;
			
			if (leftChildIndex >= list.size()) break;
			int maxIndex = leftChildIndex;
			comparisons++;
			if (rightChildIndex < list.size())
			{
				comparisons++;
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0)
				{
					maxIndex = rightChildIndex;
				}
			}
			comparisons++;

			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0)
			{
				Integer temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
				movements++;
			}
			else
				break;
		}
		return removedObject;
	}
	public long getSize()
	{
		return list.size();
	}
	public long getMovements()
	{
		return movements;
	}
	
	public long getComparisons()
	{
		return comparisons;
	}
}

public class HeapSort {

	public static SortingObject heapSort(int[] temp)
	{
		int comparisons = 0, movements = 0;
		int totalTime;
		long startTime, endTime;
		startTime =  System.currentTimeMillis(); //current time before sorting
		Heap heap = new Heap();
		
		for (int i = 0; i < temp.length; i++)
		{
			heap.add(temp[i]);
		}
		
		for (int i = temp.length - 1; i >= 0; i--)
			temp[i] = heap.remove();
		endTime = System.currentTimeMillis(); //current time after sorting
		totalTime = (int) (endTime - startTime); //total time
		return new SortingObject(temp.length, heap.getComparisons(),heap.getMovements(), totalTime);
	}
}


