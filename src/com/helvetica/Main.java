package com.helvetica;

import com.helvetica.consumer_producer.command.ProducerConsumerExampleCommand;
import com.helvetica.dining_philosophers.command.DiningPhilosophersExampleCommand;
import com.helvetica.reader_writer.command.ReaderWriterExampleCommand;
import com.helvetica.sleeping_barber.command.SleepingBarberExampleCommand;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        ProducerConsumerExampleCommand.execute();
        ReaderWriterExampleCommand.execute();
//        DiningPhilosophersExampleCommand.execute();
//        SleepingBarberExampleCommand.execute();

    }

}
