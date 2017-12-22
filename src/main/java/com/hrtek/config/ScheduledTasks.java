package com.hrtek.config;

import com.hrtek.Application;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    public  static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private  static  final SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Scheduled(fixedRate = 5000)
    public  void reportCurrentTime(){
        System.out.println("time is" + dateformat.format(new Date()));
    }
    @Autowired
    private KafkaTemplate template ;
    private final CountDownLatch latch = new CountDownLatch(3);
    @Scheduled(fixedRate = 100)
    public void Producer() throws Exception {
        template.send("myTopic","0","a");
        template.send("myTopic1","1","b");
        template.send("myTopic2","2","c");
        template.send("myTopic3","3","d");
        template.send("myTopic4","4","e");
        template.send("myTopic5","5","f");
     latch.await(60, TimeUnit.SECONDS);
     logger.info("全部都接收啦");
    }
    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?,?> cr){
    logger.info("myTopic:-----"+cr.toString());
    latch.countDown();
    }
    @KafkaListener(topics = "myTopic1")
    public void listen1(ConsumerRecord<?,?> cr){
        logger.info("myTopic1:-----"+cr.toString());
        latch.countDown();
    }
    @KafkaListener(topics = "myTopic2")
    public void listen2(ConsumerRecord<?,?> cr){
        logger.info("myTopic2:-----"+cr.toString());
        latch.countDown();
    }
    @KafkaListener(topics = "myTopic3")
    public void listen3(ConsumerRecord<?,?> cr){
        logger.info("myTopic3:-----"+cr.toString());
        latch.countDown();
    }
    @KafkaListener(topics = "myTopic4")
    public void listen4(ConsumerRecord<?,?> cr){
        logger.info("myTopic4:-----"+cr.toString());
        latch.countDown();
    }
    @KafkaListener(topics = "myTopic5")
    public void listen5(ConsumerRecord<?,?> cr){
        logger.info("myTopic5:-----"+cr.toString());
        latch.countDown();
    }
}
