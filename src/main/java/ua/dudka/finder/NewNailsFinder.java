package ua.dudka.finder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;

public class NewNailsFinder implements NailsFinder {

    @Override
    public int solution(int[] A, int Y) {
        Integer result = checkCornerCases(A, Y);
        if (result != null) {
            return result;
        }

        int length = A.length;
        int maxNailLength = 0;

        LinkedList<Group> nailsGroups = getGroups(A);

        for (int i = 0; i < nailsGroups.size() - 1; i++) {
            Group group = nailsGroups.get(i);

            int possibleKicksCount = Math.min(Y, length - group.end - 1);
            int groupCountWithPossibleKicks = possibleKicksCount + group.count;
            if (groupCountWithPossibleKicks > maxNailLength) {
                maxNailLength = groupCountWithPossibleKicks;
            }
        }
        return maxNailLength;
    }

    private Integer checkCornerCases(int[] A, int Y) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int length = A.length;

        if (Y >= length) {
            return length;
        }

        return null; //Optional ?
    }

    private LinkedList<Group> getGroups(int[] A) {
        LinkedList<Group> nailsGroups = new LinkedList<>();

        for (int i = 0; i < A.length; ) {
            int value = A[i];
            int nextIndex = i + 1;
            int start = i;
            int end = i;
            int count = 1;
            while (nextIndex < A.length && value == A[nextIndex]) {
                count++;
                end = nextIndex++;
            }
            i = nextIndex;
            nailsGroups.add(new Group(value, count, start, end));
        }
        return nailsGroups;
    }

    @AllArgsConstructor
    @EqualsAndHashCode(of = "value")
    static class Group {
        int value;
        int count;
        int start;
        int end;
    }
}
