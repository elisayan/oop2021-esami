package a03b.e1;

import java.util.*;

public class LensFactoryImpl implements LensFactory {

    @Override
    public <E> Lens<List<E>, E> indexer(int i) {
        return new Lens<List<E>,E>() {

            @Override
            public E get(List<E> s) {
                return s.get(i);
            }

            @Override
            public List<E> set(E a, List<E> s) {
                List<E> list = new ArrayList<>(s);
                list.set(i, a);
                return list;
            }
            
        };
    }

    @Override
    public <E> Lens<List<List<E>>, E> doubleIndexer(int i, int j) {
        return new Lens<List<List<E>>,E>() {

            @Override
            public E get(List<List<E>> s) {
                return s.get(i).get(j);
            }

            @Override
            public List<List<E>> set(E a, List<List<E>> s) {
                List<List<E>> matrix = new ArrayList<>(s);
                List<E> row = new ArrayList<>(matrix.get(i));
                row.set(j, a);
                matrix.set(i, row);
                return matrix;
            }
        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, A> left() {
        return new Lens<Pair<A,B>,A>() {

            @Override
            public A get(Pair<A, B> s) {
                return s.get1();
            }

            @Override
            public Pair<A, B> set(A a, Pair<A, B> s) {
                return new Pair<A,B>(a, s.get2());
            }
            
        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, B> right() {
        return new Lens<Pair<A,B>,B>() {

            @Override
            public B get(Pair<A, B> s) {
                return s.get2();
            }

            @Override
            public Pair<A, B> set(B a, Pair<A, B> s) {
                return new Pair<A,B>(s.get1(), a);
            }
            
        };
    }

    @Override
    public <A, B, C> Lens<List<Pair<A, Pair<B, C>>>, C> rightRightAtPos(int i) {
        return new Lens<List<Pair<A,Pair<B,C>>>,C>() {

            @Override
            public C get(List<Pair<A, Pair<B, C>>> s) {
                return s.get(i).get2().get2();
            }

            @Override
            public List<Pair<A, Pair<B, C>>> set(C a, List<Pair<A, Pair<B, C>>> s) {
                List<Pair<A, Pair<B, C>>> newList = new ArrayList<>(s);
                Pair<A, Pair<B, C>> pair = s.get(i);
                Pair<B, C> innerPair = pair.get2();
                Pair<B, C> newInnerPair = new Pair<>(innerPair.get1(), a);
                newList.set(i, new Pair<>(pair.get1(), newInnerPair));
                return newList;
            }
            
        };
    }

}
