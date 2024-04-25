package sanctuary.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the Enclosure class, it implements the Housing interface
 * Enclosure represents a troop of primates with the same type, primates are moved to enclosure from isolation cages
 * Individual Enclosure is used in the higher sanctuary class as part of the enclosures
 * Name: XIN DING
 * Date: 4/8/2024
 */
public class Enclosure implements Housing {
  private final PrimateType type;
  private List<Primate> troop;

  /**
   * This is the constructor of the Enclosure class
   * @param type, the type of the troop.
   * The troop is initialized as an empty list
   */
  public Enclosure(PrimateType type) {
    this.type = type;
    this.troop = new ArrayList<>();
  }

  /**
   * Add a primate to the troop
   * @param primate, the primate to be added
   * After addition, the troop is sorted so the primates are in name order
   */
  @Override
  public void addPrimate(Primate primate) {
    if (primate != null && primate.getType() == this.type) {
      troop.add(primate);
      Collections.sort(troop);
    }
  }

  /**
   * Remove the primate with the input name from the enclosure
   * @param name, the name of the primate to be removed
   * After removal, the troop is sorted so the primates are in name order
   */
  @Override
  public void removePrimate(String name) {
    for (Primate primate : troop) {
      if (primate.getName().equals(name)) {
        troop.remove(primate);
        Collections.sort(troop);
        return;
      }
    }
  }


  /**
   * Remove all the primates in the troop that are unhealthy
   * @return the list of sick primates
   * If no primates in the troop is sick, the return should be an empty list
   */
  public List<Primate> removeAllSickPrimates() {
    List<Primate> sickList = troop.stream()
                                  .filter(primate -> !primate.checkHealth())
                                  .collect(Collectors.toList());
    troop.removeAll(sickList);
    return sickList;
  }


  /**
   * @return current troop (A list of primates)
   */
  @Override
  public List<Primate> getPrimate() {
    return troop;
  }

  /**
   * @return reformatted string of the class, which include the troop type and the primate list
   */
  @Override
  public String toString() {
    return type+": "+ troop.toString();
  }

  public PrimateType getEnclosureType() {
    return this.type;
  }
}
