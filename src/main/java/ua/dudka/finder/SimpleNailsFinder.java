package ua.dudka.finder;

public class SimpleNailsFinder implements NailsFinder {

    @Override
    public int find(int[] A, int Y) {
        Integer result = checkCornerCases(A, Y);
        if (result != null) {
            return result;
        }

        int maxNailsCount = 1;
        int groupNailsCount = 1;

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                groupNailsCount++;
                continue;
            }
            int possibleChangesCount = Math.min(Y, A.length - 1 - i);
            int localMaxCount = possibleChangesCount + groupNailsCount;
            if (localMaxCount > maxNailsCount) {
                maxNailsCount = localMaxCount;
            }
            groupNailsCount = 1;
        }
        return Math.max(groupNailsCount, maxNailsCount);
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

}
