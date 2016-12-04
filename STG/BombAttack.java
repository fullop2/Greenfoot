import greenfoot.*;
/**
 * Bomb Attack class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombAttack extends PlayerStatus
{
    private int bomb; 
    private BombEffect bombEffect;
    private int bombDelay;
    private int bombAvailTime;
   

    public void Init()
    {
        bomb = StatusManager.GetInstance().getBomb();
        if(bomb < 2)
            bomb = 2;
    }
    
    public void addBomb()
    {
        if(bomb < 8)
            ++bomb;
    }
    
    public int getBomb()
    {
        return bomb;
    }
    
   public void act()
    {
        if (bomb > 0 && --bombDelay < 0 && Greenfoot.isKeyDown("x"))
           {
               --bomb;
               baseWorld.player.setImmortal();
               bombDelay = 300;
               bombEffect = new BombEffect(baseWorld.player.getX(),baseWorld.player.getY());
               BackgroundSwap bgs = new BackgroundSwap(30,100,100,230);
               getWorld().addObject(bombEffect,baseWorld.player.getX(),baseWorld.player.getY());
               getWorld().addObject(bgs,BackgroundSwap.xpos,BackgroundSwap.ypos);
               
            }
    }
}
