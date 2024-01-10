package Lab8.utils;

import Lab8.services.ProtoService;
import Lab8.bunch.AbstractTech;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Fixer<T extends AbstractTech> implements Runnable {
    private final List<T> techs;
    private final ProtoService<T> service;
    private final Semaphore semaphore = new Semaphore(2);

    public Fixer(List<T> techs, ProtoService<T> service) {
        this.techs = techs;
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            fixBrokenTechs();
        }
    }

    private void fixBrokenTechs() {
        for (T tech : techs) {
            if (tech.getIsBroken()) {
                try {
                    service.fix(tech, semaphore);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
