import java.util.concurrent.CountDownLatch;

public class CountDownLatchCase {

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 在这里创建一个线程或线程池，
        readNum readNum = new readNum(countDownLatch);
        Thread thread = new Thread(readNum);
        // 异步执行 下面方法
        thread.start();

        countDownLatch.await();
        int result = readNum.getNum(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    static class readNum  implements Runnable{
        private volatile Integer num;
        private CountDownLatch latch;

        public readNum(CountDownLatch latch){
            this.latch = latch;
        }
        public Integer getNum() {
            return num;
        }

        @Override
        public void run() {
            this.num = sum();
            latch.countDown();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
