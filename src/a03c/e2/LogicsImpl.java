package a03c.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    private final int size;
    private final Random random = new Random();
    private final List<Pair<Integer, Integer>> obstacles = new LinkedList<>();
    private Set<Pair<Integer,Integer>> positions = new HashSet<>();
    private Map<Pair<Integer, Integer>, Direction> asterisks = new HashMap<>();
    private Direction direction = Direction.UP;

    public LogicsImpl(int size) {
        this.size = size;
        setAll();
    }

    enum Direction {
        UP(0, -1), DOWN(0, 1);

        int x;
        int y;

        private Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction next() {
            return switch (this) {
                case UP -> DOWN;
                case DOWN -> UP;
            };
        }
    }

    private void setAll() {
        for (int i = 0; i < size; i++) {
            obstacles.add(new Pair<Integer, Integer>(i, random.nextInt(size - 2)));
            asterisks.put(new Pair<Integer, Integer>(i, size - 1), direction);
        }
    }

    @Override
    public boolean isOstacle(Pair<Integer, Integer> pair) {
        return obstacles.contains(pair);
    }

    @Override
    public boolean isAsterisch(Pair<Integer, Integer> pair) {
        return asterisks.containsKey(pair);
    }

    private Pair<Integer, Integer> nextPosition(Pair<Integer, Integer> p) {
        return new Pair<Integer, Integer>(p.getX() + asterisks.get(p).x, p.getY() + asterisks.get(p).y);
    }

    @Override
    public void hit() {
        for (var p : positions) {
            var next = nextPosition(p);
            if (next.getY() == size || obstacles.contains(next)) {
                asterisks.put(p, asterisks.get(p).next());
                obstacles.remove(next);
                next = nextPosition(p);
            }
            asterisks.put(next, asterisks.remove(p));
        }
        positions.clear();
        positions.addAll(asterisks.keySet());
    }

    @Override
    public boolean isOver() {
        return asterisks.keySet().stream().anyMatch(e -> e.getY() < 0);
    }
}
