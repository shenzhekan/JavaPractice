package com.szk.test.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	
		 public static void main(String[] args) {
//		        int[] nums = {1,2,3,4,5};
//		        int target = 6;
			 	int[] nums = {-10,-1,-18,-19};
			 	int target = -19;
		        int[] result = twoSum(nums, target);
		        for (int i : result) {
					System.out.println(i);
				}
		 }
		 
		 //Runtime: 3 ms, faster than 99.77% of Java online submissions for Two Sum.
		 /**
		  * 将所有循环到的数据放入map中，每循环一个数据，使用target-nums【i️】的形式获取对应需求的值
		  * 并从map中匹配，若未匹配到则将本次数据存入，若匹配到则可以获取两个数据
		  * @param nums
		  * @param target
		  * @return
		  */
		 public static int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int index = 0;
			int[] result = new int[2];
	        for(int i = 0; i< nums.length; i++){
	        	if (map.isEmpty()) {
					map.put(nums[i], i);
				}else{
					index = target - nums[i];          
					if(map.containsKey(index)){
						result[0] = map.get(index);
						result[1] = i;
						return result;
					}else {
						map.put(nums[i], i);
					}
				}
	        }
	        return null;
		 }
		 
		 // 较上面的方法，这里直接删去了每次判断map是否为空，因为直接将第一个数据放入map中了
		 public static int[] twoSum2(int[] nums, int target) {
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				int index = 0;
				int[] result = new int[2];
				map.put(nums[0], 0);
		        for(int i = 1; i< nums.length; i++){
		        	
					index = target - nums[i];          
					if(map.containsKey(index)){
						result[0] = map.get(index);
						result[1] = i;
						return result;
					}else {
						map.put(nums[i], i);
					}
					
		        }
		        return null;
			 }
}
