package a05.e2;

import java.util.*;
import java.util.function.*;

public interface Logics {
    enum Operation {
        AND("AND", (x,y) -> x && y), OR("OR", (x, y) -> x || y), XOR("XOR", (x, y) -> x ^ y);

        private BinaryOperator<Boolean> operator;
        private String name;

        private Operation(String name, BinaryOperator<Boolean> operator) {
            this.operator = operator;
            this.name = name;
        }

        public BinaryOperator<Boolean> getX() {
            return operator;
        }

        public String getName() {
            return name;
        }
    }

    Optional<Operation> getOperatorName(int x, int y);
    Optional<Boolean> getBoolean(int x, int y);
    boolean isBoolean(int x, int y); 
}
