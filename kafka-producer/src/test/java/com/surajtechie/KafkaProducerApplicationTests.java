package com.surajtechie;

import com.surajtechie.model.Customer;
import com.surajtechie.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerApplicationTests {

	@Container
	static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

	@DynamicPropertySource
	public static void initKafkaProperties(DynamicPropertyRegistry registry){
	registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
	}

	@Autowired
	KafkaMessagePublisher publisher;

	@Test
	public void testsendSerializeCustomer(){
		publisher.sendSerializeCustomer(new Customer(13,"rama","axy@c.in","123123"));
		await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS).untilAsserted(() ->{
			//assert statement
		});
	}

}
