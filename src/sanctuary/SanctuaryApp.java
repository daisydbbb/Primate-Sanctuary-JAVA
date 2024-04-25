package sanctuary;

import javax.swing.*;

import sanctuary.controller.SanctuaryController;
import sanctuary.model.Primate;
import sanctuary.model.Sanctuary;
import sanctuary.view.SanctuaryGUI;

/**
 * Main class of the project, run the program successfully
 * Name: XIN DING
 * Date: 4/12/2024
 */
public class SanctuaryApp {
  public static void main(String[] args) {
    Sanctuary model = new Sanctuary();
    SanctuaryGUI view = new SanctuaryGUI();
    SanctuaryController sanctuaryController = new SanctuaryController(model, view);

    SwingUtilities.invokeLater(() -> view.setVisible(true));
  }
}
