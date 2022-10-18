package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import Characters.Entity;
import Game.Engine;

public class UnitTest {

    private Engine testEngine;

    protected void setUp() throws Exception {
        System.out.println("Set up for unit testing.");
        testEngine = new Engine();
    }

    @Test
    public void testCreatureNumber() throws Exception {
        // Given
        testEngine.initialize();
        // When
        ArrayList<Entity> testCreatures = testEngine.getCreatures();
        // Then
        assertEquals(testCreatures.size(), 12);
    }

    @Test
    public void testAdventurerNumber() throws Exception {
        // Given
        testEngine.initialize();
        // When
        ArrayList<Entity> testAdventurer = testEngine.getAdventurers();
        // Then
        assertEquals(testAdventurer.size(), 1);
    }

    protected void tearDown() throws Exception {
        System.out.println("Tearing down.");
        testEngine = null;
        assertNull(testEngine);
    }
}