Lab 2-2: GC-Content With Attitude

Team Members:
Matthew Tong
Kyle Lozier
Ryan Quinlan

Directions:
   Navigate to .JAR file:
      Use 'cd' to change directories in a terminal
      Use 'ls' to see which files are in that directory
      
   Run 'java -jar GCContent.jar'
      JAVA 1.8 is required to run this.
   
   In Application:
      Use the file explorer to select your CORRECT test file
      Set the window size and step size
      Hit submit
      A CSV file will be generated with your expected results and also the results will be
        displayed in the application's console
        
        

========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "GCContent.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.
