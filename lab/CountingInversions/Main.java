class Main {
    public static int countInversions(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length ; i++) {
            for (int j = 0; j < A.length ; j++) {
                if (A[i] > A[j] && i <= j) {
                    count++;
                }
            }
        }
        return count;
    }
}
