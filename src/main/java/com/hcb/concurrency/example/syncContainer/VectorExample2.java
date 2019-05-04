package com.hcb.concurrency.example.syncContainer;

import com.hcb.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

@NotThreadSafe
public class VectorExample2 {
    private static Vector testVector = new Vector();

    public static void main(String arg[]) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                testVector.add(i);
                System.out.println("add" + i);
            }
            Thread remove = new Thread() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < testVector.size(); i++) {
                            testVector.remove(i);
                            System.out.println("remove" + i);
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread print = new Thread() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < testVector.size(); i++) {
                            System.out.println(testVector.get(i));
                            Thread.sleep(100);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            print.start();
            remove.start();
            while (Thread.activeCount() > 10) ;
        }
    }
}