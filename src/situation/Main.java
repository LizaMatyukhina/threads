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
                    System.out.println("interrupted");
                }
            }
        };

        Thread thread1 = new Thread(task);
        System.out.println(thread1.getState());

        thread1.start();
        System.out.println(thread1.getState());

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());

        synchronized (lock) {
            lock.notify();
        }
        System.out.println(thread1.getState());

        thread1.stop();
        System.out.println(thread1.getState());
    }
}