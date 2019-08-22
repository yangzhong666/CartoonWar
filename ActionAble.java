package com.neuedu.action;
import java.awt.Graphics;
/**
* @ClassName: ActionAble
* @Description: 行为接口
* @author neuedu_yhl
* @date 2019年8月19日 下午3:42:37
*
*/
public interface ActionAble {
//移动的方法
	void move();
	//画方法
	void draw(Graphics g);
}
