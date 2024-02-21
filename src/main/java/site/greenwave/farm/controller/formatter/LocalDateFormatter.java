package site.greenwave.farm.controller.formatter;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// LocalDate 형식 데이터를 문자열로 파싱하고 풀력하기 위한 클래스
public class LocalDateFormatter implements Formatter<LocalDate> {

    // 문자열을 LocalDate 객체로 파싱
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // LocalDate 객체를 문자열로 변환
    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
