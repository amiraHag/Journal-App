# Journal-App


Project for Google Udacity Scholarship Challenge

This is journal application where in users can pen down their thoughts and feelings. 

Main Features:
* Register and Login using google authentication.
* View all entries of the diary.
* View the contents of a diary entry.
*  Add, modify and delete an entry.
* Profile page for the user.
Screenshots:



Pre-requisites
* Android SDK 26
* Android Support Repository
* Firebase Project (for authentication and realtime database).

Getting Started
Authentication:
This app uses firebase Google authentication to allow user to Sign in to the app.

Data Model:
This app demonstrates a simple data model for a blog application. 
The database has one "root" node:
Posts contain three properties, which are ID (unique value), title and description.


Database Rules:
Below are the rule that limit access to authenticated user:

// Posts can be read and written by logged-in users.
    {
  "rules": {
    ".read": "auth != null",
    ".write": "auth != null"
  }
}
    

To know more about Firebase visit https://firebase.google.com/

Installation:
Download the project.
Add Firebase to your Android Project.
Log in to the Firebase Console.
Go to Auth tab and enable Google authentication.
Run the sample on Android device or emulator.

Or
Download the apk from Journal-App/apk/debug/app-debug.apk


Support
You can communicate directly with the author via email: 
Miro.mustafa@gmail.com or Amira_hagag@feng.bu.edu.eg

Or use the following resources to get support:
* Stack Overflow
* Firebase Support


If you've found an error in this sample, please file an issue.
Patches are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

License
This project is licensed under the MIT License - see the LICENSE.md file for details


