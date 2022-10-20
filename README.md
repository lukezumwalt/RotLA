# RotLA
Raiders of the Lost Arctangent: a game project based on Temple of the Beastmen

***********
NAMES
***********
Lukas Zumwalt<br>
Marie Hargan

***********
JAVA VERSION
***********
IntelliJ IDEA 2022.2.1 (Community Edition)<br>
Build #IC-222.3739.54, build on August 15,2022<br>
Runtime version: 17.0.3+7-b469.37 amd64<br>
VM: OpenJDK 64-bit Server VVm by JetBrains s.r.o.<br>
Windows 10 10.0<br>
GC: G1 Young Generation, G1 Old Generation<br>
Memory: 996M<br>
Cores: 4<br>

Kotlin: 222-1.7.10-release-334-IJ3739.54<br>

openjdk 17.0.4.1 2022-08-12 <br>
OpenJDK Runtime Environment Temurin-17.0.4.1+1 (build 17.0.4.1+1) <br>
OpenJDK 64-Bit Server VM Temurin-17.0.4.1+1 (build 17.0.4.1+1, mixed mode, sharing) <br>

***********
COMMENTS
***********
The Raiders of Lost Arctangent program is a fully simulated game adaptation of Temple of the Beastmen, made by Lester Smith of Game Designer’s Workshop. By running this program, the game will run a simulated game until the game is won or lost. The player will lose the game either by the adventurer player being killed with 0 damage remaining or the adventurer player re-enters the entrance without treasure or without killing all the creatures. The player may win the game by re-entering the entrance with treasure or after all creatures being killed. In this repository, you will find the code to run the game, as well as, the terminal output of one completed game and another output of completed games within corresponding Logger-n text files. Users must press enter to transition between turns.<br>
<br>
A Command pattern was implemented with the User Interface package. The UserInterface class will issue each of the four valid commands. This done via switch statements and user querying. To avoid duplicating code, we decided to call the methods that already exist and are owned by Player concrete class.<br>
<br>
A Singleton pattern was implemented in the Render and Logger classes. We gave both classes a private constructor, with the Render class representing eager instantiation and the Logger class representing lazy instantiation. We chose this format since Render is called at the beginning of every game instance, which aligns with eager instantiation. Since Logger is called at the beginning of every turn, we chose lazy instantiation.<br>
<br>
A Factory pattern was implemented via characterCreator() which encapsulates the creation of Adventurer and creatures. Since both Adventurer and Creature are abstract classes, this method of character creation made the most sense. We are creating the Adventurer and creature classes via inheritance through the characterCreator() method. Thus, we have a Factory pattern and not an abstract factory pattern. <br>
<br>
There is also an addition of a Test package that has our JUnit test suite. The captured results of the 15 running tests can be found in UnitTest.txt<br>
<br>

*********
UML UPDATE
*********
The main structure stayed relatively the same. The biggest change was adding an additional UserInterface package. We decided to change the location of our Singleton pattern as well, from Engine.java to the Board package with Render.java and Logger.java. We had very few changes to implement Factory pattern minus the addition of a method. <br>
