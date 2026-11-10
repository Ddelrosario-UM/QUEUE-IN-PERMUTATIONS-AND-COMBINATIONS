package queue_assignment;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {

        // Ask how many elements to add
        System.out.print("Enter number of elements to add: ");
        int n = input.nextInt();

        // Use queue.add() to insert elements
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " +( i + 1 )+ ": ");
            String element = input.next();
            queue.add(element);
        }

        System.out.println("Queue: " + queue);

        System.out.print("Enter number of elements to dequeue: ");
        int removeCount = input.nextInt();

        for (int i = 0; i < removeCount; i++) {
            if (!queue.isEmpty()) {
                queue.poll();
            }
        }

        System.out.println("Queue: " + queue);

    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {

        // Display the contents of the Queue
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue: " + queue);
        }

    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {

        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("\nQueue: " + queue);

        // Ask how many elements should be selected (r)
        System.out.print("\nEnter number of elements to arrange: ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid number of elements!");
            return;
        }

        // Generate all possible arrangements
        int n = queue.size();
        String[] elements = new String[n];
        for (int i = 0; i < n; i++) {
            elements[i] = queue.peek();
            queue.add(queue.poll());
        }

        Queue<String> permQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            permQueue.add(elements[i]);
        }

        for (int step = 1; step < r; step++) {
            int levelSize = permQueue.size();
            for (int i = 0; i < levelSize; i++) {
                String current = permQueue.poll();
                for (int j = 0; j < n; j++) {
                    if (!current.contains(elements[j])) {
                        permQueue.add(current + elements[j]);
                    }
                }
            }
        }

        // Display the total number of permutations
        System.out.println();
        int totalPermutations = 0;
        while (!permQueue.isEmpty()) {
            System.out.println(permQueue.peek());
            permQueue.poll();
            totalPermutations++;
        }

        System.out.println("\nTotal Permutations: " + totalPermutations);

    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {

        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("\nQueue: " + queue);

        // Ask how many elements should be selected (r)
        System.out.print("\nEnter number of elements to select: ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid number of elements!");
            return;
        }

        // Generate all possible selections
        int n = queue.size();
        String[] elements = new String[n];
        for (int i = 0; i < n; i++) {
            elements[i] = queue.peek();
            queue.add(queue.poll());
        }

        Queue<String> combQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            combQueue.add(elements[i]);
        }

        // Avoid duplicate combinations
        for (int step = 1; step < r; step++) {
            int levelSize = combQueue.size();
            for (int i = 0; i < levelSize; i++) {
                String current = combQueue.poll();

                int lastIndex = -1;
                for (int j = 0; j < n; j++) {
                    if (current.endsWith(elements[j])) {
                        lastIndex = j;
                    }
                }

                for (int j = lastIndex + 1; j < n; j++) {
                    combQueue.add(current + elements[j]);
                }
            }
        }

        // Display the total number of combinations
        System.out.println();
        int totalCombinations = 0;
        while (!combQueue.isEmpty()) {
            System.out.println(combQueue.peek());
            combQueue.poll();
            totalCombinations++;
        }

        System.out.println("\nTotal Combinations: " + totalCombinations);

    }
}