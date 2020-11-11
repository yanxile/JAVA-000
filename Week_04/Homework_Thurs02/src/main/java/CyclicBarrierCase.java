import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierCase {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long start = System.currentTimeMillis();
        // 等待子线程中计算方法执行完后一起执行，所以总数为2
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        // 在这里创建一个线程或线程池，
        readNum readNum = new readNum(cyclicBarrier);
        Thread thread = new Thread(readNum);
        // 异步执行 下面方法
        thread.start();

        cyclicBarrier.await();
        int result = readNum.getNum(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    static class readNum implements Runnable {
        private volatile Integer num;
        private CyclicBarrier barrier;

        public readNum(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public Integer getNum() {
            return num;
        }

        @Override
        public void run() {
            this.num = sum();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
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
