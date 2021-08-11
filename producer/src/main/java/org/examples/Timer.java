/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.examples;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.BytesMessage;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

@Singleton
public class Timer {

  @Resource(name = "pulsar-javax.jms.ConnectionFactory")
  ConnectionFactory factory;

  @Resource(name = "pulsar-javax.jms.Queue")
  private Queue queue;

  private AtomicInteger counter = new AtomicInteger();

  @Schedule(
          hour = "*",
          minute = "*",
          second = "*/2",
          info = "Every 2 second timer",
          timezone = "UTC",
          persistent = false
  )
  public void send() {
    try (JMSContext context = factory.createContext()) {
      String text = "Message " + counter.incrementAndGet();
      BytesMessage message = context.createBytesMessage();
      message.writeUTF(text);
      JMSProducer send = context.createProducer().send(queue, message);
      System.out.println("Message '" + text + "' sent");
    } catch (Throwable ex) {
      ex.printStackTrace(System.out);
    }
  }
}
