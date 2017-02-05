package mergeSort;

import java.util.Random;
import lab1.ArrayBag;

public class MergeSort {

	public static void main(String[] args) {
		
		ArrayBag<Integer> a1 = new ArrayBag<>(1000);
		ArrayBag<Integer> a2 = new ArrayBag<>(1000);

		Random r = new Random();
		int[] array =new int[1000];
		System.out.println("random integers: ");
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(500);
			a1.add(array[i]);
		}
		
		System.out.println("mergeSortByIteration: ");
		array = mergeSortByIteration(array);
		for(int i = 0; i < array.length; i++) {
			a2.add(array[i]);
		}
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i + 1]) {
				System.out.println("*****Failed");
				break;
			}
		}
		
		//System.out.println(a1.equals(a2));
		
		a1.clear();
		a2.clear();
		System.out.println("random integers: ");
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(50);
			a1.add(array[i]);
		}
		System.out.println("mergeSortByRecursion: ");
		mergeSortByRecursion(array);
		for(int i = 0; i < array.length; i++) {
			a2.add(array[i]);
		}
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i + 1]) {
				System.out.println("*****Failed");
				break;
			}
		}
		//System.out.println(a1.equals(a2));
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
		merge(array, tempArray, 0, array.length - 1);
		
	}
	
	public static void merge(int[] array, int[] tempArray, int firstIndex, int lastIndex) {
		if((lastIndex - firstIndex) < 8) {
			sort(array, firstIndex, lastIndex);

		}
		else {
			int mid = (firstIndex + lastIndex) / 2;
			merge(array, tempArray, firstIndex, mid - 1);
			merge(array, tempArray, mid, lastIndex);	
			sortMergeParts(array, tempArray,firstIndex, lastIndex);
		}
	}//end merge
	
	private static void sortMergeParts(int[] array, int[] tempArray, int firstIndex, int lastIndex) {
		for(int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}
		int mid = (firstIndex + lastIndex) / 2;
		int index = firstIndex;
		int pointer1 = firstIndex;
		int pointer2 = mid;
		while(pointer1 < mid && pointer2 <= lastIndex) {
			if(tempArray[pointer1] <= tempArray[pointer2]) {
				array[index] = tempArray[pointer1];
				pointer1++;
				index++;
			}
			else {
				array[index] = tempArray[pointer2];
				pointer2++;
				index++;
			}
		}
		if(pointer1 > mid - 1) {
			while(pointer2 <= lastIndex) {
				array[index] = tempArray[pointer2];
				index++;
				pointer2++;
			}
		}
		if(pointer2 > lastIndex) {
			while(pointer1 < mid) {
				array[index] = tempArray[pointer1];
				index++;
				pointer1++;
			}
		}
	}
}
