/**
 * @description: 键盘输入接口
 */
public interface InputSource {
    /**
     * @description: 得到键盘按入的键
     * @return: char
     */
    public char getNextKey();
    /**
     * @description: 检测键盘是否有按下
     * @return: boolean
     */
    public boolean possibleNextInput();
}
