/**
 * This class get a list of numbers and using insertionSort to sort the array and return the data
 * @author Kha Nguyen
 *
 */
public class InsertionSort {
	SortingObject data;
	public static SortingObject insertionSort(int[] list, int length) {
		long comparisons = 0, movements = 0;
		int totalTime;
		long startTime, endTime;
		startTime =  System.currentTimeMillis();
		for (int i = 1; i< length; i++) {
			int currentElement = list[i];
			int j;
			for (j = i-1; j >= 0 && list[j] > currentElement; j--) {
				movements++;
				comparisons++;
				list[j+1] = list[j];
				
			}
			list[j+1] = currentElement;
		}
		endTime = System.currentTimeMillis();
		totalTime = (int) (endTime - startTime);
		return new SortingObject(length, comparisons, movements, totalTime);
	}
}
