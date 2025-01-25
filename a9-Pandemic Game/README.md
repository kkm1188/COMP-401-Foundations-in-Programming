# A9 - Pandemic

This is an extra credit assignment in which you will start with a functioning pandemic simulator that has a minimal interface and add various features. You are welcome to change any and all parts of the application and to add additional features above and beyond the ones suggested below. 

## What You Are Starting With

The application is organized into model, view, and controller components. Below is a brief overview of how those parts fit together.

### Model Components

The model components include:
* PandemicModel
* Person

PandemicModel is the main model component where the application's state is maintained. At construction, the model is provided the number of simulated people in the population, a scale factor that relates the size of the "world" to a person, and a step size which indicates the length of simulated time in terms of simulated days that is simulated at each step. These configuration parameters can not be changed during a simulation but the model can be reset with a new configuration using the reset method. Getter methods for retriving these values are available.

The model has four read / write properties that can be retrieved or changed dynamically via getters and setters during a simulation. These include:
* LockdownFactor
  * A value between 0.0 and 1.0 that indicates to what degree people in the simulation are slowed down. A factor of 0.0 indicates that people are allowed to move at their "natural" pace. A factor of 1.0 indicates that people will stay in place and not move at all. 
* DiseaseDuration
  * A positive value that indicates the amount of simulated time that an infected person has the disease and is contagious before either recovering or dying.
* Asymptomaticity
  * A value between 0.0 and 1.0 that indicates the percentage chance that a person is "asymptomatic" if they do get the disease. Asymptomatic people who are infected are rendered in the same manner as a normal person (i.e., you can't tell they have the disease), but they are, in fact, infecting others they come across. A value of 0.0 indicates that there is no chance that a person is aymptomatic, a value of 1.0 indicates there is a 100% chance that a person is asymptomatic.
* Mortality
  * A value between 0.0 and 1.0 that indicates the percentage chance that an infected person who is symptomatic dies as a result of the disease. Asymptomatic people never die.
  
The model has five read-only properties available through getters:
* Time
  * The amount of simulated time that has passed expressed in days.
* EconomicDamage
  * The amount of economic damage that has accrued due to lockdown expressed in billions of dollars. Economic damage is accrued proportionally in relation to the lockdown factor. When the lockdown factor is 0, no economic damage is accrued. When the lockdown facotr is 1.0, maximum economic damage is accrued. The formula for economic damage that is accrued is a prorated portion of an estimate of gross domestic product and is simply hard-coded.
* DeadCount, InfectedCount, RecoveredCount
  * The number of people that have died, have been infected, or have recovered. Every infected person will either recover or die so you can calculate the number of people that are currently infected as: InfectedCount - DeadCount - RecoveredCount

The Person class models a specific person. When PandemicModel is constructed or reset, the population is generated as a collection of randomly constructed Person objects. Each Person is associated with a location, direction, speed, and disease state. The location of a person is a coordinate (x,y) where x and y are expresses as double values betweend 0.0 and 1.0. You can think of the world that people live in as having coordinate (0.0, 0.0) in the upper left corner and (1.0, 1.0) in the lower right corner.

PandemicModel acts as an Observable by publishing simulation events to registered PandemicModelObserver objects using PandemicModelEvent objects. There are five event types:
* ADVANCED - indicates that the model has been advanced one simulated time step.
* RESET - indicates that the model has been reset
* DEATH - indicates that a person has died
* INFECTION - indicates that a person has been infected
* RECOVERY - indicates that a person has recovered

## View Components

The view components include:
* PandemicView
* World

PandemicView is the main view component as follows the widget pattern that we have used in prior class examples. It registers itself as an observer of model events and responds accordingly. The widget contains only one UI component, a World widget, that renders the world graphically as a rectangle with circles representing people. 

The World component is a subclass of JPanel that renders the world at approximately 40 times / second. It acts as its own mouse and mouse motion listener to allow the user to drag out an "infection area". If an infection area is dragged out, an infection area view event is published (see below).

The PandemicView component also starts a thread that reads and interprets commands from the console. Type "help" at the console to see which commands are available. The commands are interpreted and may result in view events being published.

PandemicView acts as an Observable by publishing view events to registered PandemicViewObserver objects using PandemicViewEvent objects. There are three PandemicViewEvent types:
* INFECT_AREA - indicates a request to infect the people in a particular area of the world.
* STEP - indicates a request to step the simulation forward by one time step.
* RESET - indicates a request to reset the model providing values for the configuration parameters population, scale, and step size.

## Controller Components

The controller component is PandemicController. The controller registers itself as an obeserver of PandemicViewEvents with PandemicView and responds accordingly by interacting with and controlling PandemicModel.

## The Main Game

The main game is in the class Pandemic. It creates the main model, view, and controller components and sets up a top-level applicationand gets it going. It follows the standard boilerplate model that we've used in class for all of our GUI examples.

# What To Do

First, play with the game and read the code in order to make sure that you understand how the various pieces fit together. Feel free to discuss the assignment with your peers, but as an extra credit assignment, help from learning assistants will be limited at best. 

Add the following features (listed in order of difficulty):
* Novice
  * Replace the console-based interface by incorporating into PandemicView user interface components for interacting with all of the various aspects of the game that the console-based inteface provides and displaying the state of the game (i.e., current time, number dead/infected/recovered, economic damage).
* Adept
  * Add new view events (and corresponding user interface components for generating them) for requesting that the simulation be stopped / started at a configureable pace (i.e., steps / second). The controller should handle these events by creating a thread to step the model through the simulation at the desired pace.
* Jedi
  * Change World so that death events are rendered as animations on the world somehow. This is pretty challenging to do correctly and will require you to learn about and understand how the World object is able to draw things as 2D graphics.
  
See this video for a demonstration of these features: https://youtu.be/0k3uW5tiSQw

# Due Date and How Extra Credit Will Be Given

Submissions for this assignment will not be considered after 6:00 AM, May 2nd. This is a hard deadline. Extra credit will be given in the form of a course grade step depending on how far away from the next course grade you are. The farther away from the next course grade you are, the more that will be expected of this assignment. This means that if you are a just slightly below the next grade step (say you end up with a B+ and are just shy of an A-), then a modest effort on this assignment (say adding UI controls to replace the console-based interface) will suffice to push you to the next step (i.e. an A-). Similarly, if you are very far away from the next grade step (say you end up just barely over the line for getting a B), then a much higher standard will apply for you to be given the next grade step (in this case a B+). In particular:

* For well-functioning novice features, you will be given the next course grade step if you are already half-way to the next course grade step.
* For well-functioning novice and adept features, you will be given the next course grade step if you are already 1/4 of the way to the next course grade step. 
* Well-functioning novice, adept, and jedi features will get you the next course grade step even if you are just over the currrent grade step.
