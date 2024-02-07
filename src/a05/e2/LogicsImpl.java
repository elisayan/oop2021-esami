package a05.e2;

import java.util.*;
import java.util.function.*;

public class LogicsImpl implements Logics {
    private final int size;
    private final Random random = new Random();
    private final Map<Pair<Integer, Integer>, Object> booleanValues = new HashMap<>();
    private final Map<Pair<Integer, Integer>, Operator> operators = new HashMap<>();
    private final List<Pair<Integer, Integer>> selected = new LinkedList<>();
    private boolean over = false;

    public LogicsImpl(int size) {
        this.size = size;
        reset();
    }

    enum Operator {
        AND((x, y) -> x && y), OR((x, y) -> x || y), XOR((x, y) -> x ^ y);

        private BinaryOperator<Boolean> operator;

        public BinaryOperator<Boolean> getOperator() {
            return operator;
        }

        private Operator(BinaryOperator<Boolean> operator) {
            this.operator = operator;
        }

    }

    @Override
    public void reset() {
        for (int i = 0; i < size; i += 2) {
            for (int j = 0; j < size; j += 2) {
                booleanValues.put(new Pair<Integer, Integer>(i, j), random.nextBoolean());
            }
        }

        for (int i = 1; i < size; i += 2) {
            for (int j = 1; j < size; j += 2) {
                booleanValues.put(new Pair<Integer, Integer>(i, j), random.nextBoolean());
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!booleanValues.containsKey(new Pair<>(i, j))) {
                    operators.put(new Pair<Integer, Integer>(i, j), Operator.values()[random.nextInt(3)]);
                }
            }
        }
    }

    @Override
    public Optional<Boolean> getValues(Pair<Integer, Integer> position) {
        return Optional.of((Boolean) booleanValues.get(position));
    }

    @Override
    public Operator getOperator(Pair<Integer, Integer> position) {
        return operators.get(position);
    }

    @Override
    public boolean hit(int x, int y) {
        var pair = new Pair<Integer, Integer>(x, y);
        if (selected.isEmpty()) {
            selected.add(pair);
            return true;
        }

        if (Math.abs(x - selected.get(selected.size() - 1).getX())
                + Math.abs(y - selected.get(selected.size() - 1).getY()) == 1) {
            

            selected.add(pair);
            if (booleanValues.containsKey(pair)) {
                booleanValues.remove(pair);
            }
            if (operators.containsKey(pair)) {
                operators.remove(pair);
            }
            return true;
        }
        selected.add(pair);
        return false;
    }

    @Override
    public boolean isOver() {
        return over;
    }

}
