package Models;

public abstract class BaseButton {
    private Position Position;
    private int Width;
    private int Height;

    public BaseButton(Position position, int width, int height) {
        Width = width;
        Height = height;
    }

    public Position GetPosition() {
        return Position;
    }

    public int GetWidth() {
        return Width;
    }

    public int GetHeight() {
        return Height;
    }

    public void Draw() {
        // draw button
    }

    public void Describe() {
        // show description on mouse hover
    }

    public void Activate() {

    }
}
