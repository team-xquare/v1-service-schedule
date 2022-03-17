package app.xqaure.schedule.infrastructure.database.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import java.nio.ByteBuffer
import java.util.UUID

@WritingConverter
class UUIDToByteArrayConverter : Converter<UUID, ByteArray> {

    override fun convert(source: UUID): ByteArray {
        val bb = ByteBuffer.wrap(ByteArray(16))

        bb.putLong(source.mostSignificantBits)
        bb.putLong(source.leastSignificantBits)

        return bb.array()
    }
}

@ReadingConverter
class ByteArrayToUUIDConverter : Converter<ByteArray, UUID> {

    override fun convert(source: ByteArray): UUID =
        UUID.nameUUIDFromBytes(source)
}
