import greenfoot.*; 
import java.awt.Color;
import java.awt.Font;
import java.util.List;

/**
 * Write a description of class StatusManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatusManager
{
    private static StatusManager statusManager;
    public static StatusManager GetInstance() 
    {
        if(statusManager == null) statusManager = new StatusManager();
        return statusManager;
    }
   // Stage
   private int nowStage;
   // boss status
   private Boss boss;
   
   // player status
   private double power = 1.0f;   
   private double bomb;
   private boolean alive;
   private boolean dead;
   private int revivalTime;
   private int score;
   private int bombTimer;
   private int graze;
   private int point = 10000;

   // accessor
   public double getPower() {return power;};
   public double getBomb() {return bomb;};
   public int getStage() {return nowStage;};
   public int getScore() {return score;};
   public boolean getAlive() {return alive;};
   public int getGraze() {return graze;};
   
   StatusManager()
   {
   }
   
   public void reset()
   {
       nowStage = 0;      
       revivalTime = 100;
       alive = true;
       score = 0;
       power = 1;
       bomb = 2;
    }
    
    public void act()
    {
        if(alive)
            {
            power = Player.getInstance().getPower();
            bomb = Player.getInstance().getBomb();
        }
    }
    
    public void StageChange()
    {
        nowStage++;
    }
    
    public void PlayerDead()
    {
        if(power >= 2) power -= 1;
        else power = 1;
        alive = false;        
    }
    
    public void StrikeEnemy()
    {
        score += 10;
    }
    
    public void ItemGet(int n)
    {
        score += point * n;
    }
    
    public void BombOn()
    {
        bombTimer = 180;
    }
    
    public boolean isBombOn()
    {
        if(--bombTimer > 0)
            return true;
        else
            return false;
    }
        
    public boolean Revival()
    {  
        if(!alive && --revivalTime <= 0)
            {
                revivalTime = 100;
                alive = true;
                return true;
            }
        else
            {
                return false;
            }
    }
}
