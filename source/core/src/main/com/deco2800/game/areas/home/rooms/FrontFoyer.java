package com.deco2800.game.areas.home.rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.deco2800.game.areas.home.Room;
import com.deco2800.game.areas.home.RoomObject;
import com.deco2800.game.areas.home.RoomProperties;

public class FrontFoyer extends Room {

    private FrontFoyerInterior interior;

    public FrontFoyer(Integer maxDoorways, ObjectMap<Class<Room>, String[]> doorwayRestrictions) {
        super(maxDoorways, doorwayRestrictions);
    }

    @Override
    public void initialise(GridPoint2 offset, Vector2 dimensions) {
        super.initialise(offset, dimensions);
        interior = designateInterior(FrontFoyerInterior.class, dimensions,
                RoomProperties.ROOM_CLASS_TO_PATH.get(this.getClass()));
    }

    public FrontFoyerInterior getInterior() {
        return interior;
    }

    public void setInterior(FrontFoyerInterior interior) {
        this.interior = interior;
    }

    static public class FrontFoyerInterior extends RoomInterior {

        public FrontFoyerInterior(ObjectMap<Character, RoomObject> tileMappings,
                              ObjectMap<Character, RoomObject> entityMappings,
                              Character[][] tileGrid, Character[][] entityGrid) {
            super(tileMappings, entityMappings, tileGrid, entityGrid);
        }
    }
}
