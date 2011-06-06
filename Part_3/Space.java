import java.awt.*;
import java.util.ArrayList;
public class Space extends Rectangle
{
    private Color color;
    private boolean used = false;
    private int posX, posY;
    private static double length = Math.sqrt(5);
    public Space(int x, int y)
    {
        this.setRect((x*100)+25, (y*100)+25, 100, 100);
        if ((x+y)%2==0)
            color = Color.white;
        else
            color = Color.black;
        posX = x;
        posY = y;
    }
    public Color getColor()
    {
        return color;
    }
    public void setUsed()
    {
        used = true;
        if ((posX+posY)%2==0)
            color = new Color(150, 255, 150, 100);
        else
            color = new Color(0, 255, 0, 100);
    }
    public boolean possibleMove(Space test)
    {
        int tX = test.getVarX();
        int tY = test.getVarY();
        double slope = ((double)(tY)-(double)(posY))/((double)(tX)-(double)(posX));
        System.out.println(posX);
        System.out.println(posY);
        System.out.println(tX);
        System.out.println(tY);
        //System.out.println(slope);
        double tlengthX = tX-posX;
        double tlengthY = tY-posY;
        double tlength = Math.sqrt(Math.pow(tlengthX,2)+Math.pow(tlengthY,2));
        System.out.println(tlength);
        if((slope == .5 || slope == (-.5) || slope == 2 || slope == (-2)) && tlength == Space.length)
            return true;
        return false;
    }
    public ArrayList<Space> getAdjacentMoves(Space spaceArray[][])
    {
        ArrayList<Space> returnArray = new ArrayList<Space>();
        for (int i=0; i<8; i++)
        {
            if(i!=posX){
                for(int j=0; j<8; j++)
                {
                    Space temp = spaceArray[i][j];
                    if(!(temp.isUsed()) && possibleMove(temp))
                        returnArray.add(temp);
                }
            }
        }
        return returnArray;
    }
    public Space leastPossibleMoves(ArrayList<Space> spaceList, Space spaceArray[][])
    {
        Space returnSpace = new Space(0, 0);
        int lowestCount = 100;
        for(int i=0; i<spaceList.size(); i++)
        {
            Space testSpace = spaceList.get(i);
            ArrayList<Space> temp = testSpace.getAdjacentMoves(spaceArray);
            if(temp.size() < lowestCount)
            {
                lowestCount = temp.size();
                returnSpace = testSpace;
            }
        }
        return returnSpace;
    }
    public int getVarX()
    {
        return posX;
    }
    public int getVarY()
    {
        return posY;
    }
    public boolean isUsed()
    {
        return used;
    }
}
