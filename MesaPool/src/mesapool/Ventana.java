package mesapool;
import javax.swing.JFrame;

public class Ventana extends JFrame {
    public Ventana(){
        JFrame frame = new JFrame ("Windowframe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(new GameManager());
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

  } 