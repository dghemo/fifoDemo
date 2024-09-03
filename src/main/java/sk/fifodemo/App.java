package sk.fifodemo;


import sk.fifodemo.commands.AddCommand;
import sk.fifodemo.commands.DeleteAllCommand;
import sk.fifodemo.commands.ICommand;
import sk.fifodemo.commands.PrintAllCommand;
import sk.fifodemo.model.User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The App class is the entry point for the application.
 * It initializes necessary components like the command queue, data access, producer, and consumer.
 * It starts the consumer in a new thread and uses the producer to issue various commands.
 * The application waits for a while and then stops the consumer thread.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        //our fifo
        BlockingQueue<ICommand> queue = new ArrayBlockingQueue<>(100);

        //Database access
        DataAccess database = new DataAccess();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue, database);

        //start new thread with consumer
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // run commands with producer
        producer.produce(new AddCommand(new User(1, "a1", "Robert")));
        producer.produce(new AddCommand(new User(2, "a2", "Martin")));
        producer.produce(new PrintAllCommand());
        producer.produce(new DeleteAllCommand());
        producer.produce(new PrintAllCommand());

        // wait
        Thread.sleep(1000);

        // Stop the consumer thread
        consumerThread.interrupt();
    }
}
