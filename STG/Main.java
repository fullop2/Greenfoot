import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main extends World
{
    private int ypos = 600;
    private int xpos = 300;
        
    /**
     * Constructor for objects of class Main.
     * 
     */
    public Main()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 720, 1); 
        

        MakeUI("UI-start.png",xpos,ypos-120);
        MakeUI("UI-setting.png",xpos,ypos-60);
        MakeUI("UI-exit.png",xpos,ypos);

        addObject(new Cursor(),xpos - xpos / 2 - 15, ypos);
        
    }

    private void MakeUI(String location, int xpos, int ypos)
    {
       GUI text = new GUI(location);
       addObject(text,xpos,ypos);
    }
    
}
