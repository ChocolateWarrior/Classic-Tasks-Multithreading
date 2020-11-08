package com.helvetica.sleeping_barber.entities;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread {

    private ReentrantLock clientLock;
    private ReentrantLock seatLock;
    private ReentrantLock jobLock;

    private Condition vacantClient;
    private Condition jobFinished;

    private ClientBuffer clientBuffer;

    public Client(ReentrantLock clientLock, ReentrantLock seatLock,
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

        System.out.println("CLIENT " + getName() + " CAME IN");

        if (!clientBuffer.isFull()){
            seatLock.lock();
            clientBuffer.bookPlace();
            System.out.println("CLIENT " + getName() + " TOOK A SEAT");
            seatLock.unlock();
            jobLock.lock();

            //WAKING BARBER UP
            clientLock.lock();
            try {
                vacantClient.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                clientLock.unlock();
            }

            //WAITING TILL HAIRCUT IS FINISHED
            try {
                jobFinished.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                jobFinished.signal();
                jobLock.unlock();
            }
        }
        System.out.println("CLIENT " + getName() + " LEFT");
    }
}
