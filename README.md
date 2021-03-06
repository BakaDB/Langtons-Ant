# Langton's Ant
[Langton's Ant](https://en.wikipedia.org/wiki/Langton%27s_ant) is an universal turing machine, a machine capable of having a finite set of configurations. Squares on a plane are colored either black or white. In this case the ant is identified as a red square. The ant can travel in any of the four cardinal directions at each step it takes and moves accordingly to these rules:
* At a white square, turn 90° right, flip the color of the square, move forward one unit
* At a black square, turn 90° left, flip the color of the square, move forward one unit

## Ant's behaviour
**_Starting on a completely white grid._**
- Simplicity - During the first few hundred moves it creates very simple patterns which are often symmetric.
- Chaos - After a few hundred moves, a big, irregular pattern of black and white squares appears. The ant traces a pseudo-random 
path until around 10,000 steps.
- Emergent order - Finally the ant starts building a recurrent "highway" pattern of 104 steps that repeats indefinitely.

In my opinion, the most interesting part is that all finite initial configurations tested eventually converge to the same repetitive pattern, also known as the "highway". For now no one has be able to find an initial pattern as counter example.

## Gameplay
This is a 0 players game. Configure the initial board by right clicking the different squares to turn them grey, then add the ant by left clicking on any square of your choice. Press start and see how the ant keeps chainging the board pattern. A start/stop button was added to be able to tweak the configuration mid-game. There is also a *step counter* to keep track of the number of moves the ant has made

## Instructions
Execute the Main.java class.
