package com.example.messagingservicebus;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;
import org.springframework.messaging.support.MessageBuilder;

@Component
public class Producer {

    private final ServiceBusTemplate serviceBusTemplate;

    public Producer(ServiceBusTemplate serviceBusTemplate) {
        this.serviceBusTemplate = serviceBusTemplate;
    }

    public void run()  {
        for (int i = 0; i < 10; i++) {
            System.out.println("Sending message..."+i);
            String responseString = "test "+i;
            Message<String> responseMessage = MessageBuilder.withPayload(responseString)
                    .build();
            if (i % 2 == 0) {
                serviceBusTemplate.send(MessagingServicebusApplication.queueName2, responseMessage);
            } else {
                serviceBusTemplate.send(MessagingServicebusApplication.queueName1, responseMessage);
            }
        }
    }
}
