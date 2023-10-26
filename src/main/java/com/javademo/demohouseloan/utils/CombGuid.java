package com.javademo.demohouseloan.utils;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

//generates  comb uuid
public  class CombGuid {
    //NHibernate combuuid
    public static UUID newuuid() {
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime baseDate = LocalDateTime.of(1900, 1, 1, 0, 0, 0);

        Duration duration = Duration.between(baseDate, now);
        long days = duration.toDays();
        long millis = duration.minusDays(days).toMillis();

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

        byte[] daysArray = buffer.putLong(days).array();
        byte[] millisArray = buffer.clear().putLong((long) (millis / 3.333333)).array();

        byte[] guidArray = ByteBuffer.allocate(2 * Long.BYTES).putLong(uuid.getMostSignificantBits()).putLong(leastSignificantBits).array();

        System.arraycopy(daysArray, daysArray.length - 2, guidArray, guidArray.length - 6, 2);
        System.arraycopy(millisArray, millisArray.length - 4, guidArray, guidArray.length - 4, 4);

        ByteBuffer byteBuffer = ByteBuffer.wrap(guidArray);
        long mostSignificantBits = byteBuffer.getLong();
        long leastSignificantBitsNew = byteBuffer.getLong();

        return new UUID(mostSignificantBits, leastSignificantBitsNew);
    }
}
