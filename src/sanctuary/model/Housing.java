package sanctuary.model;

import java.util.List;

/**
 * This is the interface for 2 types of housings: isolation cage and enclosure
 * The interface includes the methods that are applied in both classes
 */
public interface Housing {
  /**
   * Add a primate to the class
   * @param primate, the primate to be added
   */
  void addPrimate(Primate primate);

  /**
   * Remove a primate from the class
   * @param name, the name of the primate to be removed
   */
  void removePrimate(String name);

  /**
   * @return the list of primate in the class
   * For isolation cage it should be a single item list or null;
   * For enclosure it the list could be single item, multiple items or null;
   */
  List<Primate> getPrimate();
}
