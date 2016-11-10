import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerSprite extends PlayerStatus
{ 
    private GreenfootImage image[] = new GreenfootImage[8];
    private int cnt;
    private int grazeCnt;
    public PlayerSprite()
    {
        for(int i = 1; i <= 8; i++)
        image[i-1] = new GreenfootImage("player-sprite" + i  +".png");
    }
    public void act()
    {
        if(cnt++ % 3 == 0)
            setImage(image[cnt/3 % 8]);
         
        List<EnemyBullet> bullets = getIntersectingObjects(EnemyBullet.class);

       if(bullets.size() > 0)
                {
                    grazeCnt = 0;
                    for(EnemyBullet bullet : bullets)
                    {
                        if(!bullet.grazed)
                        {
                            bullet.grazed = true;
                            grazeCnt++;
                        }
                    }
                    StatusManager.GetInstance().addGraze(grazeCnt);
                } 
        }
    }

