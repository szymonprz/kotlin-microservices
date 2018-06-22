package pl.szymonprz.eureka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication(exclude = [(GsonAutoConfiguration::class)])
@EnableEurekaServer
class EurekaServer


fun main(args: Array<String>) {
    SpringApplication.run(EurekaServer::class.java, *args)
}