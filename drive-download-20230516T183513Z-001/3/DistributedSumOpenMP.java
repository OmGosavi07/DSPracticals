public class DistributedSumOpenMP {
    public static void main(String[] args) {
        // Define the number of elements in the array and the number of processors/threads
        int N = 100;
        int n = Runtime.getRuntime().availableProcessors();

        // Generate a random array of N integers
        int[] array = new int[N];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(10);
        }
        System.out.println("Array: " + java.util.Arrays.toString(array));

        // Calculate the sum of the array elements using OpenMP
        int[] sums = new int[n];
       // pragma omp parallel for num_threads(n)
        for (int i = 0; i < n; i++) {
            int start = i * (N / n);
            int end = (i + 1) * (N / n);
            int sum = 0;
            for (int j = start; j < end; j++) {
                sum += array[j];
            }
            sums[i] = sum;
        }

        // Reduce the sums to a final sum and display the intermediate sums
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += sums[i];
            System.out.println("Intermediate sum " + i + ": " + sums[i]);
        }
        System.out.println("Total sum: " + totalSum);
    }
}
