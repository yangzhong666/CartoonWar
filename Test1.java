package com.neudeu.homework2;

import java.security.Signature;

public class Test1 {
   //������ѡ��
	
	public static void main(String[] args) {
		MultiChoice mc = new MultiChoice("����������Щ����", new String[] {"1.����","2.һ��","3.����","4.����"}, new int[] {1,2});
	    int[] answer =new int[] {1,3};
		boolean check = mc.check(answer);
		System.out.println(check);
	
	
		SingleChoice sc= new SingleChoice("��Ҫ����������", new String[] {"1.����","2.һ��","3.����","4.����"}, 3);
	   
		boolean check2 = sc.check(new int[] {3});
		System.out.println(check2);
}
		
}	
	

