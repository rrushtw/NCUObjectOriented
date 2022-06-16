package Models;

public class CloseButton extends BaseButton {
    public CloseButton(Position position){
        super(position, 10, 10);
    }

    public CloseButton(Position position, int width, int height){
        super(position, width, height);
    }

    public void Draw() {
        // draw button
    }

    public void Activate(){
        //close window
    }
}
