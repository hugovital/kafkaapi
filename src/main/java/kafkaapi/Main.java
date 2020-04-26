package kafkaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Main {
	
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @PutMapping("/mensagem")
    String sendMessage(@PathVariable String topicName, @RequestBody String payload) throws Exception {
    	
    	if (topicName == null || topicName.trim().equals("") )
    		throw new Exception("topicName é nulo ou em branco");    	

    	SimpleProducer simpleProducer = new SimpleProducer();
    	simpleProducer.produce(topicName, payload);

    	return "sucesso";

    }
    
    @GetMapping("/listarTopicos")
    String listTopics() throws Exception {
      
    	ExecutorProcessos executorProcessos = new ExecutorProcessos();    	
    	return executorProcessos.execute( Consts.COMMAND_LIST_TOPICS );
    	
    }     

    @GetMapping("/mensagem")
    String getMessages(@PathVariable String topicName) throws Exception {
    	
    	if (topicName == null || topicName.trim().equals("") )
    		throw new Exception("topicName é nulo ou em branco");

    	VerySimpleConsumer consumer = new VerySimpleConsumer();
    	return consumer.toString();

    }    

}
