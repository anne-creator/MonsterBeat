import frontend.GuiTester;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GuiTester gui = new GuiTester();
        gui.initialiseFrame();
        try {
            gui.initialisePanels();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
