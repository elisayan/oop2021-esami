package a03b.e2;

public interface Logics {
    void hit();
    String getIndex(Pair<Integer,Integer> position);
    boolean isSelected(int x, int y);
    boolean isOver();
}
