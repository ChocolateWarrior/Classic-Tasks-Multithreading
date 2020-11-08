package com.helvetica.sleeping_barber.entities;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barber extends Thread{

    private ReentrantLock clientLock;
    private ReentrantLock seatLock;
    private ReentrantLock jobLock;

    private Condition vacantClient;
    private Condition jobFinished;

    private ClientBuffer clientBuffer;

    public Barber(ReentrantLock clientLock, ReentrantLock seatLock,
                  ReentrantLock jobLock, Condition vacantClient,
                  Condition jobFinished, ClientBuffer clientBuffer) {
        this.clientLock = clientLock;
        this.seatLock = seatLock;
        this.jobLock = jobLock;
        this.vacantClient = vacantClient;
        this.jobFinished = jobFinished;
        this.clientBuffer = clientBuffer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            seatLock.lock();
            //IF NO CLIENT QUEUE FOR HAIRCUT
            if (clientBuffer.getCurrentSize() == 0) {
                System.out.println("BARBER SLEEPING");
                clientLock.lock();
                seatLock.unlock();

                //WAITING FOR CLIENT TO APPEAR
                try {
                    vacantClient.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    clientLock.unlock();
                }
                if (isInterrupted()) return;
            }

            lockSeatsIfNeeded();
            clientBuffer.leavePlace();
            seatLock.unlock();
            System.out.println("DOING HAIRCUT FOR CLIENT " + getName());
            System.out.println("FINISHED");
            jobLock.lock();
            try {
                jobFinished.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                jobLock.unlock();
            }
        }
    }

    private void lockSeatsIfNeeded() {
        if (!seatLock.isHeldByCurrentThread()) {
            seatLock.lock();
        }
    }
}
