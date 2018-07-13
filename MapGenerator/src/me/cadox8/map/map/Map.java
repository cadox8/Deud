package me.cadox8.map.map;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class Map {

    private ArrayList<Room> avRooms;

    @Getter private ArrayList<Room> rooms;
    @Getter private int mapSize;

    private final Random random = new Random();

    public Map(int mapSize, @NonNull ArrayList<Room> avRooms) {
        this.mapSize = mapSize;
        this.avRooms = avRooms;

        try {
            checkRooms();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Room> generateMap(@NonNull Room initRoom) {
        rooms = new ArrayList<>();

        rooms.add(initRoom);

        for (int x = 0; x < mapSize; x++) {
            Room r = avRooms.get(random.nextInt(avRooms.size()));
            rooms.add(r);
            avRooms.remove(r);
        }

        return rooms;
    }

    private void checkRooms() throws IllegalArgumentException {
        if (mapSize > avRooms.size()) throw new IllegalArgumentException();
    }
}
