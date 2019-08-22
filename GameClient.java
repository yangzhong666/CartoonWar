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
	* @Description: ��Ϸ�ͻ���
	* @author neuedu_yh
	* @date 2019��8��17�� ����10:00:17
	*
	*/
	public class GameClient extends Frame{
		//����һ��star�ɻ�����
		//Star star = new Star(100,200,"plane/12.png",this,true);
	   //����һ���ҷ���ɫ�ļ���
		public List<Star> stars = new ArrayList<>();
		//�������߼���
		public List<Prop> props = new ArrayList<>();
		
		
		//����һ���ӵ��ļ���
		public List<Bullet> bullets = new ArrayList<>();
		
		//����һ������ͼ����
	BackGround	 backImg = new BackGround(0,0,"plane/555.jpg");
		//����һ����ը�ļ���
	  public List<Boom> booms = new ArrayList<>();
	
	
	    //�����з�����
	     public List<Star> enemys =new ArrayList<>();
	     
	     //����һ��boos����
	     public List<Star> bosss = new ArrayList<>();
	     
	     
	     //���ͼƬ��˸����
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
		 //���ɿͻ��˴��ڵķ���
		public void launchFrame() 
		{
			SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/background1.mp3");
			soundPlayer.start();
			
			
			//���ô��ڴ�С
		    this.setSize(Constant.Game_WIDTH,Constant.Game_HEIGHT);
		    //���ñ���
			this.setTitle("��ͨ����ս");
			//�����Ƿ��ܹ���ʾ
		    this.setVisible(true);
		    //��ֹ���
		    this.setResizable(false);
		    //���ھ���
		    this.setLocationRelativeTo(null);
		    //�رմ�����ӹرռ�����
		    this.addWindowListener(new WindowAdapter() {
			   @Override
			   public void windowClosing(WindowEvent e) {
				System.exit(0);  
				
			}
		  });
		    //���������
		  //  this.addMouseListener(new MouseAdapter() {
		    	//@Override
		    	//public void mouseClicked(MouseEvent e) {
		    	//System.out.println("�����һ�����");	
		    	//}
			//});
		     star = new Star(100,200,"plane/12.png",this,true);
			   //���ҷ�����������Լ�
			   stars.add(star);
			   
		    //��Ӽ��̼���
		    this.addKeyListener(new KeyAdapter() {
		    	//�������µ������
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    	     star.keyPressed(e);
		    			
		    }
		    	@Override
		    	public void keyReleased(KeyEvent e) {
		    		  star.keyReleased(e);
		    	}
			});
		    
		  //�����߳�
		   new paintThread().start();
		  
		   
		   //���з���������ӵ���
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
//		  //���boss
		  bosss.add(new Boss(1200,350,this,false));
		   
		}		
		//��дpaint����
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
			
			//��ǿѭ������ÿ���ӵ�
			//��ǿforѭ���в������κβ���
			for(int i=0;i<bullets.size();i++)
			{
				Bullet bullet = bullets.get(i);
				bullet.draw(g);
				bullet.hitMonsters(enemys);
				bullet.hitMonsters(stars);
				bullet.hitMonsters(bosss);
			}
			g.drawString("��ǰ�ӵ�������:"+bullets.size(),10, 40);
		    g.drawString("��ǰ�л�������:"+enemys.size(), 10, 70);
		    g.drawString("��ǰ��ը������:"+booms.size(), 10 ,100);
		    g.drawString("��ǰ�ҷ�������:"+stars.size(), 10 ,130);
		    g.drawString("��ǰ���ߵ�����:"+props.size(), 10 ,160);
			//ѭ�����з�
		    for(int i=0;i<enemys.size();i++)
			{
				enemys.get(i).draw(g);
			}
			//ѭ����ը
			for(int i=0;i<booms.size();i++)
			{
				if(booms.get(i).isLive()==true)
				{
					booms.get(i).draw(g);
				}
				
			}
			//ѭ��������
			for(int i=0;i<props.size();i++)
			{
				
					props.get(i).draw(g);
			}
			if (enemys.size()==0)
			{
				//ѭ��boss����
				for(int i=0;i<bosss.size();i++)
				{
					
						bosss.get(i).draw(g);
				}
				
			}
			
		}
		
		//�ڲ���
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
		



