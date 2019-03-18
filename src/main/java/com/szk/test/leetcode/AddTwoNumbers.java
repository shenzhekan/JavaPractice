package com.szk.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.szk.pojo.ListNode;

class Solution {
	
	// When I Wrote this , only God and I understood what I was doing
	// no comments for you 
	// it was hard to wrote
	// so it should be hard to read
	// Now, God only knows
	// Runtime: 21 ms, faster than 93.53% of Java online submissions for Add Two Numbers.
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode list1 = l1;
		ListNode list2 = l2;
		ListNode resultNode = new ListNode(-1);    // 最低位
		ListNode headNode = new ListNode(-1);
		int val1 = 0;
		int val2 = 0;
		int temp = 0;
		int flag = 0;   // 用于显示是否需要进位
		int resVal = 0;
		while(list1 != null || list2 != null)
		{
			val1 = 0;
			val2 = 0;
			if(list1 != null){
				val1 = list1.val;
				list1 = list1.next;
			}
			if(list2 != null){
				val2 = list2.val;
				list2 = list2.next;
			}
			if((temp = (val1+val2+flag)) < 10){
				resVal = temp;
				flag = 0;
			}else {
				resVal = temp-10;
				flag = 1;
			}
			if(resultNode.val == -1){
				resultNode.val = resVal;
				headNode = resultNode;
			}else{
				ListNode newNode = new ListNode(resVal);
				resultNode.next  = newNode;
				resultNode = newNode;
			}
		}
		// 当判断完链表数据后，还存在进位flag，接着往结果中存
		if(flag == 1){
            ListNode newNode = new ListNode(1);
            resultNode.next  = newNode;
            resultNode = newNode;
        }
		return headNode;
	}

}

public class AddTwoNumbers {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);
    
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    
    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }
    
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
        	System.out.println("------start stringToListNode------");
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);
            System.out.println("-----end stringToListNode------");
            ListNode ret = new Solution().addTwoNumbers(l1, l2);
            System.out.println("-----end the solution");
            String out = listNodeToString(ret);
            
            System.out.print(out);
        }
    }
}
