package sanctuary.view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import sanctuary.model.PrimateType;

public interface ViewInterface {
  void clearInputFields();
  void displayIsolations(List<String> isolationData);
  void displayEnclosures(Map<String, List<String>> enclosureData);
  void displayAll(List<String> allPrimateData);
}
