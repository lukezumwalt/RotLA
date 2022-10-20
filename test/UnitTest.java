package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.junit.*;



import Board.Logger;
import Board.Render;
import Board.Room;
import Characters.*;
import Characters.Enemies.*;
import Characters.Friendlies.*;
import Game.Engine;
import Treasure.*;

public class UnitTest {

    private Engine testEngine;

    @Before
    public void setUp() throws Exception {
        System.out.println("Set up for unit testing.");
        testEngine = new Engine();
        
    }

    @Test
    public void testCreatureNumber() throws Exception {
        System.out.println("Running: testCreatureNumber");
        // Given
        testEngine.initialize();
        // When
        ArrayList<Entity> testCreatures = testEngine.getCreatures();
        // Then
        assertEquals(testCreatures.size(), 12);
    }

    @Test
    public void testTreasureNumber() throws Exception {
        System.out.println("Running: testTreasureNumber");
        // Given
        testEngine.initialize();
        // When
        ArrayList<Treasure> testTreasures = testEngine.getTreasures();
        // Then
        assertEquals(testTreasures.size(), 24);
    }

    @Test
    public void testRenderInstance() throws Exception {
        System.out.println("Running: testRenderInstance");
        // Given
        testEngine.initialize();
        // When
        Render testInstance = Render.getInstance();
        // Then
        assertNotEquals(testInstance, null);
    }

    @Test
    public void testLoggerInstance() throws Exception {
        System.out.println("Running: testLoggerInstance");
        // Given
        testEngine.initialize();
        // When
        Logger testLogger = Logger.getInstance();
        // Then
        assertNotEquals(testLogger, null);
    }

    @Test
    public void testLoggerTerminate() throws Exception {
        System.out.println("Running: testLoggerTerminate");
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
        System.out.println("Running: testPlayerName");
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

    @Test
    public void checkCreatureRoomOccupancy() throws Exception {
        System.out.println("Running: checkCreatureRoomOccupancy");
        // Given
        testEngine.initialize();
        // When
        Creature testOrbiter = new Orbiter();
        Room testRoom = testEngine.Facility.get("122");
        testOrbiter.setCurrentRoom(testRoom);
        testRoom.occupyCreature(testOrbiter);
        ArrayList<Creature> testOccupantCreatures = testRoom.getOccupantCreatures();
        // Then
        assertNotEquals(null, testOccupantCreatures);
        assertEquals(testRoom, testOrbiter.checkRoom());
    }

    @Test
    public void checkAdventurerRoomOccupancy() throws Exception {
        System.out.println("Running: checkAdventurerRoomOccupancy");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        Room testRoom = testEngine.Facility.get("211");
        testAdventurer.setCurrentRoom(testRoom);
        testRoom.occupyAdventurer(testAdventurer);
        ArrayList<Adventurer> testOccupantAdventurers = testRoom.getOccupantAdventurers();
        // Then
        assertNotEquals(null, testOccupantAdventurers);
        assertEquals(testRoom, testAdventurer.checkRoom());
    }

    @Test
    public void checkOrbiterNotInCenterRoom() throws Exception {
        System.out.println("Running: checkOrbiterNotInCenterRoom");
        // Given
        testEngine.initialize();
        // When
        ArrayList<Room> centerRooms = new ArrayList<Room>();
        centerRooms.add(testEngine.Facility.get("111"));
        centerRooms.add(testEngine.Facility.get("211"));
        centerRooms.add(testEngine.Facility.get("311"));
        centerRooms.add(testEngine.Facility.get("411"));
        ArrayList<Room> orbiterRooms = new ArrayList<Room>();
        ArrayList<Entity> testCreatureList = testEngine.getCreatures();
        for (Entity c : testCreatureList) {
            if (c.getName() == "orbiter") {
                Room currentRoom = c.checkRoom();
                orbiterRooms.add(currentRoom);
            }
        }
        // Then
        assertNotEquals(orbiterRooms, centerRooms);

    }

    @Test
    public void checkTeleport() throws Exception {
        System.out.println("Running: checkTeleport");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testPortal = new Portal();
        testRoom.occupyTreasure(testPortal);
        testAdventurer.collectTreasure(testRoom);
        testPortal.activate(testAdventurer);
        // Then
        assertNotEquals(testAdventurer.checkRoom(), testRoom);
    }

    @Test
    public void checkPotion() throws Exception {
        System.out.println("Running: checkPotion");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        int testHealth = testAdventurer.getHealth();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testPotion = new Potion();
        testRoom.occupyTreasure(testPotion);
        testAdventurer.collectTreasure(testRoom);
        testPotion.activate(testAdventurer);
        // Then
        assertEquals(testAdventurer.getHealth(), testHealth + 1);
    }

    @Test
    public void checkSword() throws Exception {
        System.out.println("Running: checkSword");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        int testCombatBonus = testAdventurer.getOffenseBonus();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testSword = new Sword();
        testRoom.occupyTreasure(testSword);
        testAdventurer.collectTreasure(testRoom);
        testSword.activate(testAdventurer);
        // Then
        assertEquals(testAdventurer.getOffenseBonus(), testCombatBonus + 1);
    }

    @Test
    public void checkArmor() throws Exception {
        System.out.println("Running: checkArmor");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        int testDefense = testAdventurer.getDefenseBonus();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testArmor = new Armor();
        testRoom.occupyTreasure(testArmor);
        testAdventurer.collectTreasure(testRoom);
        testArmor.activate(testAdventurer);
        // Then
        assertEquals(testAdventurer.getDefenseBonus(), testDefense + 1);
    }

    @Test
    public void checkTrap() throws Exception {
        System.out.println("Running: checkTrap");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        int testHealth = testAdventurer.getHealth();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testTrap = new Trap();
        testRoom.occupyTreasure(testTrap);
        testAdventurer.collectTreasure(testRoom);
        testTrap.activate(testAdventurer);
        // Then
        assertEquals(testAdventurer.getHealth(), testHealth - 1);

    }

    @Test
    public void checkGem() throws Exception {
        System.out.println("Running: checkGem");
        // Given
        testEngine.initialize();
        // When
        Adventurer testAdventurer = new Brawler();
        int testDefense = testAdventurer.getDefenseBonus();
        Room testRoom = testEngine.Facility.get("122");
        testAdventurer.setCurrentRoom(testRoom);
        Treasure testGem = new Gem();
        testRoom.occupyTreasure(testGem);
        testAdventurer.collectTreasure(testRoom);
        testGem.activate(testAdventurer);
        // Then
        assertEquals(testAdventurer.getDefenseBonus(), testDefense - 1);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down.");
        testEngine = null;
        assertNull(testEngine);
    }
}