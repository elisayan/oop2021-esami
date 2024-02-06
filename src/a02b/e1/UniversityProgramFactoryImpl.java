package a02b.e1;

import java.util.*;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    private static abstract class AbstractUniversityProgram implements UniversityProgram{

        Map<String,Pair<Sector,Integer>> course = new HashMap<>();
        @Override
        public void addCourse(String name, Sector sector, int credits) {
            course.put(name, new Pair<UniversityProgram.Sector,Integer>(sector, credits));
        }

    }

    @Override
    public UniversityProgram flexible() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                return course.entrySet().stream().filter(e -> courseNames.contains(e.getKey()))
                        .mapToInt(e -> e.getValue().get2()).sum() == 60;
            }
            
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                return false;
            }
            
        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortComputerScience'");
    }

    @Override
    public UniversityProgram realistic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realistic'");
    }

}
