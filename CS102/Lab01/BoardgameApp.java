import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BoardgameApp {
    public static void main(String[] args) {

        Player winner = new Player();

        String playAgain = "";

        boolean won = false;

        ArrayList<Player> players = new ArrayList<>();
        int width;
        int height;
        int playerCount;

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to the board game.");
        System.out.print("Please enter board width: ");
        width = scan.nextInt();
        System.out.print("Please enter board height: ");
        height = scan.nextInt();
        System.out.print("How many players? ");
        playerCount = scan.nextInt();

        for (int i = 1; i < playerCount + 1; i++) {
            players.add(new Player());
        }
        Board board = new Board(height, width, players);

        int[] trapCells = board.trapCells();

        System.out.println("Please enter a character (symbol) for each player.");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("For player %d: ", i + 1);
            players.get(i).setSymbol(scan.next().charAt(0));
        }
        System.out.println("Players are rolling dice.");
        for (int i = 0; i < players.size(); i++) {
            players.get(i).diceRoll(rand.nextInt(6) + 1);
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%c: %d", players.get(i).getSymbol(), players.get(i).getDiceRoll());
            if (i != players.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - 1; j++) {
                if (players.get(j).getDiceRoll() < players.get(j + 1).getDiceRoll()) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);

                }
            }
        }

        for (int i = 0; i < players.size() - 1; i++) {
            if (players.get(i).getDiceRoll() == players.get(i + 1).getDiceRoll()) {
                System.out.printf("Breaking tie for: %c %c %n", players.get(i).getSymbol(),
                        players.get(i + 1).getSymbol());
            }
        }
        System.out.print("Playing order is: ");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%c", players.get(i).getSymbol());
            if (i != players.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        do {

            for (int i = 0; i < players.size(); i++) {
                System.out.println(board.getBoard());
                players.get(i).diceRoll(rand.nextInt(6) + 1);
                System.out.printf("Player %c rolls %d, how many cells you move? (0-%d): ",
                        players.get(i).getSymbol(), players.get(i).getDiceRoll(), players.get(i).getDiceRoll());
                int move = scan.nextInt();
                System.out.println();
                if (move > players.get(i).getDiceRoll()) {
                    move = players.get(i).getDiceRoll();
                }

                players.get(i).increaseCell(move);
                for (int j = 0; j < trapCells.length; j++) {
                    if (players.get(i).getCell() == trapCells[j]) {
                        for (int k = 0; k < board.corners.length - 1; k++) {
                            if (board.corners[k] < players.get(i).getCell()
                                    && board.corners[k + 1] > players.get(i).getCell()) {
                                players.get(i).setCell(board.corners[k]);
                            }
                        }
                        System.out.println("You moved into a trap!");
                        System.out.println("You moved back to the closest corner!");
                        trapCells[j] = -1;
                        players.get(i).triggeredTraps();

                    }
                }

                if (players.get(i).getCell() >= 2 * (width + height - 2)) {
                    i = i + 5;
                    won = true;
                    winner = players.get(i - 5);
                }
            }

        } while (!won);

        System.out.printf("Winner is %c, congratulations!\n", winner.getSymbol());
        System.out.println("Player Move Trap");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%c      %d    %d\n", players.get(i).getSymbol(), players.get(i).getCell() - 1,
                    players.get(i).getTriggeredTraps());
        }

        playAgain = scan.next();

        scan.close();

        System.out.print("Bye...");
    }
}
