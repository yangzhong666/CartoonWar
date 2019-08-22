package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

public class Boss extends Star implements ActionAble {
   private boolean up = true;
	
	private int speed = 2;
	public Boss() {
		// TODO Auto-generated constructor stub
	}
	
	public Boss(int x,int y,GameClient gc,boolean isGood)
	{
		this.x = x;
		this.y = y;
		this.gc = gc; 
		this.isGood = isGood;
		this.blood = 10000;
	}
	
	
	//动态初始化一个图片数组
	private static Image[] imgs =  new Image[6];
	static 
	{
		for (int i = 0; i < imgs.length; i++)
		{
			imgs[i] = GetImageUtil.getImg("boss/0"+(i+1)+".png");
		}
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count > 5)
		{
			count = 0;
		}
		g.drawImage(imgs[count++], x, y,null);
		move();
		g.drawString("当前血量:"+blood, x, y);
	}
	@Override
	public void move() {
		x -= speed;
	
		if(x < 1100) 
		{
			x = 1000;
			if(up)
			{
				y -= speed;
			}
			if(!up)
			{
				y += speed;
			}
			if(y>Constant.Game_HEIGHT-imgs[0].getHeight(null))
			{
				up = true;
			}
			if(y<30)
			{
				up = false;
			}
		}
		
		if(random.nextInt(500)>480)
		{
			fire();
		}
		
	}
	//生成随机数
	Random random = new Random();
	
	//获取到boss所在的矩形
			public Rectangle getRec()
			{
				return new Rectangle(x,y,imgs[0].getWidth(null),imgs[0].getHeight(null));
			}
		@Override	
		public void fire()
		{
			play.Play("com/neuedu/sound/发射.mp3");
			Bullet 	b = new Bullet(x,y,"bullet/boss1.png",gc,false);
			gc.bullets.add(b);
		}
			
			
			
}
