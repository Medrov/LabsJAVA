package Lab8.utils;

import Lab8.bunch.AbstractTech;

import java.util.List;

public class CrashRandomizer implements Runnable {
    private final List<AbstractTech> techList;

    public CrashRandomizer(List<AbstractTech> techList) {
        this.techList = techList;
    }

    @Override
    public void run() {
        while (true) {
            randomizeCrashes();
            sleepThread();
        }
    }

    private void randomizeCrashes() {
        for (AbstractTech tech : techList) {
            if (!tech.getIsBroken()) {
                tech.tryCrash();
            }
        }
    }

    private void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
