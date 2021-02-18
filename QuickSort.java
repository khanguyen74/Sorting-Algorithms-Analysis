
public class QuickSort {
	public static long comparisons = 0, movements = 0;
	public static int totalTime;
	public static SortingObject quickSort(int[] list, int length) {
		long startTime, endTime;
		comparisons = 0;
		movements = 0;
		startTime =  System.currentTimeMillis(); //current time before sorting
		quickSort(list, 0, list.length-1);
		endTime = System.currentTimeMillis(); // current time after sorting
		totalTime = (int) (endTime - startTime); //total time
		return new SortingObject(length, comparisons, movements, totalTime);
	}
	public static void quickSort(int[] list, int first, int last)
	{
		if (last > first)
		{
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex);
			quickSort(list, pivotIndex + 1, last);
		}
	}
	public static int partition(int[] list, int first, int last) {

		int pivot= list[first + (last - first) / 2];
		int i = first -1;
		int j = last + 1;
		while (true)
		{
			do
			{
				i++;
//				movements++;
				comparisons++;
			} while (list[i] < pivot);
			do
			{
				j--;
//				movements++;
				comparisons++;
			}while (list[j] > pivot);
			if (i>=j)
				return j;
			movements++;
			int temp = list[i];
			list[i] = list[j];
			list[j]= temp;
		}
	}
}

