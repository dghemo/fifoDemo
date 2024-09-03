package sk.fifodemo;

import sk.fifodemo.commands.ICommand;

import java.util.concurrent.BlockingQueue;

/**
 * The Consumer class is responsible for consuming commands from a blocking queue
 * and executing them using a provided DataAccess object.
 * This class implements the Runnable interface, allowing it to be run in a separate thread.
 * It continuously takes commands from the queue and executes them until interrupted.
 */
public class Consumer implements Runnable {
    private final BlockingQueue<ICommand > queue;
    private final DataAccess database;

    public Consumer(BlockingQueue<ICommand> queue, DataAccess database) {
        this.queue = queue;
        this.database = database;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ICommand command = queue.take();
                command.execute(database);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
