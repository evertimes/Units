DROP schema my_schema CASCADE ;
CREATE schema my_schema;
SET schema 'my_schema';
CREATE TABLE addresses
(
    id      INTEGER PRIMARY KEY,
    address varchar(50),
    deleted boolean DEFAULT false,
    version int DEFAULT 0
);

CREATE TABLE cars
(
    id     INTEGER PRIMARY KEY,
    number VARCHAR(8),
    brand  VARCHAR(30)
);

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY,
    name       VARCHAR(50),
    address_id INTEGER,
    CONSTRAINT fk_users_to_address
        FOREIGN KEY (address_id)
            REFERENCES addresses (id)
);

CREATE TABLE users_cars
(
    user_id INTEGER,
    car_id  INTEGER,
    PRIMARY KEY (user_id, car_id),
    CONSTRAINT fk_to_cars
        FOREIGN KEY (car_id)
            REFERENCES cars (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_to_users
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);

INSERT INTO addresses
VALUES (1, 'Moscow, Tverskaya 2');
INSERT INTO addresses
VALUES (2, 'Ryazan, Frunze 15');
INSERT INTO addresses
VALUES (3, 'Nizhniy Novgorod, Lenina 4');
INSERT INTO addresses
VALUES (4, 'St. Petersburg, Nevsky Pr. 9');
INSERT INTO addresses
VALUES (5, 'Ryazan, Esenina 91');

INSERT INTO cars
VALUES (1, 'A213BC', 'BMW');
INSERT INTO cars
VALUES (2, 'E213BC', 'Mercedes');
INSERT INTO cars
VALUES (3, 'A200HC', 'BMW');
INSERT INTO cars
VALUES (4, 'C073BC', 'Kia');
INSERT INTO cars
VALUES (5, 'A103ET', 'Lada');
INSERT INTO cars
VALUES (6, 'T523CT', 'BMW');

INSERT INTO users
VALUES (1, 'Andrey', 1);
INSERT INTO users
VALUES (2, 'Maxim', 1);
INSERT INTO users
VALUES (3, 'Daria', 2);
INSERT INTO users
VALUES (4, 'Victoria', 3);
INSERT INTO users
VALUES (5, 'Evgeniy', 4);

INSERT INTO users_cars
VALUES (1, 1);
INSERT INTO users_cars
VALUES (1, 4);
INSERT INTO users_cars
VALUES (2, 2);
INSERT INTO users_cars
VALUES (3, 3);
INSERT INTO users_cars
VALUES (4, 5);
INSERT INTO users_cars
VALUES (5, 6);
