package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import Board.Logger;
import Board.Render;
import Characters.Entity;
import Characters.Friendlies.Adventurer;
import Characters.Friendlies.Brawler;
import Game.Engine;
import Treasure.Treasure;

public class UnitTest {

    private Engine testEngine;

    @Before
    public void setUp() throws Exception {
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

    @Test
    public void testTreasureNumber() throws Exception {
        // Given
        testEngine.initialize();
        // When
        ArrayList<Treasure> testTreasures = testEngine.getTreasures();
        // Then
        assertEquals(testTreasures.size(), 24);
    }

    @Test
    public void testRenderInstance() throws Exception {
        // Given
        testEngine.initialize();
        // When
        Render testInstance = Render.getInstance();
        // Then
        assertNotEquals(testInstance, null);
    }

    @Test
    public void testLoggerInstance() throws Exception {
        // Given
        testEngine.initialize();
        // When
        Logger testLogger = Logger.getInstance();
        // Then
        assertNotEquals(testLogger, null);
    }

    @Test
    public void testLoggerTerminate() throws Exception {
        // Given
        testEngine.initialize();
        // When
        int testTurn = 1;
        Logger testRecorder = Logger.getInstance();
        testRecorder.terminate(testTurn);
        Logger testLogger = Logger.getInstance();
        // Then
        assertNotEquals(testLogger, null);
    }

    @Test
    public void checkPlayerName() throws Exception {
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        testAdventurer.setPlayerName("testName");
        String testName = testAdventurer.getPlayerName();
        // Then
        assertNotEquals(testName, null);
        assertEquals(testName, "testName");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down.");
        testEngine = null;
        assertNull(testEngine);
    }
}