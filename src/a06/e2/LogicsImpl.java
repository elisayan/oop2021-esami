package a06.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    private final int size;
    private List<Pair<Integer, Integer>> selected = new LinkedList<>();
    private List<Pair<Integer, Integer>> advanced = new LinkedList<>();
    private int countAdvance = -1;
    private int index = 0;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean hit(int x, int y) {
        if (x == 0 || y == 0 || x == size - 1 || y == size - 1) {
            return false;
        }

        for (Pair<Integer, Integer> pair : selected) {
            if (Math.abs(x - pair.getX()) <= 1 && Math.abs(y - pair.getY()) <= 1) {
                return false;
            }
        }

        selected.add(new Pair<Integer, Integer>(x, y));
        return true;
    }

    @Override
    public void setAdvance() {
        countAdvance++;
        if (countAdvance % 2 == 0 && index < selected.size()) {
            advanced.add(
                    new Pair<Integer, Integer>(selected.get(index).getX(), selected.get(index).getY() - 1));
        } else {
            if (index < selected.size()) {
                advanced.add(
                        new Pair<Integer, Integer>(selected.get(index).getX(), selected.get(index).getY() + 1));
                index++;
            }
        }

    }

    @Override
    public boolean isAdvance(int x, int y) {
        return advanced.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isSelected(int x, int y) {
        return selected.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isOver() {
        return countAdvance == selected.size() * 2;
    }

}
