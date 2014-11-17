CS414.A5.RJH2H

Parking Garage Implementation


1) lists the external .jar file dependencies, if any, (database, user interface, logging, etc.), 

2) shows how to run from command line, 


3) gives user names and passwords if necessary, and 


4) discusses what is especially strong, weak, or missing from the assignment, 



5) what patterns and refactorings were used.


	There was a major refactoring to client / server via Java RMI.  The ParkingGarage class became the server for the system and was refactored into the .server package.  There are two different clients to the server, the entry and the exit.  Each of these are distributed to be able to run on different machines and to have multiple instances.  

	Deodorizing Bad Smells:  I reviewed the code in light of the Fowler list of bad smells and refactored several pieces.  



	Observer Pattern:  I initially used the observer pattern in A4, but implemented it incorrectly.  Rather than having the observer query the subject for the new state after being notified, I was having the subject send a message through the update method.  This caused the observer and subject to be more tightly coupled, which is not the goal of the observer pattern.  This was refactored in A5 to be implemented correctly.  

	Facade pattern:  

  


	Strategy Pattern:  
