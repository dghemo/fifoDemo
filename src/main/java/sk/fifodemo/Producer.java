package sk.fifodemo;

import sk.fifodemo.commands.ICommand;

import java.util.concurrent.BlockingQueue;

/**
 * The Producer class is responsible for producing commands and placing them into a blocking queue.
 * This class implements the Runnable interface, allowing it to be run in a separate thread. However in this case it is producing from main method of App class
 */
public class Producer implements Runnable {
    private final BlockingQueue<ICommand> queue;

    public Producer(BlockingQueue<ICommand> queue) {
        this.queue = queue;
    }

    public void produce(ICommand command) throws InterruptedException {
        queue.put(command);
    }

    @Override
    public void run() {
        try {} catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
