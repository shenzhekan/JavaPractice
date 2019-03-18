package com.szk.test.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class LengthOfLongestSubString {
	
	public static void main(String[] args){
		int max = lengthOfLongestSubstring("au");
		System.out.println(max);
	}
	
	/**
	 * 要考虑到Sting字符串为  "" -> 0 或者  " " -> 1
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        List<Character> list = new ArrayList<Character>();
        int max = 0;
        if(ch.length > 1){
	    	for (char c : ch) {
	          	// 当前字符已经存在
	          	// 删除之前重复数据及其前面的数据，
	          	// 在后面添加当前数据，并记录
	  			if(list.contains(c)){   
	  				if(list.size() > max){
	  					max = list.size();
	  				}
	  				// 经测试，这个三目运算符比if判断耗时，为求速度，果断注释
	  				//max = max > list.size() ? max : list.size();
	  				List<Character> subList = list.subList(0, list.indexOf(c)+1);
	  				list.removeAll(subList);
	  			}
	  			list.add(c);
      		}
        }
        if(max < list.size()){
        	max = list.size();
        }
        return max;
    }

}
