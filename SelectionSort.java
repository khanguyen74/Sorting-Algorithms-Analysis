
public class SelectionSort {
	public static SortingObject selectionSort(int[] list, int length)
	{
		long comparisons = 0, movements = 0, startTime, endTime;
		int totalTime;
		startTime =  System.currentTimeMillis();
		for (int i = 0; i< list.length - 1; i++)
		{
			int currentMin = list[i];
			int currentMinIndex = i;
			for (int j = i+1; j < list.length; j++)
			{
				comparisons++;
				if (currentMin > list[j])
				{
					currentMin = list[j];
					currentMinIndex = j;
					movements++;
				}
			}
			comparisons++;
			if (currentMinIndex != i)
			{
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
				movements++;
			}
		}
		endTime = System.currentTimeMillis();
		totalTime = (int) (endTime - startTime);
		return new SortingObject(length, comparisons, movements, totalTime);
	}
}
