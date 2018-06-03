package com.java.threads.producerconsumer;

import java.util.LinkedList;
import java.util.Random;
/**
 * 
 * Producer Consumer problem using wait and notify
 * 
 * @author Nagaraja R
 *
 */
public class Processor {

	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {

		int value = 0;
		while (true) {

			synchronized (lock) {

				while (list.size() == LIMIT) {
					lock.wait();
				}

				list.add(value++);
				lock.notify();
			}

		}

	}

	public void consume() throws InterruptedException {

		Random random = new Random();
		while (true) {
			synchronized (lock) {

				while (list.size() == 0) {
					lock.wait();
				}

				System.out.println("List Size is : " + list.size());
				int value = list.removeFirst();
				System.out.println("Value is : " + value);

				lock.notify();
			}

			Thread.sleep(random.nextInt(1000));

		}

	}

}
