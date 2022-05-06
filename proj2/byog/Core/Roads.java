package byog.Core;

import java.util.Iterator;

public class Roads implements Iterable<HallwayGenerator> {
    private HallwayGenerator[] roads;
    private int size;
    private static final int MAXROAD = 50;

    public Roads() {
        roads = new HallwayGenerator[MAXROAD];
        size = 0;
    }

    public void append(HallwayGenerator road) {
        roads[size] = road;
        size += 1;
    }

    public Iterator iterator() {
        return new Roads.RoadIterator();
    }

    private class RoadIterator implements Iterator {
        private int wizPos;

        RoadIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public HallwayGenerator next() {
            HallwayGenerator returnItem = roads[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
}
