package ru.otus.lesson21;

public class Main {
    private static final int ARRAY_SIZE = 100_000_000;
    private static final int THREADS_NUMBER = 4;

    public static void main(String[] args) {
        long startSingleThread = System.currentTimeMillis();
        singleThreadCalc();
        System.out.println("Single-thread result: " + (System.currentTimeMillis() - startSingleThread) + " ms");
        long startMultiThread = System.currentTimeMillis();
        multiThreadCalc();
        System.out.println("Multi-thread result: " + (System.currentTimeMillis() - startMultiThread) + " ms");
    }

    public static void singleThreadCalc() {
        double[] array = new double[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static void multiThreadCalc() {
        double[] array = new double[ARRAY_SIZE];
        for (int i = 0; i < THREADS_NUMBER; i++) {
            int elemsPerThread = ARRAY_SIZE / THREADS_NUMBER;
            int lb = i * elemsPerThread;
            int ub = (i == THREADS_NUMBER - 1) ? (ARRAY_SIZE - 1) : (lb + elemsPerThread - 1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = lb; j <= ub; j++) {
                        array[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                    }
                }
            }).start();
        }
    }
}
