# Simulation Design Plan
### 29
### Claudia Chapman, Libba Lawrence, Andrew Krier, Alexander Uzochukwu

## Introduction

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

Public class Config( ) {
public Config( ){}
public ReadFile( ) {}
public GetScanner() {}
}

Public class Cell(){
Public Cell(){}
Public getState() {}
Public getName() {}
}


Public class Grid() {
Public Collection Grid() {}

}

	Simulation:

Public class Simulation() {
	Public Simulation() {}
}
Public class Neighborhood(){
	Public Neighborhood(){}
}
Public class Percolation () {}
Public class Fire () {}
Public class GameOfLife () {}
Public class Predator_Prey() {}
Public class Segregation () {}

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
                
    **Visualization**   
    * This component allows for the display of the 2D grid and any state changes to
        the cells. It displays the UI and collaborates with the Main class to 
        collect and display user input. The resources that this component would use include
        a drop down box, a start-stop button, and a speed button within the UI. It can be extended
        to implement a grid of irregular size and shape because the animation of the grid is implemented
        on a panel in the UI. 


## Design Considerations

#### Components

#### Use Cases


## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3

