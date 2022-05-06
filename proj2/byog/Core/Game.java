package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import static byog.Core.HallwayGenerator.drawHallway;
import static byog.Core.RoomGenerator.clean;
import static byog.Core.RoomGenerator.drawRoom;
import static byog.Core.WorldGenerator.generateRooms;
import static byog.Core.WorldGenerator.randomPath;

public class Game {
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        char[] inputarr = input.toCharArray();
        int[] num = new int[inputarr.length - 2];
        for (int i = 1; i < inputarr.length - 1; i++) {
            num[i - 1] = (int) inputarr[i];
        }

        int index = 0;
        long inputLong = 0;
        for (int j = num.length - 1; j >= 0; j--) {
            inputLong += num[index] * Math.pow(10, j);
            index += 1;
        }

        WorldGenerator world = new WorldGenerator(inputLong);

        Rooms rooms = generateRooms();
        Roads roads = randomPath(rooms);

        for (RoomGenerator room : rooms) {
            drawRoom(world.tiles, room);
        }
        for (HallwayGenerator road : roads) {
            drawHallway(world.tiles, road);
        }
        for (RoomGenerator room :rooms) {
            clean(world.tiles, room);
        }
        for (HallwayGenerator road : roads) {
            HallwayGenerator.clean(world.tiles, road);
        }

        TETile[][] finalWorldFrame = world.tiles;
        return finalWorldFrame;
    }
}
