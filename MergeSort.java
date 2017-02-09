package mergeSort;

import java.util.Random;
import lab1.ArrayBag;

public class MergeSort {
	
	private static int[] tempArray;

	public static void main(String[] args) {
		
		ArrayBag<Integer> a1 = new ArrayBag<>(1000);
		ArrayBag<Integer> a2 = new ArrayBag<>(1000);

		Random r = new Random();
		int[] array =new int[1000];
		
		
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(500);
			a1.add(array[i]);
		}
		System.out.println("mergeSortByIteration: ");
		mergeSortByIteration(array);
		for(int i = 0; i < array.length; i++) {
			a2.add(array[i]);
		}
		boolean pass = true;
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i + 1]) {
				System.out.println("*****Failed");
				pass = false;
				break;
			}
		}
		
		if(a1.equals(a2) && pass) {
			System.out.println("Sorted.");
		}
		System.out.println();
		a1.clear();
		a2.clear();
		
		
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(100000);
			a1.add(array[i]);
		}
		
		System.out.println("mergeSortByRecursion: ");
		mergeSortByRecursion(array);
		
		for(int i = 0; i < array.length; i++) {
			a2.add(array[i]);
		}
		pass = true;
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i + 1]) {
				System.out.println("*****Failed");
				pass = false;
				break;
			}
		}
		
		if(a1.equals(a2) && pass) {
			System.out.println("Sorted.");
		}
	}
	
	public static void mergeSortByIteration(int[] array) {

		int mid;
		int firstIndex = 0;
		int lastIndex = array.length - 1;
		tempArray = new int[array.length];
		int range = 1;
		while(range < array.length) {
			while(firstIndex < array.length - range) {
				mid = range + firstIndex - 1;
				if(lastIndex < firstIndex + range * 2 -1) {
					combineMergeParts(array, firstIndex, lastIndex, mid);
				}
				else {
					combineMergeParts(array, firstIndex, firstIndex + range * 2 -1, mid);
				}
				firstIndex += range * 2;
			}
			firstIndex = 0;
			range *= 2;
		}

	}//end mergeSortByIteration
	

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
			combineMergeParts(array,firstIndex, lastIndex, mid);
		}
		
		
	}//end merge
	
	private static void combineMergeParts(int[] array, int firstIndex, int lastIndex, int mid) {

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
	}// end combineMergeParts
	
	private static void swarp(int[] array, int firstIndex, int lastIndex) {
		int temp = array[firstIndex];
		array[firstIndex] = array[lastIndex];
		array[lastIndex] = temp;
	}
	
}
