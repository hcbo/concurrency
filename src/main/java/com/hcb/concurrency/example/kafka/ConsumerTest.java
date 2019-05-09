package com.hcb.concurrency.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {
    public static void main(String[] args) {
        String topic_name = "test_idea";
        String group_id = "test_group";
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.225.6:9092");
        properties.put("group.id",group_id);
        properties.put("enable.auto.commit","true");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic_name));
        try {
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record:records){
                    System.out.println(record.offset()+" "+record.key()+" "+record.value());

                }
            }
        }finally {
            consumer.close();
        }
    }



}
