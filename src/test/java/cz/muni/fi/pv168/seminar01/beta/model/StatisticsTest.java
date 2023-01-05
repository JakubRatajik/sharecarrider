package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsTest {

    private List<Vehicle> vehicles = List.of(new Vehicle(1, "3C56787", "VW", "Golf", 5, 5.5,FuelType.CNG),
            new Vehicle(2, "3C56787", "VW", "Golf", 5, 3.2,FuelType.CNG));

    private List<Ride> rides = List.of(new Ride(1, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Katerinske jeskyne", "Strasne paliva india", 780, new HashSet<>(), new HashSet<>(), new Vehicle(2, "3C56787", "VW", "Golf", 5, 3.2,FuelType.CNG), Repetition.MONTHLY, ""),
            new Ride(2, LocalDate.MAX, LocalTime.MIN, LocalTime.MAX, "Katerinske jeskyne", "Strasne paliva india", 220, new HashSet<>(), new HashSet<>(), new Vehicle(2, "3C56787", "VW", "Golf", 5, 3.2,FuelType.CNG), Repetition.MONTHLY, ""));

    @Test
    void cheapestVehicle() {
        assertThat(Statistics.findCheapestVehicle(vehicles)).isEqualTo(new Vehicle(2, "3C56787", "VW", "Golf", 5, 3.2, FuelType.CNG));
    }

    @Test
    void mostExpensiveVehicle() {
        assertThat(Statistics.findMostExpensiveVehicle(vehicles)).isEqualTo(new Vehicle(1, "3C56787", "VW", "Golf", 5, 5.5,FuelType.CNG));
    }

    @Test
    void countTotalDistanceTest() {
        assertThat(Statistics.countTotalDistance(rides)).isEqualTo(1000);
    }

    @Test
    void countAverageDistance() {
        assertThat(Statistics.countAverageDistance(rides)).isEqualTo(500);
    }

    @Test
    void findLongestRide() {
        assertThat(Statistics.findLongestRide(rides)).isEqualTo(new Ride(1, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Katerinske jeskyne", "Strasne paliva india", 780, new HashSet<>(), new HashSet<>(), new Vehicle(2, "3C56787", "VW", "Golf", 5, 3.2,FuelType.CNG), Repetition.MONTHLY, ""));
    }
}
