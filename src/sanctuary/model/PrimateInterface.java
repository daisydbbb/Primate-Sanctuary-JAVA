package sanctuary.model;

/**
 * This is the interface for the Primate class, include the methods for the Primate
 * Name: XIN DING
 * Date: 4/8/2024
 */
public interface PrimateInterface extends Comparable<Primate>{
  /**
   * @return the name of the primate
   */
  String getName();

  /**
   * check if the primate is healthy or not
   * @return true if the primate is healthy, return false if the primate is sick
   */
  boolean checkHealth();

  /**
   * @return the type of the primate
   */
  PrimateType getType();

  /**
   * Change the health condition of the primate
   * If currently healthy, change it to sick; if currently sick, change it to healthy
   */
  void changeHealth();
}
