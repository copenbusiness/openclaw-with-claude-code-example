/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakarta.tutorial.producer;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Queue;

/**
 * The QueueProducer class consists only of a main method, which sends several
 * messages to a queue.
 *
 * Run this program in conjunction with SynchConsumer or AsynchConsumer.
 * By default, the program sends one message. Specify a number as the first argument
 * to send that number of messages.
 */
public class QueueProducer {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MyQueue")
    private static Queue queue;

    /**
     * Main method.
     *
     * @param args optionally, the number of messages to send (default: 1)
     */
    public static void main(String[] args) {
        int numMsgs = 1;
        if (args.length > 1) {
            System.err.println("Usage: java QueueProducer [<number-of-messages>]");
            System.exit(1);
        }

        if (args.length == 1) {
            try {
                numMsgs = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument must be a valid number");
                System.exit(1);
            }
        }

        final int NUM_MSGS = numMsgs;
        Destination dest = queue;

        /*
         * Within a try-with-resources block, create context.
         * Create producer and message.
         * Send messages, varying text slightly.
         * Send end-of-messages message.
         */
        try (JMSContext context = connectionFactory.createContext();) {
            int count = 0;

            for (int i = 0; i < NUM_MSGS; i++) {
                String message = "This is message " + (i + 1) 
                        + " from producer";
                // Comment out the following line to send many messages
                System.out.println("Sending message: " + message);
                context.createProducer().send(dest, message);
                count += 1;
            }
            System.out.println("Text messages sent: " + count);
            
            /*
             * Send a non-text control message indicating end of
             * messages.
             */
            context.createProducer().send(dest, context.createMessage());
            // Uncomment the following line if you are sending many messages
            // to two synchronous consumers
            // context.createProducer().send(dest, context.createMessage());
        } catch (JMSRuntimeException e) {
            System.err.println("Exception occurred: " + e.toString());
            System.exit(1);
        }
        System.exit(0);
    }
}

