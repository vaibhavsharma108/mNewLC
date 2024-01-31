package changeFast.mediumThinking;

import java.util.Arrays;

public class NextPermutation {
	public static void main(String[] args) {
		 int [] nums = {1,2,3};
		 
		 nextPermutation(nums);
		 Arrays.stream(nums).forEach(s -> {System.out.println(s);});
	}

	public static void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		
		while(nums[i] >= nums[i + 1]) {
			i--;
		}
		
		int ngIndex  = i ;
		while((ngIndex + 1) < nums.length && nums [i] < nums[ngIndex + 1]) {
			ngIndex++;
		}
		
		int temp = nums[i];
		nums[i] = nums[ngIndex];
		nums[ngIndex] = temp;
		
		reverse(nums, i + 1);
	}
	
	private static void reverse(int[] nums, int i) {
		int start = i , end = nums.length - 1;
		
		while(start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

}
