package Game;

import Board.Room;
import Characters.Enemies.Creature;
import Characters.Entity;
import Characters.Friendlies.Adventurer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TurnOrchestrator {

    // process entity
    private Entity processEntity( Entity e )
    {
        return e;
    }

    private void processAdventurers( ArrayList<Entity> Adventurers ){
        ArrayList<Entity> targets = new ArrayList<>();
        for( Entity player : Adventurers ){
            Room thisRoom = player.checkRoom();
            if( thisRoom.getOccupantCreatures().size() == 0 ){
                player.move();
            }
            for( Creature target : thisRoom.getOccupantCreatures() ){
                player.fight((Entity) target);
            }
        }
    }

    private void processCreatures( ArrayList<Entity> Creatures ){
        for( Entity monster : Creatures ){
            Room thisRoom = monster.checkRoom();
            if( thisRoom.getOccupantAdventurers().size() == 0 ){
                monster.move();
            }
            for( Adventurer target : thisRoom.getOccupantAdventurers() ){
                monster.fight((Entity) target);
            }
        }
    }
}
