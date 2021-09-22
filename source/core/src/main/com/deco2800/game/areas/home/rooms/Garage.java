package com.deco2800.game.areas.home.rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.deco2800.game.areas.home.Room;
import com.deco2800.game.areas.home.RoomObject;
import com.deco2800.game.areas.home.RoomProperties;

public class Garage extends Room {

    private GarageInterior interior;

    public Garage(Integer maxDoorways, ObjectMap<Class<Room>, String[]> doorwayRestrictions) {
        super(maxDoorways, doorwayRestrictions);
    }

    @Override
    public void initialise(GridPoint2 offset, Vector2 dimensions) {
        super.initialise(offset, dimensions);
        interior = designateInterior(GarageInterior.class, dimensions,
                RoomProperties.ROOM_CLASS_TO_PATH.get(this.getClass()));
    }

    public GarageInterior getInterior() {
        return interior;
    }

    public void setInterior(GarageInterior interior) {
        this.interior = interior;
    }

    static public class GarageInterior extends RoomInterior {

        public GarageInterior(ObjectMap<Character, RoomObject> tileMappings,
                                  ObjectMap<Character, RoomObject> entityMappings,
                                  Character[][] tileGrid, Character[][] entityGrid) {
            super(tileMappings, entityMappings, tileGrid, entityGrid);
        }
    }
}
