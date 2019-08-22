package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: star
* @Description: ����һ������ɻ���
* @author neuedu_yhl
* @date 2019��8��19�� ����3:57:56
*
*/
public class Star extends GameObj implements ActionAble{
   
	SinglePlay play = new SinglePlay();
	
	
	
	
	//�ٶ�
	private int speed;
	//���򲼶�����
	private boolean left,up,right,down;
	
	//�ͻ����ù���
	public GameClient gc;
	//�ж��Ƿ����Ҿ����ǵо�
	public boolean isGood;
	//��ӷɻ��ӵ��ȼ�����
	public int BulletLevel  = 1 ;
	//���Ѫֵ
	public int blood;
	
	public Star() {
		
	}
	public Star(int x , int y, String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 10000;
	}
	//�ƶ��ķ���
	@Override
	public void move() {
		if(left)
		{
			x -= speed;
		}
		if(up)
		{
			y -= speed;
		}
		if(right) 
		{
			x += speed;
		}
		if(down) 
		{
			y += speed;
		}
		outOfBound();
		
	}
	//��һ���ɻ�����
	@Override
	public  void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ��:"+blood,10,200);
	}
	//����ɻ��ı߽�
	public void outOfBound() 
	{
		if(y<=30) 
		{
			y = 25;
		}
		if(x<=5) 
		{
			x = 0 ;
		}
		if(x>=Constant.Game_WIDTH-img.getWidth(null)) 
		{
			x = Constant.Game_WIDTH-img.getWidth(null);
		}
		if(y>=Constant.Game_HEIGHT-img.getHeight(null)) 
		{
			y = Constant.Game_HEIGHT-img.getHeight(null);
		}
		
	}
	
	
	//��������
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up= true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		
		default:
			break;
		}
		
	}
	//�����ͷ�
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up= false;
			break;
		case KeyEvent.VK_D:
			right =false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_G:
			fire();
			
			break;

		default:
			break;
		}
	}
	
	//�ҷ��ɻ��Ŀ���
	public void fire() 
	{
		play.Play("com/neuedu/sound/����.mp3");
	Bullet 	b = new Bullet(x+this.img.getWidth(null),y,"bullet/w"+BulletLevel+".png",gc,true);
	gc.bullets.add(b);
	}
	
	//��ȡ����ǰ�ľ���
		public Rectangle getRec()
		{
			return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
		}
	//����ҷ���ɫ��ײ������
		public void containItem(Prop prop) 
		
		{
			if(this.getRec().intersects(prop.getRect()))
			{
				//�Ƴ�����
				gc.props.remove(prop);
				if(BulletLevel>2)
				{
					BulletLevel = 3;
					return;
				}
				//�����ӵ��ȼ�
				this.BulletLevel +=1;
			}
		}
		
		//����ҷ���ɫ��ײ���������
		public void containItems(List<Prop> props) 
		{
			if(props==null)
			{
				return;
			}else 
			{
				for (int i = 0; i < props.size(); i++) 
				{
					containItem(props.get(i));
				}
			}
		}
	
}
