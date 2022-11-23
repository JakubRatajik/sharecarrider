package cz.muni.fi.pv168.seminar01.beta.data;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCat;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public final class TestDataGenerator {
    private static final List<Character> genders = List.of('f', 'm');
    private static final List<String> maleNames =
            List.of("Jiří", "Jan", "Petr", "Josef", "Pavel", "Martin", "Tomáš", "Jaroslav", "Miroslav", "Zdeněk");
    private static final List<String> maleSurnames =
            List.of("Novák", "Novotný", "Dvořák", "Černý", "Procházka", "Šťastný", "Veselý", "Horák", "Němec", "Pokorný");
    private static final List<String> femaleNames =
            List.of("Jana", "Marie", "Eva", "Hana", "Anna", "Lenka", "Kateřina", "Lucie", "Věra", "Alena");
    private static final List<String> femaleSurnames =
            List.of("Nováková", "Novotná", "Dvořáková", "Černá", "Procházková", "Šťastná", "Veselá", "Horáková", "Němcová", "Pokorná");
    private static final List<String> phoneNumbers =
            List.of("+420777408524", "+420668745827", "+420584401287", "+421875428554", "+421668547421", "+421542875142");
    private static final RideCategory RIDE_CATEGORY_1 = new RideCategory(Color.BLUE, "work");
    private static final RideCategory RIDE_CATEGORY_2 = new RideCategory(Color.YELLOW, "party");
    private static final List<Set<RideCategory>> rideCategories =
            List.of(new HashSet<>(), new HashSet<>(List.of(RIDE_CATEGORY_1, RIDE_CATEGORY_2)), new HashSet<>(List.of(RIDE_CATEGORY_1)), new HashSet<>(List.of(RIDE_CATEGORY_2)));
    private static final List<Set<PassengerCategory>> passengerCategories =
            List.of(new HashSet<>(), new HashSet<>(List.of(PassengerCategory.OTHER, PassengerCategory.FRIENDS)), new HashSet<>(List.of(PassengerCategory.WORK)), new HashSet<>(List.of(PassengerCategory.WORK, PassengerCategory.FRIENDS)));
    private static final Map<String, Map<String, Integer>> brands = Map.of(
            "VW", Map.of("Arteon", 5, "Touran", 5, "Golf", 5, "Polo", 5),
            "Audi", Map.of("A6", 5, "A7", 5, "Q5", 5, "R8", 2),
            "Škoda", Map.of("Kodiaq", 5, "Karoq", 5, "Eniaq", 5, "Superb", 5, "Octavia", 5),
            "BMW", Map.of("M5", 5, "M6", 4, "X5", 5, "R 18", 2),
            "Ferrari", Map.of("F8", 2, "458 Italia", 2),
            "Zetor", Map.of("Crystal", 1, "Proxima", 1)
    );

    private static final List<String> destinations =
            List.of("Supíkovice", "Vranov nad Topľou", "Kino Scala", "Skybar", "Brno", "Bratislava", "Vídeň", "*tajné*");

    private final Random random = new Random(2L);

    private final List<Passenger> passengers = new ArrayList<>();
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Ride> rides = new ArrayList<>();

    public Passenger createPassenger() {
        int gender = selectRandom(genders);
        Passenger passenger;
        if (gender == 'f') {
            passenger = new Passenger(selectRandom(femaleNames), selectRandom(femaleSurnames),
                    selectRandom(phoneNumbers), selectRandom(passengerCategories));
        } else {
            passenger = new Passenger(selectRandom(maleNames), selectRandom(maleSurnames),
                    selectRandom(phoneNumbers), selectRandom(passengerCategories));
        }
        passengers.add(passenger);
        return passenger;
    }

    public Vehicle createVehicle() {
        String brand = selectRandom(brands.keySet().stream().toList());
        String type = selectRandom(brands.get(brand).keySet().stream().toList());
        FuelType fuelType = selectRandom(Arrays.stream(FuelType.values()).toList());
        Vehicle vehicle = new Vehicle(Integer.toString(Objects.hash(brand, type)), brand, type, brands.get(brand).get(type), (float) (randomInt(35, 200)) / 10, fuelType);
        vehicles.add(vehicle);
        return vehicle;
    }

    public List<PassengerCat> getPassengerCategories() {
        List<PassengerCat> list = new ArrayList<>();
        list.add(new PassengerCat("Firma A"));
        list.add(new PassengerCat("Firma B"));
        list.add(new PassengerCat("Rodina"));
        list.add(new PassengerCat("Milenky"));
        return list;
    }

    public Ride createRide() {
        LocalDate date = LocalDate.now().minusDays(random.nextInt(366));
        LocalTime time = LocalTime.of(0, 0, 0).plusHours(random.nextInt(24)).plusMinutes(random.nextInt(60));
        String from = selectRandom(destinations);
        String to = selectRandom(destinations.stream().filter(dest -> !from.equals(dest)).toList());
        Vehicle vehicle;
        ArrayList<Passenger> ridePassengers = new ArrayList<>();
        if (vehicles.size() == 0) {
            vehicle = createVehicle();
        } else {
            vehicle = selectRandom(vehicles);
        }
        if (passengers.size() < vehicle.getCapacity()) {
            for (int i = 0; i < vehicle.getCapacity() - passengers.size(); i++) {
                createPassenger();
            }
        }
        int passengersCount = randomInt(0, vehicle.getCapacity());
        for (int i = 0; i <= passengersCount; i++) {
            ridePassengers.add(selectRandom(passengers));
        }

        Ride ride = new Ride(date, time, null, from, to, randomInt(8, 500), selectRandom(rideCategories), ridePassengers, vehicle, Repetition.NONE, "");
        rides.add(ride);
        return ride;
    }

    private <T> T selectRandom(List<T> data) {
        int index = random.nextInt(data.size());
        return data.get(index);
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // getters

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Ride> getRides() {
        return rides;
    }
}
