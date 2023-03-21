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

INSERT INTO continent(name)  VALUES ('Australia');
INSERT INTO continent(name)  VALUES ('Asia');
INSERT INTO continent(name)  VALUES ('Africa');
INSERT INTO continent(name)  VALUES ('Europe');
INSERT INTO continent(name)  VALUES ('North America');
INSERT INTO continent(name)  VALUES ('South America');

--Australia
INSERT INTO country(name, continent) VALUES ('Eastern Australia', 1);
INSERT INTO country(name, continent) VALUES ('Indonesia', 1);
INSERT INTO country(name, continent) VALUES ('New Guinea', 1);
INSERT INTO country(name, continent) VALUES ('Western Australia', 1);

--Asia
INSERT INTO country(name, continent) VALUES ('Afghanistan', 2);
INSERT INTO country(name, continent) VALUES ('China', 2);
INSERT INTO country(name, continent) VALUES ('India', 2);
INSERT INTO country(name, continent) VALUES ('Irkutsk', 2);
INSERT INTO country(name, continent) VALUES ('Japan', 2);
INSERT INTO country(name, continent) VALUES ('Kamchatka', 2);
INSERT INTO country(name, continent) VALUES ('Middle East', 2);
INSERT INTO country(name, continent) VALUES ('Mongolia', 2);
INSERT INTO country(name, continent) VALUES ('Siam (Southeast Asia)', 2);
INSERT INTO country(name, continent) VALUES ('Siberia', 2);
INSERT INTO country(name, continent) VALUES ('Ural', 2);
INSERT INTO country(name, continent) VALUES ('Yakutsk', 2);

--Africa
INSERT INTO country(name, continent) VALUES ('Congo (Central Africa)', 3);
INSERT INTO country(name, continent) VALUES ('East Africa', 3);
INSERT INTO country(name, continent) VALUES ('Egypt', 3);
INSERT INTO country(name, continent) VALUES ('Madagascar', 3);
INSERT INTO country(name, continent) VALUES ('North Africa', 3);
INSERT INTO country(name, continent) VALUES ('South Africa', 3);

--Europe
INSERT INTO country(name, continent) VALUES ('Great Britain (Great Britain & Ireland)', 4);
INSERT INTO country(name, continent) VALUES ('Iceland', 4);
INSERT INTO country(name, continent) VALUES ('Northern Europe', 4);
INSERT INTO country(name, continent) VALUES ('Scandinavia', 4);
INSERT INTO country(name, continent) VALUES ('Southern Europe', 4);
INSERT INTO country(name, continent) VALUES ('Ukraine (Eastern Europe, Russia)', 4);
INSERT INTO country(name, continent) VALUES ('Western Europe', 4);

--North America
INSERT INTO country(name, continent) VALUES ('Alaska', 5);
INSERT INTO country(name, continent) VALUES ('Alberta (Western Canada)', 5);
INSERT INTO country(name, continent) VALUES ('Central America', 5);
INSERT INTO country(name, continent) VALUES ('Eastern United States', 5);
INSERT INTO country(name, continent) VALUES ('Greenland', 5);
INSERT INTO country(name, continent) VALUES ('Northwest Territory', 5);
INSERT INTO country(name, continent) VALUES ('Ontario (Central Canada)', 5);
INSERT INTO country(name, continent) VALUES ('Quebec (Eastern Canada)', 5);
INSERT INTO country(name, continent) VALUES ('Western United States', 5);

--South America
INSERT INTO country(name, continent) VALUES ('Argentina', 6);
INSERT INTO country(name, continent) VALUES ('Brazil', 6);
INSERT INTO country(name, continent) VALUES ('Peru', 6);
INSERT INTO country(name, continent) VALUES ('Venezuela', 6);
