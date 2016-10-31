import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombEffect extends Effects
{
    private int xpos, ypos;
    private int time = 300;
    private int timer = 2;
    private int idleTime = 60;
    private GreenfootImage image = getImage();
    private GreenfootImage tempi;
    
    public BombEffect(int x, int y)
    {
        xpos = x;
        ypos = y;
        tempi = new GreenfootImage(image);
        tempi.scale(50,50);
        setImage(tempi);
    }
    
    public void act() 
    {
        timer++;
        if(getImage().getHeight() >= 450)
            {
                BackgroundSwap bgs = new BackgroundSwap(10,120,30,255,Color.white);
                getWorld().addObject(bgs,480,360);
                getImage().scale(getImage().getHeight()+100,getImage().getHeight()+100);
                StatusManager.GetInstance().BombOn();
                getWorld().removeObject(this);
            }
        else 
            {
                tempi = new GreenfootImage(image);
                int radius =(int)Math.pow(2,timer/4 - 30)+getImage().getWidth();
                 if(timer%4==0)
                {
                    thunderEffect();
                    ++radius;
                }
                tempi.scale(radius,radius);
                setImage(tempi);

            }
    }
    
    private void thunderEffect()
    {
                ThunderEffect th;
                for(int i = 1; i <= timer/60; i++)
                {   
                    th = new ThunderEffect();
                    th.turn(Greenfoot.getRandomNumber(360));
                    getWorld().addObject(th,getX(),getY());  
                }
    }
}
