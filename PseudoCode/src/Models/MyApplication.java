package Models;

import java.util.ArrayList;

public class MyApplication {
    private ArrayList<BaseButton> Buttons = new ArrayList<BaseButton>();
    private ArrayList<BaseBar> Bars = new ArrayList<BaseBar>();

    public MyApplication() {
        Buttons.add(new CloseButton(new Position(0, 0)));
        Buttons.add(new MaximizeButton(new Position(0, 10)));
        Buttons.add(new MinimizeButton(new Position(0, 20)));
        Buttons.add(new HelpButton(new Position(0, 30)));

        for (BaseButton button : Buttons) {
            button.Draw();
        }

        Bars.add(new VerticalBar(new Position(0, 0)));
        Bars.add(new HorizontalBar(new Position(0, 0)));

        for (BaseBar bar : Bars) {
            bar.Draw();
        }
    }

    public void OnClicked(BaseButton button) {
        button.Activate();
    }

    public void OnScroll(BaseBar bar) {
        bar.Scroll();
    }
}
