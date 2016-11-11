import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor extends GUI
{
    private int state=2;
    private int xpos,ypos;
    private int timer;
    private int speed = 60;
    
    public Cursor()
    {
        timer = 2;      
        state = 2;
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {        
        xpos = getX();
        ypos = getY();
        move();
        setLocation(xpos,ypos);
        selectMenu();
        
    } 
    
    private void selectMenu()
    {
        if(Greenfoot.isKeyDown("z") || Greenfoot.isKeyDown("enter"))
            {
            switch(state)
            {
                case 0:
                StatusManager.GetInstance().Init();
                Greenfoot.setWorld(new BaseWorld());
                break;
                case 1:
                
                break;
                case 2:
                System.exit(0);
                break;
                default:
                break;
            }
        }
    }
    
    private void move()
    {
        if(timer > 0) 
        {        
            timer--;
        }
        if(timer == 0)
        {
            if(Greenfoot.isKeyDown("up") && ypos > 480)
            {
                ypos -= speed;
                state--;
                timer = 10;
            }
            if(Greenfoot.isKeyDown("down") && ypos < 600)
            {
                ypos += speed;
                state++;
                timer = 10;
            } 
        }
    }
}
