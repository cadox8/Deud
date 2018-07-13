package me.cadox8.map;

public class MapGenerator {

    public static void main(String... args) {
/*        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(0, "3, 5, 6"));
        rooms.add(new Room(1, "0, 2, 1"));
        rooms.add(new Room(2, "2, 1, 2, 3"));
        rooms.add(new Room(3, "8, 1, 0"));

        Map map = new Map(4, rooms);
        map.generateMap(new Room(-1, "0, 5, 5, 6, 7")).forEach(r -> System.out.println(r.getRoomID()));*/

        String result = "";
        int b = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                b++;
                if (b == 100) {
                    b = 0;
                    result += "0\n";
                }
                System.out.println(b);
                result += "0 ";
            }
        }
        System.out.print(result);
    }
}
