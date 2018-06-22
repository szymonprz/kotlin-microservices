package pl.szymonprz.zuul.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
@Configuration
@EnableEurekaClient
@EnableZuulProxy
class EdgeApp {

    @LoadBalanced
    @Bean
    internal fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}


fun main(args: Array<String>) {
    SpringApplication.run(EdgeApp::class.java, *args)
}