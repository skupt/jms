# JGMP 2022. Java Messaging. Task 10. JMS

# How To Build Project

To run project you should run Apache ActiveMQ preliminary. Download it from https://activemq.apache.org/   
Unpack it in some folder. Run it: `...\apache-activemq\bin\activemq.bat start`
Admin page of ActiveMQ here: http://localhost:8161/admin/
User & Password: admin & admin.

Then, clean project's build folder:

+ `.../jms>mvn clean`

After, build projects, use following maven commands (from the root folder of each module):

+ `.../jms/app1>mvn assembly:assembly`
+ `.../jms/app2>mvn assembly:assembly`
+ `.../jms/app3>mvn assembly:assembly`

Finally, Run each application separately:

+ `.../jms/app1/target/java -jar app1-1.0-jar-with-dependencies.jar`
+ `.../jms/app2/target/java -jar app2-1.0-jar-with-dependencies.jar`
+ `.../jms/app3/target/java -jar app3-1.0-jar-with-dependencies.jar`

Console input is in app1.

# Task description

Create demo applications that implement the following order processing flow. (3 points)

The first application read the following order details from the console and send them to the order queue (orders):

+ A user who makes the order;
+ Type of goods for the order – liquids or countable item;
+ The volume of order for liquids;
+ Number of items for countable items;
+ Order total.

The second application should process orders with the following rules:

+ If the order total greater than some threshold – the order should be rejected;
+ If already ordered more than N liters – the order should be rejected;
+ Summary information for accepted and rejected logs should be passed to other queues or topics.

Third application to log summary about accepted and rejected orders into some file.

Tasks to be implemented:

+ Implement the full flow described above.
+ Use message selectors to split orders for liquids and countable items.
+ Use topics to implement message exchange.
+ Show transactions in message processing.
+ Show usage of durable topics – extra 2 points.