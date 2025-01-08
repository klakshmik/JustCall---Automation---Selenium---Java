package org.justcall.utilities;

public class MiscUtil {
    public static final int DELAY_TEST_TIME = 2;

    public static void defaultPause() {
        pause(DELAY_TEST_TIME);
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception occurring during thread.sleep call.");
        }
    }
}
