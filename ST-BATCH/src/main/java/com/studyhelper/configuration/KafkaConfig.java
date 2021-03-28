package com.studyhelper.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.studyhelper.domain.matching.entity.Matching;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Bean
	public ConsumerFactory<String, Matching> matchingConsumerFactory() {
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "batch1");

		return new DefaultKafkaConsumerFactory<String, Matching>(config, new StringDeserializer(),
				new JsonDeserializer<>(Matching.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Matching> matchListener() {
		ConcurrentKafkaListenerContainerFactory<String, Matching> factory = new ConcurrentKafkaListenerContainerFactory<String, Matching>();
		factory.setConsumerFactory(matchingConsumerFactory());
		return factory;
	}
}
