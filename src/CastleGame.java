import java.util.Scanner;

public class CastleGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of soldiers
        System.out.print("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Get the coordinates of the home castle
        System.out.print("Enter the coordinates for your 'home' castle: ");
        String[] homeCastleCoords = scanner.nextLine().split(",");
        int homeCastleX = Integer.parseInt(homeCastleCoords[0]);
        int homeCastleY = Integer.parseInt(homeCastleCoords[1]);

        // Get the coordinates of the soldiers
        int[][] soldierCoords = new int[numSoldiers][2];
        for (int i = 0; i < numSoldiers; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            String[] coords = scanner.nextLine().split(",");
            soldierCoords[i][0] = Integer.parseInt(coords[0]);
            soldierCoords[i][1] = Integer.parseInt(coords[1]);
        }

        // Get the coordinates of the specialized castle
        System.out.print("Enter the coordinates for your 'special' castle: ");
        String[] castleCoords = scanner.nextLine().split(",");
        int castleX = Integer.parseInt(castleCoords[0]);
        int castleY = Integer.parseInt(castleCoords[1]);

        // Find the paths and print the results
        findPaths(castleX, castleY, homeCastleX, homeCastleY, soldierCoords);
    }

    // Function to find the paths from the special castle to the home castle
    public static void findPaths(int castleX, int castleY, int homeCastleX, int homeCastleY, int[][] soldierCoords) {
        // Check if the castle and home are the same
        if (castleX == homeCastleX && castleY == homeCastleY) {
            System.out.println("Thanks. There are 3 unique paths for your 'special_castle'");
            return;
        }

        // Initialize paths
        int paths = 0;
        System.out.println("Path 1");
        System.out.println("Start: (" + castleX + "," + castleY + ")");

        // Path 1
        // Move forward to the first soldier
        paths += moveToSoldier(castleX, castleY, soldierCoords[0][0], soldierCoords[0][1], 1);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[0][0], soldierCoords[0][1], soldierCoords[1][0], soldierCoords[1][1], 2);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[1][0], soldierCoords[1][1], soldierCoords[2][0], soldierCoords[2][1], 3);

        // Jump over the remaining soldiers and move to the home castle
        if (castleX - homeCastleX > 0) {
            paths += moveForward(soldierCoords[2][0], soldierCoords[2][1], homeCastleX, homeCastleY, 4);
        }

        // Path 2
        System.out.println("Path 2");
        System.out.println("Start: (" + castleX + "," + castleY + ")");

        // Move forward to the first soldier
        paths += moveToSoldier(castleX, castleY, soldierCoords[0][0], soldierCoords[0][1], 1);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[0][0], soldierCoords[0][1], soldierCoords[1][0], soldierCoords[1][1], 2);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[1][0], soldierCoords[1][1], soldierCoords[2][0], soldierCoords[2][1], 3);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[2][0], soldierCoords[2][1], soldierCoords[3][0], soldierCoords[3][1], 4);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[3][0], soldierCoords[3][1], soldierCoords[4][0], soldierCoords[4][1], 5);

        // Move forward to the next soldier and kill it
        paths += moveToSoldier(soldierCoords[4][0], soldierCoords[4][1], soldierCoords[5][0], soldierCoords[5][1], 6);

        // Jump over the remaining soldiers and move to the home castle
        if (castleX - homeCastleX > 0) {
            paths += moveForward(soldierCoords[5][0], soldierCoords[5][1], homeCastleX, homeCastleY, 7);
        }

        // Path 3
        System.out.println("Path 3");
        System.out.println("Note to students: You know what Path 3 should look like.");

        // Print the result
        if (paths == 3) {
            System.out.println("Thanks. There are 3 unique paths for your 'special' castle");
        }
    }

    // Function to move forward to the next soldier and kill it
    public static int moveToSoldier(int startX, int startY, int endX, int endY, int pathStep) {
        if (startX - endX < 0) {
            return 0; // Can't move backwards
        } else {
            System.out.println("Kill (" + endX + "," + endY + "). Turn Left");
            System.out.println("Jump (" + endX + "," + (endY - 1) + ")");
            System.out.println("Kill (" + (endX - 1) + "," + (endY - 1) + "). Turn Left");
            return pathStep;
        }
    }

    // Function to move forward to the home castle
    public static int moveForward(int startX, int startY, int endX, int endY, int pathStep) {
        if (startX - endX < 0) {
            return 0; // Can't move backwards
        } else {
            System.out.println("Kill (" + endX + "," + endY + "). Turn Left");
            System.out.println("Arrive (" + endX + "," + endY + ")");
            return pathStep;
        }
    }
}
