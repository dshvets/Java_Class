

Pathogen Dynamic Graph
===================

**Mongo Database set up**
-------------
 - Check if you have mongo set up by typing "mongo" in the command line
 - You will need 2 json files to set up the two necessary collections within mongo
 - Create a database named "test". The two collections within "test" are "pathogens" (lowercase first letter) and "Accessions" (uppercase first letter).  Create the two collections using the json files that will be provided. (pathogens.json is available on github but Accessions.json is too large and is stored elsewhere).

**Running the server**
-------------
- Clone the project from github and checkout the appropriate branch. Typically the most recent working version will be in the "wip" (work in progress) branch.
- To work on the project, you can either open the entire project in an IDE such as Eclipse or open it in something like Atom.

> **Details:**

> - If you are working in Atom, then on the command line go into the directory where you have cloned the project. Any time that you want to start the server type: `mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=3000'`. Then go into a web browser and type localhost:3000 and you should see the project.

> - If you are working in Eclipse, open Eclipse with a working space folder that is different from the one in which you have cloned the project. Then go to new project and enter the location of the BSVE project. Once the project is loaded into Eclipse do the following command to load all of the dependencies: "Configure" => "Convert to Maven Project". Next to the green "Run" button click the arrow drop down menu  and choose "Run As Maven Build". For "goal" write: `spring-boot:run -Drun.jvmArguments='-Dserver.port=3000'`

**Project Organization**
-------------
* *Front end*
  * most code is in the `src/main/resources/static` directory
  * index.html and functions.js

* *Back end*
  * written in Java, found in `src/main/java/pull` directory
  * RestfulResponse.java contains controllers which receive post/get requests from the client side
  * Updater.java
  
* *Saving custom data to database*
  * Updater.java -- addPathogen class adds new user uploaded entries such as ad hoc or custom to the database (custom being a list of NCBI accessions, ad hoc being user uploaded popset data including disease, organism, fasta, etc)

* *Pulling data from NCBI*
  * info info

* *Visualization stuff*
  * info info
