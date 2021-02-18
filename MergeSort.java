
public class MergeSort {
	public static long comparisons = 0, movements = 0;
	public static int totalTime;
	
	public static SortingObject mergeSort(int[] list)
	{
		long startTime, endTime;
		comparisons = 0;
		movements = 0;
		startTime =  System.currentTimeMillis(); //get current time right before sorting
		Sort(list);
		endTime = System.currentTimeMillis(); //get current time right after sorting
		totalTime = (int) (endTime - startTime); //total time
		return new SortingObject(list.length, comparisons, movements, totalTime);
	}
	public static void Sort(int[] list)
	{

		if (list.length > 1)
		{
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf,0 , list.length / 2);
			Sort(firstHalf);
			
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			Sort(secondHalf);
			merge(firstHalf, secondHalf, list);
		}

	}
	
	public static void merge(int[] list1, int[] list2, int[] temp)
	{
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;
		
		while (current1 < list1.length && current2 < list2.length)
		{
			comparisons++;
			if (list1[current1] < list2[current2])
			{
				temp[current3++] = list1[current1++];
				movements++;
			}
			else
			{
				temp[current3++] = list2[current2++];
				movements++;
			}
		}
		while (current1 < list1.length)
		{
			movements++;
			temp[current3++] = list1[current1++];
		}
		
		while (current2 < list2.length)
		{
			movements++;
			temp[current3++] = list2[current2++];
		}
	}
}
