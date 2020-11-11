import java.util.concurrent.Semaphore;

public class SemaphoreCase {

    public static volatile Integer sumResult = null;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 同步信号量，确保Worker中计算方法先执行，主线程才能获取执行
        Semaphore semaphore = new Semaphore(0);

        // 在这里创建一个线程或线程池，
        Worker worker = new Worker(semaphore);
        // 异步执行 下面方法
        worker.start();

        semaphore.acquire();
        int result = worker.getNum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    static class Worker extends Thread {
        private volatile Integer num;
        private Semaphore semaphore;

        public Worker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public Integer getNum() {
            return num;
        }

        @Override
        public void run() {
            this.num = sum();
            semaphore.release();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

}
