package Models;

public abstract class BaseBar {
    private Position Position;
    private int Width;

    public BaseBar(Position position, int width) {
        Width = width;
    }

    public Position GetPosition() {
        return Position;
    }

    public int GetWidth() {
        return Width;
    }

    public void Draw() {
        // draw button
    }

    public void Scroll() {

    }
}
