# README - PROJECT SETUP
***

## Phase 2 Git repository
* https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0669

## Project setup instructions

* Please note Java SDK version 8 is required. Ensure you have upgraded before attempting to run our program!

1. Open Android Studio. Click "Check out project from Version Control" -> "Git".
2. Copy the Phase 2 Git repository link (above) into the URL box. Click "Clone".
3. Enter any MarkUs login info if prompted.
4. In the "Import Project" window, select "Import project from external model". Choose "Gradle" from the list and click "Next".
5. Set the "Gradle project:" path to Phase2/slidingtiles (inside your new project folder) and hit "OK". You should now be able to select the radio button beside "Use default gradle wrapper (recommended)". Do so and click "Finish". (The directory may still carry the name "slidingtiles" from a previous assignment, but the root package inside the app is "gamecentre".)
6. If you receive any "Unregistered VCS root detected" message, address this by clicking "Add root". If you receive any "Unsupported Modules Detected" error, you can safely ignore it.
7. Let the project complete its build. You should now be able to run the app!

# Playing the Game:

## Creating Account
If you don't have the account, please click "SignUp" button to sign up. You can create the name and
password to whatever you like. There is almost no restriction of it. You are not allowed to have the
same username with other players.

## LogIn Account
After creating your account, you can enter the correct password and username to get into
GameLauchCenter.

## Game Lauch Center
In the Game Lauch Center, there are three games to play and you can play them by clicking the icon.

## The common button in these three game:
A. New Game:
1. Starting your Game by clicking these Button.
2. Choose the level of difficulty to play.
2. After Starting the New Game, you can undo the steps by clicking the undo button and save game
by clicking the save button.(MemoryGame does not have undo functionality)
3. After Winning the Game, there will be a page to show the globalScoreBoard and your own
scoreboard page. The last one is the score you get for this game which is the number of steps
you take to win.

B. Load Saved Game:
You can load the game that you saved before (the game will autosave after each movement
you make(when you start the game)).
If there is no savedGame, then we will display "no loaded game" message.

C. View ScoreBoard:
You can view the global score board and personal scoreboard by this button.

D. Quit:
You can quit to the Game Launch Center by clicking this.

** Important Note:
1. If you already win the game and you get back to game page by clicking the phone's back button,
anything will not work!!!(but it will not crash) Also, we will not save the game that you
already win. Instead of that, your score for the game will be stored in our scoreboard.
The correct way to go back is clicking return button in the scoreboard page.

## In the ScoreBoard Page:
For the ScoreBoard, we will only display the top five scores of all users for the game you are playing
and the your top five scores of the game.(It means score will be refreshed)

## SlidingTilesGame:
Game Rule:
You can win by swapping the Tile with the blank one and get the Number Tile in the correct order.

In the game page:
1. Undo
you can undo infinitely steps (until the state you starts) by clikcing  undo button.

2.Save
you can save the game by clicking this button.

3.Set Number Of Undo:
Set the number of undos you wants.


## SudokuGame:
Game Rule:
You can win these by filling in the number with 1 - 9 in the sudoku puzzle until each column, row,
and square will not have repeated number through 1 - 9.

In the Game Page:
1. If the colour of the grid is blue, those are the grids that you can fill in the number.
2. Choose the number that you wants to fill in to the blue grid
(not in the board, but below the board) and Choose the spot that you want to fill in the number.
3.The colour of the grid that you are currently clicking will be yellow and
the green grid just helps you to check whether there are repeated number.
4.Everytime you try to fill in a number is one steps and everytime you undo the button will be
counted as one step. The more steps you takes to win, the less score you get.
** Important Note:
If you can't fill in the number, the reason will pop up the at the bottom of the page.
Please follow that instructions to finish the Game!!

## MemoryGame:
Game Rule:
You can click the card to flip the card and see what picture is in the front. Your goal is to flip
all the card in the front by matching the pairs of card that have the same picture in the front.

In the Game Page:
1. After matching each pairs of cards and readying to click the next card, the screen will show up
the total matching pairs you have.
2.If the two cards that you flip are not matching, they will flip back after you click the third card.
3. The more steps you takes to win, the less score you get.

** Important Note:
If you can't flip the card, the reason will pop up the at the bottom of the page.
Please follow that instructions to finish the Game!!

Unnitest:
We don't write the unnitest for any MovementController class. Even though it is Controller, it only
display the message on the screen (it is something like view). However, we are not deciding to move
the "process to move method" into other classes in case some day we want to extend it and it will
be a "real controller".