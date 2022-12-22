import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        generateAndBenchmark(20);
        generateAndBenchmark(75);
        generateAndBenchmark(150);

        generateAndBenchmark(10000);
        generateAndBenchmark(25000);
        generateAndBenchmark(50000);

        generateAndBenchmark(100000);
        generateAndBenchmark(250000);
        generateAndBenchmark(500000);

        generateAndBenchmark(1000000);
        generateAndBenchmark(2500000);
        generateAndBenchmark(5000000);

        generateAndBenchmark(10000000);
        generateAndBenchmark(25000000);
        generateAndBenchmark(50000000);

//        int[] smallInput1 = generateRandomArray(new Random(20), 20);
//        System.out.println("Unsorted array: ");
//        System.out.println(Arrays.toString(smallInput1));
//
//        quicksort(smallInput1, 0, smallInput1.length - 1);
//        System.out.println("Sorted array: ");
//        printArray(smallInput1);
//
//        benchmark("quicksort with input size 20", () -> {
//            quicksort(smallInput1);
//        });

    }

    public static int[] generateRandomArray(Random random, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public static int[] generateAndBenchmark(int size) {
        Random random = new Random(size);
        int[] arr = generateRandomArray(random, size);
        benchmark("quicksort with input size " + size, () -> {
            quicksort(arr);
        });
        return arr;
    }

    public static <A> A benchmark(String name, Supplier<A> supplier) {
        return benchmark(name, 10, supplier);
    }

    public static <A> A benchmark(String name, int measurements, Supplier<A> supplier) {
        A value = supplier.get();
        long startTime = System.currentTimeMillis();
        for (int idx = 0; idx < measurements; idx++) {
            value = supplier.get();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / measurements;
        System.out.println("Average running time of '" + name + "' is " + totalTime + " ms");
        return value;
    }

    public static void benchmark(String name, int measurements, Runnable runnable) {
        benchmark(name, measurements, () -> {
            runnable.run();
            return 0;
        });
    }

    public static void benchmark(String name, Runnable runnable) {
        benchmark(name, 10, runnable);
    }


    static int partition(int[] list, int first, int last){
        // The pivot should be the median of the first, middle, and last elements.
        int middle = first + (last - first) / 2;

        if (list[first] > list[middle])
            swapArray(list, first, middle);

        if (list[first] > list[last])
            swapArray(list, first, last);

        if (list[middle] > list[last])
            swapArray(list, middle, last);

        swapArray(list, middle, first);

        int pivot = list[first];
        int i = first - 1;
        int j = last + 1;

        while (true) {
            do {
                i++;
            } while (list[i] < pivot);

            do {
                j--;
            } while (list[j] > pivot);

            if (i >= j)
                return j;

            swapArray(list, i, j);
        }
    }

    static void quicksort(int[] list) {
        quicksort(list, 0, list.length - 1);
    }

    static void quicksort(int[] list, int first, int last) {
        if (first < last) {
            // Find the pivot index
            int pivotLocation = partition(list, first, last);
            // Apply Divide and conquer strategy to sort the left side and right side of an array
            // respective to the pivot position

            // Left side of array
            quicksort(list, first, pivotLocation);
            // Right side of array
            quicksort(list, pivotLocation + 1, last);
        }
    }
    static void printArray(int[] arr)
    {
        int n = arr.length;
        for (Integer integer : arr) System.out.print(integer + " ");
        System.out.println();
    }

    static void swapArray(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
