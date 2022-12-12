package mesapool;


public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        GameManager gm = new GameManager();
        Ventana v = new Ventana(gm);
        FPS.calcBeginTime();
        
        while(true){
            gm.updatePhyisics(FPS.getDeltaTime());
            FPS.calcDeltaTime();
        }
    }
    
}

