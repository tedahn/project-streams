package com.tedahn.projectstreams.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjectStreamsApplication

fun main(args: Array<String>) {
	runApplication<ProjectStreamsApplication>(*args)
}
