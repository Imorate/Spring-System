package com.imorate.springsystem.configuration;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CalenderConfiguration {

    public String toPersianFormat(Timestamp timestamp) {
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTimeInMillis(timestamp.getTime());
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String beforeEncode = simpleDateFormat.format(calendar);
        CharacterEncoder characterEncoder = new CharacterEncoder
                .CharacterEncoderBuilder(beforeEncode, ':')
                .build();
        return characterEncoder.getAfterEncode() + " - " + df.format(calendar);
    }

}