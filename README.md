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

  
