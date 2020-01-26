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


## Design Details


## Design Considerations

#### Components

#### Use Cases


## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3

