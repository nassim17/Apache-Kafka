package com.kafka.springforapachekafka.config.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


@Configuration
public class KafkaTopicsConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.topic.base.partitions}")
  private int partitions;

  @Value("${kafka.topic.base.replicas}")
  private int replicas;

  @Value("${kafka.topic.base.retention.ms}")
  private String retention;

  @Value("${kafka.topic.base.cleanup.policy}")
  private String cleanupPolicy;

  @Value("${kafka.topic.bookTopic.name}")
  private String bookTopic;


  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(props);
  }

  @Bean
  public NewTopic swiftBillingConsolidationTopic() {
    return TopicBuilder
        .name(bookTopic)
        .partitions(partitions)
        .replicas(replicas)
        .config(TopicConfig.RETENTION_MS_CONFIG, retention)
        .config(TopicConfig.CLEANUP_POLICY_CONFIG, cleanupPolicy)
        .build();
  }
}
