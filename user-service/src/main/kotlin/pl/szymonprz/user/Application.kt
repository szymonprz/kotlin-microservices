package pl.szymonprz.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
@EnableEurekaClient
class Application


fun main(args: Array<String>){
    runApplication<Application>(*args)
}