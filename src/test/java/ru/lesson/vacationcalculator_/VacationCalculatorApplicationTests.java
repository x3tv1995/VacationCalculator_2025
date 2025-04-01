package ru.lesson.vacationcalculator_;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lesson.vacationcalculator_.service.VacationService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationCalculatorApplicationTests {
    @Autowired
    private VacationService vacationService;
    @Test
    void contextLoads() {
    }


    @Test
    public  void testCountHolidaysAndWeekendDays_basicCase() {
        LocalDate startDate = LocalDate.of(2025, 1, 14);
        int daysVacation = 7;
        int year = 2024;
        int expectedCount = 2;
        int actualCount = vacationService.countHolidaysAndWeekendDays(startDate, daysVacation, year);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public  void testCountPay_basicCase() {
        LocalDate startDate = LocalDate.of(2025, 1, 13);
        int daysVacation = 14;
        int year = 2025;
        int expectedCountPayDay = 10;
        int actualCountDay = vacationService.countDayPay(daysVacation,startDate,year);
        assertEquals(expectedCountPayDay, actualCountDay);
    }

    @Test
    public  void testSalaryCalculation(){
        LocalDate startDate = LocalDate.of(2025, 1, 13);
        int daysVacation = 14;
        int year = 2025;
        BigDecimal salaryForMonth = BigDecimal.valueOf(49000);
        BigDecimal expectedPayForVacation = BigDecimal.valueOf(22270);

        BigDecimal actualPayForVacation = vacationService.salaryCalculation(salaryForMonth,daysVacation,startDate,year);
        assertEquals(expectedPayForVacation, actualPayForVacation);
    }

}
