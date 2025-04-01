package ru.lesson.vacationcalculator_.service;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

@Service
public class VacationService {
    private static Set<LocalDate> getHolidays(int year) {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(LocalDate.of(year, 1, 1));
        holidays.add(LocalDate.of(year, 1, 2));
        holidays.add(LocalDate.of(year, 1, 3));
        holidays.add(LocalDate.of(year, 1, 4));
        holidays.add(LocalDate.of(year, 1, 5));
        holidays.add(LocalDate.of(year, 1, 6));
        holidays.add(LocalDate.of(year, 1, 7));
        holidays.add(LocalDate.of(year, 1, 8));
        holidays.add(LocalDate.of(year, 2, 23));
        holidays.add(LocalDate.of(year, 3, 8));
        holidays.add(LocalDate.of(year, 5, 1));
        holidays.add(LocalDate.of(year, 5, 2));
        holidays.add(LocalDate.of(year, 5, 3));
        holidays.add(LocalDate.of(year, 5, 4));
        holidays.add(LocalDate.of(year, 5, 8));
        holidays.add(LocalDate.of(year, 5, 9));
        holidays.add(LocalDate.of(year, 5, 10));
        holidays.add(LocalDate.of(year, 5, 11));
        holidays.add(LocalDate.of(year, 6, 12));
        holidays.add(LocalDate.of(year, 6, 13));
        holidays.add(LocalDate.of(year, 6, 14));
        holidays.add(LocalDate.of(year, 6, 15));
        holidays.add(LocalDate.of(year, 11, 2));
        holidays.add(LocalDate.of(year, 11, 3));
        holidays.add(LocalDate.of(year, 11, 4));
        return holidays;
    }

   public  BigDecimal salaryCalculation (BigDecimal salary,int  daysVacation, LocalDate startDateVacation,int year){
       int countDayPay = countDayPay(daysVacation, startDateVacation, year);
       if (salary != null && countDayPay != 0) {

           BigDecimal payForDay =  salary.divide(BigDecimal.valueOf(22), RoundingMode.HALF_UP);
          return payForDay.multiply(BigDecimal.valueOf(countDayPay));
       }
      return   BigDecimal.ZERO;


   }


    public int countDayPay(int  daysVacation, LocalDate startDateVacation,int year) {
        int countWeekendAndHolidays = countHolidaysAndWeekendDays(startDateVacation, daysVacation,year);
        return daysVacation - countWeekendAndHolidays;

    }


    public  int countHolidaysAndWeekendDays(LocalDate startDate,int daysVacation, int year) {
        if (startDate == null || daysVacation <= 0) {  //Если начало отпуска == null ИЛИ всего дней отпуска 0 или меньше
            return 0;
        }

        LocalDate endDate = startDate.plusDays(daysVacation); // получаю день окончания отпуска

        if (startDate.isAfter(endDate)) { //   если начало отпуска после конца отпуска
            return 0;
        }

        Set<LocalDate> holidays = getHolidays(year);
        int count = 0;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {   //пока текущая дата не равно конечной
            if (holidays.contains(currentDate) ||
                    currentDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                count++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return count;
    }


}
