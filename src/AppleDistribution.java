import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AppleDistribution {
    // Define a class to represent a person
    static class Person {
        String name;
        int amountPaid;
        int totalWeight;

        public Person(String name, int amountPaid) {
            this.name = name;
            this.amountPaid = amountPaid;
        }
    }

    // Define a class to represent an apple
    static class Apple {
        int weight;

        public Apple(int weight) {
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        // Create persons
        Person ram = new Person("Ram", 50);
        Person sham = new Person("Sham", 30);
        Person rahim = new Person("Rahim", 20);

        // Create a list to store apples
        List<Apple> apples = new ArrayList<>();

        // Input apple weights
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter apple weight in grams (-1 to stop): ");
            int weight = scanner.nextInt();
            if (weight == -1) {
                break;
            }
            apples.add(new Apple(weight));
        }

        // Sort apples in descending order of their weights
        Collections.sort(apples, (a, b) -> Integer.compare(b.weight, a.weight));

        // Calculate total weight of apples each person should get
        int totalWeight = 0;
        for (Apple apple : apples) {
            totalWeight += apple.weight;
        }
        ram.totalWeight = (int) (totalWeight * (ram.amountPaid / 100.0));
        sham.totalWeight = (int) (totalWeight * (sham.amountPaid / 100.0));
        rahim.totalWeight = (int) (totalWeight * (rahim.amountPaid / 100.0));

        // Distribute apples
        List<Apple> ramApples = new ArrayList<>();
        List<Apple> shamApples = new ArrayList<>();
        List<Apple> rahimApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (ram.totalWeight >= apple.weight) {
                ramApples.add(apple);
                ram.totalWeight -= apple.weight;
            } else if (sham.totalWeight >= apple.weight) {
                shamApples.add(apple);
                sham.totalWeight -= apple.weight;
            } else {
                rahimApples.add(apple);
                rahim.totalWeight -= apple.weight;
            }
        }

        // Print distribution result
        System.out.println("Distribution Result:");
        System.out.print("Ram: ");
        for (Apple apple : ramApples) {
            System.out.print(apple.weight + " ");
        }
        System.out.println();
        System.out.print("Sham: ");
        for (Apple apple : shamApples) {
            System.out.print(apple.weight + " ");
        }
        System.out.println();
        System.out.print("Rahim: ");
        for (Apple apple : rahimApples) {
            System.out.print(apple.weight + " ");
        }
        System.out.println();
    }
}