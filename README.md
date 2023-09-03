# Zen Flow Yoga App

Zen Flow Yoga is a yoga app for Android Studio that provides users with a comprehensive and interactive platform to enhance their yoga practice. The app offers a range of features designed to assist users in learning and performing yoga poses, tracking progress, and maintaining a healthy lifestyle.

Key Features:
1. Pose Library
2. Pre-designed programs for different goals like flexibility, relaxation, strength, or stress relief.
3. Progress Tracking
4. Option to set reminders for scheduled practice sessions and mindfulness breaks throughout the day

## Installation Steps
* Clone the Repository

```bash
git clone git@github.com:ericazpcalaca/Yoga_App.git
```
* Open in Android Studio: "Open an existing Android Studio project" from the welcome screen.
* Configure Gradle: Check if the gradle is compileSdk 33 and targetSdk 33.
* Click on the "Build" or "Run" button in Android Studio. 
* Run on Emulator/Device: Nexus 5X API 33

## API
[Yoga API 🧘‍♀️](https://github.com/alexcumplido/yoga-api)



## Usage
### Register and Login
* The initial screen presented to the user will be the Login Activity. If the user possesses an account, they can log in using this screen; otherwise, they have the option to create an account by selecting "Don’t have an account? Sign up".
* The registration process requires an email and password. If the user forgets their password, they can initiate the password retrieval process by clicking on "Forgot Password?" By entering their email address, the user will receive an email containing instructions on how to reset their password.
* Firebase was utilized for the implementation of the login and registration functionalities

### Main Screen
The Main Activity will have a button in the corner that opens the menu. The menu presents diverse options including Home, Explore, Daily Progress, Profile, Share, and Logout. Additionally, at the top, you can view the email currently associated with your account.

#### Home Page
The Home Page provides you with several options, including accessing quick workout routines, exploring all the available poses within the app, and discovering specific workouts tailored for suggested target areas. Furthermore, it offers users the ability to send messages to the team, suggesting new features or providing feedback.

#### Explore
On the Explore Page, you will find three options: Trending, Created for You, and Getting Started.

Upon selecting the "All" button within the Trending section, you will be presented with a comprehensive list of categories. Each category will display the estimated time required for its workouts and the corresponding difficulty level. Upon choosing a category, you will be directed to the Selected Workout Screen, where you can delve into the chosen workout.

Under the "Created for You" section, a personalized workout will be generated specifically for the user. And on the "Getting Started" category, a variety of curated workout routines are available for users who are new to the platform or seeking to explore different exercise options.

#### Daily Progress
Daily Progress provides you with insights into your fitness journey. You can track the number of workouts you have completed, and the total exercise minutes accumulated using the app. Additionally, your current workout streak and your all-time best streak are prominently displayed. All this data is securely stored within the Realtime Database on the Firebase platform.

#### Profile
Profile Page will have access to the user data. When you click on the ‘Account Settings’ button, you can edit the information about the user. In cases where the users don’t use numbers, it will show an Error.
You can also set a Notification to show up daily for the user

#### Share
The user will be able to share the app with friends.

## Preview
|<img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/957f21ab-7405-4cae-a169-d09d17d77837" width="280" height="510"> |  <img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/2f383099-b245-49c9-a725-d60162f3499c" width="280" height="510"> | <img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/78630883-7a46-45f7-85bd-20bcc0179204" width="280" height="510">  |
|----------|----------|----------|
| <img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/719d5811-b20e-42f3-9625-6e2ce2b6e93e" width="280" height="510">  | <img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/132ca46d-be7b-4b4a-b09c-7ae53bc2d5a6" width="280" height="510">  | <img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/6e18fbac-9b2d-4cfc-8253-ce42cf656e39" width="280" height="510">  |
|<img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/fa80b22d-f561-4a0b-af13-2c417e46d060" width="280" height="510"> |<img src="https://github.com/ericazpcalaca/Yoga_App/assets/15451346/f684328c-ded9-475a-b520-31312ebb4593" width="280" height="510">  |  |

## Video
https://github.com/ericazpcalaca/Yoga_App/assets/15451346/e08baef2-c2a1-4ae1-bd7d-a290fcbe8e43

