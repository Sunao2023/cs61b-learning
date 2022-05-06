package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

import static byog.Core.HallwayGenerator.drawHallway;
import static byog.Core.RoomGenerator.clean;
import static byog.Core.RoomGenerator.drawRoom;
import static byog.Core.HallwayGenerator.clean;

public class WorldGenerator {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final int MAXROOM = 50;
    private static final int MIN_ROOM = 4;
    private static final int MAX_ROOM = 7;
    private static long seed = 100;
    private static Random randomSeed = new Random(seed);
    protected TERenderer ter;
    protected TETile[][] tiles;

    public WorldGenerator(long input) {
        seed = input;
        randomSeed = new Random(seed);
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        tiles = new TETile[WIDTH][HEIGHT];
        // initialize tiles
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static boolean any(boolean[] items) {
        for (boolean item : items) {
            if (item) {
                return true;
            }
        }
        return false;
    }

    private static Rooms noIntersectRooms(Rooms rs) {
        Rooms noIntersect = new Rooms();
        int index = 1;
        for (RoomGenerator r : rs) {
            boolean[] bool = new boolean[MAXROOM];
            for (int i = index; i < MAXROOM; i++) {
                bool[i] = r.intersects(rs.rooms[i]);
            }
            index++;
            if (any(bool)) {
                continue;
            }
            noIntersect.append(r);
        }
        return noIntersect;
    }

    //Selection sort
    private static void sort(Rooms r) {
        for (int i = 0; i < r.size; i++) {
            for (int j = i + 1; j < r.size; j++) {
                int cmp = r.rooms[j].compareTo(r.rooms[i]);
                if (cmp < 0) {
                    RoomGenerator tmp = r.rooms[j];
                    r.rooms[j] = r.rooms[i];
                    r.rooms[i] = tmp;
                }
            }
        }
    }

    public static Rooms generateRooms() {
        Rooms sortedRooms = new Rooms();
        for (int i = 0; i < MAXROOM; i++) {
            int x = RandomUtils.uniform(randomSeed, MIN_ROOM, WIDTH - MAX_ROOM);
            int y = RandomUtils.uniform(randomSeed, MIN_ROOM, HEIGHT - MAX_ROOM);
            Position p = new Position(x, y);
            int h = RandomUtils.uniform(randomSeed, MIN_ROOM, MAX_ROOM);
            int w = RandomUtils.uniform(randomSeed, MIN_ROOM, MAX_ROOM);
            RoomGenerator room = new RoomGenerator(h, w, p);
            sortedRooms.append(room);
        }
        sortedRooms = noIntersectRooms(sortedRooms);
        sort(sortedRooms);
        return sortedRooms;
    }

    public static Roads randomPath(Rooms r) {
        Roads roads = new Roads();
        for (int i = 0; i < r.size - 1; i++) {
            Position p1 = new Position(
                    RandomUtils.uniform(randomSeed, r.rooms[i].x1 + 1, r.rooms[i].x2),
                    RandomUtils.uniform(randomSeed, r.rooms[i].y1 + 1, r.rooms[i].y2));
            Position p2 = new Position(
                    RandomUtils.uniform(randomSeed, r.rooms[i + 1].x1 + 1, r.rooms[i + 1].x2),
                    RandomUtils.uniform(randomSeed, r.rooms[i + 1].y1 + 1, r.rooms[i + 1].y2));
            HallwayGenerator road = new HallwayGenerator(p1, p2);
            roads.append(road);
        }
        return roads;
    }

    public static void main(String[] args) {
        WorldGenerator world = new WorldGenerator(123);

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
            clean(world.tiles, road);
        }
        world.ter.renderFrame(world.tiles);
    }
}

