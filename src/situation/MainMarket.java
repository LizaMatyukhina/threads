package situation;

public class MainMarket {
    public static void main(String[] args) throws InterruptedException {
        Shop market = new Shop();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    market.produce();
                } catch (InterruptedException e) {
                    System.out.println("Thread PRODUCER has stopped!" );
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    market.consume();
                } catch (InterruptedException e) {
                    System.out.println("Thread CONSUMER has stopped!" );
                }

            }
        });

        long start = System.currentTimeMillis();
        long end = start + 10000;

        while (System.currentTimeMillis() < end) {
            if (!(producer.isAlive() && consumer.isAlive())) {
                producer.start();
                consumer.start();
            }
        }
        producer.interrupt();
        consumer.interrupt();
    }
}