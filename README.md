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
1. Build the sources
   ```
   mvn clean install
   ```
2. Run the `download_tomee.sh" script to download Apache TomEE 8.0.6 and the Resource Adapter
   ```
   ./download_tomee.sh
   ```
3. Untar the TomEE distro twice, in tomee1 and tomee2
   ```
   mkdir tomee1
   mkdir tomee2
   tar zxvf tomee.tar.gz -C tomee1 --strip-components 1
   tar zxvf tomee.tar.gz -C tomee2 --strip-components 1
   ```
4. Copy system.properties inside tomee1/conf and tomee2/conf
   ```
   cp system.properties tomee1/conf
   cp system.properties tomee2/conf
   ```
5. Create tomee1/rars and tomee2/rars directories
   ```
   mkdir tomee1/rars
   mkdir tomee2/rars
   ```
6. Copy the Pulsar RAR file to both tomee1/rars and tomee2/rars
   ```
   cp pulsarra.rar tomee1/rars
   cp pulsarra.rar tomee2/rars
   ```
7. Edit tomee1/conf/server.xml and change the 8080 port to 8081
8. Edit tomee2/conf/server.xml and change the 8080 port to 8082 and the 8005 port to 8006
9. Copy consumer/target/consumer*war to tomee1/webapps
   ```
   cp consumer/target/consumer*war tomee1/webapps
   ```
10. Copy producer/target/producer*war to tomee2/webapps
   ```
   cp producer/target/producer*war tomee2/webapps
   ```
11. Start Pulsar Standalone
12. Start TomEE1 and TomEE2
   ```
    tomee1/bin/catalina.sh start
    tomee2/bin/catalina.sh start
   ```
13. Open the logs of TomEE1 and TomEE2 
   ```
    tail -f tomee1/logs/catalina.out tomee2/logs/catalina.out
   ```
14. Enjoy !
15. Stop TomEE1 and TomEE2
   ```
    tomee1/bin/catalina.sh stop
    tomee2/bin/catalina.sh stop
   ```
