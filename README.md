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

## About the Implementation

The application consists of four activities; one main activity, and three game activities.  
There are also two classes (AsyncComplex, Card) and one interface (TaskCompleted) that assist the application to function properly.  

__main activity__: contains three buttons that starts different versions of memory games respectively.  

__game activities__: retrieves image urls from the shopify API provided. The number of images to be retrieved is specified by NUM_IMGS variable. Each button in the layout is associated with a Card object. Flipped cards are temporarily stored in a variable, so they can be checked when all cards are flipped.  
When a match is found, the matched cards are stored in a set data structure. The matched cards in the set cannot be flipped again, and when the size of the set reaches the number of available cards, the game finishes.  

__Card class__: a class created to represent the card objects. In the future versions, if the game becomes more complex, the Card objects can be designed to hold more information.  

__AsyncComplex class__: the class is used for retrieving JSON object from the shopify API call.  

__TaskCompleted interface__: the interface was implemented so the game board can be initialized after necessary images are retrieved from the async API call.  
