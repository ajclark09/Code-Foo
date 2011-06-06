import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * Write a description of class Graph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graph extends JFrame
{
    Random rand = new Random();
    Knight knight = new Knight();
    boolean firstLoop = true;
    Space spaceArray[][] = new Space[8][8];
    //Graphics2D dbGraphics;
    public void init()
    {
        //JFrame frame = new JFrame("graph");
        //frame.setBackground(Color.white);
        this.setSize(850, 875);
        this.getContentPane().setBackground(Color.white);
        
        //Container graph = new Graph();
        //frame.setContentPane(graph);
        //frame.setVisible(true);
        for (int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                Space temp = new Space(i,j);
                spaceArray[i][j] = temp;
            }
        }
        new Thread() {
            public void run() {
                while(true) {
                    //while(!isDisplayable());
                    //dbImage = createImage(850,875);
                    //dbbGraphics = dbImage.getGraphics();
                   // dbGraphics = (Graphics2D)dbbGraphics;
                    paintComponent(getGraphics());
                    pause(1000);
                }
            }
        }.start();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d);
        if(g==null)
            return;
        
        for (int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                Space temp = spaceArray[i][j];
                //if(j==3)
                //temp.setUsed();
                g2d.setPaint(temp.getColor());
                g2d.fill(temp);
            }
        }
        //if(knight.getX() < 800 && knight.getY() < 800)
        if(!firstLoop){
            Space temp = new Space((knight.getX()-25)/100, (knight.getY()-25)/100);
            temp.setUsed();
            spaceArray[temp.getVarX()][temp.getVarY()]=temp;
            g2d.setPaint(temp.getColor());
            g2d.clearRect((temp.getVarX()*100)+25, (temp.getVarY()*100)+25, 100, 100);
            g2d.fill(temp);
            Space knightSpace = spaceArray[((knight.getX()-25)/100)][((knight.getY()-25)/100)];
            Space nextMove = knightSpace.leastPossibleMoves(knightSpace.getAdjacentMoves(spaceArray), spaceArray);
            knight.move(nextMove);
        }
        else{
            knight.move(0);
            firstLoop=false;
        }
        try{
            g2d.drawImage(ImageIO.read(new File("horse.png")), knight.getX(), knight.getY(), 100, 100, null);
        }
        catch (IOException e) {}
        //update(g2d);
    }
    public void update(Graphics2D g2d) {
        if(g2d == null)
            return;
        //g2d.drawImage(dbImage, 0, 0, this);
    }
    public static void pause(long millis)
    {
        try {
            Thread.sleep(millis);
        }
        catch(Exception e){
            ;
        }
    }
    public static void main(String[] args) {
        Graph main = new Graph();
        main.init();
        main.setPreferredSize(new Dimension(850,875));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
