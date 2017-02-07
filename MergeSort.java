package mergeSort;

import java.util.Random;
import lab1.ArrayBag;

public class MergeSort {
	
	private static int[] tempArray;

	public static void main(String[] args) {
		
		ArrayBag<Integer> a1 = new ArrayBag<>(100000);
		ArrayBag<Integer> a2 = new ArrayBag<>(100000);

		Random r = new Random();
		int[] array =new int[100000];
		
		/*
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
		
		System.out.println(a1.equals(a2));
		
		a1.clear();
		a2.clear();
		
		*/
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
		System.out.println(a1.equals(a2));
	}
	
	public static int[] mergeSortByIteration(int[] array) {

		int mid = array.length / 2;
		sort(array, 0, mid);
		sort(array, mid + 1, array.length-1);			
		tempArray = new int[array.length];
		sortMergeParts(array, 0, array.length - 1);
		return array;
		
		
	}//end mergeSortByIteration
	
	private static void sort (int[] array, int first, int last) {
		for (int i = first; i <= last; i++) {
			int j = i;
			while((j > first) && (array[j] < array[j-1])) {
				swarp(array, j, j-1);
				j--;
			}//end while
		}//end for
	}//end sort

	public static void mergeSortByRecursion(int[] array) {
		tempArray = new int[array.length];
		merge(array, 0, array.length - 1);
		
	}
	
	public static void merge(int[] array, int firstIndex, int lastIndex) {
		
		if(lastIndex - firstIndex == 1) {
			if(array[firstIndex] > array[lastIndex]) {
				swarp(array,firstIndex, lastIndex);
			}
		}
		else if(lastIndex > firstIndex) {
			int mid = (firstIndex + lastIndex) / 2;
			merge(array, firstIndex, mid);
			merge(array, mid + 1, lastIndex);	
			sortMergeParts(array,firstIndex, lastIndex);
		}
		
		
	}//end merge
	
	private static void sortMergeParts(int[] array, int firstIndex, int lastIndex) {

		int mid = (firstIndex + lastIndex) / 2;
		int index = firstIndex;
		int pointer1 = firstIndex;
		int pointer2 = mid + 1;
		while(pointer1 <= mid && pointer2 <= lastIndex) {
			if(array[pointer1] <= array[pointer2]) {
				tempArray[index] = array[pointer1];
				pointer1++;
			}
			else {
				tempArray[index] = array[pointer2];
				pointer2++;
			}
			index++;
		}
		if(pointer1 > mid) {
			while(pointer2 <= lastIndex) {
				tempArray[index] = array[pointer2];
				index++;
				pointer2++;
			}
		}
		if(pointer2 > lastIndex) {
			while(pointer1 <= mid) {
				tempArray[index] = array[pointer1];
				index++;
				pointer1++;
			}
		}
		
		for(int i = firstIndex; i <= lastIndex; i++) {
			array[i] = tempArray[i];
		}
	}
	
	private static void swarp(int[] array, int firstIndex, int lastIndex) {
		int temp = array[firstIndex];
		array[firstIndex] = array[lastIndex];
		array[lastIndex] = temp;
	}
}
