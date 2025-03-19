import java.util.Scanner;

public class logic {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int gameBoardSize = (int) (Math.random() * 10) + 5;

        char player = 'Z';
        char money = '@';
        char dragon = 'M';
        char trap = 'T';

        char[][] board = new char[gameBoardSize][gameBoardSize];
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                board[i][j] = '.';
            }
        }

        int verticalPlayerPosition = verticalPlayerPosition(gameBoardSize);
        int horizontalPlayerPosition = horizontalPlayerPosition(gameBoardSize);
        int moneyX = moneyPosition(gameBoardSize, verticalPlayerPosition);
        int moneyY = moneyPosition(gameBoardSize, horizontalPlayerPosition);
        int dragonX = dragonPosition(gameBoardSize, verticalPlayerPosition, moneyX);
        int dragonY = dragonPosition(gameBoardSize, horizontalPlayerPosition, moneyY);
        int trapX = trapPosition(gameBoardSize, verticalPlayerPosition, moneyX, dragonX);
        int trapY = trapPosition(gameBoardSize, horizontalPlayerPosition, moneyY, dragonY);

        board[horizontalPlayerPosition][verticalPlayerPosition] = player;
        board[moneyX][moneyY] = money;
        board[dragonX][dragonY] = dragon;
        board[trapX][trapY] = trap;

        boolean moneyCollected = false;

        while (true) {
            for (int i = 0; i < gameBoardSize; i++) {
                for (int j = 0; j < gameBoardSize; j++) {
                    if (i == 0 || i == gameBoardSize - 1 || j == 0 || j == gameBoardSize - 1) {
                        System.out.print("X");
                    } else {
                        System.out.print(board[i][j]);
                    }
                }
                System.out.println();
            }

            System.out.println("Enter your command (up, down, left, right): ");
            String userCommand = scnr.nextLine();

            board[horizontalPlayerPosition][verticalPlayerPosition] = '.';

            if (userCommand.equalsIgnoreCase("left")) {
                verticalPlayerPosition--;
            } else if (userCommand.equalsIgnoreCase("right")) {
                verticalPlayerPosition++;
            } else if (userCommand.equalsIgnoreCase("up")) {
                horizontalPlayerPosition--;
            } else if (userCommand.equalsIgnoreCase("down")) {
                horizontalPlayerPosition++;
            }

            board[horizontalPlayerPosition][verticalPlayerPosition] = player;

            if (horizontalPlayerPosition == moneyX && verticalPlayerPosition == moneyY) {
                System.out.println("You've secured the money. Now, go towards the exit!");
                moneyCollected = true;
            } else if (horizontalPlayerPosition == trapX && verticalPlayerPosition == trapY) {
                if (moneyCollected) {
                    System.out.println("You won the game with the money!");
                } else {
                    System.out.println("You won the game but without the money.");
                }
                break; 
            } else if (horizontalPlayerPosition == dragonX && verticalPlayerPosition == dragonY) {
                System.out.println("You were caught by the dragon! Game over.");
                break; 
            }
        }

        scnr.close();
    }

    public static int verticalPlayerPosition(int gameBoardSize) {
        return (int) (Math.random() * (gameBoardSize - 2)) + 1;
    }

    public static int horizontalPlayerPosition(int gameBoardSize) {
        return (int) (Math.random() * (gameBoardSize - 2)) + 1;
    }

    public static int moneyPosition(int gameBoardSize, int playerPosition) {
        int pos;
        do {
            pos = (int) (Math.random() * (gameBoardSize - 2)) + 1;
        } while (pos == playerPosition);
        return pos;
    }

    public static int dragonPosition(int gameBoardSize, int playerPosition, int moneyPosition) {
        int pos;
        do {
            pos = (int) (Math.random() * (gameBoardSize - 2)) + 1;
        } while (pos == playerPosition || pos == moneyPosition);
        return pos;
    }

    public static int trapPosition(int gameBoardSize, int playerPosition, int moneyPosition, int dragonPosition) {
        int pos;
        do {
            pos = (int) (Math.random() * (gameBoardSize - 2)) + 1;
        } while (pos == playerPosition || pos == moneyPosition || pos == dragonPosition);
        return pos;
    }
}
