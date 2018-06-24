package pl.szymonprz.turbine

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.turbine.EnableTurbine
import org.springframework.context.annotation.Bean


@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
@EnableDiscoveryClient
class TurbineApp {

    companion object {
        val LOG: Logger = LogManager.getLogger(TurbineApp::class.java)
    }

    @Bean
    fun connectionFactory(@Value("\${app.rabbitmq.host:localhost}") rabbitMQHost: String): ConnectionFactory {
        LOG.info(String.format("Creating RabbitMQHost ConnectionFactory for host: %s", rabbitMQHost))
        return CachingConnectionFactory(rabbitMQHost)
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(TurbineApp::class.java, *args)
}