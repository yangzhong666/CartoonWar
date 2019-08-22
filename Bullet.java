package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

import java.awt.Rectangle;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Bullet
* @Description: �ӵ���
* @author neuedu_yhl
* @date 2019��8��20�� ����10:43:14
*
*/
public class Bullet extends GameObj implements ActionAble{
   //���β������ֵĶ���
	SinglePlay singlePlay = new SinglePlay();
	
	// �����ٶ�����
	private Integer speed;
	//�õ��ͻ���
	public GameClient gc;
	//�ӵ�����
	public boolean isGood;
	
	public  Bullet() {
		
	}
	
	public Bullet(int x ,int y , String imgName,GameClient gc,boolean isGood) 
	{
		this.x = x;
		this.y = y;
        this.img = GetImageUtil.getImg(imgName);
        this.speed = 50;
        this.gc = gc;
        this.isGood = isGood;
	}

	@Override
	public void move() {
		if(isGood) 
		{
			x += speed;
		}else 
		{
			x -= speed;
		}
		
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x,y,null);
		move();
		outOfBounds();
	}

	
	//�ӵ�Խ������
	public void outOfBounds() 
	{
		if(x>Constant.Game_WIDTH||x<0) 
		{
			gc.bullets.remove(this);
		}
	}
	//������ɵ���
	Random random = new Random();
	
	
	
	//�ӵ���һ������
	public boolean hitMonster(Star star)
	{   
		Boom boom = new Boom(star.x,star.y,gc);
		if(this.getRec().intersects(star.getRec())&&this.isGood!=star.isGood)
		{  
			if(star.isGood)
		    {
				star.blood -=10;
				if(star.blood == 0)
				{
					//��������
					gc.stars.remove(star);
					
				}
		
				//�Ƴ��ӵ�
				gc.bullets.remove(this);
			
		    }else 
		    {
		    	singlePlay.Play("com/neuedu/sound/����.mp3");
		   	    if(star instanceof Boss)
		   	    {
		   	    	star.blood -= 100;
		   	    	if(star.blood<=0) 
		   	    	{
		   	    		gc.bosss.remove(star);
		 		   	   //�Ƴ��ӵ�
		 				gc.bullets.remove(this);
		 				
		   	    	}
		   	    	
		   	    }
		    
				else {
					
					//�Ƴ����е���
					gc.enemys.remove(star);
					//�Ƴ��ӵ�
					gc.bullets.remove(this);
					
					//�������һ�����߳���
					if(random.nextInt(500)>200)
					{
						if(star instanceof EnemyStar)
						{
							EnemyStar enemyStar = (EnemyStar)star;
						}
						Prop prop = new Prop(star.x,star.y,"Prop/prop.png");
						gc.props.add(prop);
					}
				}
			   
		    }
			//��ӱ�ը
			gc.booms.add(boom);
			
			return true;
		}
		
			
			
			return false;
	}
	//�ӵ���������
	public boolean hitMonsters(List<Star>monsters) 
	{
		if(monsters==null)
		{
			return false;
		}
		for(int i=0;i<monsters.size();i++) 
		{
			if(hitMonster(monsters.get(i)))
			{			
				return true;
			}
		}
		return false;
	}
	
	//��ȡ���ӵ��ľ���
	public Rectangle getRec()
	{
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	
	
}

