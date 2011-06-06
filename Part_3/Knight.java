
/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight
{
    private int x;
    private int y;
    public Knight()
    {
       x = 25;
       y = 25;
    }
    
    public void move(int var) {
        switch (var) {
            case 0: x+=0; y+=0; break;
            case 1: x+=100; y-=200; break;
            case 2: x+=200; y-=100; break;
            case 3: x+=200; y+=100; break;
            case 4: x+=100; y+=200; break;
            case 5: x-=100; y+=200; break;
            case 6: x-=200; y+=100; break;
            case 7: x-=200; y-=100; break;
            case 8: x-=100; y-=200; break;
            default: break;
        }
        return;
    }
    public void move(Space space) {
        x=(space.getVarX()*100)+25;
        y=(space.getVarY()*100)+25;
    }
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
