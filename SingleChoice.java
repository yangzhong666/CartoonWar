package com.neudeu.homework2;

public class SingleChoice  extends Question {
    //ѡ��
	String[] optiosn;
	//��ѡ��׼��
	int answer;

	//	������
	public SingleChoice(String text,String[] optiosn, int answer) {
		this.text = text;
		this.optiosn = optiosn;
		this.answer = answer;
	}
	@Override
	public boolean check(int[] answers) {
		return this.answer == answers[0];
	}
	
	
	
}
