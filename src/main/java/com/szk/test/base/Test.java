package com.szk.test.base;

public class Test {
	  public static void main(String[] args) {
	      int i = titleToNumber("AJHX");
	      System.out.println("+++"+i);
	  }
	  
	  public static int titleToNumber(String s) {
	        char[] ch = s.toCharArray();
	        String[][] ch2 = new String[ch.length][1];
	        System.out.println(ch.length);
	        for (int i = ch.length-1; i >= 0; i--) {
				ch2[i][0] = ch[ch.length-1-i]+"";     
				System.out.println(ch2[i][0]+"--"+i);
			}
	        int i = 0;
	        int sum = 0;
	        int temp = 26;
	        for (int j = 0; j <= ch2.length-1; j++) {
	        	if(j == 0){
	        		sum += (ch2[j][0].toCharArray()[0] - 64);
	        		System.out.println(j+"--"+ch2[j][0].toCharArray()[0]);
	        	}else{
	        		for (int j2 = 0; j2 < j-1; j2++) {
						temp *= 26;
					}
	        		sum += (ch2[j][0].toCharArray()[0] - 64)*temp;
	        		System.out.println(j+"--"+ch2[j][0].toCharArray()[0]);
	        		temp = 26;
	        	}
	        	
	        }
	        
	        return sum;
	  }
	  
  	  public static int test1(String s){
    		char[] ch = s.toCharArray();
    		int sum = 0;
    		int temp = 0;
    		for (int i = 0; i < ch.length; i++) {
				temp = ch[i]-64;
				
			}
    		
    		return sum;
    
      }
}
