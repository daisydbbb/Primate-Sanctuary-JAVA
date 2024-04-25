package sanctuary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the IsolationCage class, it implements the Housing interface
 * IsolationCage is used when the primate is first arrived, or when they get sick and need medical attention
 * Individual IsolationCage is used in the higher sanctuary class as part of the isolation cages
 * Name: XIN DING
 * Date: 4/8/2024
 */
public class IsolationCage implements Housing {
  private Primate cage;

  /**
   * Add a primate to the empty cage
   * @param primate, the primate to be added
   */
  @Override
  public void addPrimate(Primate primate) {
    if (cage == null) {
      cage = primate;
    }
  }

  /**
   * Remove the primate with the input name from the cage
   * @param name, the name of the primate to be removed
   */
  @Override
  public void removePrimate(String name){
    if (cage != null && cage.getName().equals(name)) {
      cage = null;
    }
  }

  /**
   *
   * @return true if the cage is empty, return false if the cage has primate inside
   */
  public boolean isEmpty() {
    return cage == null;
  }


  /**
   * @return the list of primate in the current cage
   * If the cage is empty, it the list is empty; else, the list should include only one primate
   */
  @Override
  public List<Primate> getPrimate() {
    List<Primate> primateList= new ArrayList<>();
    primateList.add(this.cage);
    return primateList;
  }

  /**
   * @return current cage
   */
  public Primate getCage() {
    return cage;
  }

  /**
   * @return the formatted string that include the primate information and its health condition
   * e.g. [Name: Tara, Sex: FEMALE, Favorite Food: FRUITS]-Healthy
   */
  @Override
  public String toString() {
    if (cage==null) {
      return "[]";
    }
    return getCage().toString() + (cage.checkHealth()? "-Healthy" : "-Sick");
  }

}
