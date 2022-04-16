##ECE651 RISK PROJECT EVO3
###By Yuyin Li, Qishen Wang, Wenchao Guo
![pipeline](https://gitlab.oit.duke.edu/yl761/RISK-EVO3/badges/master/pipeline.svg)
![coverage](https://gitlab.oit.duke.edu/yl761/RISK-EVO3/badges/master/coverage.svg?job=test)
## Coverage
[Detailed coverage](https://yl761.pages.oit.duke.edu/risk-evo3/dashboard.html)

## UML

[UML](https://processon.com/diagraming/605e7d32f346fb6d9eeb4224)

## Features
1. Fog of War
2. Resilience + Persistence
3. Impressive UI(90%)

## How to use

###Resilience:
To use the database system:
1. Install postgres on your linux machine.
2. Create as user with name 'postgres' and password 'passw0rd'.
3. Create a database with name 'risc' by this user.
4. Edit "postgresql/version/main/pg_hba.conf". Change "local all postgres peer" to "local all postgres md5". Then restart the service.
5. Edit server/src/main/resources/Hibernate.cfg.xml.
If you want to reset the db to empty, replace 'update' with 'create' in <property name="hbm2ddl.auto">update</property>.

This process is complex. If you failed in any of these steps, Please contact us for help.

### Client:
1. Edit the ipAddr in the /client/src/main/java/edu/duke/group1/client/App.java to server's ip address. The default value is "localhost".
2. cd client
3. sudo ./gradlew -PmainClass=edu.duke.group1.client.App.java run

### Server:
1. cd server 
2. sudo ./gradlew -PmainClass=edu.duke.group1.server.App.java run

### User input:
1. Type in your account and password, hit register, then hit login.
2. You can create a new game, waiting for players to join, or entering a existing game.
3. Click the action you want to perform, select the details, and submit your action. All actions in this round is recorded below.
4. You can go to other rooms any time you want by clicking switch room.


