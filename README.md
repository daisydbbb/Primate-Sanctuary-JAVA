# Primate Sanctuary System App 🐵

This Java application is designed to assist with the management and registration of primates at a primate sanctuary. It supports various functions, including registration, moving, sickness treatment etc.

## Description

The Primate Sanctuary System app facilitates the management of two types of primate housing:
- **Isolation Cages:** There are 20 individual cages for new arrivals and those that are sick and need medical attention.
- **Enclosures:** Larger areas that can accommodate a troop of monkeys from the same species.

## Key Features

- **Registration of New Monkeys:** Allows users to register new monkeys directly into isolation.
- **Notifications:** Alert users when the action has issue and can not be performed. E.g. when isolation cages are full and no further monkeys can be accommodated.
- **Monkey Transfers:** Enables users to move monkeys to and out from isolation cages based on health status.
- **Enclosure Lists:** Generates detailed lists for each enclosure showing the monkeys housed, including their name, sex, favorite food, enclosure type and healthy status.
- **All Primates Lists:** Generates the list that includes all the primates in the sanctuary, including isolation cages and enclosures.
- **Alphabetical Order:** All monkeys housed in the sanctuary are listed in alphabetical order.'

## Additional Notes

The Model part of this project was constructed first, with all methods in different classes tested and validated. While writing the Controller and View parts, I realized that the original Model version lacks some key functions, therefore I made some edit and also included additional methods that can better coordinate with the Controller and improve user experience.

## Getting Started

### Prerequisites

Ensure you have Java installed on your system to run the application. You can download Java from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

### Installation

1. **Download the Application**
   - Download the `project6.jar` file located in the `res/` folder of this repository.

2. **Running the Application**
   - Open a terminal.
   - Navigate to the directory where the `project6.jar` file is located.
   - Execute the following command:
     ```bash
     java -jar project6.jar
     ```
   - OR double click the `project6.jar` file to activate the program.

## Usage

After launching the application, you can:
- Register new monkeys through the provided interface.
- View and manage the primate lists as needed.
- Treat sick primates in isolation cages.
- Mark primates in all sanctuary list as sick.
- Move healthy primates to target enclosures.
- Move sick primates from enclosure back to isolation cages.
- Receive alerts regarding housing capacities.

## Assumption

- Assume primates that got registered cannot be deleted
- Assume all registered primates have unique name

## Limitation

- Free Isolation cages number is fixed to 20 and cannot be edited
- Primate can only be selected using dropdown nav bar
- The information of the primate, for example, age and size, cannot be modified
- The application does not support more advanced actions such as dragging and dropping to different locations


## Authors

- [XIN DING (Daisy)](mailto:[ding.xin3@northeastern.edu])

## Citations

- Compile and build applications with IntelliJ IDEA: https://www.jetbrains.com/help/idea/compiling-applications.html)
- Java Swing Tutorial: https://www.javatpoint.com/java-swing

## Contributing

Contributions to improve the app are welcome. Please fork the repository and submit a pull request with your updates.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.

## Acknowledgments

- Inspiration from NEU CS5004 SPR24 Project, instructor Prof. Rasika Bhalerao.
- Java community contributions.
