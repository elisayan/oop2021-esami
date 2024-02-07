package a03a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private final int size;

    public LogicsImpl(int size) {
        this.size = size;
    }

    private Set<Pair<Integer, Integer>> rectangle = new HashSet<>();
    private List<Pair<Integer, Integer>> selected = new LinkedList<>();

    @Override
    public void hit(int x, int y) {
        selected.add(new Pair<Integer, Integer>(x, y));
        for (int i = x; i < size - x; i++) {
            for (int j = y; j < size - y; j++) {
                if (inBox(x, y) && (i == x || j == y || i == size - 1 - x || j == size - 1 - y)) {
                    rectangle.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }
    }

    private boolean inBox(int x, int y) {
        return x > selected.get(selected.size() - 2).getX() && y > selected.get(selected.size() - 2).getY();
    }

    @Override
    public boolean isRectangle(int x, int y) {
        return rectangle.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isOver(int x, int y) {
        return x >= size / 2 - 1 || y >= size / 2 - 1;
    }

}
