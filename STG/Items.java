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
        if(!StatusManager.GetInstance().getAlive() || !absorbMode)
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
            dx = baseWorld.player.getX() - getX();
            dy = baseWorld.player.getY() - getY();
            velocity = 15 / Math.sqrt(dx*dx+dy*dy);
 
            setLocation(getX() + dx*velocity,getY() + dy*velocity);
        }
    
        chkisEdge();
    }
    
    @Override
    protected void chkisEdge()
    {
       if(isAtEdge()) 
       {
         getWorld().removeObject(this);
       }
       else
       {
        Actor player = getOneIntersectingObject(ItemGetBorder.class);
        if(player != null)
            {
                addItem();
                getWorld().removeObject(this);
            } 
        }
    }
    
    protected abstract void addItem();
}
