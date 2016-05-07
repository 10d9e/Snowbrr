# Snowbrr
## Uber for Snow Removal
* CSCIE-56 Final Project
* Jay Logelin
* HUID 70970775

# Introduction 
Snowbrr is a specialized commerce platform for finding and procuring snow removal vendors. In northern climates, snow removal is big business. However the business model still exists in the stone age - usually involving a door to door marketing campaign where vendors lock in customers at a fixed price for an entire season. Customers do not have a centralized place with which to interact with vendors and therefore it is very difficult for both the provider and customer to connect. "Snowbrr" would address these issues by offering both a provider and customer interface. On the provider side, Snowbrr offers offer a set of screens that allows them to set up a profile with customer reviews/comments, photos of equipment and recent jobs finished. On the client side, Snowbrr has a customer profile page with provider reviews/comments, a utility to input driveway dimensions, and list/map view of active snow removal provider from which to choose. Snowbrr uses an MVC model and factors out all of the domain CRUD operations into a set of Gails Services in order to have a single API for the web app and be architecturally ready for a future mobile app.

# Quick Start

The application has been designed to have 2 roles associated with it, giving each role a different experience. For example Customers can only rate Providers and vice-versa. So to get up and running, I have created a 'CUSTOMER_ROLE' user 'jay' with password 'jay' and a 'PROVIDER_ROLE' user 'maryse' with password 'maryse'.  You will have to use different http sessions when testing these users out in tandem, so I would recommend using your regular browser for one user and 'In Cognito' for another. ( or, simply, another browser altogther).

You can start by logging into the application as a Provider using 'maryse/maryse'.  Navigate to the Transactions tab to get things going.

Now, in other browser, or In Cognito, log into the Customer side using 'jay/jay' and nagivate to the 'Providers' area from the main menu.  You will be presented with a map and list view of locally available providers. To begin a Transaction you can scroll down to the bottom of the page and click the 'Request' button for user 'maryse'. Once you initiate the transaction you should note that both users' Messages and Transactions tabs have been updated to indicate that the transaction has started.

On the Provider side, navigate to the Transaction and click 'Accept Job' to initiate the transaction. As before, you will notice that the Messages and Transactions areas are updated to reflect the changing state of the transaction. Finally clicking on 'Complete Job' will place the transaction into a finished state, and update the user.

On both the Provider and Customer side, you can also Rate customers/providers by navigating to their respective profiles and clicking on the rate button.

It is also note-worthy to view the Provider Profile review tab, where all of the star-ratings, customer comments and rating statistics are displayed for the Provider ( from user 'jay' find and view provider 'maryse' to see a populated example )

Primary Development Artifacts

# Domain model
* Consumer : the customer class which contains a User object for authentication/authorization, owns one Driveway, and can have many ConsumerReviews associated with it.
* ConsumerReview : The ConsumerReview object is created by a Provider reviewer and owned by a Consumer. There is a content field for the text part of the review and a rating field that is a range between 0 and five.
* Driveway : Encapsulates a driveway with a length and width
* Message : A simple message object for sending messages between different User ( Customer, Provider and System) Inboxes. 
* Provider : A Provider is a person or company that provides a snowblowing service. Providers can set a blanket initialPrice for the start of a transaction, and have zero to many ProviderReviews associated.
* ProviderReview : A ProviderReview is created by a Consumer reviewer and owned by a Provider. There is a content field for the text part of the review and a rating field that is a range between 0 and five.
* Transaction : A transaction is the primary artifact used for workflow between Customer and Provider.  Transactions are initiated by Providers and the state of the transaction is updated through the lifecycle of the snow removal activity. Transactions contain both one Provider and one Consumer, include several transaction states ( 'Request', 'Cancelled', 'Price Change', 'In Progress', 'Complete' ), have an optional field for photo upload of the completed job, and contain notes for both parties to fill.
* User/Role/UserRole : The User object is responsible for connecting to the Spring Security Plugin, offering all users authentication and authorization services to different parts of the application.  Fields include email, first and last name, address, phone number, coordinates and an optional avatar image.

# Controllers
* ConsumerController :

| Method        | Security Role                 |
| ------------- | ----------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| map           | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| save          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_ADMIN  |

* ConsumerReviewController :

| Method        | Security Role              |
| ------------- | -------------------------- |
| index         | ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_CUSTOMER, ROLE_ADMIN  |
| save          | ROLE_CUSTOMER, ROLE_ADMIN  |
| edit          | ROLE_CUSTOMER, ROLE_ADMIN  |
| update        | ROLE_CUSTOMER, ROLE_ADMIN  |
| delete        | ROLE_ADMIN                 |

* MessageController :

| Method        | Security Role                             |
| ------------- | ----------------------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| save          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |

* MessageController :

| Method        | Security Role                             |
| ------------- | ----------------------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| save          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |

* ProviderController :

| Method        | Security Role                             |
| ------------- | ----------------------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_PROVIDER, ROLE_ADMIN  |
| save          | ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_ADMIN  |

* ProviderReviewController :

| Method        | Security Role                             |
| ------------- | ----------------------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| create        | ROLE_PROVIDER, ROLE_ADMIN  |
| save          | ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_ADMIN  |

* TransactionController :

| Method        | Security Role                             |
| ------------- | ----------------------------------------- |
| index         | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| show          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| consumerRequest | ROLE_CONSUMER |
| consumerCancel | ROLE_CONSUMER |
| providerAccept | ROLE_PROVIDER |
| providerCancel | ROLE_PROVIDER |
| providerComplete | ROLE_PROVIDER |
| uploadImage | ROLE_PROVIDER |
| proofImage | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN |
| create        | ROLE_CUSTOMER  |
| save          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| edit          | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| update        | ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN  |
| delete        | ROLE_ADMIN  |

# Views
Each view has a correlated standard GSP associated with it ( index, show, create, save, edit, update, delete ), along with the following additions :

### View Templates
* Provider Request Dialog Template: 'provider/_providerRequestDialog.gsp' is a template used to encapsulate a Provider Request button, popup dialog and url submit to initiate a Transaction
* Stats Template : 'shared/_stats.gsp' is a template used to render Ratings Statistics for Providers and Consumers.
* Star Ratings : 'shared/_starRatings.gsp' is a templated used to render and edit a star rating widget for consumer and provider reviews
* Review Template : 'shared/_review.gsp' is a template used to render a complete listing of the reviews and ratings associated with a Provider or Consumer

### Image Upload/Render/Preview
* 'transaction/show.gsp' contains an 'g:uploadForm' tag used to upload a file object and works with the controller methods 'uploadImage' and 'proofImage' along with the lightbox jquery plugin to create a nice way to load an image.

#Services

## Message Service
The MessageService object is responsible for sending messages between Providers, Consumers and the system itself. For example during a Transaction each participant is sent a message during every state change or update.  Messages can also be sent between Users and or Providers using the standard forms.

## ProviderService
The ProviderService is responsible for producing a list of easy to consume 'ProviderInfo' data objects. It loops through each Provider and creates a list of ProviderInfo objects for the Provider views.

## StatisticsService
The StatisticsService collects together all of the star ratings for a Provider or Consumer into a list and calculates the averate rating in the form of a 'RatingsStatistics' Value Object.

#TagLibs

## StatisticsTagLib
The StatisticsTagLib uses the aforementioned Statistics Service to render star widgets, averageRating, and star rating counts and percentages.

usage:
<stats:renderStars target="${consumerOrProvider}"/>
<stats:averageRating target="${consumerOrProvider}"/>

##TransactionLabelTagLib
The TransactionLabelTagLib is responsible for producing a stylized text label based on a Transaction's current status.

sage:
<trans:label transaction="${transactionInstance}"/>

#Third Party Dependencies
* spring-security-core:2.0.0
* build-test-data:2.4.0
* scaffolding:2.1.2
* hibernate4:4.3.10
* lightbox (http://lokeshdhakar.com/projects/lightbox2/)
