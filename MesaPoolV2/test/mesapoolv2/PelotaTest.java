/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mesapoolv2;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Si2
 */
public class PelotaTest {
    private Pelota instance;
    
    public PelotaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    @BeforeEach
    public void setUp() {
        instance = new Pelota(new Mesa(), Color.BLACK, 15, 1);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of update method, of class Pelota.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class Pelota.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Vector2 position = null;
        Pelota instance = null;
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVelocity method, of class Pelota.
     */
    @Test
    public void testSetVelocity() {
        System.out.println("setVelocity");
        Vector2 velocity = null;
        Pelota instance = null;
        instance.setVelocity(velocity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paint method, of class Pelota.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        Pelota instance = null;
        instance.paint(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTroneria method, of class Pelota.
     */
    @Test
    public void testCheckTroneria() {
        System.out.println("checkTroneria");
        Hoyo hoyo = null;
        Pelota instance = null;
        boolean expResult = false;
        boolean result = instance.checkTroneria(hoyo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCollition method, of class Pelota.
     */
    @Test
    public void testCheckCollition() {
        System.out.println("checkCollition");
        Pelota other = null;
        Pelota instance = null;
        boolean expResult = false;
        boolean result = instance.checkCollition(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of separarPelotasEnColision method, of class Pelota.
     */
    @Test
    public void testSepararPelotasEnColision() {
        System.out.println("separarPelotasEnColision");
        Pelota other = null;
        Pelota instance = null;
        instance.separarPelotasEnColision(other);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class Pelota.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color color = null;
        Pelota instance = null;
        instance.setColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalPosition method, of class Pelota.
     */
    @Test
    public void testGetGlobalPosition() {
        System.out.println("getGlobalPosition");
        Pelota instance = null;
        Vector2 expResult = null;
        Vector2 result = instance.getGlobalPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
