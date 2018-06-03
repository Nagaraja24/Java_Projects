package com.java.threads.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * Producer consumer problem using BlockingQueue
 * 
 * @author Nagaraja R
 *
 */
public class App {

	// This is thread safe queue
	// Automatically handles underflow and overflow
	//If queue is full, it waits until the element is taken out
	//If queue is empty it waits till the data is added 
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			try {
				producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void producer() throws InterruptedException {

		Random random = new Random();

		while (true) {
			queue.put(random.nextInt(100));
		}

	}

	private static void consumer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			Thread.sleep(100);

			if (random.nextInt(10) == 0) {

				Integer value = queue.take();

				System.out.println("Taken value is : " + value + " and  Queue size is :" + queue.size());

			}
		}

	}

}
