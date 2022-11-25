--
-- RideCategory table definition
--
CREATE TABLE IF NOT EXISTS RideCategory
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(50) NOT NULL
);

--
-- PassengerCategory table definition
--
CREATE TABLE IF NOT EXISTS PassengerCategory
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(50) NOT NULL
);

--
-- Passenger table definition
--
CREATE TABLE IF NOT EXISTS Passenger
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    firstName   VARCHAR(50)     NOT NULL,
    lastName    VARCHAR(50)     NOT NULL,
    phoneNumber VARCHAR(20)     NOT NULL
);

--
-- Fuel table definition
--
CREATE TABLE IF NOT EXISTS Fuel
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    price   DECIMAL NOT NULL
);

--
-- Vehicle table definition
--
CREATE TABLE IF NOT EXISTS Vehicle
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    licensePlate    VARCHAR(10) NOT NULL,
    brand           VARCHAR(50) NOT NULL,
    type            VARCHAR(50) NOT NULL,
    capacity        INT         NOT NULL,
    consumption     DOUBLE      NOT NULL,
    fuelType        BIGINT REFERENCES Fuel (id)
);

--
-- Ride table definition
--
CREATE TABLE IF NOT EXISTS Ride
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    date       DATE        NOT NULL,
    departure  TIME        NOT NULL,
    arrival    TIME        NOT NULL,
    startDest  VARCHAR(50) NOT NULL,
    endDest    VARCHAR(50) NOT NULL,
    distance   INT         NOT NULL,
    vehicle    BIGINT REFERENCES Vehicle (id),
    repetition ENUM('none', 'daily', 'weekly', 'monthly', 'yearly') NOT NULL
);

--
-- RideToRideCategory table definition (many-to-many)
--
CREATE TABLE IF NOT EXISTS RideCategories
(
    rideId          BIGINT REFERENCES Ride (id),
    rideCategoryId  BIGINT REFERENCES RideCategory (id)
);

--
-- RideToPassenger table definition (many-to-many)
--
CREATE TABLE IF NOT EXISTS RidePassengers
(
    rideId      BIGINT REFERENCES Ride (id),
    passenger   BIGINT REFERENCES Passenger (id)
);

--
-- PassengerToPassengerCategory table definition (many-to-many)
--
CREATE TABLE IF NOT EXISTS PassengerCategories
(
    passengerId           BIGINT REFERENCES Passenger (id),
    passengerCategoryId   BIGINT REFERENCES PassengerCategory (id)
);
