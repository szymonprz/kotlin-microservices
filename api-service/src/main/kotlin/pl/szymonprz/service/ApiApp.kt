package pl.szymonprz.service

import com.rabbitmq.client.ConnectionFactory
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
@Configuration
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
class ApiApp {

    companion object {
        val LOG: Logger = LogManager.getLogger(ApiApp::class.java)
    }

    @LoadBalanced
    @Bean
    internal fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun connectionFactory(@Value("\${app.rabbitmq.host:localhost")rabbitMqHost: String): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory(rabbitMqHost)
        return connectionFactory.rabbitConnectionFactory
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ApiApp::class.java, *args)
}