/*GameFrame.java
 * Lucas Seto
 * 
 * class to generate a Game frame 
 */

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    GamePanel panel;

    public GameFrame(){
        super("3D something wtf?");
        panel = new GamePanel();

        this.add(panel);
        this.pack();

        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
