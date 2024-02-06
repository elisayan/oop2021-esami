package a02b.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private final int size;
    private final Random random = new Random();
    private Map<Integer, Set<Pair<Integer, Integer>>> direction = new HashMap<>();
    private Pair<Integer, Integer> asterisch;
    private Pair<Integer, Integer> moveDirection = new Pair<Integer,Integer>(0, -1);

    public LogicsImpl(int size) {
        this.size = size;
        initialize();
    }

    @Override
    public void initialize() {
        Set<Pair<Integer, Integer>> positions = new HashSet<>();
        asterisch = new Pair<Integer, Integer>(random.nextInt(size), size - 1);

        while (positions.size() < 20 || positions.contains(asterisch)) {
            while (positions.size() < 20) {
                var randomPosition = setRandomPosition();
                positions.add(randomPosition);
                direction.computeIfAbsent(random.nextInt(2), k -> new HashSet<>()).add(randomPosition);
            }
        } 
    }

    private Pair<Integer, Integer> setRandomPosition() {
        return new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return asterisch;
    }

    @Override
    public boolean isOver() {
        return asterisch.getX() < 0 || asterisch.getX() >= size || asterisch.getY() < 0 || asterisch.getY() >= size;
    }

    @Override
    public boolean isDirectionL(int x, int y) {
        return direction.get(0).contains(new Pair<>(x, y));
    }

    @Override
    public boolean isDirectionR(int x, int y) {
        return direction.get(1).contains(new Pair<>(x, y));
    }

    @Override
    public void move() {        
        if (direction.get(0).contains(asterisch)) {
            moveDirection = new Pair<Integer, Integer>(-1, 0);
        }

        if (direction.get(1).contains(asterisch)) {
            moveDirection = new Pair<Integer, Integer>(1, 0);
        }
        
        asterisch = new Pair<Integer, Integer>(asterisch.getX() + moveDirection.getX(),
                moveDirection.getY() + asterisch.getY());
    }

}
