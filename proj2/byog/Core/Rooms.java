package byog.Core;

import java.util.Iterator;

public class Rooms implements Iterable<RoomGenerator> {
    protected RoomGenerator[] rooms;
    protected int size;
    private static final int NUM = 50;

    public Rooms() {
        rooms = new RoomGenerator[NUM];
        size = 0;
    }

    public void append(RoomGenerator room) {
        rooms[size] = room;
        size += 1;
    }

    public Iterator iterator() {
        return new RoomIterator();
    }

    private class RoomIterator implements Iterator {
        private int wizPos;

        RoomIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public RoomGenerator next() {
            RoomGenerator returnItem = rooms[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
}
