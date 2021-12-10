# Pairs Calculator

## Exercise

The company ACME offers their employees the flexibility to work the hours they want. But due to some external circumstances they need to know what employees have been at the office within the same time frame

The goal of this exercise is to output a table containing pairs of employees and how often they have coincided in the office.

Input: the name of an employee and the schedule they worked, indicating the time and hours. This should be a .txt file with at least five sets of data. You can include the data from our examples below:

Example 1:

INPUT
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00- 21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00
ANDRES=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00


OUTPUT:
ASTRID-RENE: 2
ASTRID-ANDRES: 3
RENE-ANDRES: 2

Example 2:

INPUT:
RENE=MO10:15-12:00,TU10:00-12:00,TH13:00-13:15,SA14:00-18:00,SU20:00-21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00

OUTPUT:
RENE-ASTRID: 3


### Installing
mvn install

### Testing
mvn test

### Executing program
java -jar target/pairs-finder-1.0-SNAPSHOT.jar


## About

### Folder Structure
  business : Business logic classes \
  dtos: data transfer objets \
  enums \
  interfaces \
  models \
  parsers  \
  outputmanagers: classes that handles de results and implements outputManger interface
  parsers \
### Process Description 
**PairsFinder**: main method holder this initiates the program \
**Worker**: contains the name of the worker  \
**TimeFrame**: Model that represents a working day period with initial time and endTime, time is calculated as seconds passed since 00:00 (ex. 00:01 = 60 seconds) \
**TimeSheet**: Model that contains a **TimeFrame** and a **Worker** \
**PairsCalculator**: this is the core class, it takes a file path as an input and passes a **TimesheetParser** which parse it into 
**Timesheets**, then it combines them in groups of 2 and compare each group, creating a **PairDTO** per combination.  Finally, the list of **PairDTOs** is passed to an **OutputManager** that decides what to do with it.

### Design Patterns 
**Singleton**: We only need one instance of the CSVTimeSheetParser and ConsoleOutputManager \
**Inversion of Control**:  PairsCalculator is the core class, it shouldn't depend upon a specific parser or ouputManager. 
It receives an **OutputManager** and a **TimeSheetParser** as params in its constructor. This comes handy for the tests. \
**DTOs**: The result of the pair calculation is hold in a **PairDTOs**, this contains only minimal details. 2 names and a count, making this data easier to be consumed by client that are only interested in the result.