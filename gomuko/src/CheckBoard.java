/**
 * @description: 棋盘检查类
 */
public class CheckBoard {

    /**
     * @description: 判断当前棋势是否赢了
     * @param Player 当前落子的位置
     * @param board 当前的棋盘
     * @return: boolean
     */
    public static boolean winCheck(Tile Player, Tile[][] board) {
        //只有三种情况可能赢：横着、竖着或斜着刚好连成五个子
        if (horizontalCheck(Player, board) || verticalCheck(Player, board) || diagonalCheck(Player, board)) {
            return true;
        }
        return false;
    }

    /**
     * @description: 棋盘的横向检查 但是是看棋子的竖着是否成五个
     * @param Player 当前落子的位置
     * @param board 当前的棋盘
     * @return: boolean
     */
    public static boolean horizontalCheck(Tile Player, Tile[][] board) {
        //横向遍历
        for (int i = 0; i < board.length - 5; i += 1) {
            for (int j = 0; j < board[0].length; j += 1) {
                if (board[i][j] == Player) {
                    //五子相连
                    if (board[i + 1][j] == Player && board[i + 2][j] == Player
                    && board[i + 3][j] == Player && board[i + 4][j] == Player) {
                        try {
                            //当连着的棋子大于5个时，是不算胜利的
                            if (board[i - 1][j] == Player || board[i + 5][j] == Player) {
                                return false;
                            }
                        } catch (IndexOutOfBoundsException e) { //可能出现数组越界异常
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @description: 棋盘的垂直检查 但是是看棋子的横着是否成五个
     * @param Player
     * @param board
     * @return: boolean
     */
    public static boolean verticalCheck(Tile Player, Tile[][] board) {
        for (int j = 0; j < board.length - 5; j += 1) {
            for (int i = 0; i < board[0].length; i += 1) {
                if (board[i][j] == Player) {
                    if (board[i][j + 1] == Player && board[i][j + 2] == Player
                            && board[i][j + 3] == Player && board[i][j + 4] == Player) {
                        try {
                            if (board[i][j - 1] == Player || board[i][j + 5] == Player) {
                                return false;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @description: 棋盘的对角线检查
     * @param Player
     * @param board
     * @return: boolean
     */
    public static boolean diagonalCheck(Tile Player, Tile[][] board) {
        //pos orientation
        for (int i = 0; i < board.length - 4; i += 1) {
            for (int j = 0; j < board[0].length - 4; j += 1) {
                if (board[i][j] == Player) {
                    //五子相连
                    if (board[i + 1][j + 1] == Player && board[i + 2][j + 2] == Player
                            && board[i + 3][j + 3] == Player && board[i + 4][j + 4] == Player) {
                        try {
                            //当连着的棋子大于5个时，是不算胜利的
                            if (board[i - 1][j - 1] == Player || board[i + 5][j + 5] == Player) {
                                return false;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }/*
                        try{
                            if(board[i + 1][j + 1] == Player && board[i + 2][j + 2] == Player
                            && board[i + 3][j + 3]==Player&& board[i + 1][j + 1] == Player && board[i + 2][j + 2] == Player
                            && board[i + 3][j + 3]==Player){
                            return false;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            三三禁手
                        }*/
                        return true;
                    }
                }
            }
        }
        //neg orientation
        for (int i = 0; i < board.length - 4; i += 1) {
            for (int j = 4; j < board.length; j += 1) {
                if (board[i][j] == Player) {
                    if (board[i + 1][j - 1] == Player && board[i + 2][j - 2] == Player
                            && board[i + 3][j - 3] == Player && board[i + 4][j - 4] == Player) {
                        try { //check if more than 5
                            if (board[i - 1][j + 1] == Player || board[i + 5][j - 5] == Player) {
                                return false;
                            }
                        } catch (IndexOutOfBoundsException e) { //可能出现数组越界异常
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
