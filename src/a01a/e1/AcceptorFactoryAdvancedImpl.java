package a01a.e1;

import java.util.*;
import java.util.function.*;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        return new Acceptor<String,Integer>() {

            private List<String> allString = new LinkedList<>();
            @Override
            public boolean accept(String e) {
                allString.add(e);
                return true;
            }

            @Override
            public Optional<Integer> end() {
                int count=0;
                for (String string : allString) {
                    if (string.length()==0) {
                        count++;
                    }
                }
                return Optional.of(count);
            }
            
        };
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer,String>() {

            List<Integer> allString = new LinkedList<>();
            boolean notAccept = false;

            @Override
            public boolean accept(Integer e) {
                if (allString.isEmpty() || e > allString.get(allString.size() - 1)) {
                    allString.add(e);
                    return true;
                }
                notAccept=true;
                return false;
            }

            @Override
            public Optional<String> end() {
                if (notAccept) {
                    return Optional.empty();
                }
                return Optional.ofNullable(String.valueOf(allString).replace(", ", ":").replace("[", "").replace("]", ""));
            }
            
        };
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer,Integer>() {

            List<Integer> allString = new LinkedList<>();
            int numAceptor=0;
            @Override
            public boolean accept(Integer e) {
                numAceptor++;
                if (allString.size() < 3) {
                    allString.add(e);
                    return true;
                }
                return false;
            }

            @Override
            public Optional<Integer> end() {
                if (numAceptor!=3) {
                    return Optional.empty();
                }
                return Optional.ofNullable(allString.stream().mapToInt(Integer::valueOf).sum());
            }
            
        };
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E,Pair<O1,O2>>() {

            boolean notAccept=false;

            @Override
            public boolean accept(E e) {
                if (a1.accept(e) && a2.accept(e)) {
                    return true;
                }
                notAccept=true;
                return false;
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                if (notAccept) {
                    return Optional.empty();
                }
                return Optional.ofNullable(new Pair<>(a1.end().get(),a2.end().get()));
            }
        };
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generalised'");
    }

}
