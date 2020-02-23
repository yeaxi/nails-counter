package ua.dudka.finder;

import java.util.LinkedList;
import java.util.Objects;

public class LinkedGroupsNailsFinder implements NailsFinder {

    @Override
    public int find(int[] nails, int changesCount) {
        Integer result = checkCornerCases(nails, changesCount);
        if (result != null) {
            return result;
        }

        int length = nails.length;
        int maxNailLength = 0;

        LinkedList<Group> nailsGroups = getGroups(nails);

        for (int i = 0; i < nailsGroups.size() - 1; i++) {
            Group group = nailsGroups.get(i);

            int possibleKicksCount = Math.min(changesCount, length - group.end - 1);
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

    private static class Group {
        int value;
        int count;
        int start;
        int end;

        public Group(int value, int count, int start, int end) {
            this.value = value;
            this.count = count;
            this.start = start;
            this.end = end;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Group group = (Group) o;
            return value == group.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
