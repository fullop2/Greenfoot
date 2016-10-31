import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Items extends SmoothMover
{    
    private int speed = -4;
    private int cnt = 6;
    private boolean absorbMode;
    private double dx,dy;
    private double velocity;
    
    public void setAbsorb() { absorbMode = true;};
    public void freeAbsorb() {absorbMode = false;};
    
    public void act()
    {
        if(!absorbMode)
        {
                if( cnt > 0) 
            {
                cnt--;
            }
            else if( speed < 3)
            {
                cnt = 5;
                speed += 1;
            }
            setLocation(getX(),getY()+speed);
            
        }
        else if(StatusManager.GetInstance().getAlive())
        {
            
            dx = Player.getInstance().getX() - getX();
            dy = Player.getInstance().getY() - getY();
            velocity = 15 / Math.sqrt(dx*dx+dy*dy);
 
            setLocation(getX() + dx*velocity,getY() + dy*velocity);
            
            if(dx * dy <= 225)
                addItem();
        }
        chkisEdge();
    }
    
    protected void chkisEdge()
    {
       if(isAtEdge()) 
       {
         getWorld().removeObject(this);
       }
    }
    
    protected void addItem()
    {
        
    }
}
