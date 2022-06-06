The Basic RPG game can be played by running the Game.java program. It will guide the user through creating their character and a weapon and spell as a sample of the game mechanics.

Polymorphism is demonstrated in the use(Item) method of the Character class.

GameTool.java serves to simulate battles, create randomized weapons, spells, and enemies that I was hoping to expand more upon before the scope of the project grew to where its methods served a 
similar purpose to what could be covered in Game.java. I left this as is because I wanted to have experience utilizing static methods in this way but I think best 
 practice would have those methods in Game.java with the game working as it does now.

The CharacterTester.java file contains the testing of the character class which due to its relationships to other classes, also has some testing of the classes Enemy, Weapon, Spell, 
HealingPotion, and ManaPotion.  Some of the methods are interactable so I tested them in debugging the main program and could not show this. There are additional tester classes for the non-trivial or super classes.

Weapon.java and Spell.java ended up being very similar due to simplifying spell casting as it came to how the mechanic would work. These could probably have a common parent
class but they also inherently are different so I left them seperate despite some similar methods.

