//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;


public class ConsumerProducer extends Thread {
    private List<Integer> listaValores = new ArrayList<>();
    private int tamMax;

    public ConsumerProducer(int tamMax){
        this.tamMax = tamMax;
    }

    public synchronized int get() throws InterruptedException {
        while(listaValores.isEmpty()){
            wait();
        }
        int primeiroValor = listaValores.get(0);
        listaValores.remove(0);
        notifyAll();
        return primeiroValor;
    }
    public synchronized void put(int valor) throws InterruptedException {
        while (listaValores.size() >= tamMax){
            wait();
        }
        listaValores.add(valor);
        notifyAll();
    }

    public static void main(String[] args) {

        ConsumerProducer consumerproducer = new ConsumerProducer(10);
        Thread producer = new Thread(() -> {
            try {
                for (int i=0; i < 1000; i++) {
                    consumerproducer.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    int value= consumerproducer.get();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

    }
}