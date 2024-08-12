package gist.pilldispenser.drug.userDrugInfo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayType {

    MON("Monday", "MON"),
    TUE("Tuesday","TUE"),
    WED("Wednesday","WED"),
    THU("Thursday","THU"),
    FRI("Friday","FRI"),
    SAT("Saturday","SAT"),
    SUN("Sunday","SUN"),
    EVERYDAY("Everyday","*");

    private final String dayName;
    private final String cronName;

    public static DayType fromDayName(String dayName) {
        for (DayType dayType : DayType.values()) {
            if (dayType.getDayName().equalsIgnoreCase(dayName)) {
                return dayType;
            }
        }
        throw new IllegalArgumentException("No enum constant for day name: " + dayName);
    }
}
