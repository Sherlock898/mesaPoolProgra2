package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Mesa {
    private Vector2 position;
    private Vector2 size;
    public ArrayList<Hoyo> hoyos;  
    
    private final int radioTronera = 25;
    private double paredWidth = 30;

    public Mesa(){
        size = new Vector2(0.68 * Ventana.w, 0.6 * Ventana.h);
        
        position = new Vector2(0.0625 * Ventana.w, Ventana.h - size.y - (Ventana.h - size.y)/2);

        hoyos = new ArrayList<Hoyo>();
        hoyos.add(new Hoyo(this, new Vector2(radioTronera, radioTronera), radioTronera));   //Esquina de ^<-
        hoyos.add(new Hoyo(this, new Vector2(radioTronera, size.y - radioTronera), radioTronera)); // v<-
        hoyos.add(new Hoyo(this, new Vector2(size.x/2, radioTronera), radioTronera)); // ^
        hoyos.add(new Hoyo(this, new Vector2(size.x/2, size.y - radioTronera), radioTronera)); // v
        hoyos.add(new Hoyo(this, new Vector2(size.x - radioTronera, radioTronera), radioTronera)); // ->^
        hoyos.add(new Hoyo(this, new Vector2(size.x - radioTronera, size.y - radioTronera), radioTronera)); // ->v
    }

    public Vector2 getPosition(){
        return Vector2.copy(position);
    }

    public Vector2 getSize(){
        return Vector2.copy(size);
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect((int)(position.x), (int)(position.y), (int)(size.x), (int)(size.y)); //Cloth
        //Paredes
        g.setColor(Color.GRAY);
        g.fillRect((int)(position.x), (int)(position.y), (int)(paredWidth), (int)(size.y));  //<-
        g.fillRect((int)(position.x + size.x - paredWidth), (int)(position.y), (int)(paredWidth), (int)(size.y));     //->
        g.fillRect((int)(position.x), (int)(position.y), (int)(size.x), (int)(paredWidth)); //^
        g.fillRect((int)(position.x), (int)(position.y + size.y - paredWidth) , (int)(size.x), (int)(paredWidth)); //v
        g.setColor(Color.BLACK); 
        for(int i = 0; i < hoyos.size(); i++){
            hoyos.get(i).paint(g);;
        }
    }  

    public double getBordeWidth(){
        return paredWidth;
    }
}