/*GamePanel.java
 * Lucas Seto
 * 
 * main class for updating and painting the game
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // Constants
    public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;

    // Class objects
    private Thread thread;
    private static Random rand;

    public static MouseHandler mouse = new MouseHandler();
    public static KeyHandler key = new KeyHandler();

    private Cam cam;

    // Regular fields
    public int gamestate;
    private final int FPS = 60;
    public static int FOV = 90;

    private double[] p1;
    private double[] p2;
    private double[] p3;
    private double[] p4;
    private double[] p5;
    private double[] p6;
    private double[] p7;
    private double[] p8;

    private double[][] points;


    public GamePanel() {// Constructor
        this.thread = new Thread();
        rand = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.addMouseListener(mouse);
        this.addMouseWheelListener(mouse);
        this.addKeyListener(key);
        this.setFocusable(true);

        this.gamestate = 0;
        
        this.cam = new Cam(0, 0, 0);
        
        int scale = 100;
        int dist = 2;
        p1 = new double[]{1 * scale, 1 * scale, -1 + dist};
        p2 = new double[]{1 * scale, -1 * scale, -1 + dist};
        p3 = new double[]{-1 * scale, 1 * scale, -1 + dist};
        p4 = new double[]{-1 * scale, -1 * scale, -1 + dist};
        p5 = new double[]{1 * scale, 1 * scale, 1 + dist};
        p6 = new double[]{1 * scale, -1 * scale, 1 + dist};
        p7 = new double[]{-1 * scale, 1 * scale, 1 + dist};
        p8 = new double[]{-1 * scale, -1 * scale, 1 + dist};
        // p1 = new double[]{1 * scale, 1 * scale, 2 + dist};
        // p2 = new double[]{1 * scale, 2 * scale, 2 + dist};
        // p3 = new double[]{2 * scale, 1 * scale, 2 + dist};
        // p4 = new double[]{2 * scale, 2 * scale, 2 + dist};
        // p5 = new double[]{1 * scale, 1 * scale, 1 + dist};
        // p6 = new double[]{1 * scale, 2 * scale, 1 + dist};
        // p7 = new double[]{2 * scale, 1 * scale, 1 + dist};
        // p8 = new double[]{2 * scale, 2 * scale, 1 + dist};
        points = new double[][]{p1, p2, p3, p4, p5, p6, p7, p8};
    }

    public void startGameThread(){// Starts the game loop
        this.thread = new Thread(this);
        this.thread.start();
    }
    @Override
    public void run() {//1 second in nano secs per frame
        double frame_interval = 1000000000/FPS;// var for the amount of nanoseconds between frames 
        double next_frame_time = System.nanoTime() + frame_interval;// time for the next frame is the current time plus the interval
        while(thread != null){// Game loop
            this.update();
            this.repaint();
            try{
                double remaining_time = next_frame_time - System.nanoTime();// var for the remaining time before thenext frame
                remaining_time /= 1000000;
                if (remaining_time < 0){
                    remaining_time = 0;
                }
                Thread.sleep((long) remaining_time);
                next_frame_time += frame_interval;// the system time for the next frame is moved up one second (frame interval)
            }catch (InterruptedException e){// catch error
            }
        }
    }

    public void update(){
        try {// try to set the mouse position to where it is relative to the JPanel
            mouse.pos[0] = getMousePosition().x;
            mouse.pos[1] = getMousePosition().y;
        } catch (Exception e) {// incase mouse leaves the screen
        }

        switch (gamestate){
            case 0 -> {
                
            }
        }
        mouse.previous = mouse.pressed;
        key.previous = key.keys.clone();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        switch (gamestate) {
            case 0 -> {// 
                int counter = 0;
                for (double[] p : points){
                    counter ++;
                    double projected_x = Utils.near_plane_intersection(p[0], p[2], FOV);
                    double projected_y = Utils.near_plane_intersection(p[1], p[2], FOV);
                    g2D.setColor(Color.blue);
                    g2D.fillOval((int)projected_x + SCREEN_WIDTH/2 - 5, (int)projected_y + SCREEN_HEIGHT/2 - 5, 10, 10);
                }
                g2D.drawLine((int)Utils.near_plane_intersection(p1[0], p1[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p1[1], p1[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p2[0], p2[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p2[1], p2[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p1[0], p1[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p1[1], p1[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p3[0], p3[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p3[1], p3[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p4[0], p4[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p4[1], p4[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p3[0], p3[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p3[1], p3[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p4[0], p4[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p4[1], p4[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p2[0], p2[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p2[1], p2[2], FOV) + SCREEN_HEIGHT/2);

                g2D.drawLine((int)Utils.near_plane_intersection(p5[0], p5[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p5[1], p5[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p6[0], p6[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p6[1], p6[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p5[0], p5[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p5[1], p5[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p7[0], p7[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p7[1], p7[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p8[0], p8[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p8[1], p8[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p7[0], p7[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p7[1], p7[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p8[0], p8[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p8[1], p8[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p6[0], p6[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p6[1], p6[2], FOV) + SCREEN_HEIGHT/2);

                g2D.drawLine((int)Utils.near_plane_intersection(p1[0], p1[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p1[1], p1[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p5[0], p5[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p5[1], p5[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p7[0], p7[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p7[1], p7[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p3[0], p3[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p3[1], p3[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p4[0], p4[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p4[1], p4[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p8[0], p8[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p8[1], p8[2], FOV) + SCREEN_HEIGHT/2);
                g2D.drawLine((int)Utils.near_plane_intersection(p6[0], p6[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p6[1], p6[2], FOV) + SCREEN_HEIGHT/2,
                     (int)Utils.near_plane_intersection(p2[0], p2[2], FOV)+ SCREEN_WIDTH/2, (int)Utils.near_plane_intersection(p2[1], p2[2], FOV) + SCREEN_HEIGHT/2);
            }
        }
        
        g2D.dispose();
    }
}