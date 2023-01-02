--
-- Testing data - used in the tests
--

-- Testing Vehicles
INSERT INTO Vehicle (licensePlate,
                     brand,
                     type,
                     capacity,
                     consumption,
                     fuelType)
VALUES  ('3C55448', 'BMW', 'X5', 5, 10.1, 'GASOLINE'),
        ('BL187AR', 'Škoda', 'Fabia', 5, 5.5, 'DIESEL'),
        ('7H43223', 'Škoda', 'Yetti', 7, 7.4, 'CNG')
;

-- Testing Passengers
INSERT INTO Passenger (firstName,
                       lastName,
                       phoneNumber)
VALUES  ('Jana', 'Sedmohorská', '7894561567'),
        ('Dominika', 'Verterská', '+420145687245'),
        ('Peter', 'Mahan', '7458953214')
;

