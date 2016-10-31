import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

 public class Player extends PlayerStatus
{
    // Singleton
    private static Player player;
    public static Player getInstance() 
    {
        if(player == null) player = new Player();
        return player;
    }
    
    // Accessor
    public double getPower() {return playerAttack.power;};
    public double getBomb() { return bombAttack.bomb;};
    public void addPower() { 
        if(playerAttack.power + 0.01 < 4)
                  playerAttack.power += 0.01;
               else
                playerAttack.power = 4;
            };
    public void addBomb() {
        if(bombAttack.bomb < 8)
        bombAttack.bomb += 1;
            else 
        bombAttack.bomb = 8;
    };
   
    private PlayerSprite playerSprite;  // 플레이어 스프라이트    
    private PlayerAttack playerAttack; // 플레이어의 공격 클래스
    private MovePlayer movePlayer; // 플레이어 이동 클래스
    private BombAttack bombAttack; // 플레이어의 폭탄 관리 클래스
    
    // Player Location
    private int xpos;
    private int ypos;
    
    private Player()
    { 
        xpos = 300;
        ypos = 600;
    }
    
    public void reset()
    {
        playerSprite = new PlayerSprite();
        playerAttack = new PlayerAttack();
        movePlayer = new MovePlayer();
        bombAttack = new BombAttack();
        
        bombAttack.immortalTime = 300;
        bombAttack.bomb = 2.0f;
        
        playerAttack.power = StatusManager.GetInstance().getPower();
        xpos = 300;
        ypos = 600;
        
        getWorld().addObject(playerSprite,xpos,ypos);   
    }
    
    public void act() 
    {       
        statusUpdate();
        setPos();
        playerAttack.Attack();
        bombAttack.Attack();
        hitDetection();
    }
    
    private void setPos()
    {
        setLocation(xpos,ypos);
        playerAttack.SetPosition();       
        playerSprite.setLocation(getX(),getY());
    }
     
        
    // 상태 갱신 함수
    private void statusUpdate()
        {
         xpos = xpos + movePlayer.HorizontalMove();
         ypos = ypos + movePlayer.VerticalMove();
         if(bombAttack.immortalTime > 0)
          {bombAttack.immortalTime--;}
         
    }
    
    // 피격 판정 함수 
    private void hitDetection()  {
           List<PowerItem> powerItems = getIntersectingObjects(PowerItem.class);
           List<ScoreItem> scoreItems = getIntersectingObjects(ScoreItem.class);
           List<EnemyBullet> bullets = getObjectsInRange(7,EnemyBullet.class);
           
           if(!powerItems.isEmpty())
           {
               double inc = 0.01 * powerItems.size();
               if(playerAttack.power + inc < 4)
                  playerAttack.power += inc;
               else
                playerAttack.power = 4;
               getWorld().removeObjects(powerItems);   
               if(playerAttack.power + 1.0f <= 4.0f)
                    playerAttack.power += 1.0f;
           }
        
           if(!scoreItems.isEmpty())  
           {  
               StatusManager.GetInstance().ItemGet(powerItems.size());              
               getWorld().removeObjects(scoreItems);   
           } 
           
           if(bullets.size() > 0 )  
           {  
                World w = getWorld();
                if(bombAttack.immortalTime <= 0)
                {  
                    w.removeObjects(w.getObjects(Weapon.class));
                    w.removeObjects(w.getObjects(EnemyBullet.class));
                    w.removeObject(playerSprite);
                    w.removeObject(bombAttack);
                    w.removeObject(playerAttack);
                    w.removeObject(this);
                    
                    StatusManager.GetInstance().PlayerDead();
                }
                w.removeObjects(bullets);
           } 
    }

}
