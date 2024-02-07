package a03a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    private final int size;

    public LogicsImpl(int size) {
        this.size = size;
    }

    private Set<Pair<Integer,Integer>> rectangle = new HashSet<>();

    @Override
    public void hit(int x, int y) {
        for (int i = x; i < size - x; i++) {
            for (int j = y; j < size - y; j++) {
                if (i == x || j == y || i == size - 1 - x || j == size - 1 - y) {
                    rectangle.add(new Pair<Integer,Integer>(i, j));
                }
            }
        }
    }

    @Override
    public boolean isRectangle(int x, int y) {
        return rectangle.contains(new Pair<>(x,y));
    }

    @Override
    public boolean isOver(int x, int y) {
        return x >= size / 2 - 1 || y == size / 2 - 1;
    }
    
}
