package sanctuary.model;

/**
 * This is the Primate class, which implements the primate interface
 * Name: XIN DING
 * Date: 4/8/2024
 */
public class Primate implements PrimateInterface{
  private final String name;
  private final PrimateType type;
  private final Sex sex;

  private double size;
  private double weight;
  private int age;
  private final FavFood food;
  private boolean isHealthy;


  /**
   * This is the constructor of the Primate class
   * @param name, the name of the primate
   * @param type, the type of the primate, 1 of the 8 Primate Types
   * @param sex, the sex of the primate, either male or female, 1 of 2 Sex
   * @param size, the size of the primate
   * @param weight, the weight of the primate
   * @param age, the age of the primate
   * @param food, tha favorite food of the primate, 1 of the 7 FavFood
   */
  public Primate(String name, PrimateType type, Sex sex, double size, double weight,
                 int age, FavFood food) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name is invalid!");
    }
    if (type == null) {
      throw new IllegalArgumentException("The type is invalid!");
    }
    if (sex == null) {
      throw new IllegalArgumentException("The sex is invalid!");
    }
    if (food == null) {
      throw new IllegalArgumentException("The food is invalid!");
    }
    if (size <= 0) {
      throw new IllegalArgumentException("Size must be greater than 0!");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0!");
    }
    if (age <= 0) {
      throw new IllegalArgumentException("Age must be greater than 0!");
    }
    this.name = name;
    this.type = type;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.age = age;
    this.food = food;
    this.isHealthy = true;
  }

  /**
   * @return the name of the primate
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * check if the primate is healthy or not
   * @return true if the primate is healthy, return false if the primate is sick
   */
  @Override
  public boolean checkHealth() {
    return this.isHealthy;
  }

  /**
   * @return the type of the primate
   */
  @Override
  public PrimateType getType() {
    return this.type;
  }

  /**
   * Change the health condition of the primate
   * If currently healthy, change it to sick; if currently sick, change it to healthy
   */
  @Override
  public void changeHealth() {
    isHealthy = !this.isHealthy;
  }

  /**
   *
   * @return the formatted string of the primate. e.g. [Name: Tara, Sex: FEMALE, Favorite Food: FRUITS]
   */
  @Override
  public String toString() {
    return String.format("[Name: %s, Sex: %s, Favorite Food: %s]", name, sex, food);
  }

  /**
   * compare the name of current primate to the other primate in lexicographical order
   * @param other the object to be compared
   * @return negative if current's name is alphabetically smaller than other one's name;
   * return positive if current's name is alphabetically larger than other one's name;
   * return 0 if the name is the same
   */
  @Override
  public int compareTo(Primate other) {
    return this.name.compareTo(other.name);
  }


  public String getSex() {
    return this.sex.toString();
  }
  public String getFood() {
    return this.food.toString();
  }
  public String getHealth() {
    return this.checkHealth() ? "Healthy" : "Sick";
  }


}
