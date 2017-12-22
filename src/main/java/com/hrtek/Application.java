package com.hrtek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class Application   {
    public  static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        System.out.println("hello,springboot!");
    }
    /*@Autowired
    private KafkaTemplate template ;
    private final CountDownLatch latch = new CountDownLatch(3);
    @Override
    public void run(String... args) throws Exception {
        Map<String,String> map = new HashMap<String,String>();
        map.put("foo1","上上签物流");
        map.put("foo2","演员的自我修养");
        map.put("foo3","实打实大苏打大苏打撒旦撒大苏打");
        template.send("myTopic","1","1332");
        template.send("myTopic","2","b");
        template.send("myTopic1","3","c");
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
    }*/
}
