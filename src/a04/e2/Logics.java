package a04.e2;

import java.util.*;

import a04.e2.LogicsImpl.Operation;

public interface Logics {
    Optional<String> getCellNumber(Pair<Integer,Integer> position);
    Optional<Operation> getCellOperation(Pair<Integer,Integer> position);
    boolean isCellNumber(Pair<Integer,Integer> position);
    boolean hit(int x, int y);
    Optional<Integer> getResult();
}
