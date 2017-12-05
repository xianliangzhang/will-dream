package top.kou.dream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @org.junit.Test(expected = RuntimeException.class)
    public void testX() {
        System.out.println("xx");
        throw new RuntimeException();
    }

    @org.junit.Test
    public void test2() {
        assert new Key(1).equals(new Key(2));
        assert new Key(1).hashCode() == new Key(2).hashCode();
        assert !new Key(1).equals(new Object());
        assert "Key@1".equals(new Key(1).toString());
    }

    @org.junit.Test
    public void test3() {
        Set<Key> container = new HashSet<>();
        container.add(new Key(1));
        container.add(new Key(2));

        assert container.size() == 1;
        assert container.iterator().next().equals(new Key(1));
        assert container.iterator().next().toString().equals(new Key(1).toString());

        Map<Key, Object> map = new HashMap<>();
        map.put(new Key(1), new Object());
        map.put(new Key(2), new Object());

        assert map.size() == 1;
        assert map.keySet().iterator().next().equals(new Key(1));
        assert map.keySet().iterator().next().toString().equals(new Key(1).toString());
    }

    static class Key {
        int id = 0;
        Key(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return String.format("Key@%d", id);
        }
    }
}
