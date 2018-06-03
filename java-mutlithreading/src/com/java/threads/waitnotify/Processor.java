package com.java.threads.waitnotify;

import java.util.Scanner;
/**
 * Interthread communication using wait and notify
 * 
 * @author Nagaraja R
 *
 */
public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running");
			// wait will release the lock immediatley
			wait();
			System.out.println("Resumed...");
		}

	}

	public void consume() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for rturn key...");
			scanner.nextLine();
			// notify will not release the lock untill the block is completed
			notify();
			
			System.out.println("Return key pressed..");
		}
	}

}
