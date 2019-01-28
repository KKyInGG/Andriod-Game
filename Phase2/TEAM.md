# TEAM AGREEMENT (Group 0669)
***

## Contact Information & Responsibilities

* Ellen Puhalovich: (416) 833-6771, ellen.puhalovich@mail.utoronto.ca, 'ellenpuhalovich'
-- Refactoring, design patterns, unit testing, memory card game

* Allen (Xuanzhong) Zhao:  (647) 673-5798, xuanzhong.zhao@mail.utoronto.ca, 'zhaoxuanzhong'
-- Sudoku game, graphics and UI, make sliding tiles always solvable, unit tests

* Nick Perrin: (647) 965-7083, nick.perrin@mail.utoronto.ca, 'perrinni'
-- Team documentation & repo setup, unit testing, graphics, refactoring

* Katherine (Keying) Chen: (647) 992-2327, kkeying.chen@mail.utoronto.ca, 'chenkey8'
-- Sudoku game, unit testing, keeping meeting minutes, additional documentation

* Luke (Kyungseoup) Han: (437) 970-2292, kyungseoup.han@mail.utoronto.ca, 'LukeHan'
-- Memory card game

###### All team members will contribute to small, general fixes with regards to style and documentation of their code.
#
## Primary Communication Tools

* Facebook Messenger

## Team Contract

* We will all attend every team meeting and actively participate.
* Should an emergency arise that prevents us from attending a team meeting, we will notify our team immediately.
* The work will be divided roughly equally among all team members.
* If we do not understand a concept or code, we will ask the team and use course resources (profs, TAs, office hours) to resolve the issue.
* We will respect everyone's ideas and do our best to choose designs and implementations on the merit of the ideas alone, allowing everyone to contribute.

###### Outside of meetings, the work will be done mostly individually.

## Meetings & Minutes

Phase 2 Team Meeting:

Meeting 1:
Location: BA2210
Time: November 5th (15:00 - 16:09)

Some questions and answers during meetings:
1. Which Game we are going to do?
Snack, MemoryCard, Sudoku...
At the end, we are choosing MemoryCard and Sudoku since they are easier to apply the undo and
save functionality.

2. Who is reponsible for which part of the game?
Better two people work for one game and the unnittests of the game, one for Design
and the slidingtile.

3. How Can we extend the project from phase 1 to phase 2?
We decides to have a interface and we hope that the design can be similar to the slidingTile. In
this way, we can figure out the whole design depends on the slidingTile.

4. What method are in Common?(Create a abstract class or Interface?)
  TouchMove(), isValidTap(), Method in the ScoreBoard class.
  question:
  # Maybe We should create a GameManager?
  # Maybe We can set up Manager in the controller?




Meeting 2:
Location: BA2210 (18:00 - 19:35)
Time: November 11th

Some questions and answers during meetings:
1. What things to add into Controller?

2. How to create the sudoku?
There are two methods. We just decided to create the seed sudoku and create them by swapping elements
in the valid sudoku to create it.

3. How to implement the animation for memory game?
We are not doing the animation. We decide just set two images for the same card and refresh the
display.




Meeting 3:
Location: BA2210 (16:00 - 17:35)
Time: November 27th

Some questions and answers during meetings:
1. How to write TestCase for each Game?
  After looking at piazza, only writing test case for Controller and public methods.

2. Which class that we don't need to write the TestCase?
  View(Activity) and Model.

3. Check whether all the instance variable can be set to private.
4. Write Javadoc for the class and write "@Override" if you are inheriting the method!!!!
5. Discuss About the presentation part.