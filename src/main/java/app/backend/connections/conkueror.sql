CREATE DATABASE conkueror;

CREATE TABLE continent (
    id SERIAL,
    name VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE country (
    id SERIAL,
    name VARCHAR(255),
    continent INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (continent) REFERENCES continent(id)
);