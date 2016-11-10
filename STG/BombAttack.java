import greenfoot.*;
/**
 * Bomb Attack class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombAttack extends PlayerStatus
{
    private World world;
    public double bomb; 
    public int immortalTime;
    private BombEffect bombEffect;
    private int bombDelay;
    private int bombAvailTime;
    
    public BombAttack()
    {
        world = Player.getInstance().getWorld();
    }
    public void act()
    {
        if (--bombDelay < 0 && Greenfoot.isKeyDown("x"))
           {
               immortalTime = 420;
               bombDelay = 300;
               
               bombEffect = new BombEffect(Player.getInstance().getX(),Player.getInstance().getY());
               BackgroundSwap bgs = new BackgroundSwap(30,100,100,230);
               world.addObject(bombEffect,Player.getInstance().getX(),Player.getInstance().getY());
               world.addObject(bgs,BackgroundSwap.xpos,BackgroundSwap.ypos);
               
            }
    }
}
