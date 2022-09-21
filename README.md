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
The Raiders of Lost Arctangent program is a fully simulated game adaptation of Temple of the Beastmen, made by Lester Smith of Game Designer’s Workshop. By running this program, the game will autonomously run until the game is won, either by all treasure being found, all adventurers being killed, or all creatures being killed. In this repository, you will find the code to run the game autonomously, as well as, the terminal output of one completed game and another output of 30 completed games within corresponding text files. Users must press enter to transition between turns.<br>
<br>
An example of inheritance can be found in the Brawler.java file at line 12. Runner.java at line 13, Sneaker.java at line 13, and Thief.java at line 13.
All of the Adventurer subclasses inherit from the Adventurer superclass.<br>
<br>
An example of encapsulation can be found in the Adventurer.java file at line 15. The example of the get function for the encapsulation can be found at line 47. This shows encapsulation by encapsulating the member data, which is private, and making it accessible via a getter method.<br>
<br>
An example of abstraction can be found in the Orbiter.java at line 13. All of the entity classes, creatures and adventurers extend their corresponding superclasses. For example, Orbiter extends Creature that implements Entity.<br>
<br>
An example of polymorphism can be found in Orbiter.java, Blinker.java, and Seeker.java as they all are extensions of the Creature superclass. The associated code lines are Orbiter.java at line 9, Blinker.java at line 14, and Seeker.java at line 13. They all carry the same methods and attributes as a result with minor variance.<br>
An example of cohesion can be found in Render.java at line 22. This is a good example because Render.java holds all the print statements and methods for the game simulation. This is considered high cohesion since they are highly related to the class they are within.<br>
<br>
An example of identity can be found in Render.java in line 78. We can see how the Adventurer a is set equal to Adventurer a0. <br>
