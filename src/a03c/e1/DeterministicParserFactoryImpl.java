package a03c.e1;

import java.util.*;

public class DeterministicParserFactoryImpl implements DeterministicParserFactory {

    @Override
    public DeterministicParser oneSymbol(String s) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                List<String> list = new LinkedList<>(tokens);
                if (list.contains(s)) {
                    list.remove(s);
                    return Optional.of(list);
                }
                return Optional.empty();
            }

        };
    }

    @Override
    public DeterministicParser twoSymbols(String s1, String s2) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                List<String> list = new LinkedList<>(tokens);
                if (list.contains(s1) && list.contains(s2)) {
                    list.remove(s1);
                    list.remove(s2);
                    return Optional.of(list);
                }
                return Optional.empty();
            }

        };
    }

    @Override
    public DeterministicParser possiblyEmptyIncreasingSequenceOfPositiveNumbers() {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                if (tokens.stream().anyMatch(s -> Integer.valueOf(s) < 0)) {
                    return Optional.of(tokens);
                }

                List<String> list = new LinkedList<>(tokens);
                List<String> output = new LinkedList<>();

                for (int i = 0; i < list.size() - 1; i++) {
                    var next = Integer.valueOf(list.get(i + 1));
                    if (Integer.valueOf(list.get(i)) < next) {
                        next = Integer.valueOf(list.get(i));
                    } else {
                        output.add(list.get(i + 1));
                    }
                }
                return Optional.of(output);
            }

        };
    }

    @Override
    public DeterministicParser sequenceOfParsersWithDelimiter(String start, String stop, String delimiter,
            DeterministicParser element) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'accepts'");
            }

        };
    }

    @Override
    public DeterministicParser sequence(DeterministicParser first, DeterministicParser second) {
        return token -> first.accepts(token).flatMap(second::accepts);
    }

}
