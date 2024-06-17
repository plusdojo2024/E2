package model;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Method {
    public static ArrayList<String> getDatesFromMonday() {
        ArrayList <String> dateList = new ArrayList<>();
        // 今日の日付を取得
        LocalDate hiduke = LocalDate.now();

        // 日付に対応する曜日を取得
        DayOfWeek youbi = hiduke.getDayOfWeek();

        // 曜日を表す整数値を取得
        int number = youbi.getValue() - DayOfWeek.MONDAY.getValue();

        // 直近の月曜の日付を取得
        LocalDate mondayBefore = hiduke.minusDays(number);

        // フォーマットをただす
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (!mondayBefore.isAfter(hiduke)) {
            String kekka = formatter.format(mondayBefore);
            dateList.add(kekka);
            mondayBefore = mondayBefore.plusDays(1);       // プラス１する
        }
        return dateList;
    }
    public static void main(String[] args){
        ArrayList<String> dates = getDatesFromMonday();
        for (String date : dates) {
            System.out.println(date);
        }
    }


}


