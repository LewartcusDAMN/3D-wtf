/*Main.java
 * Lucas Seto
 * 
 * class to start the game thread
 */

public class Main {
    public static void main(String[]args){
        GameFrame frame = new GameFrame();// frame is and object of GameFrame which is my class to extend JFrame

        frame.panel.startGameThread();// start game thread
    }
}
