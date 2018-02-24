package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentLogging;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ConsumerService {
    private List<String> list;
    private final CountDownLatch latch;

    @Autowired
    ConsumerService() {
        this.latch = new CountDownLatch(1);
        list = new ArrayList<>();
        initKey();
    }

    private void initKey(){
        list.add("microserviceId");
        list.add("totalMemory");
        list.add("freeMemory");
        list.add("cpuNumber");
        list.add("totalHeap");
        list.add("usedHeap");
        list.add("startedThreads");
        list.add("totalDiskSpace");
        list.add("freeDiskSpace");
    }

    @KafkaListener(topics = "${topic.name}")
    public void consumeMessage(ConsumerRecord<?, ?> consumerRecord) {
        AgentLogging.log("received payload='{}'", consumerRecord.toString());
        latch.countDown();
        Metrics m = parse(consumerRecord.value().toString());
        System.out.println(m.toString());
    }

    private Metrics parse(String stream){
        List<String> datas = new ArrayList<>();

        list.forEach(s -> {
            Pattern p = Pattern.compile("\""+ s +"\":.*?[,}]");
            Matcher m = p.matcher(stream);

            while(m.find()){
                String t = stream.substring(m.start(), m.end());
                Pattern p1 = Pattern.compile(":.*?[,}]");
                Matcher m1 = p1.matcher(t);
                while(m1.find()) {
                    String f = t.substring(m1.start() + 1, m1.end() - 1);
                    datas.add(f);
                }

            }
        });

        return Metrics.builder().
                microserviceId(datas.get(0)).
                totalMemory(Long.valueOf(datas.get(1))).
                freeMemory(Long.valueOf(datas.get(2))).
                cpuNumber(Integer.valueOf(datas.get(3))).
                totalHeap(Long.valueOf((datas.get(4)))).
                usedHeap(Long.valueOf(datas.get(5))).
                startedThreads(Long.valueOf(datas.get(6))).
                totalDiskSpace(Long.valueOf(datas.get(7))).
                freeDiskSpace(Long.valueOf(datas.get(8))).
                build();
    }
}
