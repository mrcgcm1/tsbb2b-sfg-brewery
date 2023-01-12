package guru.springframework.brewery.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if (ts != null) {
            LocalDateTime dt = ts.toLocalDateTime();
            return OffsetDateTime.of(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond(), dt.getNano(), ZoneOffset.UTC);
        }
        return null;
    }

    public Timestamp asTimestamp(OffsetDateTime off){
            return off != null ? Timestamp.valueOf(off.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()) : null;
    }
}
