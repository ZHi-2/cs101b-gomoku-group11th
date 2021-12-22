import java.awt.*;

/**
 * @description: 游戏画面类
 */
public class GameScreen {
    //宽度
    private static int width = 30;
    //高度
    private static int height = 30;
    //宽的中间位置
    private static int midWidth = width / 2;
    //高的中间位置
    private static int midHeight = height / 2;

    public GameScreen() { }

    /**
     * @description: 画面的初始化
     * @return: void
     */
    public void initialize() {
        //设置画布的宽和高
        StdDraw.setCanvasSize(width * 18, height * 18);
        //创建一个Font对象，设置字体格式，加粗，字体大小
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        //设置StdDraw的字体
        StdDraw.setFont(font);
        //将画布的x坐标轴设置为0-width
        StdDraw.setXscale(0, width);
        //将画布的y坐标轴设置为0-height
        StdDraw.setYscale(0, height);
        //清空画布并用颜色BLACK对其进行填充
        StdDraw.clear(Color.BLACK);
        //设置为双缓冲
        StdDraw.enableDoubleBuffering();
        //设置画笔的颜色
        StdDraw.setPenColor(Color.BLACK);
    }

    /**
     * @description: 画面的开始显示
     * @return: void
     */
    public void startScreen() {
        //清空画布并用颜色WHITE对其进行填充
        StdDraw.picture(midHeight,midWidth,"/boardimages/start.png");
        //在对应的位置上显示提示词
        StdDraw.text(midWidth, height - 2, "Gomoku");
        StdDraw.text(midWidth, midHeight + 2, "Play (P)");
        StdDraw.text(midWidth, midHeight, "Rules (R)");
        StdDraw.text(midWidth, midHeight - 2, "Credits (C)");
        //显示所有图像
        StdDraw.show();
    }

    /**
     * @description: 玩法介绍
     * @return: void
     */
    public void helpScreen() {
        //清空画布并用颜色WHITE对其进行填充
        StdDraw.clear(Color.WHITE);
        //在对应的位置上显示提示词
        StdDraw.text(midWidth, height - 4, "Used WASD or mouselicks");
        StdDraw.text(midWidth, height - 6, "to move");
        StdDraw.text(midWidth, height - 8, "Press J to confirm the move");
        StdDraw.text(midWidth, height - 10, "When five pieces are joined together,");
        StdDraw.text(midWidth, height - 12, " you win.");
        StdDraw.text(midWidth, height - 14, "Press B to go back to the menu");
        StdDraw.text(midWidth, midHeight - 2, " Press Q to quit");

        StdDraw.text(midWidth, 4, "Good Luck Guys!");
        //显示所有图像
        StdDraw.show();
    }

    /**
     * @description: 游戏介绍
     * @return: void
     */
    public void creditScreen() {
        //清空画布并用颜色WHITE对其进行填充
        StdDraw.clear(Color.WHITE);
        //在对应的位置上显示提示词
        StdDraw.text(midWidth, height - 4, "Made by cs102b fall 2021 ");
        StdDraw.text(midWidth, height - 6, "ruizhi deng");
        StdDraw.text(midWidth, height - 8, "shuwen zhang");
        StdDraw.text(midWidth, height - 10, "yanlan ye");
        StdDraw.text(midWidth, height - 12, " ");
        StdDraw.text(midWidth, height - 14, "");
        //显示所有图像
        StdDraw.show();
    }

    public void optionScreen() {
        //choose whether 1 v 1 or 1 v computer (later)
    }

    /**
     * @description: 赢了时对应的画面
     * @param winner 赢的棋颜色
     * @return: void
     */
    public void winnerScreen(Tile winner) {
        //清空画布并用颜色WHITE对其进行填充
        StdDraw.clear(Color.WHITE);

        String win;
        //赢的棋子未黑色则为玩家1赢，否则为玩家2赢
        if (winner == TileSet.BLACK) {
            StdDraw.picture(midHeight,midWidth,"/boardimages/blackwin.png");
        } else {
            StdDraw.picture(midHeight,midWidth,"/boardimages/whitewin.png");
        }
        //显示对应的文字
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midWidth, midHeight - 4, "Press N to Replay");
        StdDraw.text(midWidth, midHeight - 6, "Press Q to Quit");
        StdDraw.show();
    }
}
