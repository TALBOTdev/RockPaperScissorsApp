CLIENTDRIVER README

MADE BY:
--------------------------
Ben Talbot (000791397)
Feb 7, 2020
---------------------------


---------------------------
*******CONTENTS********
---------------------------
-INSTALLATION
-INSTRUCTIONS
-DEVELOPER NOTES

---------------------------
*****INSTALLATION******
---------------------------
Extract ClientDriver.jar file if currently zipped.
Open a Command Prompt window and type:

java -jar */filepath/*

The easiest way to do this is to drag the file into the program.
The filepath will automatically be pasted.
Press enter to launch the client.


---------------------------
*****INSTRUCTIONS*****
---------------------------
1.) *LOGIN*
On the login screen, the user is prompted to enter the an IP Address and username.
The IP Address is the IP of the host machine (the machine running the server application).
The username can be whatever the user desires.

2.) *MATCHMAKING*
Once credentials are entered, a chat window will appear.
The window will say it is finding an opponent if a connection has not yet been made.
Once two clients have been connected, the window will show the game interface.

3.) *PLAYING*
Users can make a selection by pressing the rock, paper, or scissors buttons and finalize their
selection by pressing the "battle" button.

This will send the selection to the opponent client and if they other player is ready, the game's results 
will be displayed on both clients.

4.) *POST GAME*
Once the game is finished, both players will have the options to quit or play again. 
If both players press the play again button, the game screen will appear again and another game can be played.
If one player decides to quit, the other player will wait for another opponent to connect.
If both players quit, the connection to the server is dropped and both clients will close. 


---------------------------
***DEVELOPER NOTES***
---------------------------
DO NOT exit the client GUI by pressing the 'X' button on the top right of the window.
The program will not exit correctly this way and will prevent the connection of potential clients.
