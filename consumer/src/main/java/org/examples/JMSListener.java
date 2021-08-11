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
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
  activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "the-queue")
  }
)
public class JMSListener implements MessageListener {

  @Override
  public void onMessage(final Message message) {
    try {
      if (message instanceof BytesMessage) {
        byte[] contents = message.getBody(byte[].class);
        System.out.println("Received (binary) message '" + new String(contents, StandardCharsets.UTF_8) + "' from destination " + message.getJMSDestination());
      } else {
        String contents = message.getBody(String.class);
        System.out.println("Received (string) message '" + contents + "' from destination " + message.getJMSDestination());
      }
    } catch (final Throwable e) {
      e.printStackTrace(System.out);
    }
  }
}
