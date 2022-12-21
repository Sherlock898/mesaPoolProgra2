package mesapoolv2;
import javax.swing.JFrame;

public class Ventana extends JFrame {
    public static final int w = 1280;
    public static final int h = 720;

    public Ventana(){
        JFrame frame = new JFrame ("Mesa de pull");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(GameManager.getGameManager());
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

} 