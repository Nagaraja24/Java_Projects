package com.java.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Here we make use of two different objects for locking 
 * 
 * Now two threads can parallel access the stageone and stage two
 * 
 * If you make methods as synchronized the intrinsic lock will happen on Worker object
 * and no two threads can access stageone and stagetwo parallel.
 * 
 * However, only one thread can acccess the one method at a given point of time
 * 
 * 
 * @author Nagaraja R
 *
 */
public class Worker {

	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list1.add(random.nextInt(100));
		}
	}

	public synchronized void stageTwo() {

		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list2.add(random.nextInt(100));
		}

	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting ");
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(() -> {
			process();
		});

		Thread t2 = new Thread(() -> {
			process();
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

		long end = System.currentTimeMillis();

		System.out.println("Time taken : " + (end - start));

		System.out.println("List1: " + list1.size() + ";" + "List2 size: " + list2.size());
	}

}
