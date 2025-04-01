package ru.lesson.vacationcalculator_.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lesson.vacationcalculator_.service.VacationService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculationVacationController {
    private final VacationService vacationService;


    @GetMapping("/salary/{salary}/{daysVacation}/{startDateVacation}/{year}")
    public ResponseEntity<BigDecimal> calculateSalary(@PathVariable BigDecimal salary,@PathVariable int  daysVacation,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDateVacation,@PathVariable int year) {
        BigDecimal bigDecimal = vacationService.salaryCalculation(salary, daysVacation, startDateVacation, year);
        return  ResponseEntity.ok(bigDecimal);
    }
    @GetMapping("/countPay/{daysVacation}/{startDateVacation}/{year}")
    public ResponseEntity<Integer> countDayPay(@PathVariable int  daysVacation, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDateVacation, @PathVariable int year){
        int countDayPay = vacationService.countDayPay(daysVacation, startDateVacation, year);
        return ResponseEntity.ok(countDayPay);
    }

    @GetMapping("/countDayNoPay/{startDate}/{daysVacation}/{year}")
    public ResponseEntity<Integer>countHolidaysAndWeekendDays(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable int daysVacation, @PathVariable int year) {
        int i = vacationService.countHolidaysAndWeekendDays(startDate, daysVacation, year);
        return ResponseEntity.ok(i);
    }


}
