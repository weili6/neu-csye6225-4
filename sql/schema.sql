-- H2 Schema
DROP TABLE IF EXISTS user;
CREATE TABLE user (
        userId IDENTITY,  -- auto-increment
        username VARCHAR(20),
        password VARCHAR(40),
        firstName VARCHAR(40),
        lastName VARCHAR(40),
        email VARCHAR(40),
        phone varchar(25),
        address VARCHAR(100),
        PRIMARY KEY (userId)
        );

DROP TABLE IF EXISTS spot;
CREATE TABLE spot (
        spotId IDENTITY,
        ownerId INT,
        rate NUMERIC(20, 2), -- per hour
        size NUMERIC(2,1), -- e.g. 7.5, 8, 8.5, 9 ft
        address VARCHAR(100),
        PRIMARY KEY (spotId),
        FOREIGN KEY (ownerId) REFERENCES user(userId)
        );

DROP TABLE IF EXISTS booking;
CREATE TABLE booking (
        spotId IDENTITY,
        renterId INT,
        rate NUMERIC(20, 2),
        total NUMERIC(20, 2),
        orderedOn TIMESTAMP,
        startTime TIMESTAMP,
        endTime TIMESTAMP,
        FOREIGN KEY (renterId) REFERENCES user(userId),
        FOREIGN KEY (spotId) REFERENCES spot(spotId)
        );
