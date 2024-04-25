package sanctuary.controller;
import java.util.List;
import java.util.Map;

import sanctuary.model.Primate;
import sanctuary.model.Sanctuary;
import sanctuary.model.Sex;
import sanctuary.model.PrimateType;
import sanctuary.model.FavFood;
import sanctuary.view.SanctuaryGUI;

/**
 * This is the Controller part of the project
 * Controller communicate with both View and Model
 * Name: XIN DING
 * Date: 4/12/2024
 */
public class SanctuaryController {
  private Sanctuary sanctuary;
  private SanctuaryGUI view;

  /**
   * Construction for SanctuaryController
   * @param sanctuary, the model of the project
   * @param view, the view of the project
   */
  public SanctuaryController(Sanctuary sanctuary, SanctuaryGUI view) {
    this.sanctuary = sanctuary;
    this.view = view;
    initialize();
  }

  /**
   * Add callback functions to the ActionListener of each button defined in view
   */
  public void initialize() {
    view.addRegisterButtonListener(e -> register());
    view.addSickButtonListener(e -> sick());
    view.addTreatButtonListener(e -> treat());
    view.addMoveButtonListener(e -> moveToEnclosure());
    view.addMoveSickButtonListener(e -> moveSickToIsolation());
  }

  /**
   * Register a new primate to the sanctuary, add error handling
   */
  public void register() {
    try {
      String name = view.getInputName();
      if (name.isEmpty()) {
        view.showError("Name cannot be empty!");
        return;
      }

      double size = view.getInputSize();
      if (size <= 0) {
        view.showError("Size must be positive!");
        return;
      }

      double weight = view.getInputWeight();
      if (weight <= 0) {
        view.showError("Weight must be positive!");
        return;
      }

      int age = view.getInputAge();
      if (age < 0) {
        view.showError("Age cannot be negative!");
        return;
      }

      PrimateType type = view.getInputType();
      Sex sex = view.getInputSex();
      FavFood food = view.getInputFavFood();

      if (view.getIsoDropDown().contains(name)) {
        view.showError("Duplicate Name!");
        return;
      }
      sanctuary.addNewPrimate(new Primate(name, type, sex, size, weight, age, food));
      view.showMessage("Primate registered successfully.");
      view.clearInputFields();
      view.addIsoDropDown(name);
      view.addAllDropDown(name);
      showIsolation();
      showAll();
    } catch (NumberFormatException e) {
      view.showError("Invalid numerical input! Please check your entries.");
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
    } catch (Exception e) {
      view.showError("An unexpected error occurred: " + e.getMessage());
    }
  }

  /**
   * Get isolation data from model and pass it to view for display isolation cages
   */
  public void showIsolation() {
    List<String> isolationData = sanctuary.isolationCageList();
    view.displayIsolations(isolationData);
  }

  /**
   * Get enclosure data from model and pass it to view for displaying enclosures
   */
  public void showEnclosures() {
    Map<String, List<String>> enclosureData = sanctuary.EnclsouresMap();
    view.displayEnclosures(enclosureData);
  }

  /**
   * Get all primate data from model and pass it to view for displaying all primates
   */
  public void showAll() {
    List<String> allPrimatesData = sanctuary.sanctuaryList();
    view.displayAll(allPrimatesData);
  }

  /**
   * Action when the user click the sick button
   * Change the health status of the selected primate from healthy to sick
   */
  public void sick() {
    view.showMessage("");
    String sickName = view.getSelectedAllDropdownItem();
    Primate sickPrimate = sanctuary.findPrimateByName(sickName);
    if (sickPrimate != null && sickPrimate.checkHealth()) {
      sickPrimate.changeHealth();
    }
    showIsolation();
    showAll();
  }

  /**
   * Action when the user click the treat button
   * Change the health status of the selected primate from sick to healthy
   */
  public void treat() {
    view.showMessage("");
    String treatName = view.getSelectedIsoDropdownItem();
    Primate treatPrimate = sanctuary.findPrimateByName(treatName);
    if (treatPrimate.checkHealth()) {
      view.showError(treatName + " is healthy, no need treatment 😄");
      return;
    }
    treatPrimate.changeHealth();
    showIsolation();
    showAll();

  }

  /**
   * Action when the user click the move to enclosure button
   * Move the selected primate from isolation to enclosure, add error handling
   */
  public void moveToEnclosure() {
    view.showMessage("");
    String nameMoveEnclosure = view.getSelectedIsoDropdownItem();
    try {
      sanctuary.movePrimateToEnclosure(nameMoveEnclosure);
      showIsolation();
      view.delIsoDropDown(nameMoveEnclosure);
      showEnclosures();
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
    }
  }

  /**
   * Action when the user click the move to isolation button
   * Move the selected primate from enclosure to isolation, add error handling
   */
  public void moveSickToIsolation() {
    view.showMessage("");
    String nameMoveIsolation = view.getSelectedAllDropdownItem();
    try {
      sanctuary.moveSingleSickToIsolation(nameMoveIsolation);
      view.addIsoDropDown(nameMoveIsolation);
      showIsolation();
      showAll();
      showEnclosures();
    } catch (Sanctuary.PrimateAlreadyInIsolationException e) {
      view.showError(e.getMessage());
    } catch (IllegalStateException e) {
      view.showError(e.getMessage());
    }

  }
}
