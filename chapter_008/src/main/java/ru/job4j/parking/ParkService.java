package ru.job4j.parking;

/**
 * Class ParkService | Implement Car parking [#853]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 03.12.2019
 */
public class ParkService {
    private Parking parking;
    private static final int PASSENGER_CAR_SIZE = 1;
    private static final int TRUCK_SIZE = 3;

    /** Constructor. */
    public ParkService(Parking parking) {
        this.parking = parking;
    }

    /**
     * Put car to parking.
     * @param parkable Car (passenger car or track).
     * @return True if car was parked.
     */
    public boolean park(Parkable parkable) {
        boolean result = false;
        if (checkFree(parkable)) {
            if (parkable.getSize() == TRUCK_SIZE) {
                if (parking.getTruckPlacesRest() > 0) {
                    parking.setTruckPlacesRest(parking.getTruckPlacesRest() - 1);
                } else {
                    parking.setPassengerCarPlacesRest(parking.getPassengerCarPlacesRest() - TRUCK_SIZE);
                }
            }
            if (parkable.getSize() == PASSENGER_CAR_SIZE) {
                parking.setPassengerCarPlacesRest(parking.getPassengerCarPlacesRest() - 1);
            }
            parking.getCars().add(parkable);
            result = true;
        }
        return result;
    }

    /**
     * Check parking for free place.
     * @param parkable Car (passenger car or track).
     * @return True if parking has free place.
     */
    public boolean checkFree(Parkable parkable) {
        boolean result = false;
        if (parkable.getSize() == TRUCK_SIZE) {
            if ((parking.getTruckPlacesRest() != 0) || (parking.getPassengerCarPlacesRest() >= TRUCK_SIZE)) {
                result = true;
            }
        }
        if (parkable.getSize() == PASSENGER_CAR_SIZE) {
            if (parking.getPassengerCarPlacesRest() != 0) {
                result = true;
            }
        }
        return result;
    }
}