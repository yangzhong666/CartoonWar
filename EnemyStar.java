package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

public class EnemyStar extends Star implements ActionAble {
   
	private Integer enemyType;
	private Integer speed;
	private GameClient gc;
	
	
	private static Image[] imgs1 = 
		{
			GetImageUtil.getImg("enemy/BOSS01.png")	,
			GetImageUtil.getImg("enemy/BOSS02.png"),	
			GetImageUtil.getImg("enemy/BOSS03.png")	,
			GetImageUtil.getImg("enemy/BOSS04.png")	,
			GetImageUtil.getImg("enemy/BOSS05.png")	
			
		};
	
	
	public EnemyStar() {
	
}
	public EnemyStar(int x, int y,int enemyType,GameClient gc,boolean isGood) 
	{
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
		this.speed = 1;
		this.gc = gc ;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		x -= speed;
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count >4)
		{
			count = 0;
		}
		g.drawImage(imgs1[count++], x ,y,null);
	    move();
	    
	   if( random.nextInt(500)>490)
	   {
		   fire();
	   }
	    
	}
	//随机数
	Random random = new Random(); 
	//敌军发火
	public void fire() 
	{
		
		Bullet bullet = new Bullet(x, y,"bullet/chinaz20.png" ,gc,false);
		gc.bullets.add(bullet);
	}
	
	
	
	//获取到子弹的矩形
		public Rectangle getRec()
		{
			return new Rectangle(x,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
		}
		
}
