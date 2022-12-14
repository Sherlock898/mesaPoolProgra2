package mesapoolv2;

import java.time.Duration;
import java.time.Instant;

/**
 * Store and calculate data to control physics acording to frame rate
 * @author Si2
 */
public class FPS {
    private static Duration fpsDeltaTime = Duration.ZERO;
    private static Duration lastTime = Duration.ZERO;
    private static Instant beginTime = Instant.now();
    private static double deltaTime = fpsDeltaTime.toMillis() - lastTime.toMillis();
    
    
    private FPS(){
    }
    
    /**
     *  Set begin time to start calculating delta time
     */
    public static void calcBeginTime(){
        beginTime = Instant.now();
        fpsDeltaTime = Duration.ZERO;
    }
    /**
     * Calcules delta time
     */
    public static void calcDeltaTime(){
        fpsDeltaTime = Duration.between(beginTime, Instant.now());
        deltaTime = (double)fpsDeltaTime.toMillis() - lastTime.toMillis();
        lastTime = fpsDeltaTime;  
    }
    
    /**
     * Converts the value of delta time to seconds and returns it
     * @return delta time in seconds
     */
    public static double getDeltaTime(){
        return deltaTime / 1000;
    }
}
