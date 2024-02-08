package a03b.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    private final int size;
    private Direction direction = Direction.UP;
    private List<Pair<Integer, Integer>> selected = new LinkedList<>();

    public LogicsImpl(int size) {
        this.size = size;
    }

    enum Direction {
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        private int x;
        private int y;

        private Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction next() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }

    private Pair<Integer, Integer> setFirst() {
        final Random random = new Random();
        return new Pair<>(random.nextInt(size - 4) + 2, random.nextInt(size - 4) + 2);
    }

    @Override
    public Boolean hit() {
        if (selected.isEmpty()) {
            selected.add(setFirst());
            return true;
        }
        var last = selected.get(selected.size() - 1);
        var directions = List.of(direction.next(), direction);
        for (var dir : directions) {
            
            Pair<Integer, Integer> position = new Pair<Integer, Integer>(last.getX() + dir.x,
                    last.getY() + dir.y);
            if (!selected.contains(position)) {
                selected.add(position);
                direction = dir;
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean isOver() {
        return selected.stream().anyMatch(p -> p.getX() < 0 || p.getY() < 0 || p.getX() >= size || p.getY() >= size);
    }

    @Override
    public String getIndex(Pair<Integer, Integer> position) {
        return String.valueOf(selected.indexOf(position));
    }

    @Override
    public boolean isSelected(int x, int y) {
        return selected.contains(new Pair<>(x, y));
    }
}
