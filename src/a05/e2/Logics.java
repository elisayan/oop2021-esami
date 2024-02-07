package a05.e2;

import a05.e2.LogicsImpl.Operator;
import java.util.*;

public interface Logics {
    void reset();
    Optional<Boolean> getValues(Pair<Integer,Integer> position);
    Operator getOperator(Pair<Integer,Integer> position);
    boolean hit (int x, int y);
    boolean isOver();
}
