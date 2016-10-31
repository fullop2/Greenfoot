import greenfoot.*;
import greenfoot.GreenfootImage;

/**
 * Write a description of class bulletController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShotBullet extends SmoothMover
{
    // 복사할 탄막
    public EnemyBullet obj;
    
    // 따라다닐 적 기체
    public Enemy enemy;
    
    // 탄막 생성을 위한 변수들
    public int mainNum;
    public int subNum;
    public int mainDelay;
    public int subDelay;
    public int time, subTime;
    public int leftCount;
    public int aimAngle;
    public boolean playerAim;
    public boolean rotate = false;
    public int rotVal;
    public int availTime;
    public int rrot;
    public Effect effect;
    public int increaseSpeed;
    
    // 생성자
    protected ShotBullet(int availTime,
                            Enemy enemy,
                            EnemyBullet obj, 
                            int mainNum,
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            boolean playerAim,
                            boolean rotate,
                            int rotVal,
                            int increaseSpeed)
    {
        this.availTime = availTime;
        this.enemy = enemy;
        this.obj = obj;
        this.mainNum = mainNum;
        this.mainDelay = mainDelay;
        this.subNum = subNum;
        this.subDelay = subDelay;
        this.aimAngle = aimAngle;
        this.playerAim = playerAim;
        this.rotate = rotate;
        this.rotVal = rotVal; 
        this.increaseSpeed =increaseSpeed;
        effect = new Effect(2,60);
        
        setRotation(90);
   
    }
    
    protected void Turning()
    {
         if(playerAim)
         {
               if(StatusManager.GetInstance().getAlive())
                  turnTowards(Player.getInstance().getX(),Player.getInstance().getY());
         }
         else if(rotate)
         {
               setRotation(getRotation() + rotVal);   
         }
         rrot = getRotation();
    }
}
