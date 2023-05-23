# Set

This project was an individual project I did for my Object Oriented Design class at Northeastern.

This project aims to recreate the classic card game Set while implementing the Model-View-Controller (MVC) pattern in programming. The game allows players to identify sets of cards based on their shapes, number of shapes, and filling patterns. The project also includes an option to play on a customizable game board size.

##Getting Started
To start the game, run either the Main or General Main class depending on your desired game board size. The Main class sets up a fixed 3x3 game board, while the General Main class allows you to specify the dimensions of the board.

When running the General Main class, you will be prompted to enter the desired height and width of the game board. Please note that the product of the height and width cannot exceed 27, as there are a total of 3^3 (27) unique cards in the game. The Main class will also prompt you for a height and width since both run off the same controller, however it will only accept a 3x3 board size since that's how the game was originally presented to us.

##Gameplay
Once the game has begun, the game board will be displayed, and players can start selecting coordinates to form sets of cards. The coordinates are represented by a combination of numbers and letters. For example, 1A corresponds to the top-left spot on the board, with numbers representing the rows and letters representing the columns.

Players should input three coordinates for each set they wish to claim. The game will then check if the selected cards form a valid set. If a valid set is found, the cards will be removed from the board, and the player's score will be incremented. The board will refresh with updated cards after each turn.

The game continues until either all sets have been claimed, or there are no remaining sets on the board. At this point, the game will automatically end. Throughout the game, the current score will be displayed to keep track of the player's progress.

##Future Developments
There are plans to enhance the game's visual experience by implementing a graphical user interface (GUI). This GUI will provide a more visually appealing interface for players to interact with. However, the GUI feature has not yet been made and is a work in progress.
