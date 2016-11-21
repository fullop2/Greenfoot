import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Player extends PlayerStatus
{
    private int x, y;
    private int immortalTime;
    private int life;
    private MovePlayer movePlayer = new MovePlayer();
    
    public ItemGetBorder itemGetBorder;
    public PlayerAttack playerAttack;
    public PlayerSprite playerSprite;
    public BombAttack bombAttack;
    
    public Player()
    {
        itemGetBorder = new ItemGetBorder();
        playerAttack = new PlayerAttack();
        playerSprite = new PlayerSprite();
        bombAttack = new BombAttack();
        immortalTime = 300;
        life = StatusManager.GetInstance().getLife();
    }
    

    public void Init()
    {    
       getWorld().addObject(itemGetBorder,300,600); 
       getWorld().addObject(playerSprite,300,600);
       getWorld().addObject(playerAttack,0,0);
       getWorld().addObject(bombAttack,0,0);
       playerAttack.Init();
    }
    
    
    public void addLife()
    {
        ++life;
    }
    
    public int getLife()
    {
        return life;
    }
    
    public void setImmortal()
    {
        immortalTime = 420;
    }
    
    public void act() 
    {       
        locationUpdate();
        hitDetection();
        --immortalTime;
    } 
    
    private void locationUpdate()
    {
        x = movePlayer.HorizontalMove(getX());
        y = movePlayer.VerticalMove(getY());
        if(StatusManager.GetInstance().getAlive())
        {
           setLocation(x,y);
           itemGetBorder.setLocation(x,y);
           playerSprite.setLocation(x,y);
           playerAttack.setPosition(x,y);
        }
    }

    
    // 피격 판정 함수 
    private void hitDetection()  {

           EnemyBullet bullet = (EnemyBullet)getOneIntersectingObject(EnemyBullet.class);
           if(bullet != null && bullet.checkHit())  
           {  
                if(immortalTime <= 0)
                {  
                    getWorld().removeObjects(getWorld().getObjects(Weapon.class));
                    getWorld().removeObjects(getWorld().getObjects(EnemyBullet.class));
                    removePlayer();
                    StatusManager.GetInstance().PlayerDead();
                }
                else
                    {baseWorld.removeObject(bullet);}
           } 
    }
    
    
    private void removePlayer()
    {
         getWorld().removeObject(itemGetBorder);
         getWorld().removeObject(playerSprite);
         getWorld().removeObject(playerAttack);
         getWorld().removeObject(bombAttack);
         getWorld().removeObject(this);         
    }
}
