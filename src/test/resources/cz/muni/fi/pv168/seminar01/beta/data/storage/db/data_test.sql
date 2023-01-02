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

-- Testing Rides
INSERT INTO Ride (date,
                  departure,
                  arrival,
                  startDest,
                  endDest,
                  distance,
                  vehicle,
                  repetition,
                  description)
VALUES  ('2021-12-01', '10:59:00.0000000', '11:59:00.0000000', 'Vilhemovice', 'Budapest', 15, 3, 'weekly', 'nezapomen si prášky'),
        ('2022-11-03', '06:12:00.0000000', '06:45:00.0000000', 'Plana nad Luznou', 'Klenovice', 42, 3, 'none', 'vyzvednou babicku'),
        ('2019-06-04', '18:00:00.0000000', '23:15:00.0000000', 'Slezske muzeum', 'Opava', 75, 2, 'monthly', 'v 5:35 jednou z pravidelnich linek 7 zastavek do Katerinek')
;

-- Testing Passenger Categories
INSERT INTO PassengerCategory (name)
VALUES  ('Přátelé'),
        ('Rodina'),
        ('Práce')
;

-- Testing Rides Categories
INSERT INTO RideCategory (name)
VALUES  ('Pracovní'),
        ('Tajné'),
        ('Zábava')
;
