package a06.e1;

import java.util.*;

public class CirclerFactoryImpl implements CirclerFactory {

    public abstract class CirclerImpl<T> implements Circler<T> {

        Iterator<T> iterator;

        @Override
        public T produceOne() {
            return iterator.next();
        }

        @Override
        public void setSource(List<T> elements) {
            iterator = new InfiniteIterator<>(elements);
        }

        private static class InfiniteIterator<T> implements Iterator<T> {
            private final List<T> elements;
            private int index;
    
            public InfiniteIterator(List<T> elements) {
                if (elements.isEmpty()) {
                    throw new IllegalArgumentException("Source list must not be empty");
                }
                this.elements = elements;
                this.index = 0;
            }
    
            @Override
            public boolean hasNext() {
                return true; // Always true for an infinite iterator
            }
    
            @Override
            public T next() {
                T element = elements.get(index);
                index = (index + 1) % elements.size(); // Loop back to the beginning if we reach the end
                return element;
            }
        }
    }

    @Override
    public <T> Circler<T> leftToRight() {
        return new CirclerImpl<T>() {

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new LinkedList<>();

                for (int i = 0; i < n; i++) {
                    output.add(iterator.next());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> alternate() {
        return new CirclerImpl<T>() {

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new LinkedList<>();

                for (int i = 0; i < n; i++) {
                    output.add(iterator.next());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> stayToLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stayToLast'");
    }

    @Override
    public <T> Circler<T> leftToRightSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leftToRightSkipOne'");
    }

    @Override
    public <T> Circler<T> alternateSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alternateSkipOne'");
    }

    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stayToLastSkipOne'");
    }

}
