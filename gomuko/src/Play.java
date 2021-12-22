import static java.lang.StrictMath.floor;

/**
 * @description: 游戏入口
 */
public class Play {
    //玩家一
    private static int[] player1;
    //玩家二
    private static int[] player2;
    //记录玩家1落子的历史记录
    private static History p1History;
    //记录玩家2落子的历史记录
    private static History p2History;
    //当前执期方，默认为0，黑棋
    private static int currentPlayer = 0;
    //当前局是否赢了，默认为否
    private static boolean winCondition = false;
    //游戏棋盘
    private static Tile[][] board;
    //拷贝的游戏棋盘
    private static Tile[][] copy;
    //拷贝的游戏棋盘
    private static Tile[][] base;
    //是否退出
    private static boolean quit;
    //棋盘渲染器
    static TileRenderer renderer;
    //游戏画面类
    static GameScreen game;

    /**
     * @description: 下棋方的切换
     * @return: void
     */
    private static void changePlayers() {
        //0为黑棋，1为白棋
        currentPlayer = (currentPlayer + 1) % 2;
    }

    /**
     * @description: 判断当前的执棋方
     * @param placeholder 标志是正在下的棋子还是已经下好了的棋子(因为不同棋子图像不一样)
     * @return: Tile
     */
    private static Tile getPlayerColor(boolean placeholder) {
        //得到黑子
        if (currentPlayer == 0) {
            if (placeholder) { //正在下的棋子
                return TileSet.BLACKMOVE;
            }
            return TileSet.BLACK; //已经下好的棋子
        } else {
            if (placeholder) {
                return TileSet.WHITEMOVE;
            }
            return TileSet.WHITE;
        }
    }

    /**
     * @description: 得到当前下棋的玩家
     * @return: int[]
     */
    private static int[] getCurrentPlayer() {
        //currentPlayer为0代表为当前下棋玩家为玩家一
        if (currentPlayer == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    /**
     * @description: 判断要落子的位置是否已被占用
     * @param player 落子的位置
     * @return: boolean
     */
    private static boolean notOccupied(int[] player) {
        //若player为TileSet.WHITE或者TileSet.BLACK则表示已经被占用
        if (copy[player[0]][player[1]] != TileSet.WHITE && copy[player[0]][player[1]] != TileSet.BLACK) {
            return true;
        }
        return false;
    }

    /**
     * @description: 更新下的棋的历史记录
     * @return: void
     */
    private static void resetPlayerPosition() {
        //黑棋
        if (currentPlayer % 2 == 0) {
            //将当前下的位置加入到历史记录中
            p1History.addMove(player1);
            //初始化下次要下棋的位置
            player1 = new int[] {8, 8};
        } else { //白棋
            p2History.addMove(player2);
            player2 = new int[] {8, 8};
        }
    }


    /**
     * @description: 走一步棋的细节实现
     * @param input 按下的键
     * @return: void
     */
    public static void playGame(InputSource input) {
        //当前玩家即将要下的棋子在棋盘的(8,8)显示
        board[8][8] = getPlayerColor(true);
        int[] player;
        loop : while (true) {
            //当前执棋方为黑棋
            if (currentPlayer % 2 == 0) {
                player = new int[] {player1[0], player1[1]};
            } else { //当前执棋方为白棋
                player = new int[] {player2[0], player2[1]};
            }
            //监测是否有在用鼠标点击下棋位置
            if (StdDraw.isMousePressed()) {
                int x = (int) floor(StdDraw.mouseX()) - player[0];
                int y = (int) floor(StdDraw.mouseY()) - player[1];
                //将棋下到对应位置
                move(x, y);
            }
            //获取按下的按键
            char move = input.getNextKey();
            switch (move) {
                //向上走
                case 'W' :
                    move(0, 1);
                    break;
                //向左走
                case 'A' :
                    move(-1, 0);
                    break;
                //向下走
                case 'S' :
                    move(0, -1);
                    break;
                //向右走
                case 'D' :
                    move(1, 0);
                    break;
                //悔棋
                case 'U' :
                    int[] lastMove;
                    //当前执期方为黑棋说明悔棋的时白棋
                    if (currentPlayer % 2 == 0) {
                        //得到被悔的那一步棋
                        lastMove = p2History.goBack();
                    } else {
                        lastMove = p1History.goBack();
                    }
                    //如果被悔的那一步棋存在
                    if (lastMove != null) {
                        //这里采用了双缓冲
                        copy[lastMove[0]][lastMove[1]] = base[lastMove[0]][lastMove[1]];
                        board[lastMove[0]][lastMove[1]] = copy[lastMove[0]][lastMove[1]]; // resetboard
                        board[player[0]][player[1]] = base[player[0]][player[1]];
                        //
                        player1 = new int[] {8, 8};
                        player2 = new int[] {8, 8};
                        break loop;
                    }
                    break;
                    //退出
                case 'Q':
                    System.exit(1);
                case 'B':
                    quit = true;
                    break loop;
                //确定落子
                case 'J':
                    //要落子的位置未被占用
                    if (notOccupied(player)) {
                        copy[player[0]][player[1]] = getPlayerColor(false);
                        board[player[0]][player[1]] = copy[player[0]][player[1]];
                        //更新下的棋的历史记录
                        resetPlayerPosition();
                        break loop;
                    }
                    System.out.println("Invalid Move");
                    break;
            }
            //渲染棋盘
            renderer.renderFrame(board);
        }
        //渲染棋盘
        renderer.renderFrame(board);
    }

    /**
     * @description: 将棋下到指定位置
     * @param xMove 指定位置的横坐标
     * @param yMove 指定位置的纵坐标
     * @return: void
     */
    private static void move(int xMove, int yMove) {
        //得到当前下棋的玩家
        int[] player = getCurrentPlayer();
        //得到当前棋子的横纵坐标
        int x = player[0];
        int y = player[1];
        try {
            if (xMove != 0 || yMove != 0) {
                //将当前下棋方设置到移动后的位置
                board[x + xMove][y + yMove] = getPlayerColor(true);
                board[x][y] = copy[x][y];
                //黑棋
                if (currentPlayer % 2 == 0) {
                    //记录移动后的位置
                    player1 = new int[] {x + xMove, y + yMove};
                } else {
                    player2 = new int[] {x + xMove, y + yMove};
                }
            }
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Invalid Move");
        }
    }

    /**
     * @description:  设置主菜单画面
     * @param input 按下的按键
     * @return: void
     */
    public static void setScreens(InputSource input) {
        //创建画面对象
        game = new GameScreen();
        //画面的初始化
        game.initialize();
        //画面的开始显示
        game.startScreen();
        //不断循环检测玩家是否有键盘输入
        loop : while (input.possibleNextInput()) {
            //得到玩家键盘输入的按键
            char start = input.getNextKey();
            switch (start) {
                //开始游戏，继续检测用户输入
                case 'P' :
                    break loop;
                //游戏介绍
                case 'C':
                    game.creditScreen();
                    break;
                //玩法介绍
                case 'R':
                    game.helpScreen();
                    break;
                //回到游戏主菜单
                case 'B':
                    game.startScreen();
                    break;
                //退出
                case 'Q':
                    System.exit(0);
            }
        }
    }

    /**
     * @description: 设置游戏画面并且设置初始属性
     * @return: void
     */
    private static void resetGame() {
        //初始化两位玩家
        player1 = new int[] {8, 8};
        player2 = new int[] {8, 8};
        currentPlayer = 0; //set first player to black
        winCondition = false;
        //创建棋盘
        board = GameSetup.playGame();
        //将创建的棋盘拷贝一份，实现双缓冲
        copy = Tile.copy(board);
        base = Tile.copy(board);
        //棋盘渲染器的创建
        renderer = new TileRenderer();
        //棋盘渲染器的初始化
        renderer.initialize(16, 16);
        //棋盘选软
        renderer.renderFrame(board);
        //true为黑子
        p1History = new History(true);
        //false为白子
        p2History = new History(false);
        //将玩家1的落子记录加入到历史记录中
        p1History.addMove(player1);
        //将玩家2的落子记录加入到历史记录中
        p2History.addMove(player2);
        //默认不退出游戏
        quit = false;
    }

    /**
     * @description: 下一局棋的细节
     * @param input 按下的按键
     * @return: void
     */
    public static void play(InputSource input) {
        //设置主菜单画面
        setScreens(input);
        //设置游戏画面并且设置初始属性
        resetGame();
        //当没有分出胜负且没人退出游戏时
        while (!winCondition && !quit) {
            //开始下棋
            playGame(input);
            //判断当前棋势是否胜负已分
            winCondition = CheckBoard.winCheck(getPlayerColor(false), copy);
            //胜负已分
            if (winCondition) {
                System.out.println("Game Over");
                //画面初始化
                game.initialize();
                //显示赢了的画面
                game.winnerScreen(getPlayerColor(false));
            } else { //胜负未分
                //另一方继续下棋
                changePlayers();
            }
        }
    }


    public static void main(String[] args) {
        //按下的按键
        InputSource input = new KeyboardInputSource(); // I wanted to mess around with entering Strings, so I had the inputsource set here.
        //下一局棋
        play(input);
        //时刻监测键盘是否有按键按下
        while (input.possibleNextInput()) {
            //获取到按下的按键
            char replay = input.getNextKey();
            //这里quit一直是false
            if (quit) {
                play(input);
            }
            switch (replay) {
                //退出游戏
                case 'Q' :
                    System.exit(0);
                //再来一局
                case 'N':
                    play(input);
            }
        }
    }
}
