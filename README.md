# Example about how to JMS to Connect to Apache Pulsar with Apache TomEE

This repository contains a simple example about how to create some simple applications
that uses the JMS to Connect to Apache Pulsar.

This example is using DataStax Fast JMS for Apache Pulsar JMS client
you can find it here

https://github.com/datastax/pulsar-jms

## Contents

You can see two directories:
- consumer: contains a MessadeDriver EJB that listens for messages on a Pulsar Queue
- producer: contains a Singleton EJB with a Timer that writes messages to a Pulsar Queue

The system.properties file is an example configuration for TomEE in order to deploy the DataStax Fast JMS for Pulsar Resource Adapter

## Setting up the demo

You need Maven, JDK8 and a Mac or Linux system.

Steps:
1. Build the sources using "mvn clean install"
2. Run the `download_tomee.sh" script to download Apache TomEE 8.0.6 and the Resource Adapter
3. Untar the TomEE distro twice, in tomee1 and tomee2
4. Copy system.properties inside tomee1/conf and tomee2/conf
5. Create tomee1/rars and tomee2/rars directories
6. Copy the Pulsar RAR file to both tomee1/rars and tomee2/rars
7. Edit tomee1/server.xml and change the 8080 port to 8081
8. Edit tomee2/server.xml and change the 8080 port to 8082 and the 8005 port to 8006
9. Copy consumer/target/consumer*war to tomee1/webapps
10. Copy producer/target/producer*war to tomee2/webapps
11. Start Pulsar Standalone
12. Start TomEE1 with tomee1/bin/catalina.sh start
13. Start TomEE2 with tomee2/bin/cataline.sh start
14. Open the logs of TomEE1 with tail -f tomee1/log/catalina.out
15. Open the logs of TomEE2 with tail -f tomee2/log/catalina.out
16. Enjoy !
