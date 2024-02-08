package a05.e2;

import java.util.*;

public class LogicsImpl implements Logics{
    private final int size;
    private final Random random = new Random();
    private Map<Pair<Integer,Integer>, Object> map = new HashMap<>();

    public LogicsImpl(int size) {
        this.size = size;
        initialize();
    }

    private void initialize(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var obj = (i + j) % 2 == 0 ? random.nextBoolean()
                        : Operation.values()[random.nextInt(Operation.values().length)];
                map.put(new Pair<Integer, Integer>(i, j), obj);
            }
        }
    }

    @Override
    public Optional<Operation> getOperatorName(int x, int y) {
        var obj = map.get(new Pair<>(x,y));
        if (obj instanceof Operation) {
            return Optional.of((Operation) obj);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> getBoolean(int x, int y) {
        var obj = map.get(new Pair<>(x,y));
        if (obj instanceof Boolean) {
            return Optional.of((Boolean) obj);
        }
        return Optional.empty();
    }

    @Override
    public boolean isBoolean(int x, int y) {
        return ! (map.get(new Pair<>(x,y)) instanceof Operation);
    }

    
}
