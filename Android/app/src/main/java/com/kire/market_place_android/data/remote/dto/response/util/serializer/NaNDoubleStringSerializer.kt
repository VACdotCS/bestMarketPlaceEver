package com.kire.market_place_android.data.remote.dto.response.util.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NaNDoubleStringSerializer : KSerializer<Double?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("NaNDoubleStringSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Double? {
        val value = decoder.decodeString()
        return when {
            value == "NaN" -> Double.NaN
            value == "null" -> null
            else -> value.toDoubleOrNull()
        }
    }

    override fun serialize(encoder: Encoder, value: Double?) {
        if (value == null || value.isNaN()) {
            encoder.encodeString("NaN")
        } else {
            encoder.encodeString(value.toString())
        }
    }
}