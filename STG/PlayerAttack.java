import greenfoot.*;

/**
 * Write a description of class PlayerAttackValue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAttack extends PlayerStatus
{
    private double power = 1;
    
    private int shotDelay = 0;
    private int subshotDelay = 1;
    private int subCount= 0;
        
    private PlayerBullet bullet;
    
    private int weaponCount = 0;
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
    }
    

    public void Init()
    {
         getWorld().addObject(subWeapon[0],baseWorld.player.getX() + wL[weaponCount][0][0], baseWorld.player.getY() + wL[weaponCount][0][1]);
         power = StatusManager.GetInstance().getPower();
         setWeapon();
    }
    
    public void addPower()
    { 
        if(power + 0.1 < 4)
            {power += 0.1;}
        else
            {power = 4;}
        
            setWeapon();
    }
    
    public double getPower()
    {
        return power;
    }
    
    public void setWeapon()
    {
        weaponCount = (int)power - 1;
        for(int i = 0; i <= weaponCount; i++)
        { 
             getWorld().addObject(subWeapon[i],baseWorld.player.getX() + wL[weaponCount][i][0], baseWorld.player.getY() + wL[weaponCount][i][1]); 
        }  
    }
    
    public void act()
    {
        attack();
    }
    
    public void setPosition(int x, int y)
    {
        for(int i = 0; i <= weaponCount; i++)
            { 
                subWeapon[i].setlocation(x + wL[weaponCount][i][0], y + wL[weaponCount][i][1]); 
            }    
    }
    
    public void attack()
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
                    getWorld().addObject(bullet,baseWorld.player.getX()+i,baseWorld.player.getY());   
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
