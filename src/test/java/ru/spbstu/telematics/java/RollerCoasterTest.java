package ru.spbstu.telematics.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.*;

public class RollerCoasterTest {
    @Test
    public void testRunningCart() throws InterruptedException {
        BlockingQueue<Passenger> platformQueue = new LinkedBlockingQueue<>();
        Semaphore cartSemaphore = new Semaphore(0);
        int cartCapacity = 5;

        Turnstile turnstile = new Turnstile(platformQueue);
        Cart cart = new Cart(cartSemaphore, cartCapacity);
        Controller controller = new Controller(platformQueue, cartSemaphore, cartCapacity);

        Thread turnstileThread = new Thread(turnstile);
        Thread controllerThread = new Thread(controller);
        Thread cartThread = new Thread(cart);

        turnstileThread.start();
        controllerThread.start();
        cartThread.start();

        Thread.sleep(5000);

        assertFalse(cartSemaphore.tryAcquire(), "Тележка не должна отправляться без загрузки.");

        turnstileThread.interrupt();
        controllerThread.interrupt();
        cartThread.interrupt();
    }

}