package situation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new DemoThreadB());
        System.out.println(thread1.getState());

        thread1.start();
        System.out.println(thread1.getState());

        Thread thread2=  new Thread(new DemoThreadB());
        thread2.start();

        Thread.sleep(1000);
        System.out.println(thread2.getState());
        System.exit(0);

    }

    System.out.println()

    }

class DemoThreadB implements Runnable {
    @Override
    public void run() {
        commonResource();
    }

    public static synchronized void commonResource() {
        while(true) {

        }
    }
}