# Tunetown

### Development
You should be able to open this project in Eclipse. Just select the root directory /TuneTown.

You'll probably also need to install the JavaFX Plugin for Eclipse. I followed the "Installing JavaFX in Eclipse" section from this tutorial: https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm

##### Problem: javafx is not accessible
Solution: Change access resitrictions
1. Go to the properties of your Java project, (i.e. by selecting "Properties" from the context menu of the project in the "Package Explorer".)
2. Go to "Java Build Path", tab "Libraries".
3. Expand the library entry
4. Select  
..."Access rules",  
..."Edit..." and  
..."Add..." a "Resolution: Accessible" with a corresponding rule pattern. In this case it is, "javafx/**"  


### Building and Running
Right click on the "pom.xml" file on the left then "Run As" > "1 Maven Build"

Right click on "TuneTown" folder on the left then "Run As" > "1 Java Application"

If theres a popup that asks you to select the java application then click "Main - application" and then "ok".
