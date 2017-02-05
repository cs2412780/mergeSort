package mergeSort;

import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {

		Random r = new Random();
		int[] array =new int[10];
		System.out.println("random integers: ");
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(50);
			System.out.println(array[i]);
		}
		
		System.out.println("mergeSortByIteration: ");
		array = mergeSortByIteration(array);
		for(int i = 0; i < array.length; i++) {
			 System.out.println(array[i]);
		}
		
		System.out.println("random integers: ");
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(50);
			System.out.println(array[i]);
		}
		System.out.println("mergeSortByRecursion: ");
		mergeSortByRecursion(array);
		for(int i = 0; i < array.length; i++) {
			 System.out.println(array[i]);
		}
		
	}
	
	public static int[] mergeSortByIteration(int[] array) {
		if (array.length > 1) {
			int mid = array.length / 2;
			sort(array, 0, mid - 1);
			sort(array, mid, array.length-1);
			
			int[] newArray = new int[array.length];
			int pointer1 = 0;
			int pointer2 = mid;
			int index = 0;
			int indexOfSmaller = 0;
			while((pointer1 < mid) && (pointer2 < array.length)) {
				indexOfSmaller = compare(array, pointer1, pointer2);
				newArray[index] = array[indexOfSmaller];
				if (indexOfSmaller < mid) {
					pointer1++;
				}
					
				else {
					pointer2++;
				}
				index++;
			}
			if(pointer1 >= mid) {
				while(pointer2 < array.length) {
					newArray[index] = array[pointer2];
					index++;
					pointer2++;
				}
			}
			if(pointer2 >= array.length) {
				while(pointer1 < mid) {
					newArray[index] = array[pointer1];
					index++;
					pointer1++;
				}
			}
			return newArray;
		}
		
		return array;
		
	}//end mergeSortByIteration
	
	private static void sort (int[] array, int first, int last) {
		for (int i = first; i <= last; i++) {
			int j = i;
			while((j > first) && (array[j] < array[j-1])) {
				int temp = array[j];
				array[j] = array[j-1];
				array[j-1] = temp;
				j--;
			}//end while
		}//end for
	}//end sort
	
	private static int compare(int[] array, int pointer1, int pointer2) {
		if(array[pointer1] <= array[pointer2]) {
			return pointer1;
		}
		else 
			return pointer2;	
	}//end compare

	public static void mergeSortByRecursion(int[] array) {
		int[] tempArray = new int[array.length];
		merge(array, tempArray,0, array.length - 1);
		
	}
	
	public static void merge(int[] array,int[] tempArray, int first, int last) {
		if((last - first) < 1)
			;
		else if((last - first) < 5) {
			sort(array, first, last);
		}
		else {
			int mid = (first + last) / 2;
			merge(array,tempArray,first, mid);
			merge(array, tempArray,mid + 1, last);	
			sortMergeParts(array, tempArray,first, last);
		}
	}//end merge
	
	private static void sortMergeParts(int[] array, int[] tempArray, int first, int last) {
		for(int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}
		int mid = (first + last) / 2;
		int pointer1 = first;
		int pointer2 = mid + 1;
		while(pointer1 < mid + 1 && pointer2 < last) {
			if(tempArray[pointer1] <= tempArray[pointer2]) {
				array[first] = tempArray[pointer1];
				pointer1++;
				first++;
			}
			else {
				array[first] = tempArray[pointer2];
				pointer2++;
				first++;
			}
		}
		if(pointer1 >= mid) {
			while(pointer2 < array.length) {
				array[first] = tempArray[pointer2];
				first++;
				pointer2++;
			}
		}
		if(pointer2 >= array.length) {
			while(pointer1 < mid) {
				array[first] = tempArray[pointer1];
				first++;
				pointer1++;
			}
		}
	}
}
