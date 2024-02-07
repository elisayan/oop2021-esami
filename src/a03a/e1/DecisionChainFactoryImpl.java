package a03a.e1;

import java.util.*;
import java.util.function.Predicate;

public class DecisionChainFactoryImpl implements DecisionChainFactory {

    @Override
    public <A, B> DecisionChain<A, B> oneResult(B b) {
        return new DecisionChain<A, B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.of(b);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return this;
            }

        };
    }

    @Override
    public <A, B> DecisionChain<A, B> simpleTwoWay(Predicate<A> predicate, B positive, B negative) {
        return new DecisionChain<A, B>() {
            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                if (predicate.test(a)) {
                    return new DecisionChain<A, B>() {
                        @Override
                        public Optional<B> result(A a) {
                            return Optional.of(positive);
                        }

                        @Override
                        public DecisionChain<A, B> next(A a) {
                            return this;
                        }
                    };
                } else {
                    return new DecisionChain<A, B>() {
                        @Override
                        public Optional<B> result(A a) {
                            return Optional.of(negative);
                        }

                        @Override
                        public DecisionChain<A, B> next(A a) {
                            return this;
                        }
                    };
                }
            }

        };
    }

    @Override
    public <A, B> DecisionChain<A, B> enumerationLike(List<Pair<A, B>> mapList, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                if (a.equals(mapList.get(0).get1())) {
                    return Optional.of(mapList.get(0).get2());
                }
                if (a.equals(mapList.get(1).get1()) || a.equals(mapList.get(2).get1())) {
                    return this.next(a).result(a);
                }
                if (!mapList.contains(a)) {
                    return Optional.of(defaultReply);
                }
                
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                if (a.equals(mapList.get(1).get1())) {
                    return new DecisionChain<A,B>() {

                        @Override
                        public Optional<B> result(A a) {
                            return Optional.of(mapList.get(1).get2());
                        }

                        @Override
                        public DecisionChain<A, B> next(A a) {
                            return this;
                        }
                        
                    };
                } else{
                    return new DecisionChain<A,B>() {

                        @Override
                        public Optional<B> result(A a) {
                            return Optional.of(mapList.get(2).get2());
                        }

                        @Override
                        public DecisionChain<A, B> next(A a) {
                            return this;
                        }
                        
                    };
                }
            }
            
        };
    }

    @Override
    public <A, B> DecisionChain<A, B> twoWay(Predicate<A> predicate, DecisionChain<A, B> positive,
            DecisionChain<A, B> negative) {
        return new DecisionChain<A, B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return predicate.test(a) ? positive : negative;
            }

        };
    }

    @Override
    public <A, B> DecisionChain<A, B> switchChain(List<Pair<Predicate<A>, B>> cases, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                for (Pair<Predicate<A>,B> pair : cases) {
                    if (pair.get1().test(a)) {
                        return Optional.of(pair.get2());
                    } 
                    
                }
                return Optional.of(defaultReply);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'next'");
            }
            
        };
    }

}
