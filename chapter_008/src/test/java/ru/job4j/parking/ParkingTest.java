package ru.job4j.parking;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;

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
        parking.takeCar(scania);
        parking.takeCar(man);
        parking.takeCar(rx350h);
        parking.takeCar(prius);
        parking.takeCar(tesla);
        parking.takeCar(leaf);
        assertFalse(parking.takeCar(insight));
        assertFalse(parking.takeCar(volvo));
    }

    @Test
    public void whenTake4PassengerCarsThenParkingCantTakeMorePassengerCars() {
        Parking parking = new Parking(2, 4);
        parking.takeCar(rx350h);
        parking.takeCar(prius);
        parking.takeCar(tesla);
        parking.takeCar(leaf);
        assertFalse(parking.takeCar(insight));
    }

    @Test
    public void whenTake3TrucksThenParkingCantTakeMoreTrucks() {
        Parking parking = new Parking(2, 4);
        parking.takeCar(scania);
        parking.takeCar(man);
        parking.takeCar(volvo);
        assertFalse(parking.takeCar(mersedes));
    }

    @Test
    public void whenTake3TrucksAnd1PassengerCarThenParkingCantTakeMorePassengerCars() {
        Parking parking = new Parking(2, 4);
        parking.takeCar(scania);
        parking.takeCar(man);
        parking.takeCar(volvo);
        parking.takeCar(rx350h);
        assertFalse(parking.takeCar(insight));
    }
}