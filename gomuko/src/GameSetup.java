import java.awt.*;

/**
 * @description: 对游戏进行设置
 */
public class GameSetup {
    private static final int WIDTH = 42;
    private static final int HEIGHT = 42;

    /**
     * @description: 棋盘的设置，将对应的图像赋给棋盘对象
     * @param goBoard 棋盘
     * @return: void
     * @date: 2021/12/21
     */
    public static void boardSetup(Tile[][] goBoard) {
        //将所有棋盘的所有落子点的图像设置为TileSet.BOARD
        for (int i = 0; i < goBoard.length; i += 1) {
            for (int j = 0; j < goBoard[0].length; j += 1) {
                goBoard[i][j] = TileSet.BOARD;
            }
        }
        //将棋盘中的某些落子点的图像设置为TileSet.DOTBOARD
        for (int i = 3; i < goBoard.length; i += 7) {
            for (int j = 3; j < goBoard[0].length; j += 7) {
                goBoard[i][j] = TileSet.DOTBOARD;
            }
        }
        //设置棋盘边界的落子点的图像
        for (int i = 1; i < goBoard.length - 1; i += 1) {
            goBoard[i][0] = TileSet.BBOARD;
            goBoard[i][15] = TileSet.TBOARD;
        }
        for (int j = 1; j < goBoard[0].length - 1; j += 1) {
            goBoard[0][j] = TileSet.LBOARD;
            goBoard[15][j] = TileSet.RBOARD;
        }
        //设置棋盘四个的边角的落子点的图像
        goBoard[0][0] = TileSet.BLCORNER;
        goBoard[0][15] = TileSet.TLCORNER;
        goBoard[15][15] = TileSet.TRCORNER;
        goBoard[15][0] = TileSet.BRCORNER;
    }

    public static void displaySetup() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(WIDTH - 10, HEIGHT - 10, "Player 1");
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH - 10, HEIGHT - 30, "Player 2");
        StdDraw.show();
    }

    public static void updateDisplay() {

    }


    /**
     * @description: 开始游戏，得到一个棋盘
     * @return: Tile[][]
     */
    public static Tile[][] playGame() {
        //初始化一个15*15的棋盘
        Tile[][] goBoard = new Tile[16][16];
        //设置棋盘的图像
        boardSetup(goBoard);
        return goBoard;
    }

}
