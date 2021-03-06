CS414.A5.RJH2H

Richard Humphrey
November, 2014


Parking Garage Implementation


1) lists the external .jar file dependencies, if any, (database, user interface, logging, etc.), 

	No external dependencies. I didn?t use an external database.

2) shows how to run from command line, 

	I start the rmi registry from the server, so there?s no need to start it manually.

	First compile and run server in:
cs414.a5.rjh2h.server.GarageServer.java

	Then start one or more entry kiosk clients:
cs414.a5.rjh2h.entry.EntryKiosk.java

	Then start one or more exit kiosk clients:
cs414.a5.rjh2h.exit.ExitKiosk.java

3) gives user names and passwords if necessary, and 

	login to admin and usage data:
	username: test
	password: test


4) discusses what is especially strong, weak, or missing from the assignment, 

	Thanks to the use of GRASP principles, the refactoring and movement to a distributed architecture went more smoothly because the code was loosely coupled.  The time spend designing in A1-A3 created a highly cohesive system that was easier to adapt to change.

	Much of the complexity of my version of this assignment may have been unnecessary.  I put myself in the position of the client as well as the developer and increased the requirements beyond the scope of the project.  I wanted to create a system that if implemented in reality would be the least difficult for the driver who wants to park a car.  I implemented a system by which accounts can be tied to license plates that can be read by an automatic system.  It is possible for the driver to enter and leave without having to do anything.  Again, in retrospect, I probably over engineered it.  


	Java difficulties.  The most time consuming part of the project was figuring out Swing and how to lay out UI objects.  Figuring out 

	I would have liked to add more robustness to the RMI Client and Server connections.  The client should handle the server crashing or restarting.  The entry client would store any ticket data that was created while the server was down and then retransmit it when it recovers.  The exit would have a harder time without the server since it needs the server to authenticate credit cards and user account.  Again some of the lack of proper use of exceptions comes from my short experience in Java (A1 was my first program). 


5) what patterns and refactorings were used.

	RMI Refactoring:  There was a major refactoring to client / server via Java RMI.  The ParkingGarage class became the server for the system and was refactored into the .server package.  There are two different clients to the server, the EntryKiosk and the ExitKiosk.  Each of these are distributed to be able to run on different machines and to have multiple instances.  

	Observer Pattern:  I initially used the observer pattern in A4, but implemented it incorrectly.  Rather than having the observer query the subject for the new state after being notified, I was having the subject send a message through the update method.  This caused the observer and subject to be more tightly coupled, which is opposite the goal of the observer pattern.  This was refactored in A5 to be implemented correctly.  Also, since Java RMI requires the server to extend UnicastRemoteObject, I could no longer extend the Observable class.  Instead, I created a RemoteSubject interface and implemented the Observer pattern methods myself.

	Facade pattern:  
	All of the UI?s are implemented as the facade pattern.  Each UI has a controller that contains the logic.

	Strategy Pattern:  
                                  	I made use of the strategy pattern for taking different types of payments.  There are Credit Card Payments, Account Payments, and Cash Payments.  Each of these extend the abstract class Payment and implements it?s own initiatePayment() method. 
                                          
	MVC / Layered Architecture
The UI layer is completely separate from the controllers and application logic.


	Deodorizing Bad Smells:  I reviewed the A4 code in light of the Fowler list of bad smells and refactored several pieces.  CreditPayment class and CreditCard were merged into one class.  I tried to remove any redundancy and copy and pasted code.  Due to the haste needed to pull this project together on time, there may be some sweaty code gym socks in there (p-ew).









Commit Log:

Richs-Mac-Pro:cs414 richhumphrey$ git log --pretty=format:"%h - %an, %ar : %s"
93f7368 - Rich Humphrey, 12 minutes ago : Ready for Submission
16ecdbe - Rich Humphrey, 25 hours ago : Testing and Finishing Issues
9cebdba - Rich Humphrey, 2 days ago : Login and more tests
3bd98ef - Rich Humphrey, 2 days ago : Statistics
e5160f3 - Rich Humphrey, 2 days ago : Validated!
e767076 - Rich Humphrey, 3 days ago : Simulates one year of data
75ed71c - Rich Humphrey, 4 days ago : The Fun in Functionality
e3865ad - Rich Humphrey, 4 days ago : You shall not pass (until you pay)
bddc9b5 - Rich Humphrey, 4 days ago : You may enter
550c0b1 - Rich Humphrey, 5 days ago : Client side observer
70b73d4 - Rich Humphrey, 5 days ago : Still more RMI
e3a81e8 - Rich Humphrey, 6 days ago : RMI Oh My
7c3e2e2 - Rich Humphrey, 6 days ago : Login dialog, pre RMI commit
97ce2c8 - Rich Humphrey, 7 days ago : Readme, observer refactoring, rmi refactoring
9fdca59 - Rich Humphrey, 9 days ago : Making the server
1b995ce - Rich Humphrey, 10 days ago : Observer
6e8eda4 - Rich Humphrey, 12 days ago : Working
73df4cd - Rich Humphrey, 12 days ago : CS414A5


