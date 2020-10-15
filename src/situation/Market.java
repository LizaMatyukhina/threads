package situation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;


class Shop {
    Random random = new Random();

    Vector<Product> products = new Vector<>();

    List<String> names = Arrays.asList("Earrings", "Necklace", "Rings", "Bracelets");
    List<String> suppliers = Arrays.asList("First", "Second", "Third");

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (products.size() == 0) {
                    System.out.println("BASKET IS EMPTY!!");
                    wait();
                }

                Product find = products.get(random.nextInt(products.size()));
                products.remove(find);

                System.out.println("CONSUMER BOUGHT: " + find);

                notify();

                int sleep = random.nextInt(900) + 100;

                Thread.sleep(sleep);
            }
        }
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {

                while (products.size() == 5) {
                    System.out.println("BASKET IS FULL");
                    wait();
                }

                ProductBuilder builder = new ProductBuilderImpl();
                Product product = builder
                        .name(names.get(random.nextInt(names.size())))
                        .price(random.nextInt(900) + 1000)
                        .supplier(suppliers.get(random.nextInt(suppliers.size())))
                        .build();

                products.add(product);


                System.out.println("Product was developed: " + product);
                System.out.println("All products: " + products);

                notify();

                Thread.sleep(500);
            }
        }
    }
}