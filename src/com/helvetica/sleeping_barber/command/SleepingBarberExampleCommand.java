package com.helvetica.sleeping_barber.command;

import com.helvetica.sleeping_barber.entities.Barber;
import com.helvetica.sleeping_barber.entities.Client;
import com.helvetica.sleeping_barber.entities.ClientBuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SleepingBarberExampleCommand {

    public static void execute() {
        ClientBuffer clientBuffer = new ClientBuffer(3);
        ReentrantLock clientLock = new ReentrantLock();
        ReentrantLock jobLock = new ReentrantLock();
        ReentrantLock seatLock = new ReentrantLock();

        Condition vacantClient = clientLock.newCondition();
        Condition jobFinished = jobLock.newCondition();

        Client client = new Client(clientLock, seatLock, jobLock, vacantClient, jobFinished, clientBuffer);
        Client client2 = new Client(clientLock, seatLock, jobLock, vacantClient, jobFinished, clientBuffer);
        Client client3 = new Client(clientLock, seatLock, jobLock, vacantClient, jobFinished, clientBuffer);
        Barber barber = new Barber(clientLock, seatLock, jobLock, vacantClient, jobFinished, clientBuffer);

        client.start();
        client2.start();
        client3.start();
        barber.start();
    }
}
