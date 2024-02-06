package a02b.e2;

public interface Logics {
    void initialize();
    Pair<Integer,Integer> getPosition();
    boolean isDirectionL(int x, int y);
    boolean isDirectionR(int x, int y);
    void move();
    boolean isOver();
}
