package sanctuary.model;

/**
 * This is the driver class (main class) of the project, which simulates the real-life practice of
 * the sanctuary class and its related methods
 * Name: XIN DING
 * Date: 4/8/2024
 */
public class driver {
  public static void main(String[] args) {
    // Initialize a sanctuary
    Sanctuary sanctuary = new Sanctuary();

    // Add new primate to the sanctuary -> goes to Isolation cage
    Primate primate1 = new Primate("Leo", PrimateType.HOWLER, Sex.MALE, 1.2, 7.5, 5, FavFood.LEAVES);
    Primate primate2 = new Primate("Mia", PrimateType.SQUIRREL, Sex.FEMALE, 0.5, 0.8, 2, FavFood.NUTS);
    Primate primate3 = new Primate("Emma", PrimateType.HOWLER, Sex.FEMALE, 1.2, 7.0, 9, FavFood.LEAVES);
    sanctuary.addNewPrimate(primate1);
    sanctuary.addNewPrimate(primate2);
    sanctuary.addNewPrimate(primate3);
    System.out.println("========Sanctuary after adding 3 new primates========");
    System.out.println(sanctuary + "\n");

    // Move the healthy primate in the Isolation cage to the target Enclosure
    // "Leo"-> Enclosure HOWLER, "Mia" -> Enclosure SQUIRREL
    sanctuary.movePrimateToEnclosure("Leo");
    sanctuary.movePrimateToEnclosure("Leo");
    sanctuary.movePrimateToEnclosure("Mia");
    sanctuary.movePrimateToEnclosure("Emma");

    System.out.println("========Sanctuary after moving 3 new primates to enclosures========");
    System.out.println(sanctuary + "\n");

    // If the primate gets sick, move it to the Isolation cage
    primate1.changeHealth();
    primate3.changeHealth();
    sanctuary.moveSickToIsolation();
    System.out.println("========Sanctuary after moving sick primates to isolations========");
    System.out.println(sanctuary + "\n");


  }
}
