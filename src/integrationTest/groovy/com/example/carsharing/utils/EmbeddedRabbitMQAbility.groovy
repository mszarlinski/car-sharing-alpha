package com.example.carsharing.utils

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig
import spock.lang.Shared

trait EmbeddedRabbitMQAbility {

    @Shared
    EmbeddedRabbitMq rabbitMq

    def setupSpec() {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build()
        rabbitMq = new EmbeddedRabbitMq(config)
        rabbitMq.start()
    }

    def cleanupSpec() {
        rabbitMq.stop()
    }
}