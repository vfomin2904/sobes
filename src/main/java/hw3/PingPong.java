package hw3;

public class PingPong {

    private boolean ping = true;

    public synchronized void ping() throws InterruptedException {
        while (!ping) {
          wait();
        }
        System.out.println("ping");
        ping = false;
        notifyAll();
    }

    public synchronized void pong() throws InterruptedException{
        while (ping) {
            wait();
        }
        System.out.println("pong");
        ping = true;
        notifyAll();
    }

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();

        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    pingPong.ping();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    pingPong.pong();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
