# Matching Game Android Mobile Application

_*This app was built on Android Studio, the code was written in Java_  
_*The application was tested using Android Studio built-in emulator (Pixel 3a API 24)_  
_*Picasso 2.71828 (Third Party Library) was used to retrieve image from url and apply to ImageButton_  

## About the App

Matching Game is a simple android mobile application where the user can play three versions of memory matching game.  
Users can flip 2 or 3 cards (depending on which version they are playing) until they find all matches in a given board.  
When all matches are found, users will be prompted whether they want to play again.  

## Play on Android Studio Emulator

After cloning current repository, open the project in Android Studio.  
On the top right corner of Android Studio, choose the virtual machine you want to run as the emulator.  
Then click the green button to launch the app.  

## About the Interface.

Once the application is launched, the main interface will appear:

![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/main_screen.PNG?raw=true)

Users have three versions they can choose from:
1. "Start Game 5x4": 5x4 grid - match 10 sets of 2 images
2. "Start Game 6x5": 6x5 grid - match 15 sets of 2 images
3. "Start Match 3": 6x5 grid - match 10 sets of 3 images

Once users click on one of the three options, a board with cards will appear:
![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/5x4_nomatch.PNG?raw=true)

On the top, "Flips" counts the number of cards uers flipped.  
"Matches" keeps track of the number of pairs found by the user in current game.  
"Shuffle" button shuffles cards that are facing down (Matched cards will not be shuffled)  
  
The green rectangles represent cards users can flip.  
Users can click on the rectangle they want to flip to find matches.  
When users have some matches, the game board will look like this:
![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/5x4_somematch.PNG?raw=true)

Once all matches are found, a dialog will be prompted:  
![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/5x4_allmatch.PNG?raw=true)

Users can choose "Yes" to play the current version again,  
or choos "No" to admire all the matches that were found!  

## Different Versions

6x5 grid - match 15 sets of 2 images  
![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/6x5-2_somematch.PNG?raw=true)

6x5 grid - match 10 sets of 3 images  
![alt text](https://github.com/melongbob/MatchingGame/blob/master/matching_game_imgs/6x5-3_somematch.PNG?raw=true)
