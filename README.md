# POC: Email Client

### 1. Class Diagram

![alt tag](https://raw.githubusercontent.com/leo-fcx/email-client-poc/master/deliverables/email-client-class-diagram.png)

### 2. On Design Patterns implementations

In this POC, following Design Patterns were implemented in order to address some problems:

##### Composite Pattern:   
`Condition` Interface and the classes that implement it are part of a "Composite" Design Pattern implementation. Note that `CompoundCondition` is compound of other two (2) Condition objects which could be either `SimpleCondition` or `CompoundCondition` objects.

Also, `Content` interface and the classes that implement it are part of a "Composite" Design Pattern implementation. Note that `FolderContent` is compound of other `Content` objects which could be either `EmailContent` or `FolderContent` objects.

##### Command Pattern:
`Email.check()` method checks whether the email received matches with the condition from the rule. If so, action is executed. That is the entry point where "Command" Design Pattern is being used by executing `action.apply()`
method.

Note also that `condition.evaluate()` is another entry point to use implementation of this pattern to evaluate specific condition.

   
##### Chain of Responsibility:   
`EmailRule.chain()` method chains all rules that are going to be evaluated for each received email.

That method is the place where "Chain Of Responsibility" Design Pattern is used. See the implementation for Rule Interface and EmailRule Class which implements it.

Currently, the order in chaining is being applied according how items/riles were added to rules list. Need to do some additional work to make it to consider some "priority" criteria.
 
### 3. On code execution and all deliverables

Following resources are available:
- Class Diagram (`email-client-class-diagram.png` file) which is show above, can be found in: `/deliverables` folder from the project.
- [_EmailClient.jar_](https://github.com/leo-fcx/email-client-poc/raw/master/deliverables/EmailClient.jar), which is located in `/deliverables` folder from the project, is going to run some Use Cases in order to show how rules are being evaluated on every new received email.
- _EmailClientRunner_ class, that is the entry point class when we want to run the App from our IDE in order to see same results as the JAR file.
- _EmailClientTest_ class. Alternatively, we can run this Test Class to run some Unit Tests that were created for the EmailClient Class.  
