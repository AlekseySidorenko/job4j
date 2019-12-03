package ru.job4j.parking;

/**
 * Class ParkService | Implement Car parking [#853]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 03.12.2019
 */
public class ParkService {
    private Parking parking;

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
            if (parkable.getSize() == 3) {
                if (parking.getTruckPlacesRest() > 0) {
                    parking.setTruckPlacesRest(parking.getTruckPlacesRest() - 1);
                } else {
                    parking.setPassengerCarPlacesRest(parking.getPassengerCarPlacesRest() - 3);
                }
            }
            if (parkable.getSize() == 1) {
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
        if (parkable.getSize() == 3) {
            if ((parking.getTruckPlacesRest() != 0) || (parking.getPassengerCarPlacesRest() >= 3)) {
                result = true;
            }
        }
        if (parkable.getSize() == 1) {
            if (parking.getPassengerCarPlacesRest() != 0) {
                result = true;
            }
        }
        return result;
    }
}