package com.java.threads.producerconsumer;

public class TestPC {

	public static void main(String[] args) {

		final Processor process = new Processor();

		Thread t1 = new Thread(() -> {

			try {
				process.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		Thread t2 = new Thread(() -> {

			try {
				process.consume();
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

}
