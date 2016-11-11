import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class PowerItem extends Items
{

    @Override
    protected void addItem()
    {
        baseWorld.player.playerAttack.addPower();
    }
}
