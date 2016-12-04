import greenfoot.*;
import greenfoot.GreenfootImage;

/**
 * Write a description of class bulletController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ShotBullet extends SmoothMover
{
    // 복사할 탄막
    private int bulletType;
    protected EnemyBullet obj;
    
    // 탄막 생성을 위한 변수들
    protected Actor follower;
    protected int relX;
    protected int relY;
    protected int mainNum;
    protected int subNum;
    protected int mainDelay;
    protected int subDelay;
    protected int time, subTime;
    protected int leftCount;
    protected int aimAngle;
    protected boolean playerAim;
    protected boolean rotate = false;
    protected int rotVal;
    protected int availTime;
    protected int rrot;
    protected Effect effect;
    protected int increaseSpeed;
    protected int initIdle;
    // 생성자
    protected ShotBullet(Actor enemy,
                            int relX, int relY,
                            int initIdle,
                            int availTime,
                            int bulletType,
                            EnemyBullet obj, 
                            int mainNum,
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            boolean playerAim,
                            boolean rotate,
                            int rotVal,
                            int increaseSpeed,
                            int initRotate)
    {
        this.follower = enemy;
        this.relX = relX;
        this.relY = relY;
        this.initIdle = initIdle+2;
        this.availTime = availTime;
        this.bulletType = bulletType;
        this.obj = obj;
        this.mainNum = mainNum;
        this.mainDelay = mainDelay;
        this.subNum = subNum;
        this.subDelay = subDelay;
        this.aimAngle = aimAngle;
        this.playerAim = playerAim;
        this.rotate = rotate;
        this.rotVal = rotVal; 
        this.increaseSpeed = increaseSpeed;
        effect = new Effect(2,60);
        time = subTime = 0;
        setRotation(90 + initRotate);
       leftCount = subNum;
       if(relX != 0 || relY != 0)
       {setImage("MagicCircle.png");}
    }
    
    
    protected void Turning()
    {
         if(playerAim)
         {
               if(StatusManager.GetInstance().getAlive())
                  turnTowards(baseWorld.player.getX(),baseWorld.player.getY());
         }
         else if(rotate)
         {
               setRotation(getRotation() + rotVal);   
         }
         rrot = getRotation();
    }
    
    protected EnemyBullet copyEnemyBullet()
    {
        switch(bulletType)
        {
            case 0:
            return new EnemyBulletRed(obj);
            case 1:
            return new EnemyBulletBigRed(obj);
            case 2:
            return new EnemyBulletBlue(obj);   
            case 3:
            return new EnemyBulletBigBlue(obj);
            case 4:
            return new EnemyBulletSeed(obj);
            case 5:
            return new EnemyBulletRing(obj);
            default:
            return null;
        }
    }
    
    protected void additionalProcess()
    {
        if(relX != 0 || relY != 0)
        setLocation(follower.getX()+relX,follower.getY()+relY);
        if(--availTime <= 0)
        getWorld().removeObject(this);
    }
}
