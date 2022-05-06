package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(TETile[][] tiles, int x, int y, int s) {
        int startUpper = y;
        int startLower = y - 2 * s + 1;
        for (int i = 0; i < s; i++) {
            int xStart = x - i;
            int xEnd = xStart + s + 2 * i;
            for (int j = xStart; j < xEnd; j++) {
                tiles[j][startUpper - i] = Tileset.WALL;
                tiles[j][startLower + i] = Tileset.WALL;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(50, 50);

        TETile[][] hexagon = new TETile[50][50];
        for (int x = 0; x < 50; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                hexagon[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(hexagon, 20, 20, 5);

        ter.renderFrame(hexagon);
    }
}