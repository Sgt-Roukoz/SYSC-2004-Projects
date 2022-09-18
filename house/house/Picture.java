/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Kšlling and David J. Barnes
 * @version 2016.02.29
 * 
 * SYSC2004 Lab 1 Submission: Marwan Zeid 101185876
 * Submitted 2021.01.15
 */
public class Picture
{
    private Square wall;
    private Square window;
    private Triangle roof;
    private Circle moon;
    private Circle dark_side;
    private boolean drawn;

    /**
     * Constructor for objects of class Picture
     */
    public Picture()
    {
        wall = new Square();
        window = new Square();
        roof = new Triangle();  
        moon = new Circle();
        dark_side = new Circle();
        drawn = false;
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {
        if(!drawn) {
            wall.moveHorizontal(-140);
            wall.moveVertical(20);
            wall.changeSize(120);
            wall.makeVisible();

            window.changeColor("black");
            window.moveHorizontal(-120);
            window.moveVertical(40);
            window.changeSize(40);
            window.makeVisible();

            roof.changeSize(60, 180);
            roof.moveHorizontal(20);
            roof.moveVertical(-60);
            roof.makeVisible();

            moon.changeColor("blue");
            moon.moveHorizontal(150);
            moon.moveVertical(-40);
            moon.changeSize(80);
            moon.makeVisible();
            
            dark_side.changeColor("black");
            dark_side.moveHorizontal(70);
            dark_side.moveVertical(-40);
            dark_side.changeSize(80);
            dark_side.makeVisible();
            
            drawn = true;
        }
    }

    /**
     * Simulates the phases of the moon
     * 
     * Marwan Zeid, 101185876
     */
    public void moonPhases()
    {
        int delta = 5;

        for(int i = 0; i < (160/delta); i++)
        {
            dark_side.moveHorizontal(delta);
        }
        
        dark_side.moveHorizontal(-160);
    }

    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        wall.changeColor("white");
        window.changeColor("black");
        roof.changeColor("white");
        moon.changeColor("white");
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        wall.changeColor("red");
        window.changeColor("black");
        roof.changeColor("green");
        moon.changeColor("blue");
    }
}
