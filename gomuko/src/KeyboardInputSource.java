public class KeyboardInputSource implements InputSource {

    public KeyboardInputSource() {
    }

    //used for the main screen
    @Override
    public char getNextKey() {
        if (StdDraw.hasNextKeyTyped()) {
            char c = Character.toUpperCase(StdDraw.nextKeyTyped());
            return c;
        }
        char hi = ' ';
        return hi;
    }

    @Override
    public boolean possibleNextInput() {
        return true;
    }
}
