package a01a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    private final int size;
    private List<Pair<Integer,Integer>> selected = new LinkedList<>();
    private Set<Pair<Integer,Integer>> rectangle = new HashSet<>();

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public int hit(int x, int y) {
        selected.add(new Pair<Integer,Integer>(x, y));
        if (selected.size()==2) {
            setRectangle(selected.get(0), selected.get(1));
            selected.clear();
        }
        return selected.size();
    }

    @Override
    public boolean isOver() {
        System.out.println(rectangle.size());
        return rectangle.size() == size * size;
    }

    private void setRectangle(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
        for (int i = Math.min(p1.getX(), p2.getX()); i <= Math.max(p1.getX(), p2.getX()); i++) {
            for (int j = Math.min(p1.getY(), p2.getY()); j <= Math.max(p1.getY(), p2.getY()); j++) {
                rectangle.add(new Pair<Integer,Integer>(i, j));
            }
        }
    }

    @Override
    public boolean isRectangle(int x, int y) {
        return rectangle.contains(new Pair<>(x,y));
    }
    
}
