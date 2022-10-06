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
An Observer pattern was implemented via the Subject, Observer, and Logger classes. The Subject class is implemented by Adventurer and Creature abstract classes. This updates the status of Adventurers and Creatures every turn. This will also notify the Observer class for the Logger summary output. Logged events are only reported from the perspective of the actor after the event, i.e. targets in combat do not report a loss if the attacker wins. This goes for both Adventurers and Creatures.<br>
It should also be noted that we combined the functionality of Logger and Tracker in the write-up to one, cohesive class called Logger. Logger is capable of meeting requirements for both Logger and Tracker. All of the Logger-n.txt files are located in the Logger Files folder.<br>
<br>
A Strategy pattern was implemented with the combatStyle and searchStyle classes. This allows for unique subclasses such as expert, trained, untrained, and stealth combat subclasses. This also allows for the unique subclasses of careful, careless, and quick search subclasses.<br>
<br>
A Decorator pattern was implemented with the celebrateDecorator class that implements the combatStyle. There are four subclasses, shout, dance, spin, and jump that have a 1 in 3 chance of occurring after an adventurer wins combat. Each celebration will be performed 0 to 2 times, also determined randomly. <br>
<br>
Therer is also a new Treasure package that contains the new Treasure objects. 24 objects are placed randomly in the Facility and provide adventurers with different events that occur upon the Treasure objects discovery. Adventueres also have an inventory to track their treasure objects. Adventurers may not carry duplicates.<br>
<br>

*********
UML UPDATE
*********
The main structure stayed relatively the same. The biggest change was adding an additional Action package to refactor our combatStyle and searchStyle correctly. Also, our Treasure package and object required far less methods than expected. Additionally, the Observer pattern refactored quite a bit of our UML to include Subject, Observer, and Logger properly.<br>
