package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

class HallwayGenerator {
    Position start;
    Position end;
    Position corner;
    boolean direction;
    HallwayGenerator(Position sp, Position ep) {
        start = sp;
        end = ep;
        if (start.y <= end.y) {
            direction = true;
        } else {
            direction = false;
        }
        corner = new Position(end.x, start.y);
    }

    public static void drawHallway(TETile[][] ter, HallwayGenerator h) {
        for (int i = h.start.x; i < h.end.x; i++) {
            ter[i][h.start.y - 1] = Tileset.WALL;
            ter[i][h.start.y] = Tileset.FLOOR;
            ter[i][h.start.y + 1] = Tileset.WALL;
        }
        if (h.direction) {
            for (int j = h.start.y; j < h.end.y; j++) {
                ter[h.end.x - 1][j] = Tileset.WALL;
                ter[h.end.x][j] = Tileset.FLOOR;
                ter[h.end.x + 1][j] = Tileset.WALL;
            }
            ter[h.end.x - 1][h.start.y] = Tileset.FLOOR;
            ter[h.end.x][h.start.y - 1] = Tileset.WALL;
            ter[h.end.x + 1][h.start.y - 1] = Tileset.WALL;
        } else {
            for (int j = h.start.y; j > h.end.y; j--) {
                ter[h.end.x - 1][j] = Tileset.WALL;
                ter[h.end.x][j] = Tileset.FLOOR;
                ter[h.end.x + 1][j] = Tileset.WALL;
            }
            ter[h.end.x - 1][h.start.y] = Tileset.FLOOR;
            ter[h.end.x][h.start.y + 1] = Tileset.WALL;
            ter[h.end.x + 1][h.start.y + 1] = Tileset.WALL;
        }
    }


    public static void clean(TETile[][] ter, HallwayGenerator h) {
        for (int i = h.start.x; i < h.end.x; i++) {
            ter[i][h.start.y] = Tileset.FLOOR;
        }
        if (h.direction) {
            for (int j = h.start.y; j < h.end.y; j++) {
                ter[h.end.x][j] = Tileset.FLOOR;
            }
        } else {
            for (int j = h.start.y; j > h.end.y; j--) {
                ter[h.end.x][j] = Tileset.FLOOR;
            }
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(50, 50);
        Position p1 = new Position(10, 20);
        Position p2 = new Position(20, 10);
        TETile[][] roomTiles = new TETile[50][50];
        // initialize tiles
        for (int x = 0; x < 50; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                roomTiles[x][y] = Tileset.NOTHING;
            }
        }

        HallwayGenerator h = new HallwayGenerator(p1, p2);
        drawHallway(roomTiles, h);
        ter.renderFrame(roomTiles);
    }
}
