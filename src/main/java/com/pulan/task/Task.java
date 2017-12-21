//package com.pulan.task;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//@Component
//public class Task {
//	@Scheduled(fixedDelay = 1000)
//    public void printTime() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("task one " + format.format(new Date()));
//    }
//
//    @Scheduled(fixedDelay = 1000)
//    public void printTimeSleep() throws InterruptedException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("===================----> task two " + format.format(new Date()));
//        Thread.sleep(5000);
//    }
//}
