import greenfoot.*;
/**
 * Write a description of class BombAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombAttack extends PlayerStatus
{
    private World world;
    public double bomb; 
    public int immortalTime;
    public BombEffect bombEffect;
    public int bombDelay;
    public int bombAvailTime;
    
    public BombAttack()
    {
        world = Player.getInstance().getWorld();
    }
    public void Attack()
    {
        if (--bombDelay < 0 && Greenfoot.isKeyDown("x") && bomb >= 1)
           {
               --bomb;
               immortalTime = 420;
               bombDelay = 300;
               bombEffect = new BombEffect(Player.getInstance().getX(),Player.getInstance().getY());
               BackgroundSwap bgs = new BackgroundSwap(60,180,30,230);
               world.addObject(bombEffect,Player.getInstance().getX(),Player.getInstance().getY());
               world.addObject(bgs,480,360);
            }
    }
}
