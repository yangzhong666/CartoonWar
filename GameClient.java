package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.stream.events.StartDocument;
import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Boss;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyStar;
import com.neuedu.entity.Prop;
import com.neuedu.entity.Star;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SoundPlayer;

import java.*;

	
	/**
	* @ClassName: GameClient
	* @Description: 游戏客户端
	* @author neuedu_yh
	* @date 2019年8月17日 上午10:00:17
	*
	*/
	public class GameClient extends Frame{
		//创建一个star飞机出来
		//Star star = new Star(100,200,"plane/12.png",this,true);
	   //创建一个我方角色的集合
		public List<Star> stars = new ArrayList<>();
		//创建道具集合
		public List<Prop> props = new ArrayList<>();
		
		
		//创建一个子弹的集合
		public List<Bullet> bullets = new ArrayList<>();
		
		//创建一个背景图出来
	BackGround	 backImg = new BackGround(0,0,"plane/555.jpg");
		//创建一个爆炸的集合
	  public List<Boom> booms = new ArrayList<>();
	
	
	    //创建敌方集合
	     public List<Star> enemys =new ArrayList<>();
	     
	     //创建一个boos集合
	     public List<Star> bosss = new ArrayList<>();
	     
	     
	     //解决图片闪烁问题
		@Override
	     public void update(Graphics g) {
	      Image backImg=createImage(Constant.Game_WIDTH,Constant.Game_HEIGHT);
	      Graphics backg=backImg.getGraphics();
	      Color color=backg.getColor();
	      backg.setColor(Color.WHITE);
	      backg.fillRect(0, 0, Constant.Game_WIDTH,Constant.Game_HEIGHT);
	      backg.setColor(color);
	      paint(backg);
	      g.drawImage(backImg, 0,0,null);
	     }
		Star star = null;
		 //生成客户端窗口的方法
		public void launchFrame() 
		{
			SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/background1.mp3");
			soundPlayer.start();
			
			
			//设置窗口大小
		    this.setSize(Constant.Game_WIDTH,Constant.Game_HEIGHT);
		    //设置标题
			this.setTitle("卡通大作战");
			//设置是否能够显示
		    this.setVisible(true);
		    //禁止最大化
		    this.setResizable(false);
		    //窗口居中
		    this.setLocationRelativeTo(null);
		    //关闭窗口添加关闭监听器
		    this.addWindowListener(new WindowAdapter() {
			   @Override
			   public void windowClosing(WindowEvent e) {
				System.exit(0);  
				
			}
		  });
		    //添加鼠标监听
		  //  this.addMouseListener(new MouseAdapter() {
		    	//@Override
		    	//public void mouseClicked(MouseEvent e) {
		    	//System.out.println("你点了一下鼠标");	
		    	//}
			//});
		     star = new Star(100,200,"plane/12.png",this,true);
			   //往我方容器中添加自己
			   stars.add(star);
			   
		    //添加键盘监听
		    this.addKeyListener(new KeyAdapter() {
		    	//键盘摁下的情况下
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    	     star.keyPressed(e);
		    			
		    }
		    	@Override
		    	public void keyReleased(KeyEvent e) {
		    		  star.keyReleased(e);
		    	}
			});
		    
		  //启动线程
		   new paintThread().start();
		  
		   
		   //往敌方容器中添加敌人
		   EnemyStar enemy1 = new EnemyStar(800,50,1,this,false);
		    EnemyStar enemy2 = new EnemyStar(800,400,1,this,false);
		    EnemyStar enemy3 = new EnemyStar(950,60,1,this,false);
		    EnemyStar enemy4 = new EnemyStar(950,410,1,this,false);
		    EnemyStar enemy5 = new EnemyStar(1050,70,1,this,false);
		    EnemyStar enemy6 = new EnemyStar(1050,420,1,this,false);
		    EnemyStar enemy7 = new EnemyStar(1100,80,1,this,false);
		    EnemyStar enemy8 = new EnemyStar(1100,480,1,this,false);
		  enemys.add(enemy1);
		  enemys.add(enemy2);
		  enemys.add(enemy3);
		  enemys.add(enemy4);
		  enemys.add(enemy5);
		  enemys.add(enemy6);
		  enemys.add(enemy7);
		  enemys.add(enemy8);
//		  //添加boss
		  bosss.add(new Boss(1200,350,this,false));
		   
		}		
		//重写paint方法
		@Override
		public void paint(Graphics g) {
			backImg.draw(g);
			g.drawLine(1200, 0, 1200, 800);
			for(int i=0;i<stars.size();i++)
			{
				Star star2  = stars.get(i);
				star2 .draw(g);
				star2.containItems(props);
			}
			
			//增强循环画出每个子弹
			//增强for循环中不能做任何操作
			for(int i=0;i<bullets.size();i++)
			{
				Bullet bullet = bullets.get(i);
				bullet.draw(g);
				bullet.hitMonsters(enemys);
				bullet.hitMonsters(stars);
				bullet.hitMonsters(bosss);
			}
			g.drawString("当前子弹的数量:"+bullets.size(),10, 40);
		    g.drawString("当前敌机的数量:"+enemys.size(), 10, 70);
		    g.drawString("当前爆炸的数量:"+booms.size(), 10 ,100);
		    g.drawString("当前我方的数量:"+stars.size(), 10 ,130);
		    g.drawString("当前道具的数量:"+props.size(), 10 ,160);
			//循环画敌方
		    for(int i=0;i<enemys.size();i++)
			{
				enemys.get(i).draw(g);
			}
			//循环爆炸
			for(int i=0;i<booms.size();i++)
			{
				if(booms.get(i).isLive()==true)
				{
					booms.get(i).draw(g);
				}
				
			}
			//循环画道具
			for(int i=0;i<props.size();i++)
			{
				
					props.get(i).draw(g);
			}
			if (enemys.size()==0)
			{
				//循环boss集合
				for(int i=0;i<bosss.size();i++)
				{
					
						bosss.get(i).draw(g);
				}
				
			}
			
		}
		
		//内部类
		class paintThread extends Thread{
			@Override
			public void run() {
				while (true) 
				{
					repaint();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}
	}
		



