package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.neuedu.action.ActionAble;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Prop
* @Description: ����������
* @author neuedu_yhl
* @date 2019��8��21�� ����10:19:03
*
*/
public class Prop extends GameObj implements ActionAble{

	public Prop() {
		
	}
	private  int speed;
	public Prop(int x, int y,String imgName)
	{
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 5;
	}
	
	
	
	private double theta = Math.PI/4;
	public void move() {
		
		 
		  x += speed*Math.cos(theta);
		  y += speed*Math.sin(theta);
		  if(x<0 || x>Constant.Game_WIDTH-img.getWidth(null))
		  {
		   theta = Math.PI-theta;
		  }
		  if(y<35 || y>Constant.Game_HEIGHT-img.getHeight(null))
		  {
		   theta = -theta;
		  }
		 }	
		
	

	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);
		move();
	}
    
	//�õ���ǰ���ߴ��ڵľ���
	public Rectangle getRect() 
	{
		return new Rectangle(x, y,this.img.getWidth(null), this.img.getHeight(null));
	}
	
	
}
