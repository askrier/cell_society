# Simulation Design Plan
### 29
### Claudia Chapman, Libba Lawrence, Andrew Krier, Alexander Uzochukwu

## Introduction
The goal of this project is to create a flexible architecture that implements different versions of a cellular automata.
The design will have a main driver class that is responsible for communicating with the three other aspects of this project 
being configuration, simulation, and visualization. The configuration part of this project will be reading the XML file 
that sets up the specifications of a given cellular automata system and the initial conditions of the cells. The simulation 
part of the project is responsible for updating the values of the individual cells based on the current value as well as 
the values of the cells in the neighborhood with regards to the rules set up by the configuration. Then, the program will 
continue as a closed loop between simulation and visualization which will display the updated values of the cells until a new
configuration is called. 

## Overview

![title](CRC_Design.png)

 	
In order to implement this project, we are splitting it into four major categories: main, simulation, visualization and configuration.
 Main is the driver class for the project, it is what will call all the other classes and be the interface on which they interact. 
 Configuration is made up of three classes: Config, Cell, and Grid. Configuration uses the XML file and user input to create the grid of cells.
  The data for the grid could be stored as a double array, or as a Cell [] array. 
  Config reads in an XML file and stores the data.
   The simulation data could be stored as a String, or a String []. 
   The settings for the global configuration parameters could be stored into an array or arrayList.
    The cell states could be stored in an array [] [] or they could be passed into each individual cell. 
    Cell will be called to create individual cells that will then populate the grid.
     Each Cell will contain its name, state as well as any other pertinent information (i.e possibly location depending on how the cells are passed as data). 
      Grid will organize the individual cells and store them as a collection. 
      Simulation will be made up of two main classes: Simulation, and Neighborhood. 
      Another way to implement the Simulation category would be to have individual classes for each one of the five types of simulations: GameofLife, Percolation, Segregation, PredatorAndPrey, and Fire. 
      Having individual classes for each simulation is likely the route that we will take in implementing our game. Each simulation class will contain that simulation rules and implement them. 
      The Simulation class will implement the grid of cells and implement the specific simulation rules.
       Neighborhood will create the neighborhoods within the grid for that simulation. Visualization will be made up of one class: Visualization.
        Visualization will display the state of the 2D grid, control the animation of the simulation and take in user input for the simulation.
 
Method Signatures:


```java
Configuration:
Public class Main(){
Public void setupInitialScreen() {}
public void ReadFile( ) {}
public void step(double elapsedTime)()
}



Public class Config( ) {
public Config( ){}
}

Public class Cell(){
Public Cell(){}
Public getCurrentState() {}
Public getNextState() {}
Public String getName() {}
Pubic updateCellValue(){}
}


Public class Grid() {
Public Collection Grid() {}

}

	Simulation:

Public class Simulation() {
	Public Simulation() {}
}
Pubic class Game(){}
Public class Percolation extends Game() {}
Public class Fire extends Game() {}
Public class GameOfLife extends Game() {}
Public class Predator_Prey extends Game() {}
Public class Segregation extends Game() {}

	Visualization:

Public class Visualization () {
Public Visualization () {}
}
```
	

## User Interface

The user interacts with the project by loading configuration files,
 pausing and resuming the simulation, and changing the speed of the animation 
 rate. The project itself will show error warnings to the user if the file given
  cannot be found, or if other important information is needed. In order to load
   configuration files, the UI will have either a text input section or a drop 
   down box in which the user can put/find the file path. The UI will have a
    pause/play button for the user to start and stop the animation. The UI will
     also have a speed up and slow down button to control the speed of the 
     animation. All of these user interface features will be on a panel at the
      bottom of the UI, below the grid of cells. 

![image](user_interface.jpeg)

## Design Details

#### Components

* Each component introduced in overview:

    **Configuration**
    
    * This component handles reading in the file and gathering its
    information through a Scanner. The title and author of 
    the data file are stored into global variables that will 
    then be passed into the UI to be shown on the screen. This component uses 
    the file information to create the grid of cells that will be used in the simulation. This 
    class collaborates with Simulation in order to implement the 
    rules of the specific simulation. It could be extended to take in different 
    types of files as well as more than one file at a time. 
        * Cell
            *  Cell handles the information pertaining to each new cell that is created.
                This class holds the cell’s name and the state of the cell. 
                The cell class will also have the ability to update the information
                stored in the cell. 
        
        *   Grid
            *   The Grid contains individual cells as a collection.    
            
    * This component and functionality was selected to be a separate class to ensure 
    that all the information whose source is the given XML file stays in the same place.
    This is primarily an initializer that will have some implementation that will allow
    it to be called at varying points in the simulation to load a new file, this can
    accommodate the fourth and fifth test cases given since what the concern is for those
    is in proper handling of an XML file, which should all be housed within this class.
            
    **Simulation**
    
    *   This component implements the rules of the specific simulation on the cells
        in the grid. It takes into account each cell's neighborhood (which is dictated
        by the simulation) and updates the state of the cell appropriately. It will use 
        constructors for each simulation in order to obtain the rules. It will also use 
        the grid collection (Either Array[][] or an object array). After the cells have been 
        updated they are displayed to the screen by the Visualization component. It can be 
        extended to implement different types of neighborhoods (Neighborhood class) and 
        new simulations. 
 
           * Neighborhood
                * This class creates the different types of neighborhoods required for each simulation. 
                *   This component will have subclasses of each of the simulations that will override specific
                    methods in order to implement different rules. 
    *   This component makes all the decisions in the Automata. Centralizing this process will allow
        middle and edge cells (as in use cases one and two) to be handled according to the 
        rules held internal to this component. This would also make adjustment much simpler 
        as long as what is passed to the visualization component remains the same.
                
    **Visualization**   
    * This component allows for the display of the 2D grid and any state changes to
        the cells. It displays the UI and collaborates with the Main class to 
        collect and display user input. The resources that this component would use include
        a drop down box, a start-stop button, and a speed button within the UI. It can be extended
        to implement a grid of irregular size and shape because the animation of the grid is implemented
        on a panel in the UI. 
    * Visualization takes what it needs to display from the simulation component. This is to simplify
        the divide and overall the delegation of the code. This component should also have the capacity
        to send signals back to the simulation class so that a click or button press in the JavaFX
        environment will appropriately affect the operation of the simulation. This implements use
        case three in being able to handle moving to the next generation and display it graphically.


#### Use Cases

- Inside the for loop of the cells of the grid within simulation, call updateCellValue() passing in the neighborhood which
will include the 8 neighboring cells. Inside updateCellValue() the nextState variable will be set to Dead and the 
getCurrentState() will be called on neighboring cells. The new state will be shown in the visualization. 

- Inside the for loop of the cells of the grid within simulation, call updateCellValue() passing in the neighborhood which
  will include the 5 neighboring cells. Inside updateCellValue() the nextState variable will be set to Alive which will be
  shown in the visualization and the getCurrentState() will be called on neighboring cells.
  
- Go through the for loop in the step method and call updateCellValue() on each cell in the grid. This will call getCurrentState()
 on neighboring cells and update the states accordingly. Then visualization will be called on the grid.
 
- This will happen inside of the ReadFile() method which is called in Main.

- The button listener that was initialized in the setupInitialScreen() method will be triggered which will pause the running
simulation and reconfigure the next game by calling ReadFile() and reset the game.

## Design Considerations

1. How exactly is the information being passed through a file? We understand how data such as the # of cells or the configuration
of the grid would be easily passed through a file and then into a method, but how are the rules of a specific instance of
cellular automata passed in through a file. 

2. What are the different types of rules and how many possibilities are there? How many different rules can there be and 
how do we implement them simultaneously?

3. What are the different neighborhood possibilities?/Are there possibilities other than being on an edge, middle, or corner?
No, but how are we going to account for the neighborhood types in our design and send in those neighboring cells.

4. How are we naming the different states? For example, when there are more than 2 states, we cannot use a boolean. So, 
we need to be aware of this.

5. There are dependencies between the cells which causes us to think about how we are going to update the cell based on the 
current state and not the one its changing to.

####Assumptions
- We are currently assuming that all of the files are formatted the same way, but if this is not the case, we will have 
to make some changed to our design.


## Team Responsibilities

 * Claudia Chapman
        - cell/grid/neighborhood

 * Libba Lawrence
        - will be responsible for UI
        - this includes setupInitialScreen(), readFile()

 * Andrew Krier
        - Working with Libba on the readFile()/config stuff and also working on the main file and what public methods
        are being called
 
 * Alex Uzochukwu
        - also simulation stuff/working with Claudia/ specifically simulation rules and how to create an expandable
        architecture concerning this concept

