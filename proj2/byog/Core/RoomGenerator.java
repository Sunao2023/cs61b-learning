package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

class RoomGenerator implements Comparable<RoomGenerator> {
    int x1, y1, x2, y2;
    RoomGenerator(int h, int w, Position p) {
        x1 = p.x;
        y1 = p.y;
        x2 = p.x + w - 1;
        y2 = p.y + h - 1;
    }

    //inside a room
    private Position[] inner() {
        Position[] inner = new Position[2];
        inner[0] = new Position(x1 + 1, y1 + 1);
        inner[1] = new Position(x2 - 1, y2 - 1);
        return inner;
    }

    public static void drawRoom(TETile[][] ter, RoomGenerator r) {
        //Draw room outside
        for (int i = r.x1; i <= r.x2; i++) {
            ter[i][r.y1] = Tileset.WALL;
            ter[i][r.y2] = Tileset.WALL;
        }

        for (int j = r.y1; j <= r.y2; j++) {
            ter[r.x1][j] = Tileset.WALL;
            ter[r.x2][j] = Tileset.WALL;
        }
        //Draw room inside
        Position[] inRoom = r.inner();
        for (int m = inRoom[0].x; m <= inRoom[1].x; m++) {
            for (int n = inRoom[0].y; n <= inRoom[1].y; n++) {
                ter[m][n] = Tileset.FLOOR;
            }
        }
    }

    public static void clean(TETile[][] ter, RoomGenerator r) {
        Position[] inRoom = r.inner();
        for (int m = inRoom[0].x; m <= inRoom[1].x; m++) {
            for (int n = inRoom[0].y; n <= inRoom[1].y; n++) {
                ter[m][n] = Tileset.FLOOR;
            }
        }
    }
    //Judge whether two room intersects.
    public boolean intersects(RoomGenerator other) {
        return (this.x1 <= other.x2 && this.x2 >= other.x1
                && this.y1 <= other.y2 && this.y2 >= other.y1);
    }

    @Override
    public int compareTo(RoomGenerator o) {
        return this.x1 - o.x1;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(50, 50);
        Position p = new Position(10, 10);

        TETile[][] roomTiles = new TETile[50][50];
        // initialize tiles
        for (int x = 0; x < 50; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                roomTiles[x][y] = Tileset.NOTHING;
            }
        }

        RoomGenerator r = new RoomGenerator(5, 5, p);
        drawRoom(roomTiles, r);
        ter.renderFrame(roomTiles);
    }
}
