CREATE TABLE continent (
    id SERIAL,
    name VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE country (
    id SERIAL,
    name VARCHAR(255),
    continent_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (continent_id) REFERENCES continent(id)
);

INSERT INTO continent(name)  VALUES ('Australia');
INSERT INTO continent(name)  VALUES ('Asia');
INSERT INTO continent(name)  VALUES ('Africa');
INSERT INTO continent(name)  VALUES ('Europe');
INSERT INTO continent(name)  VALUES ('North America');
INSERT INTO continent(name)  VALUES ('South America');

--Australia
INSERT INTO country(name, continent_id) VALUES ('Eastern Australia', 1);
INSERT INTO country(name, continent_id) VALUES ('Indonesia', 1);
INSERT INTO country(name, continent_id) VALUES ('New Guinea', 1);
INSERT INTO country(name, continent_id) VALUES ('Western Australia', 1);

--Asia
INSERT INTO country(name, continent_id) VALUES ('Afghanistan', 2);
INSERT INTO country(name, continent_id) VALUES ('China', 2);
INSERT INTO country(name, continent_id) VALUES ('India', 2);
INSERT INTO country(name, continent_id) VALUES ('Irkutsk', 2);
INSERT INTO country(name, continent_id) VALUES ('Japan', 2);
INSERT INTO country(name, continent_id) VALUES ('Kamchatka', 2);
INSERT INTO country(name, continent_id) VALUES ('Middle East', 2);
INSERT INTO country(name, continent_id) VALUES ('Mongolia', 2);
INSERT INTO country(name, continent_id) VALUES ('Siam (Southeast Asia)', 2);
INSERT INTO country(name, continent_id) VALUES ('Siberia', 2);
INSERT INTO country(name, continent_id) VALUES ('Ural', 2);
INSERT INTO country(name, continent_id) VALUES ('Yakutsk', 2);

--Africa
INSERT INTO country(name, continent_id) VALUES ('Congo (Central Africa)', 3);
INSERT INTO country(name, continent_id) VALUES ('East Africa', 3);
INSERT INTO country(name, continent_id) VALUES ('Egypt', 3);
INSERT INTO country(name, continent_id) VALUES ('Madagascar', 3);
INSERT INTO country(name, continent_id) VALUES ('North Africa', 3);
INSERT INTO country(name, continent_id) VALUES ('South Africa', 3);

--Europe
INSERT INTO country(name, continent_id) VALUES ('Great Britain (Great Britain & Ireland)', 4);
INSERT INTO country(name, continent_id) VALUES ('Iceland', 4);
INSERT INTO country(name, continent_id) VALUES ('Northern Europe', 4);
INSERT INTO country(name, continent_id) VALUES ('Scandinavia', 4);
INSERT INTO country(name, continent_id) VALUES ('Southern Europe', 4);
INSERT INTO country(name, continent_id) VALUES ('Ukraine (Eastern Europe, Russia)', 4);
INSERT INTO country(name, continent_id) VALUES ('Western Europe', 4);

--North America
INSERT INTO country(name, continent_id) VALUES ('Alaska', 5);
INSERT INTO country(name, continent_id) VALUES ('Alberta (Western Canada)', 5);
INSERT INTO country(name, continent_id) VALUES ('Central America', 5);
INSERT INTO country(name, continent_id) VALUES ('Eastern United States', 5);
INSERT INTO country(name, continent_id) VALUES ('Greenland', 5);
INSERT INTO country(name, continent_id) VALUES ('Northwest Territory', 5);
INSERT INTO country(name, continent_id) VALUES ('Ontario (Central Canada)', 5);
INSERT INTO country(name, continent_id) VALUES ('Quebec (Eastern Canada)', 5);
INSERT INTO country(name, continent_id) VALUES ('Western United States', 5);

--South America
INSERT INTO country(name, continent_id) VALUES ('Argentina', 6);
INSERT INTO country(name, continent_id) VALUES ('Brazil', 6);
INSERT INTO country(name, continent_id) VALUES ('Peru', 6);
INSERT INTO country(name, continent_id) VALUES ('Venezuela', 6);
