package ru.job4j.parking;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Class ParkingTest | Implement Car parking [#853]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 27.11.2019
 */
public class ParkingTest {

    private Parkable scania = new Truck(0);
    private Parkable man = new Truck(1);
    private Parkable volvo = new Truck(2);
    private Parkable mersedes = new Truck(3);
    private Parkable rx350h = new PassengerCar(0);
    private Parkable prius = new PassengerCar(1);
    private Parkable tesla = new PassengerCar(2);
    private Parkable leaf = new PassengerCar(3);
    private Parkable insight = new PassengerCar(4);

    @Test
    public void whenTake2TrucksAnd4PassengerCarsThenParkingIsFull() {
        Parking parking = new Parking(2, 4);
        assertTrue(parking.takeCar(scania));
        assertTrue(parking.takeCar(man));
        assertTrue(parking.takeCar(rx350h));
        assertTrue(parking.takeCar(prius));
        assertTrue(parking.takeCar(tesla));
        assertTrue(parking.takeCar(leaf));
        assertFalse(parking.takeCar(insight));
        assertFalse(parking.takeCar(volvo));
    }

    @Test
    public void whenTake4PassengerCarsThenParkingCantTakeMorePassengerCars() {
        Parking parking = new Parking(2, 4);
        assertTrue(parking.takeCar(rx350h));
        assertTrue(parking.takeCar(prius));
        assertTrue(parking.takeCar(tesla));
        assertTrue(parking.takeCar(leaf));
        assertFalse(parking.takeCar(insight));
    }

    @Test
    public void whenTake3TrucksThenParkingCantTakeMoreTrucks() {
        Parking parking = new Parking(2, 4);
        assertTrue(parking.takeCar(scania));
        assertTrue(parking.takeCar(man));
        assertTrue(parking.takeCar(volvo));
        assertFalse(parking.takeCar(mersedes));
    }

    @Test
    public void whenTake3TrucksAnd1PassengerCarThenParkingCantTakeMorePassengerCars() {
        Parking parking = new Parking(2, 4);
        assertTrue(parking.takeCar(scania));
        assertTrue(parking.takeCar(man));
        assertTrue(parking.takeCar(volvo));
        assertTrue(parking.takeCar(rx350h));
        assertFalse(parking.takeCar(insight));
    }
}