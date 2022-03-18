package app.xqaure.schedule.infrastructure.database

import app.xqaure.schedule.infrastructure.database.converter.ByteArrayToUUIDConverter
import app.xqaure.schedule.infrastructure.database.converter.UUIDToByteArrayConverter
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class MySQLConfig(
    @Value("\${spring.r2dbc.url}")
    private val url: String
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory =
        ConnectionFactories.get(url)

    override fun getCustomConverters(): MutableList<Any> {
        return mutableListOf(UUIDToByteArrayConverter(), ByteArrayToUUIDConverter())
    }
}
