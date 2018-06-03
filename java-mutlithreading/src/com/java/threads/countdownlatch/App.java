package com.java.threads.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * CountDownLatch can be used to make sure that all the threads are finished their task by keeping the count
 * 
 * 
 * {@code latch.await() }
 * Once the count is come down to zero then program will end
 * 
 * 
 * @author Nagaraja R
 *
 */
class Processor implements Runnable {

	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		System.out.println("Started");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();

	}

}

public class App {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(3);

		CountDownLatch latch = new CountDownLatch(3);

		//make sure you are creating atleast three threads otherwise programme will never end
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// This code will execute only when the count come down to zero
		// If you set i<2 then the program will never end as the count will not come down due to two threads
		System.out.println("tasks completed");

	}

}
