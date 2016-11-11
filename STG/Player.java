import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Player extends PlayerStatus
{
    private int x, y;
    private int immortalTime;
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
    }
    

    public void Init()
    {    
       getWorld().addObject(itemGetBorder,300,600); 
       getWorld().addObject(playerSprite,300,600);
       getWorld().addObject(playerAttack,0,0);
       getWorld().addObject(bombAttack,0,0);
       playerAttack.Init();
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

           List<EnemyBullet> bullets = getObjectsInRange(7,EnemyBullet.class);
           
           if(bullets.size() > 0 )  
           {  
                if(immortalTime <= 0)
                {  
                    getWorld().removeObjects(getWorld().getObjects(Weapon.class));
                    getWorld().removeObjects(getWorld().getObjects(EnemyBullet.class));
                    removePlayer();
                    StatusManager.GetInstance().PlayerDead();
                }
                baseWorld.removeObjects(bullets);
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
