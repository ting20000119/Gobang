# Gobang
Using JavaFX to design  a playable gobang game

Designed a program that simulates a traditional oriental game: Gobang. Gobang is a game that uses the same chessmen (chess pieces) as a Go game does but it is considerably simpler than the Go game. Gobang requires two players playing in turn. The first person who can place 5 consecutive chessmen in a line wins the game. The line could be vertical, horizontal, diagonal lines. Therefore, there are up to four types of lines that the player needs to consider.

Players in the Gobang game can use the mouse to click on a cell to placing a chessman on the cell. A chessman is represented as a solid or hollow ellipse with a 4-pixel space from the boundary of a cell. Any inputs would be checked for legality. For example, one cannot click on a cell that is occupied already or move the focus out of the boundary of the board. When a chessman is placed on the board, the system would check if the game is over due to this newly placed chessman. If the game is not over yet, the ownership of the keyboard will be given to the other player and the command prompts will be changed accordingly as well.

![image](https://github.com/ting20000119/Gobang/blob/main/img/gobang.png)
