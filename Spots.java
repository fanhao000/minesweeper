public class Spots {
    private char data;

    public Spots() {
        data = 0;
    }

    public Spots(char info) {
        data = info;
    }

    public char setData(char info) {
        data = info;
        return data;
    }

    public char getData() {
        return data;
    }
}