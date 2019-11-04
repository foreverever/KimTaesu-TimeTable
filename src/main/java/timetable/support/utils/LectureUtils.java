package timetable.support.utils;

public class LectureUtils {

    public static final String TWO = "two-hr";
    public static final String MON = "월";
    public static final String TUE = "화";
    public static final String WED = "수";
    public static final String THU = "목";
    public static final String FRI = "금";

    public static int CalcCodeColorNum(String code) {
        return Integer.parseInt(code.substring(7)) % 10;
    }

    public static String getHourOfTime(String formattedTime) {
        return formattedTime.substring(0, 2);
    }

    public static int getHourOfTimeToInteger(String formattedTime) {
        return Integer.parseInt(getHourOfTime(formattedTime));
    }

    public static int mulStartTimeEndTime(String startTime, String endTime) {
        return getHourOfTimeToInteger(endTime) - getHourOfTimeToInteger(startTime);
    }
}
