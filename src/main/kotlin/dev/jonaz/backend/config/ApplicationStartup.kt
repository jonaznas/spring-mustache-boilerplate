package dev.jonaz.backend.config

import dev.jonaz.backend.model.DatabaseModelInitializer
import dev.jonaz.backend.util.exposed.DatabaseInitializer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class ApplicationStartup: ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        DatabaseInitializer()
                .connect()
        DatabaseModelInitializer()
    }
}
