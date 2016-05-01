# Snowbrr
## Uber for Snow Removal
* CSCIE-56 Final Project
* Jay Logelin
* HUID 70970775
 
Snowbrr is a specialized commerce platform for finding and procuring snow removal vendors. In northern climates, snow removal is big business. However the business model still exists in the stone age - usually involving a door to door marketing campaign where vendors lock in customers at a fixed price for an entire season. Customers do not have a centralized place with which to interact with vendors and therefore it is very difficult for both the provider and customer to connect. "Snowbrr" would address these issues by offering both a provider and customer interface. On the provider side, Snowbrr offers offer a set of screens that allows them to set up a profile with customer reviews/comments, photos of equipment and recent jobs finished. On the client side, Snowbrr has a customer profile page with provider reviews/comments, a utility to input driveway dimensions, and list/map view of active snow removal provider from which to choose. Snowbrr uses an MVC model and factors out all of the domain CRUD operations into a set of Gails Services in order to have a single API for the web app and be architecturally ready for a future mobile app.

### Quick Start

The application has been designed to have 2 roles associated with it, giving each role a different experience. For example Customers can only rate Providers and vice-versa. So to get up and running, I have created a 'CUSTOMER_ROLE' user 'jay' with password 'jay' and a 'PROVIDER_ROLE' user 'maryse' with password 'maryse'.  You will have to use different http sessions when testing these users out in tandem, so I would recommend using your regular browser for one user and 'In Cognito' for another. ( or, simply, another browser altogther).

You can start by logging into the application as a Provider using 'maryse/maryse'.  Navigate to the Transactions tab to get things going.

Now, in other browser, or In Cognito, log into the Customer side using 'jay/jay' and nagivate to the 'Providers' area from the main menu.  You will be presented with a map and list view of locally available providers. To begin a Transaction you can scroll down to the bottom of the page and click the 'Request' button for user 'maryse'. Once you initiate the transaction you should note that both users' Messages and Transactions tabs have been updated to indicate that the transaction has started.

On the Provider side, navigate to the Transaction and click 'Accept Job' to initiate the transaction. As before, you will notice that the Messages and Transactions areas are updated to reflect the changing state of the transaction. Finally clicking on 'Complete Job' will place the transaction into a finished state, and update the user.

On both the Provider and Customer side, you can also Rate customers/providers by navigating to their respective profiles and clicking on the rate button.

It is also note-worthy to view the Provider Profile review tab, where all of the star-ratings, customer comments and rating statistics are displayed for the Provider ( from user 'jay' find and view provider 'maryse' to see a populated example )

###Third Party Dependencies
* spring-security-core:2.0.0
* build-test-data:2.4.0
* scaffolding:2.1.2
* hibernate4:4.3.10
