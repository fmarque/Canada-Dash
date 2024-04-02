
# CanadaDash
The software is considered a platformer game with an educational purpose, trying to teach the youngsters the Geography of Canada. This goal is fullfiled through letting the user play a game.
In CanadaDash, the player will gain score if they asnwer correctly to the pop-up quizzes in the game. Collecting these answers would be our scoring system, so the user can recognize how many more scores they need to unlockc the other provinces. 



## Libraries & Third Parties

This includes:

1- JDK      (version 21)

2- GSON     (version 2.8.9)

## JSON Installation Guide
Here's a step-by-step guide on how to set up JSON support in IntelliJ IDEA:

* Open IntelliJ IDEA: Launch IntelliJ IDEA on your system.

* Create or Open a Project: You can create a new project or open an existing one where you have JSON files.

* Navigate to Settings/Preferences: Go to File > Settings on Windows/Linux or IntelliJ IDEA > Preferences on macOS.

* Install Plugins (Optional): If you want to enhance JSON support, you can install plugins. To do this, navigate to Plugins in the settings/preferences window.

* Search for JSON Plugins: In the Plugins window, type "JSON" in the search box to find available JSON-related plugins. Some popular plugins include "JSON Formatter" or "GsonFormat".

* Install Plugin: Once you find the plugin you want, click on the "Install" button next to it. Follow the prompts to complete the installation.

* Restart IntelliJ IDEA: After installing the plugin, IntelliJ IDEA might prompt you to restart the IDE. If not, it's a good idea to restart it manually to ensure the changes take effect.

* Open or Create a JSON File: You can now open an existing JSON file or create a new one in your project.

* Start Editing: IntelliJ IDEA should now provide syntax highlighting, code completion, and other helpful features for editing JSON files.
## How to Run Software?

The project can simply start working, by only running the Main.java class.
From there the first page of the game, LogIn will be represented.
## Switch User Accounts
 * User switches can be accessed through the blue text in the login page, allowing for ease of navigation.

## Username and Password
For the first state of the game, the required Username and Password are stored as:

  Player mode:
   - username : fran
   - password : marq


   Instructor mode:
   - username : poppp    
   - password : dsds
   

  Developer mode:
   - password : FAAJS   
## How it Works?

* LogIn page: 
     - This would be the first page that is shown when running Main(). The user is able to choose between a player, instructor, or developer login and sign up. Players will have class codes associated with an instructor. The class code is generated when the instructor signs up. 
There is no developer sign-up as there is only one access code.

* MainMenu page includes: 
    - Load Saved Game button, which pushing it will lead the user to where they left the game.
    - Start New Game button, which leads the game into a new game page, where the user is at its very beginning.
    - How to Play button, a written and visualized tutorial for how to play the game, what the icons within the game mean, and the overall controls and mechanics.
    - View Highscores button, displays the highscores of all players.
    - Exit Game button, brings user back to the player login page as default.    
    
* Details about GUI:
    - The GUI is implemented using JavaFx libraries. FXML files are utilized and made functional with Java controllers which contain methods to switch, update, and store necessary values and scenes.

