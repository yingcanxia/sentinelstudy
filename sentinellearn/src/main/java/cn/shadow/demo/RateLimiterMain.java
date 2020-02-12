package cn.shadow.demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterMain {
	RateLimiter limiter = RateLimiter.create(10); // qps每秒的查询书

	public static void main(String[] args) throws IOException {
		RateLimiterMain rateLimiterMain = new RateLimiterMain();
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				try {
					countDownLatch.await();
					Thread.sleep(random.nextInt(1000));// 交替进行
					rateLimiterMain.doTest();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}).start();
		}
		countDownLatch.countDown();
		System.in.read();
	}

	public void doTest() {
		if (limiter.tryAcquire()) { // 这里就是获得一个领跑true为获得令牌成功false为获取令牌失败
			System.out.println("允许进入访问");
		} else {
			System.err.println("不允许进入访问");
		}
	}
}
