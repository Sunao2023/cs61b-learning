package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(10);
        a.enqueue(1);
        a.enqueue(2);
        a.enqueue(3);
        assertEquals(3, a.fillCount);
        int b = a.dequeue();
        assertEquals(1, b);
        assertEquals(2, a.fillCount);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
