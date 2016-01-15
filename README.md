# android-rest-api-client

A simple android app which consumes a RESTful web service.

** ScreenShot of the Result:

Click here to See [Success Message](/docs/images/android-result.png) and [Error Message](/docs/images/android-error.png)


In this project, I didn't use any 3rd part library for consuming restful web service like RETROFIT, nor did I use any build tools like Maven or Gradle here. There are many 3rd party libraries which make our life easy with little bit effort at the beginning. 
But raw solution always good for understanding.
 
## How will you run this project in eclipse IDE:

1. Clone the project:

  $ git clone https://github.com/azizurice/android-rest-api-client.git
2. Open your Eclipse IDE with your preferred workspace

3. Import the project using the work-flow:

	* File/Import/Existing Android Code Into Workspace/Browse and Select the root directory of the clone project
	* Check Mark  on  Copy projects into workspace
	* Finish

4. we need android-support-v7-appcompat project into our current workspace. So let's add it.
  The work-flow is:
  * File/Import/Existing Android Code Into Workspace/Browse and Select appcompat as root directory:
  * In my computer, the appcompat directory existed in the following directory. Your one can be at different location.
  *	E/Program Files/AndroidSDK/extras/android/support/v7/appcompat
  * Do Check  on  Copy projects into workspace
  * Finish

5. Select project/properties/android/add.. [from library section]/appcompat

6. Project clean and run as 'Android Application'

#### Helpline
Hope you will successfully run the project and see the result. In case if you face any difficultly to run
the project in Eclipse IDE, feel free to contact me at azizur.ice@gmail.com. Any feedback would be greatly appreciated.


