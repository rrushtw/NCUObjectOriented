package Shape;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Port extends Rectangle {
    private ArrayList<Line> lines = new ArrayList<Line>();

    public void SetPort(int center_x, int center_y, int offset) {
        int x = center_x - offset;
        int y = center_y - offset;
        int width = offset * 2;
        int height = offset * 2;
        setBounds(x, y, width, height);
    }

    public void AddLine(Line line) {
        lines.add(line);
    }

    public void RemoveLine(Line line) {
        lines.remove(line);
    }

    public void ResetLines() {
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            line.Relocate();
        }
    }
}
