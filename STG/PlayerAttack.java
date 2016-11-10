import greenfoot.*;

/**
 * Write a description of class PlayerAttackValue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAttack extends PlayerStatus
{
    private World world;
    
    public double power;
    
    private int shotDelay = 0;
    private int subshotDelay = 1;
    private int subCount= 0;
        
    private PlayerBullet bullet;
    
    private int weaponCount;
    private Weapon subWeapon[] = new Weapon[4];
    
    private int wL[][][] = {{{0,28}},
                           {{-13,28},{13,28}},
                           {{-25,10},{0,28},{25,10}},
                           {{-30,10},{-10,28},{10,28},{30,10}}}; // weapon 수에 따른 weapon들의 position
    private int wA[][][] = {{{0,0}},
                           {{-13,0},{13,0}},
                           {{-25,-10},{0,0},{25,10}},
                           {{-30,-10},{-5,0},{5,0},{30,10}}}; // weapon 수에 따른 weapon이 쏘는 bullet의 공격 각도
    
    public PlayerAttack()
    {
        for(int i = 0; i < 4; i++)
            { subWeapon[i] = new Weapon();}
            world = Player.getInstance().getWorld();
    }
    
    public void SetPosition(int x, int y)
    {
        weaponCount = (int)power - 1;
        for(int i = 0; i <= weaponCount; i++)
            {
                world.addObject(subWeapon[i],x + wL[weaponCount][i][0], y + wL[weaponCount][i][1]); 
                subWeapon[i].setlocation(x + wL[weaponCount][i][0], y + wL[weaponCount][i][1]); 
            }    
    }
    
    public void Attack()
    { 
        // in act (or method it calls)
       if (--shotDelay < 0 && Greenfoot.isKeyDown("z"))
            {
                shotDelay = 10;
                subCount = 5;
            }
            
        if(--subshotDelay < 0)
        {
            if (subCount > 0)
            {
               for(int i = -9;i<=9;i+=18)
                {
                    bullet = new PlayerBullet(20,0);
                    world.addObject(bullet,Player.getInstance().getX()+i,Player.getInstance().getY());   
                }
               if(subCount % 2 == 1)
               {
                   int t;
                   if(!Greenfoot.isKeyDown("shift"))
                            t = 0;
                        else
                            t = 1;
                   for(int i = 0; i <= weaponCount; i++)
                    {
                        subWeapon[i].atkCmd(wA[weaponCount][i][t]);
                    }
                }   
                subCount--;
                subshotDelay = 1;
            } 
        }
    } 
}
