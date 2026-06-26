package Gnava.Desktop.Interface.Elements;

import java.awt.*;

public class GnavaButton extends Button {
    public GnavaButton(String text) {
        super(text);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
