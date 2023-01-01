package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.RideCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.RideDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.VehicleDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.RideCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.RideMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.VehicleMapper;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class RideRepository extends AbstractRepository<Ride>{
    private final RideMapper mapper;
    private final RideDao dao;
    private final RideCategoryMapper categoryMapper;
    private final RideCategoryDao categoryDao;
    private final VehicleMapper vehicleMapper;
    private final VehicleDao vehicleDao;
    private final PassengerMapper passengerMapper;
    private final PassengerDao passengerDao;


    public RideRepository(RideMapper mapper, RideDao dao, RideCategoryMapper categoryMapper, RideCategoryDao categoryDao, VehicleMapper vehicleMapper, VehicleDao vehicleDao, PassengerMapper passengerMapper, PassengerDao passengerDao) {
        super();
        this.mapper = mapper;
        this.dao = dao;
        this.categoryMapper = categoryMapper;
        this.categoryDao = categoryDao;
        this.vehicleMapper = vehicleMapper;
        this.vehicleDao = vehicleDao;
        this.passengerMapper = passengerMapper;
        this.passengerDao = passengerDao;
        refresh();
    }

    @Override
    public void refresh() {
        repositoryMembers = fetchAll();
    }

    @Override
    public void create(Ride ride) {
        createAndGetID(ride);
    }

    @Override
    public long createAndGetID(Ride ride) {
        Ride newRide = Stream.of(ride)
                .map(mapper::mapToEntity)
                .map(dao::create)
                .map(mapper::mapToModel)
                .findFirst().orElse(null);
        newRide.setCategories(ride.getCategories());
        newRide.setPassengers(ride.getPassengers());
        newRide.setVehicle(ride.getVehicle());
        dao.createCategoryJoins(newRide.getId(), ride.getCategories().stream().toList());
        dao.createPassengerJoins(newRide.getId(), ride.getPassengers().stream().toList());
        repositoryMembers.add(newRide);
        return newRide.getId();
    }

    @Override
    public void update(Ride ride) {
        int index = repositoryMembers.indexOf(ride);
        Ride newRide = Stream.of(ride)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel)
                .findFirst().orElse(null);

        newRide.setVehicle(ride.getVehicle());
        dao.deleteRideCategories(ride.getId());
        dao.createCategoryJoins(ride.getId(), ride.getCategories().stream().toList());
        dao.deletePassengers(ride.getId());
        dao.createPassengerJoins(ride.getId(), ride.getPassengers().stream().toList());
        newRide.setCategories(ride.getCategories());
        newRide.setPassengers(ride.getPassengers());
        repositoryMembers.set(index, newRide);
    }

    @Override
    public void deleteByIndex(int index) {
        Ride ride = repositoryMembers.get(index);
        dao.deleteRideCategories(ride.getId());
        dao.deletePassengers(ride.getId());
        dao.deleteById(ride.getId());
        repositoryMembers.remove(index);
    }

    private List<RideCategory> findCategoriesForRide(Ride ride) {
        return dao.findCategoriesByRideId(ride.getId())
                .stream()
                .map(categoryDao::findById)
                .map(Optional::orElseThrow)
                .map(categoryMapper::mapToModel)
                .toList();
    }

    private List<Passenger> findPassengersForRide(Ride ride) {
        return dao.findPassengersByRideId(ride.getId())
                .stream()
                .map(passengerDao::findById)
                .map(Optional::orElseThrow)
                .map(passengerMapper::mapToModel)
                .toList();
    }

    private List<Ride> fetchAll() {
        List<Ride> newRides = new ArrayList<>();
        List<Ride> rides = dao.findAll()
                .stream()
                .map(x -> mapper.mapToModel(x, vehicleMapper, vehicleDao))
                .toList();
        for(Ride ride : rides) {
            ride.setCategories(findCategoriesForRide(ride));
            ride.setPassengers(findPassengersForRide(ride));
            newRides.add(ride);
        }
        return newRides;
    }
}
