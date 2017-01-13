import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class BouncyBalls extends Applet implements Runnable
{
	Thread th;
	int radius;
	int size;
	int[] x;
	int[] y;
	Color[] c;
	boolean[] xFlag;
	boolean[] yFlag;

	public void init()
	{
		size=5;
		radius=30;
		x=new int[size];
		y=new int[size];
		c=new Color[size];
		xFlag=new boolean[size];
		yFlag=new boolean[size];
		for(int i=0;i<2;i++)
		{
			xFlag[i]=true;
			yFlag[i]=true;
		}
		xFlag[2]=true;
		yFlag[2]=false;
		for(int i=3;i<size;i++)
		{
			xFlag[i]=false;
			yFlag[i]=false;
		}
		c[0]=Color.RED;
		c[1]=Color.GREEN;
		c[2]=Color.BLUE;
		c[3]=Color.YELLOW;
		c[4]=Color.PINK;
		x[0]=10;
		y[0]=20;
		x[1]=50;
		y[1]=80;
		x[2]=100;
		y[2]=30;
		x[3]=30;
		y[3]=150;
		x[4]=150;
		y[4]=50;

		th=new Thread(this);
		th.start();
	}
	public void paint(Graphics g)
	{	
		for(int i=0;i<size;i++)
		{
			g.setColor(c[i]);
			g.fillOval(x[i],y[i],radius,radius);
			if(xFlag[i])
				x[i]++;
			else
				x[i]--;
			if(yFlag[i])
				y[i]++;
			else
				y[i]--;
			if(x[i]==(getWidth()-radius))
			{
				xFlag[i]=false;
			}
			else if(x[i]==0)
			{
				xFlag[i]=true;
			}	
			if(y[i]==(getHeight()-radius))
			{
				yFlag[i]=false;
			}
			else if(y[i]==0)
			{
				yFlag[i]=true;
			}
		}
		for(int i=0;i<size;i++)
		{
			boolean hit=checkHit(i);
			if(hit)
			{
				if (x[i]!=0 && x[i]!=(getWidth()-radius))
					xFlag[i]=!xFlag[i];
				if (y[i]!=0 && y[i]!=(getHeight()-radius))
					yFlag[i]=!yFlag[i];
			}
		}
	}
	public void run()
	{
		while(true)
		{	
			repaint();
			try
			{
				Thread.sleep(10);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
	boolean checkHit(int ballNo)
	{
		for(int i=0;i<size;i++)
		{
			if(i!=ballNo)
			{
				int diffX=x[ballNo]-x[i];
				int diffY=y[ballNo]-y[i];
				double distance=Math.hypot((double)diffX,(double)diffY);
				if (distance<=radius)
					return true;
			}
		}
		return false;
	}
}