package Lab8.services;

import Lab8.bunch.AbstractTech;

import java.util.concurrent.Semaphore;




public abstract class ProtoService<T extends AbstractTech> {

    public abstract void fix(T brokenTech, Semaphore semaphore) throws InterruptedException;
}



