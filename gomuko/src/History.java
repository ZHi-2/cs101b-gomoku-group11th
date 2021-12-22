/**
 * @description: 下过的棋的历史记录，使用双向链表实现
 */
public class History {
    //执棋者
    private boolean player;
    //当前下的棋
    private Move curr;
    //上一步下的棋
    private Move prev;
    //下一步要下的棋
    private Move next;
    private int size;

    public History(boolean player) {
        this.player = player;
    }

    /**
     * @description: 棋节点，记录每步棋
     */
    private class Move {
        //记录当前这步棋的位置
        int[] move;
        Move next;
        Move prev;

        private Move(int[] move, Move next, Move prev) {
            this.move = move;
            this.next = next;
            this.prev = prev;
        }

        private int[] getMove() {
            return move;
        }

        @Override
        public String toString() {
            String text = "";
            for (int i : move) {
                text += Integer.toString(i);
            }
            return text;
        }
    }

    /**
     * @description: 将下的一步棋添加到历史记录中
     * @param move 刚下的一步棋
     * @return: void
     */
    public void addMove(int[] move) {
        //如果当前下的棋为空，说明这是第一步棋，直接初始化
        if (curr == null) {
            curr = new Move(move, null, null);
        } else {  // 说明前面下过棋了
            //当前这步棋变为上一步棋
            prev = curr;
            //为刚下的这步棋创建对象
            curr = new Move(move, null, prev);
            next = null;
        }
    }

    /**
     * @description: 悔棋，根据历史记录回退一步
     * @return: int[]
     */
    public int[] goBack() {
        //如果存在下棋历史记录
        if (prev != null) {
            //根据历史记录回退一步
            Move placeholder = prev.prev;
            next = curr;
            curr = prev;
            prev = placeholder;
            //返回被悔的那一步棋
            return next.getMove();
        }
        return null;
    }

    public int[] goForward() {
        if (next != null) {
            Move placeholder = next.next;
            prev = curr;
            curr = next;
            next = placeholder;
            return prev.getMove();
        }
        return null;
    }

    public int[] getMove() {
        return curr.move;
    }

    public void printPlayerHistory() {

    }

/*    public static void main(String[] args) {
        History h = new History(false);
        h.addMove(new int[] {10, 10});
        h.addMove(new int[] {20, 20});
        System.out.println(h.curr);
        h.goBack();
        System.out.println(h.curr);
        h.addMove(new int[] {30, 30});
        System.out.println(h.curr);
        h.addMove(new int[] {40, 40});
        h.goForward();
        System.out.println(h.curr);
        System.out.println(h.prev);
    }*/
}
