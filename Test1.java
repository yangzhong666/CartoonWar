package com.neudeu.homework2;

import java.security.Signature;

public class Test1 {
   //构建多选题
	
	public static void main(String[] args) {
		MultiChoice mc = new MultiChoice("您看中了那些车？", new String[] {"1.夏利","2.一汽","3.北汽","4.吉利"}, new int[] {1,2});
	    int[] answer =new int[] {1,3};
		boolean check = mc.check(answer);
		System.out.println(check);
	
	
		SingleChoice sc= new SingleChoice("您要买那辆车？", new String[] {"1.夏利","2.一汽","3.北汽","4.吉利"}, 3);
	   
		boolean check2 = sc.check(new int[] {3});
		System.out.println(check2);
}
		
}	
	

