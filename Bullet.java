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
* @Description: 子弹类
* @author neuedu_yhl
* @date 2019年8月20日 上午10:43:14
*
*/
public class Bullet extends GameObj implements ActionAble{
   //单次播放音乐的对象
	SinglePlay singlePlay = new SinglePlay();
	
	// 创建速度属性
	private Integer speed;
	//拿到客户端
	public GameClient gc;
	//子弹类型
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

	
	//子弹越界销毁
	public void outOfBounds() 
	{
		if(x>Constant.Game_WIDTH||x<0) 
		{
			gc.bullets.remove(this);
		}
	}
	//随机生成道具
	Random random = new Random();
	
	
	
	//子弹打一个怪物
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
					//销毁自身
					gc.stars.remove(star);
					
				}
		
				//移除子弹
				gc.bullets.remove(this);
			
		    }else 
		    {
		    	singlePlay.Play("com/neuedu/sound/发射.mp3");
		   	    if(star instanceof Boss)
		   	    {
		   	    	star.blood -= 100;
		   	    	if(star.blood<=0) 
		   	    	{
		   	    		gc.bosss.remove(star);
		 		   	   //移除子弹
		 				gc.bullets.remove(this);
		 				
		   	    	}
		   	    	
		   	    }
		    
				else {
					
					//移除打中敌人
					gc.enemys.remove(star);
					//移除子弹
					gc.bullets.remove(this);
					
					//随机生成一个道具出来
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
			//添加爆炸
			gc.booms.add(boom);
			
			return true;
		}
		
			
			
			return false;
	}
	//子弹打多个怪物
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
	
	//获取到子弹的矩形
	public Rectangle getRec()
	{
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	
	
}

