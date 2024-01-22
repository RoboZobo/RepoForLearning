import planes.ExperimentalPlane;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlaneList() {
        List<PassengerPlane> passengerPlaneList = new ArrayList<>();
        for (Plane plane : this.planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlaneList.add((PassengerPlane) plane);
            }
        }
        return passengerPlaneList;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlaneList().stream()
                .max(Comparator.comparing(PassengerPlane::getPassengersCapacity))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanes().stream()
                .filter(plane -> plane.getType().equals(MilitaryType.TRANSPORT))
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanes().stream()
                .filter(plane -> plane.getType().equals(MilitaryType.BOMBER))
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public Airport sortByMaxDistance() {
        this.planes = getPlanes().stream()
                .sorted(Comparator.comparing(Plane::getMaxFlightDistance))
                .collect(Collectors.toList());
        return this;
    }

    public Airport sortByMaxSpeed() {
        this.planes = getPlanes().stream()
                .sorted(Comparator.comparing(Plane::getMaxSpeed))
                .collect(Collectors.toList());
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        this.planes = getPlanes().stream()
                .sorted(Comparator.comparing(Plane::getMaxLoadCapacity))
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
