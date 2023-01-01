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

-- -- Testing
--
-- INSERT INTO Employee (firstName,
--                       lastName,
--                       userName,
--                       birthDate,
--                       gender,
--                       departmentId)
-- VALUES ('Jonas', 'Kahnwald', 'jkahnwal', '1999-12-28', 'male', 1),
--        ('Martha', 'Nielsen', 'mnielsen', '2000-12-10', 'female', 1),
--        ('Hanno', 'Tauber', 'htauber', '1921-07-27', 'male', 2),
--        ('Charlotte', 'Doppler', 'cdoppler', '2000-02-05', 'female', 2)
-- ;
