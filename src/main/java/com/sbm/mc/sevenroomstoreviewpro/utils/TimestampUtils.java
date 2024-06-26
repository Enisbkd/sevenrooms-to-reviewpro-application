package com.sbm.mc.sevenroomstoreviewpro.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimestampUtils.class);

    public static LocalDateTime convertStringToTimestamp(String strDate) {
        LocalDateTime dateTime = LocalDateTime.parse(strDate);
        return dateTime;
    }

    public static ZonedDateTime convertToZonedDateTime(String text) {
        ZonedDateTime zonedDateTime = null;
        try {
            if (text != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").withZone(ZoneId.of("UTC"));
                zonedDateTime = ZonedDateTime.parse(text, formatter);
            }
        } catch (DateTimeParseException e) {
            logger.debug("Error parsing date: " + e.getMessage());
        }
        return zonedDateTime;
    }

    public static LocalDateTime convertToLocalDateTime(String text) {
        LocalDateTime localDateTime = null;
        try {
            if (text != null) {
                localDateTime = LocalDateTime.parse(text);
            }
        } catch (DateTimeParseException e) {
            logger.debug("Error parsing date: " + e.getMessage());
        }
        return localDateTime;
    }

    public static LocalDate convertToLocalDate(String text) {
        //        logger.debug("Input string date : " + text);
        LocalDate localDate = null;
        String subStringDate = null;
        if (text != null) {
            subStringDate = text.substring(0, 10);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        try {
            if (text != null) {
                localDate = LocalDate.parse(subStringDate, formatter);
                //                logger.debug("Output date : " + localDate);
            }
        } catch (DateTimeParseException e) {
            logger.debug("Error parsing date: " + e.getMessage());
        }
        return localDate;
    }
}
