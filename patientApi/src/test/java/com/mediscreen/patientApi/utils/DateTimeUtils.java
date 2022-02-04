package com.mediscreen.patientApi.utils;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass

public class DateTimeUtils {
    public Date getDateFromStringWithFormat(String dateAsString, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateAsString);
    }
}
