package model;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class WeekDay {
    public static ArrayList<String> previousWeekDates() {
        LocalDate today = LocalDate.now();
        ArrayList <String> dateList = new ArrayList<>();
        LocalDate previousMonday = today.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate previousTuesday = previousMonday.plusDays(1);
        LocalDate previousWednesday = previousMonday.plusDays(2);
        LocalDate previousThursday = previousMonday.plusDays(3);
        LocalDate previousFriday = previousMonday.plusDays(4);
        LocalDate previousSaturday = previousMonday.plusDays(5);
        LocalDate previousSunday = previousMonday.plusDays(6);
        dateList.add(previousMonday.toString());
        dateList.add(previousTuesday.toString());
        dateList.add(previousWednesday.toString());
        dateList.add(previousThursday.toString());
        dateList.add(previousFriday.toString());
        dateList.add(previousSaturday.toString());
        dateList.add(previousSunday.toString());
        return dateList;
    }
    public static void main(String[] args) {
        ArrayList<String> dates = previousWeekDates();
        for (String date : dates) {
            System.out.println(date);
        }
    }
}

