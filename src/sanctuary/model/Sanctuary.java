package sanctuary.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * This is the Model of the project, sanctuary has 2 types of housing: Isolation cages and Enclosures
 * Isolation cages contains a list of 20 cages
 * Enclosures contains different types of troops
 * Name: XIN DING
 * Edited Date: 4/12/2024
 */
public class Sanctuary {
  private final List<IsolationCage> isolationCages;
  private final Map<PrimateType, Enclosure> enclosures;

  /**
   * This is the constructor of the Sanctuary class
   * isolationCages is initialized as an array of 20 empty cages that holds one primate in each cage
   * enclosures is initialized as an empty map with primate type as key and enclosure as value
   */
  public Sanctuary() {
    isolationCages = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      isolationCages.add(new IsolationCage());
    }
    enclosures = new TreeMap<>();
  }

  /**
   * This is the helper function that helps sort the isolationCages list
   * For filled cage, it is sorted lexicographical by primate's name, the empty cages are pushed to the back
   */
  public void sortList() {
    isolationCages.sort((c1, c2) -> {
      if (c1.getCage() == null && c2.getCage() == null) return 0;
      if (c1.getCage() == null) return 1; // Empty cages go to the end
      if (c2.getCage() == null) return -1;
      return c1.getCage().getName().compareTo(c2.getCage().getName());
    });
  }

  /**
   * Add a new primate to the sanctuary
   *
   * @param primate, the primate to be added, goes to isolation cage
   * @return true if the primate can be added to the sanctuary; return false otherwise
   * @throws IllegalArgumentException if all the isolation cages are occupied
   *                               Maintain the list order by sorting the list after move
   */
  public boolean addNewPrimate(Primate primate) throws IllegalArgumentException {
    boolean isAdded = false;
    for (IsolationCage cage : isolationCages) {
      if (cage.isEmpty()) {
        cage.addPrimate(primate);
        isAdded = true;
        break;
      }
    }
    if (!isAdded) {
      throw new IllegalArgumentException("Isolation cages are ALL full!");
    }
    sortList();
    return isAdded;
  }


  // New methods added to help with MVC construction

  /**
   * @return a list of formatted string about the primates that currently in isolation cages
   */
  public List<String> isolationCageList() {
    return isolationCages.stream()
            .filter(cage -> !cage.isEmpty())
            .flatMap(cage -> cage.getPrimate().stream())
            .map(primate -> String.format("%s - %s - %s - %s - %s", primate.getName(),
                    primate.getSex(), primate.getType().toString(), primate.getFood(), primate.getHealth()))
            .collect(Collectors.toList());
  }

  /**
   * @return a map of primates that in different enclosures
   * The map has the key of primate type (converted to String), and value of list of formatted
   * string about the primates that currently in the target enclosure
   */
  public Map<String, List<String>> EnclsouresMap() {
    Map<String, List<String>> data = new HashMap<>();
    for (Map.Entry<PrimateType, Enclosure> entry : enclosures.entrySet()) {
      String type =  entry.getKey().toString();
      Enclosure enclosure = entry.getValue();
      List<String> primateNames = enclosure.getPrimate().stream()
              .map(primate -> String.format("%s - %s - %s - %s - %s", primate.getName(),
                      primate.getSex(), primate.getType().toString(), primate.getFood(), primate.getHealth()))
              .collect(Collectors.toList());
      data.put(type, primateNames);
    }
    return data;
  }

  /**
   * @return a list of formatted string about the primates that in the entire sanctuary
   */
  public List<String> sanctuaryList() {
    List<String> allList = new ArrayList<>();
    for (Map.Entry<String, List<String>> e : EnclsouresMap() .entrySet()) {
      allList.addAll(e.getValue());
    }
    allList.addAll(isolationCageList());
    return allList;
  }

  /**
   * Find the primate with the input name in the sanctuary
   * @param sickName, the name of the primate to be searched
   * @return the primate with the target name, return null if not found
   */
  public Primate findPrimateByName(String sickName) {
    for (IsolationCage cage : isolationCages) {
      if (!cage.isEmpty() && cage.getCage().getName().equals(sickName))  {
        return cage.getCage();
      }
    }
    for (Map.Entry<PrimateType, Enclosure> entry : enclosures.entrySet()) {
      Enclosure enclosure = entry.getValue();
      for (Primate p : enclosure.getPrimate()) {
        if (p.getName().equals(sickName)) {
          return p;
        }
      }
    }
    return null;
  }

  /**
   * Create a new Exception called PrimateAlreadyInIsolationException, triggered when the user wants
   * to move a primate from enclosure to isolation cages but the primate is already in isolations
   */
  public static class PrimateAlreadyInIsolationException extends Exception{
    public PrimateAlreadyInIsolationException(String message) {
      super(message);
    }
  }

  /**
   * Move the primate with the name from enclosure to isolations
   * @param name, the name of the primate to be moved
   * @throws PrimateAlreadyInIsolationException if the primate is already in isolation cages
   * @throws IllegalStateException if the primate is healthy (Only sick primate is moved)
   */
  public void moveSingleSickToIsolation(String name) throws PrimateAlreadyInIsolationException, IllegalStateException  {
    for (IsolationCage c: isolationCages ) {
      if (!c.isEmpty() && c.getCage().getName().equals(name)) {
        throw new PrimateAlreadyInIsolationException(name + " already in Isolation cages!");
      }
    }
    for (Map.Entry<PrimateType, Enclosure> entry : enclosures.entrySet()) {
      Enclosure enclosure = entry.getValue();
      for (Primate p : enclosure.getPrimate()) {
        if (p.getName().equals(name)) {
          // found the target prime, only move if it is sick
          if (p.checkHealth()) {
            throw new IllegalStateException(name + " is healthy, can stay in the enclosure.");
          }
          enclosure.removePrimate(name);
          addNewPrimate(p);
          break;
        }
      }
    }
  }


  /**
   * Move the primate with the input name from isolation cage to its target enclosure
   * @param name, the name of the primate to be moved
   * @throws IllegalArgumentException if the primate is sick (Sick primate m)
   * Maintain the list order by sorting the list after move
   */
  public void movePrimateToEnclosure(String name) throws IllegalArgumentException {
    boolean isMoved = false;
    Primate current = null;
    // remove from isolation cage
    for (IsolationCage cage : isolationCages) {
      if (!cage.isEmpty()) {
        current = cage.getCage();
        if (current.getName().equals(name)) {
          if (!current.checkHealth()) {
            throw new IllegalArgumentException(name + " is sick, please treat before move to enclosure.");
          }
          cage.removePrimate(name);
          isMoved = true;
          break;
        }
      }
    }
    if (!isMoved) {
      throw new IllegalArgumentException(name + " cannot be moved!");
    }
    // add to the enclosure
    PrimateType currentType = current.getType();
    if (!enclosures.containsKey(currentType)) {
      Enclosure newEnclosure = new Enclosure(currentType);
      newEnclosure.addPrimate(current);
      enclosures.put(currentType, newEnclosure);
    } else {
      enclosures.get(currentType).addPrimate(current);
    }
    sortList();
  }



  // Old methods
  /**
   * Move all the sick primates in any enclosure to the isolation cage
   * Iterate though the enclosures map and include a sickList that helps with tracking
   */
  public void moveSickToIsolation() {
    for (Map.Entry<PrimateType, Enclosure> entry : enclosures.entrySet()) {
      Enclosure enclosure = entry.getValue();
      List<Primate> sickList = enclosure.removeAllSickPrimates();
      if (!sickList.isEmpty()) {
        for (Primate sickPrimate : sickList) {
          addNewPrimate(sickPrimate);
        }
      }
    }
  }

  /**
   *
   * @return the list of enclosures in the sanctuary
   */
  public List<Enclosure> getEnclosures() {
    List<Enclosure> eList = new ArrayList<>();
    for (Map.Entry<PrimateType, Enclosure> e : enclosures.entrySet()) {
      eList.add(e.getValue());
    }
    return eList;
  }


  /**
   * @return the list of isolation cages in the sanctuary
   */
  public List<IsolationCage> getIsolationCages() {
    return this.isolationCages;
  }


  /**
   * @return the formatted string that prints out all the primates in the enclosures
   */
  public String printAllEnclosures() {
    StringBuilder acc = new StringBuilder();
    for (Enclosure e : getEnclosures()) {
      acc.append(e + ";\n");
    }
    return "{\n" +  acc + "}";
  }

  /**
   * @return the formatted string that prints out all the primates in isolation cages
   */
  public String printAllIsolations() {
    return "Isolations: " + getIsolationCages().toString();
  }

  /**
   * @return the formatted string of the primates in the entire sanctuary
   */
  @Override
  public String toString() {
    return printAllIsolations() + "; \nEnclosures: \n" + printAllEnclosures();
  }

}

