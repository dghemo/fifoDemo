package sk.fifodemo;

import org.junit.jupiter.api.Test;
import sk.fifodemo.commands.ICommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConsumerTest {
    @Test
    void shouldCallExecuteMethodOfCommand() throws InterruptedException {
        BlockingQueue<ICommand> queue = new LinkedBlockingQueue<>();
        DataAccess database = mock(DataAccess.class);
        ICommand commandMock = mock(ICommand.class);
        queue.put(commandMock);

        Consumer consumer = new Consumer(queue, database);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        Thread.sleep(500); // Allow consumer thread to execute command
        consumerThread.interrupt(); // Interrupt consumer thread to finish the test

        verify(commandMock).execute(database);
    }
}