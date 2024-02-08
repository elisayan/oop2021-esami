package a03c.e2;

import java.util.*;

public interface Logics {
    boolean isOstacle(Pair<Integer, Integer> pair);

    boolean isAsterisch(Pair<Integer, Integer> pair);

    void hit();

    boolean isOver();
}
