package sanctuary.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.*;

import sanctuary.model.FavFood;
import sanctuary.model.PrimateType;
import sanctuary.model.Sex;

/**
 * This is View part of the project, GUI was built using JAVA Swing
 * View only directly communicate with Controller, not Model
 * GUI is constructed with the help chatGPT-4.0
 * Name: XIN DING
 * Date: 4/12/2024
 */
public class SanctuaryGUI extends JFrame implements ViewInterface {
  private JTextField nameTextField, sizeTextField, weightTextField, ageTextField;
  private JComboBox<PrimateType> typeComboBox;
  private JComboBox<Sex> sexComboBox;
  private JComboBox<FavFood> favoriteFoodComboBox;
  private JButton registerButton, exitButton;
  private JButton treatButton, moveButton;  // buttons for isolations section
  private JButton sickButton, moveSickButton;  //buttons for all primates section
  private JLabel messageLabel;
  private JList<String> isolationList, allPrimatesList;
  private DefaultListModel<String> isolationListModel; // List Model for the isolation
  private DefaultListModel<String> allPrimatesListModel; // List Model for the allPrimates
  private Map<String, JList<String>> enclosureLists;

  private JComboBox<String> isoDropdown;
  private JComboBox<String> allDropdown;

  /**
   * Constructor for SanctuaryGUI
   */
  public SanctuaryGUI() {
    setUp();
    setTitle("Primate Sanctuary Management");
    setSize(600, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * Initial set up for the view page, use BoxLayout
   * Include input section, data display section and buttons
   */
  private void setUp() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

    formPanel.add(new JLabel("Name:"));
    nameTextField = new JTextField();
    formPanel.add(nameTextField);

    formPanel.add(new JLabel("Type:"));
    typeComboBox = new JComboBox<>(PrimateType.values());
    formPanel.add(typeComboBox);

    formPanel.add(new JLabel("Sex:"));
    sexComboBox = new JComboBox<>(Sex.values());
    formPanel.add(sexComboBox);

    formPanel.add(new JLabel("Size:"));
    sizeTextField = new JTextField();
    formPanel.add(sizeTextField);

    formPanel.add(new JLabel("Weight:"));
    weightTextField = new JTextField();
    formPanel.add(weightTextField);

    formPanel.add(new JLabel("Age:"));
    ageTextField = new JTextField();
    formPanel.add(ageTextField);

    formPanel.add(new JLabel("Favorite Food:"));
    favoriteFoodComboBox = new JComboBox<>(FavFood.values());
    formPanel.add(favoriteFoodComboBox);

    registerButton = new JButton("Register");
    formPanel.add(registerButton);

    exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    formPanel.add(exitButton);

    messageLabel = new JLabel(" ");
    formPanel.add(messageLabel);
    mainPanel.add(formPanel);

    // Isolations section
    isolationListModel = new DefaultListModel<>();
    isolationList = new JList<>(isolationListModel);
    JScrollPane isolationScrollPane = new JScrollPane(isolationList);

    mainPanel.add(new JLabel("Isolation Cages:"));
    mainPanel.add(isolationScrollPane, BorderLayout.CENTER);

    JPanel buttonDropdownPanel1 = new JPanel();
    buttonDropdownPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
    // isoDropdown is update when primate is added to or removed from the isolation
    isoDropdown = new JComboBox<>();
    buttonDropdownPanel1.add(isoDropdown);

    treatButton = new JButton("Treat");
    buttonDropdownPanel1.add(treatButton);
    moveButton = new JButton("Move to Enclosure");
    buttonDropdownPanel1.add(moveButton);
    mainPanel.add(buttonDropdownPanel1);

    // All primate section
    allPrimatesListModel = new DefaultListModel<>();
    allPrimatesList = new JList<>(allPrimatesListModel);
    JScrollPane allPrimatesScrollPane = new JScrollPane(allPrimatesList);
    JPanel buttonDropdownPanel2 = new JPanel();
    buttonDropdownPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));

    mainPanel.add(new JLabel("All Primates:"));
    mainPanel.add(allPrimatesScrollPane, BorderLayout.CENTER);

    allDropdown = new JComboBox<>();
    buttonDropdownPanel2.add(allDropdown);

    sickButton = new JButton("Sick");
    buttonDropdownPanel2.add(sickButton);
    moveSickButton = new JButton("Move to Isolations");
    buttonDropdownPanel2.add(moveSickButton);
    mainPanel.add(buttonDropdownPanel2);

    // Individual Enclosure section
    enclosureLists = new HashMap<>();
    for (PrimateType type : PrimateType.values()) {
      JList<String> list = new JList<>();
      enclosureLists.put(type.toString(), list);
      mainPanel.add(new JLabel(type.name() + " Enclosure:"));
      mainPanel.add(new JScrollPane(list));
    }

    JScrollPane scrollPane = new JScrollPane(mainPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Add ActionListener to registerButton, called in Controller
   * @param listener, ActionListener to be added to the registerButton
   */
  public void addRegisterButtonListener(ActionListener listener) {
    registerButton.addActionListener(listener);
  }
  /**
   * Add ActionListener to sickButton, called in Controller
   * @param listener, ActionListener to be added to the sickButton
   */
  public void addSickButtonListener(ActionListener listener) {
    sickButton.addActionListener(listener);
  }
  /**
   * Add ActionListener to treatButton, called in Controller
   * @param listener, ActionListener to be added to the treatButton
   */
  public void addTreatButtonListener(ActionListener listener) {
    treatButton.addActionListener(listener);
  }
  /**
   * Add ActionListener to moveButton, called in Controller
   * @param listener, ActionListener to be added to the moveButton
   */
  public void addMoveButtonListener(ActionListener listener) {
    moveButton.addActionListener(listener);
  }
  /**
   * Add ActionListener to moveSickButton, called in Controller
   * @param listener, ActionListener to be added to the moveSickButton
   */
  public void addMoveSickButtonListener(ActionListener listener) {
    moveSickButton.addActionListener(listener);
  }


  /**
   * @return the input String for the name of the new primate
   */
  public String getInputName() {
    return nameTextField.getText();
  }

  /**
   * @return the selected primate type for the new primate
   */
  public PrimateType getInputType() {
    return (PrimateType) typeComboBox.getSelectedItem();
  }

  /**
   * @return the selected sex for the new primate
   */
  public Sex getInputSex() {
    return (Sex) sexComboBox.getSelectedItem();
  }

  /**
   * @return the input double number for the size of the new primate
   */
  public double getInputSize() {
    return Double.parseDouble(sizeTextField.getText());
  }

  /**
   * @return the input double number for the weight of the new primate
   */
  public double getInputWeight() {
    return Double.parseDouble(weightTextField.getText());
  }

  /**
   * @return the input integer number for the age of the new primate
   */
  public int getInputAge() {
    return Integer.parseInt(ageTextField.getText());
  }

  /**
   * @return the selected favorite food for the new primate
   */
  public FavFood getInputFavFood() {
    return (FavFood) favoriteFoodComboBox.getSelectedItem();
  }

  /**
   * Show message to the user
   * @param message, the message to be displayed on the panel
   */
   public void showMessage(String message) {
    messageLabel.setText(message);
  }

  /**
   * Show error message to the user
   * @param errorMessage, the error message to me displayed
   */
  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Clean up the current input data
   */
  public void clearInputFields() {
    nameTextField.setText("");
    typeComboBox.setSelectedIndex(0);
    sexComboBox.setSelectedIndex(0);
    sizeTextField.setText("");
    weightTextField.setText("");
    ageTextField.setText("");
    favoriteFoodComboBox.setSelectedIndex(0);
  }

  /**
   * Display data on the isolation section, aka show the primates in the isolation cages
   * @param isolationData, list of string received from controller, data to be displayed
   */
  public void displayIsolations(List<String> isolationData) {
    isolationListModel.clear();
    for (String data : isolationData) {
      isolationListModel.addElement(data);
    }
    isolationList.setModel(isolationListModel);
  }

  /**
   * Get the name of the primates in the dropdown, just the name, used for checking duplicates
   * @return the list of name(String) of primates that currently in isolation cages
   */
  public List<String> getIsoDropDown() {
    List<String> items = new ArrayList<>();
    for (int i = 0; i < isoDropdown.getItemCount(); i++) {
      items.add(isoDropdown.getItemAt(i));
    }
    return items;
  }

  /**
   * Add a new name to the isolation dropdown
   * @param name, the name of the primate to be added to the dropdown nav bar
   */
  public void addIsoDropDown(String name) {
    isoDropdown.addItem(name);
  }

  /**
   * Remove the target name form the isolation dropdown
   * @param name, the name to be deleted from the dropdown nav bar
   */
  public void delIsoDropDown(String name) {
    isoDropdown.removeItem(name);
  }

  /**
   * Display the primates in all enclosures, each type section shows primates that are
   * currently inside that enclosure
   * @param enclosureData, data for all the enclosures, received from the controller
   */
  public void displayEnclosures(Map<String, List<String>> enclosureData) {
    for (Map.Entry<String, List<String>> entry : enclosureData.entrySet()) {
      String type = entry.getKey();
      List<String> primates = entry.getValue();

      DefaultListModel<String> model = new DefaultListModel<>();
      for (String primate : primates) {
        model.addElement(primate);
      }

      JList<String> list = enclosureLists.get(type);
      if (list != null) {
        list.setModel(model);
      }
    }
  }

  /**
   * Add a new name to the all primate dropdown
   * @param name, the name to be added to the dropdown nav bar
   */
  public void addAllDropDown(String name) {
    allDropdown.addItem(name);
  }

  /**
   * Display all primates that are registered in the sanctuary
   * In all primate section, show the summarized list of primates in both isolation and enclosures
   * @param allPrimateData, List of primates to be displayed, received from controller
   */
  public void displayAll(List<String> allPrimateData) {
    allPrimatesListModel.clear();
    for (String data : allPrimateData) {
      allPrimatesListModel.addElement(data);
    }
    allPrimatesList.setModel(allPrimatesListModel);
  }

  /**
   * @return the name of the primate selected in isolation dropdown
   */
  public String getSelectedIsoDropdownItem() {
    Object selectedItem = isoDropdown.getSelectedItem();
    return (String) selectedItem;
  }

  /**
   * @return the name of the primate selected in all primate dropdown
   */
  public String getSelectedAllDropdownItem() {
    Object selectedItem = allDropdown.getSelectedItem();
    return (selectedItem != null) ? selectedItem.toString() : null;
  }


}
