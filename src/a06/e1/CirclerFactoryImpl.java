package a06.e1;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class CirclerFactoryImpl implements CirclerFactory {

    private class CirclerImpl<T> implements Circler<T>{

        private Iterator<T> iterator = null;
        private Function<Integer, Stream<Integer>> indexes;

        public CirclerImpl(Function<Integer, Stream<Integer>> indexes) {
            this.indexes = indexes;
        }

        @Override
        public void setSource(List<T> elements) {
            iterator = indexes.apply(elements.size()).map(elements::get).iterator();
        }

        @Override
        public T produceOne() {
            return iterator.next();
        }

        @Override
        public List<T> produceMany(int n) {
            return Stream.generate(this::produceOne).limit(n).toList();
        }
    
        
    }

    @Override
    public <T> Circler<T> leftToRight() {
        return new CirclerImpl<>(n -> Stream.iterate(0, i -> i + 1).map(i -> i % n));
    }

    @Override
    public <T> Circler<T> alternate() {
        return new CirclerImpl<>(
                n -> Stream.iterate(0, i -> i + 1).map(i -> (i / n) % 2 == 0 ? i % n : n - 1 - (i % n)));
    }

    @Override
    public <T> Circler<T> stayToLast() {
        return new CirclerImpl<>(n -> Stream.iterate(0, i -> i == n - 1 ? i : i + 1));
    }

    @Override
    public <T> Circler<T> leftToRightSkipOne() {
        return new CirclerImpl<>(n -> {
            Stream<Integer> stream = Stream.iterate(0, i -> i + 1).map(i -> i % n);
            var it = stream.iterator();
            return Stream.generate(() -> { 
                int val = it.next(); 
                it.next(); 
                return val; 
            });
        });
    }

    @Override
    public <T> Circler<T> alternateSkipOne() {
        return new CirclerImpl<>(n -> {
            Stream<Integer> stream = Stream.iterate(0, i -> i + 1).map(i -> (i / n) % 2 == 0 ? i % n : n - 1 - (i % n));
            var it = stream.iterator();
            return Stream.generate(() -> { 
                int val = it.next(); 
                it.next(); 
                return val; 
            });
        });
    }

    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        return new CirclerImpl<>(n -> {
            Stream<Integer> stream = Stream.iterate(0, i -> i == n - 1 ? i : i + 1);
            var it = stream.iterator();
            return Stream.generate(() -> { 
                int val = it.next(); 
                it.next(); 
                return val; 
            });
        });
    }


}
