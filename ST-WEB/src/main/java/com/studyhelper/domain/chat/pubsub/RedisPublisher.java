package com.studyhelper.domain.chat.pubsub;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.chat.dto.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisPublisher {
	private final RedisTemplate<String, Object> redisTemplate;
	
	public void publish(ChannelTopic topic, ChatMessage message) {
		log.info(topic.getTopic());
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
}
