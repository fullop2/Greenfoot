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
        if(statusManager == null) 
        {
            statusManager = new StatusManager();
        }
        return statusManager;
    }
   // World
   private static BaseWorld world;
    
    // Stage
   private int nowStage;
   // boss status
   private Boss boss;
   
   // player status
   private double power = 1.0f;   
   private int bomb;
   private boolean alive;
   private boolean dead;
   private int revivalTime;
   private int score;
   private int bombTimer;
   private int graze;
   private int point = 10000;
   private int grazePoint;
   private Player player;
   private int life;
   // accessor
   public double getPower() {return power;};
   public int getBomb() {return bomb;};
   public int getStage() {return nowStage;};
   public int getScore() {return score;};
   public boolean getAlive() {return alive;};
   public int getGraze() {return graze;};
   public int getPoint() {return point;};
   public int getLife(){return life;};
   
   public static void setWorld(BaseWorld baseWorld)
   {
       world = baseWorld;
   }
   
   StatusManager()
   {
   }
   
   public void Init()
   {
       nowStage = 0;      
       revivalTime = 100;
       alive = true;
       score = 0;
       power = 1;
       bomb = 8;
       graze = 0;
       point = 10000;
       life = 8;
       score = 0;
    }
   
    
    public void act()
    {
        if(alive)
            {
                power = world.player.playerAttack.getPower();
                bomb = world.player.bombAttack.getBomb();
                life = world.player.getLife();
        }
    }
    
    public void StageChange()
    {
        nowStage++;
    }
   
    
    public void addGraze(int n)
    {
        graze += n;
        grazePoint = graze / 10 - (graze - n) / 10 ;
        point += grazePoint * 10;
    }
    
    public void PlayerDead()
    {
        if(power >= 2) power -= 1;
        else power = 1;
        alive = false;  
        --life;
    }
    
    public void StrikeEnemy()
    {
        score += 10;
    }
    
    public void ItemGet()
    {
        score += point ;
    }
    
    public void BulletItemGet()
    {
        score += 10;
        point += 10;
    }
    
    public void BombOn()
    {
        bombTimer = 200;
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
        if(life < 0)
            SmoothMover.baseWorld.GoEnd();
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
