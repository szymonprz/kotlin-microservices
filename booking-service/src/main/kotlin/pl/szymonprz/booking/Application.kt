package pl.szymonprz.booking

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
@EnableEurekaClient
class Application


fun main(args: Array<String>){
    SpringApplication.run(Application::class.java, *args)
}