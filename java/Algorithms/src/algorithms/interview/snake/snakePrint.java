package algorithms.interview.snake;

/**
 * Created on  May 2, 2011
 * @author leeing
 */
public class snakePrint {

    private static final int length = 7;
    private static int value = 1;
    private static int[][] snake = new int[length][length];
    private static Direction lastDirection = Direction.Right;

    private static enum Direction {

        Right, Down, Left, Up;
    }

    public static void initialArray() {
        int row = 0, line = 0;
        for (int c = 0; c < length * length; c++) {
            snake[row][line] = value;
            lastDirection = findDirection(row, line);
            switch (lastDirection) {
                case Right:
                    line++;
                    break;
                case Down:
                    row++;
                    break;
                case Left:
                    line--;
                    break;
                case Up:
                    row--;
                    break;
                default:
                    System.out.println("error");
            }
            value++;
        }
    }

    static Direction findDirection(int row, int line) {
        Direction direction = lastDirection;
        switch (direction) {
            case Right: {
                if ((line == length - 1) || (snake[row][line + 1] != 0)) {
                    direction = direction.Down;
                }
                break;
            }
            case Down: {
                if ((row == length - 1) || (snake[row + 1][line] != 0)) {
                    direction = direction.Left;
                }
                break;
            }
            case Left: {
                if ((line == 0) || (snake[row][line - 1] != 0)) {
                    direction = direction.Up;
                }
                break;
            }
            case Up: {
                if (snake[row - 1][line] != 0) {
                    direction = direction.Right;
                }
                break;
            }
        }
        return direction;
    }

    public static void main(String[] args) {
        initialArray();

        // display.....
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(snake[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
