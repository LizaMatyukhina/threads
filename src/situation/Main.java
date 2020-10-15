package situation;

public class Main {

    public static void main(String[] args) {

        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                try {
                    lock.wait();
                }
                catch (InterruptedException e) {
                    System.out.println("INTERRUPTED");
                }
            }
        };

        Thread thread = new Thread(task);
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());

        synchronized (lock) {
            lock.notify();
        }
        System.out.println(thread.getState());

        thread.stop();
        System.out.println(thread.getState());
    }
}