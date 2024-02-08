package a04.e2;

import java.util.*;
import java.util.function.*;

public class LogicsImpl implements Logics {
    private final int size;
    private final Random random = new Random();
    private final Map<Pair<Integer, Integer>, Object> values = new HashMap<>();
    private List<Pair<Integer, Integer>> selected = new LinkedList<>();
    private Optional<Operation> pendingOperation = Optional.of(Operation.PLUS);
    private int status = 0;

    public LogicsImpl(int size) {
        this.size = size;
        initialize();
    }

    enum Operation {
        PLUS("+", (x, y) -> x + y), MINUS("-", (x, y) -> x - y), TIMES("*", (x, y) -> x * y);

        String name;
        IntBinaryOperator operator;

        private Operation(String name, IntBinaryOperator operator) {
            this.name = name;
            this.operator = operator;
        }

        public String getName() {
            return name;
        }

        public IntBinaryOperator getOperator() {
            return operator;
        }

    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Object type = random.nextBoolean() ? random.nextInt(10)
                        : Operation.values()[random.nextInt(Operation.values().length)];
                values.put(new Pair<Integer, Integer>(i, j), type);
            }
        }
    }

    @Override
    public Optional<String> getCellNumber(Pair<Integer, Integer> position) {
        if (isCellNumber(position)) {
            return Optional.of(String.valueOf(values.get(position)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Operation> getCellOperation(Pair<Integer, Integer> position) {
        Object obj = values.get(position);
        if (obj instanceof Operation) {
            return Optional.of((Operation) obj);
        }
        return Optional.empty();
    }

    @Override
    public boolean isCellNumber(Pair<Integer, Integer> position) {
        var t = values.get(position);
        return t.getClass().equals(Integer.class) && t != null;
    }

    @Override
    public boolean hit(int x, int y) {
        var pair = new Pair<>(x, y);
        if (selected.isEmpty() && isCellNumber(pair)) {
            selected.add(pair);
            return true;
        }

        if (isAdjacent(x, y) && isCellNumber(pair) && pendingOperation.isPresent()) {
            status = pendingOperation.get().getOperator().applyAsInt(status,
                    Integer.valueOf(getCellNumber(pair).get()).intValue());
            pendingOperation = Optional.empty();
            selected.add(pair);
            return true;
        } else if(pendingOperation.isEmpty() && !isCellNumber(pair)){
            pendingOperation = Optional.of(getCellOperation(pair).get());
            selected.add(pair);
            return true;
        }

        return false;
    }

    private boolean isAdjacent(int x, int y) {
        return Math.abs(selected.get(selected.size() - 1).getX() - x)
                + Math.abs(selected.get(selected.size() - 1).getY() - y) == 1;
    }

    @Override
    public Optional<Integer> getResult() {
        return Optional.of(status);
    }
}
