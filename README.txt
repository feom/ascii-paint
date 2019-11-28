
This file contains instructions and assumptions related to the ascii-paint homework.

Artifacts & Environment
==================================
- The homework is delivered using Gradle as build tool.
- Gradle wrapper "gradlew" is available in "./ascii-paint/" directory.
- The project comes built, but if desired the project could be build using the command "gradlew build"

Execution - How To Run
==================================
- The executable jar is located in:
                ./ascii-paint/build/libs/
- To run execute the following command:
                java -jar ascii-paint-1.0-SNAPSHOT.jar

Testing
==================================
- The application test suite class is "com.zuhlke.paint.ascii.AsciiPaintTestSuite".
- JUnit 5 and Jacoco was used for code coverage.
- Jacoco HTML code coverage report can be found here:
                ./ascii-paint/build/jacocoHtml/index.html
- Jacoco code coverage report can be generated with command:
                gradlew jacocoTestReport

Technical Assumptions
==================================
- It was assumed that for the purpose of this exercise no 3rd party libraries should be used.
- Thus only "plain" Java along with JUnit5 and Jacoco was used.
- Used Java Version: 11.0.2

Functional Assumptions
==================================
- Out of bound points are ignored: in case shape points are out of bounds of the canvas, only the visible points are drawn.
- Only vertical and horizontal lines are supported. Curves are not drawn - i.e. the input has no effect.
- Shapes can "overdraw" each other as in e.g. MS Paint - i.e. they have no slots to connect to each other as e.g. in MS Visio.
- The homework instruction uses positional, unnamed command arguments. Thus positional unnamed argument parsing is implemented.
- A future enhancement could be to use named options: e.g. instead of "C 10 10" -> "C -w 10 -h 10".
- A canvas needs to be explicitly created before a shape can be drawn.
- For given coordinate pairs "left" (x1,y1) & "right" (x2, y2), left must be smaller than right.
- For convenience, command codes ignore case -> e.g. c 10 10 is the same as C 10 10.
- The design should allow the future extension with e.g. new commands.


