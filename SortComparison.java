package ass7;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SortComparison {

    public static ArrayList<Integer> createRandomArrayList(int size) {
        ArrayList<Integer> randomList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomList.add(random.nextInt(101)); // Generates random integers between 0 and 100
        }
        return randomList;
    }

    public static void printArrayList(ArrayList<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // swap
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }

        int middle = list.size() / 2;
        ArrayList<Integer> left = new ArrayList<>(list.subList(0, middle));
        ArrayList<Integer> right = new ArrayList<>(list.subList(middle, list.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // Add the remaining elements
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the ArrayList:");
        int size = scanner.nextInt();

        ArrayList<Integer> randomArrayList = createRandomArrayList(size);

        // Copy the ArrayList for BubbleSort since it modifies the original list
        ArrayList<Integer> bubbleSortList = new ArrayList<>(randomArrayList);
        System.out.println("\nRandom ArrayList:");
        printArrayList(randomArrayList);

        long startTime = System.currentTimeMillis();
        bubbleSort(bubbleSortList);
        long endTime = System.currentTimeMillis();
        System.out.println("\nBubbleSorted ArrayList:");
        printArrayList(bubbleSortList);
        System.out.println("BubbleSort Time: " + (endTime - startTime) + " milliseconds");

        // MergeSort
        ArrayList<Integer> mergeSortList = new ArrayList<>(randomArrayList);
        startTime = System.currentTimeMillis();
        ArrayList<Integer> sortedList = mergeSort(mergeSortList);
        endTime = System.currentTimeMillis();
        System.out.println("\nMergeSorted ArrayList:");
        printArrayList(sortedList);
        System.out.println("MergeSort Time: " + (endTime - startTime) + " milliseconds");

        scanner.close();
    }
}
