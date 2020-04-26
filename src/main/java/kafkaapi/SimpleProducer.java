package kafkaapi;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import static kafkaapi.Logger.log;

public class SimpleProducer {

	public void produce(String topicName, String payload) throws Exception {

		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", Consts.HOST_AND_PORT);

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

//		// Specify buffer size in config
//		props.put("batch.size", 16384);
//
//		// Reduce the no of requests less than 0
//		props.put("linger.ms", 1);
//
//		// The buffer.memory controls the total amount of memory available to the
//		// producer for buffering.
//		props.put("buffer.memory", 33554432);
//
//		props.put("key.serializer", "org.apache.kafka.common.serializa-tion.StringSerializer");
//
//		props.put("value.serializer", "org.apache.kafka.common.serializa-tion.StringSerializer");

		try(Producer<String, String> producer = new KafkaProducer<String, String>(props)){
			
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, payload);

			producer.send( producerRecord );

			log("Mensagen enviada:" + payload);

		} catch (Exception ex) {

			ex.printStackTrace();
			log(ex);

		}

	}

}
