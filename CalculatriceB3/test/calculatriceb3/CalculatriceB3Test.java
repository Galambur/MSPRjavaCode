/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatriceb3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gaell
 */
public class CalculatriceB3Test {
    
    public CalculatriceB3Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class CalculatriceB3.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        double a = 10.0;
        double b = 20.0;
        CalculatriceB3 instance = new CalculatriceB3();
        double expResult = 30.0;
        double result = instance.add(a, b);
        assertEquals(expResult, result, 0.0);
    }
}
