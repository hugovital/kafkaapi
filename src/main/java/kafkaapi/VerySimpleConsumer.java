package kafkaapi;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import static kafkaapi.Logger.log;

public class VerySimpleConsumer {

    public String consume(String topicName) throws Exception {

        // Set consumer configuration properties
        final Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Consts.HOST_AND_PORT);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "very-simple-consumer"); //?
        
        StringBuilder sb = new StringBuilder();
        log("iniciando consumer para topico: " + topicName);

        // Create a new consumer
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
            // Subscribe to the topic
            consumer.subscribe(Collections.singleton( topicName ));
            final ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
            	
            	sb.append("Message from Topic : " + topicName + " ------------ ");
            	sb.append("\r\n");
            	sb.append( record );
            	
            	log("Received from topic " + topicName + ": " + record);

            }
        }
        
        log("consumer concluíd para topico " + topicName);
        return sb.toString();
    }

}