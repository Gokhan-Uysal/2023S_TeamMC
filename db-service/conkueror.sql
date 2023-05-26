DROP TABLE IF EXISTS continent cascade;
CREATE TABLE continent (
    id SERIAL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS country cascade;
CREATE TABLE country (
    id SERIAL,
    name VARCHAR(255),
    position_x INT NOT NULL,
    position_y INT NOT NULL,
    continent_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (continent_id) REFERENCES continent(id)
);

DROP TABLE IF EXISTS adjacent_country cascade;
CREATE TABLE adjacent_country (
    country_id INT NOT NULL,
    adjacent_country_id INT NOT NULL,
    PRIMARY KEY (country_id, adjacent_country_id),
    FOREIGN KEY (adjacent_country_id) REFERENCES country(id),
    CHECK (country_id <> adjacent_country.adjacent_country_id)
);

-- Continents
INSERT INTO continent(name)  VALUES ('Australia');
INSERT INTO continent(name)  VALUES ('Asia');
INSERT INTO continent(name)  VALUES ('Africa');
INSERT INTO continent(name)  VALUES ('Europe');
INSERT INTO continent(name)  VALUES ('North America');
INSERT INTO continent(name)  VALUES ('South America');

-- Insert countries
INSERT INTO country (name, position_x, position_y, continent_id)
VALUES
    -- Australia
    ('Eastern Australia', 810, 445, 1),
    ('Indonesia', 696, 393, 1),
    ('New Guinea', 789, 383, 1),
    ('Western Australia', 752, 459, 1),
    -- South America
    ('Argentina', 208, 419, 6),
    ('Brazil', 195, 337, 6),
    ('Peru', 175, 350, 6),
    ('Venezuela', 181, 309, 6),
    -- Asia
    ('Afghanistan', 563, 168, 2),
    ('China', 644, 185, 2),
    ('India', 613, 248, 2),
    ('Irkutsk', 686, 105, 2),
    ('Japan', 797, 156, 2),
    ('Kamchatka', 746, 51, 2),
    ('Middle East', 493, 257, 2),
    ('Mongolia', 693, 156, 2),
    ('Siam (Southeast Asia)', 700, 294, 2),
    ('Siberia', 618, 24, 2),
    ('Ural', 599, 52, 2),
    ('Yakutsk', 699, 37, 2),
    -- Africa
    ('Congo (Central Africa)', 452, 394, 3),
    ('East Africa', 495, 355, 3),
    ('Egypt', 454, 311, 3),
    ('Madagascar', 556, 480, 3),
    ('North Africa', 376, 293, 3),
    ('South Africa', 462, 451, 3),
    -- Europe
    ('Great Britain (Great Britain & Ireland)', 345, 140, 4),
    ('Iceland', 370, 100, 4),
    ('Northern Europe', 420, 152, 4),
    ('Scandinavia', 430, 70, 4),
    ('Southern Europe', 428, 210, 4),
    ('Ukraine (Eastern Europe, Russia)', 483, 73, 4),
    ('Western Europe', 368, 210, 4),
    -- North America
    ('Alaska', 30, 60, 5),
    ('Alberta (Western Canada)', 103, 108, 5),
    ('Central America', 117, 234, 5),
    ('Eastern United States', 160, 168, 5),
    ('Greenland', 250, 15, 5),
    ('Northwest Territory', 95, 47, 5),
    ('Ontario (Central Canada)', 175, 110, 5),
    ('Quebec (Eastern Canada)', 230, 106, 5),
    ('Western United States', 110, 166, 5);

-- Australia
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Eastern Australia
    ((SELECT id FROM country WHERE name = 'Eastern Australia'), (SELECT id FROM country WHERE name = 'Western Australia')),
    ((SELECT id FROM country WHERE name = 'Eastern Australia'), (SELECT id FROM country WHERE name = 'New Guinea')),
    -- Indonesia
    ((SELECT id FROM country WHERE name = 'Indonesia'), (SELECT id FROM country WHERE name = 'New Guinea')),
    ((SELECT id FROM country WHERE name = 'Indonesia'), (SELECT id FROM country WHERE name = 'Siam (Southeast Asia)')),
    ((SELECT id FROM country WHERE name = 'Indonesia'), (SELECT id FROM country WHERE name = 'Western Australia')),
    -- New Guinea
    ((SELECT id FROM country WHERE name = 'New Guinea'), (SELECT id FROM country WHERE name = 'Eastern Australia')),
    ((SELECT id FROM country WHERE name = 'New Guinea'), (SELECT id FROM country WHERE name = 'Indonesia')),
    ((SELECT id FROM country WHERE name = 'New Guinea'), (SELECT id FROM country WHERE name = 'Western Australia')),
    -- Western Australia
    ((SELECT id FROM country WHERE name = 'Western Australia'), (SELECT id FROM country WHERE name = 'Eastern Australia')),
    ((SELECT id FROM country WHERE name = 'Western Australia'), (SELECT id FROM country WHERE name = 'Indonesia')),
    ((SELECT id FROM country WHERE name = 'Western Australia'), (SELECT id FROM country WHERE name = 'New Guinea'));

-- South America
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Argentina
    ((SELECT id FROM country WHERE name = 'Argentina'), (SELECT id FROM country WHERE name = 'Brazil')),
    ((SELECT id FROM country WHERE name = 'Argentina'), (SELECT id FROM country WHERE name = 'Peru')),
    -- Brazil
    ((SELECT id FROM country WHERE name = 'Brazil'), (SELECT id FROM country WHERE name = 'Argentina')),
    ((SELECT id FROM country WHERE name = 'Brazil'), (SELECT id FROM country WHERE name = 'Peru')),
    ((SELECT id FROM country WHERE name = 'Brazil'), (SELECT id FROM country WHERE name = 'Venezuela')),
    -- Peru
    ((SELECT id FROM country WHERE name = 'Peru'), (SELECT id FROM country WHERE name = 'Argentina')),
    ((SELECT id FROM country WHERE name = 'Peru'), (SELECT id FROM country WHERE name = 'Brazil')),
    ((SELECT id FROM country WHERE name = 'Peru'), (SELECT id FROM country WHERE name = 'Venezuela')),
    -- Venezuela
    ((SELECT id FROM country WHERE name = 'Venezuela'), (SELECT id FROM country WHERE name = 'Brazil')),
    ((SELECT id FROM country WHERE name = 'Venezuela'), (SELECT id FROM country WHERE name = 'Peru'));

-- Asia
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Afghanistan
    ((SELECT id FROM country WHERE name = 'Afghanistan'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'Afghanistan'), (SELECT id FROM country WHERE name = 'India')),
    ((SELECT id FROM country WHERE name = 'Afghanistan'), (SELECT id FROM country WHERE name = 'Middle East')),
    ((SELECT id FROM country WHERE name = 'Afghanistan'), (SELECT id FROM country WHERE name = 'Ural')),
    -- China
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'Afghanistan')),
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'India')),
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'Mongolia')),
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'Siam (Southeast Asia)')),
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'Siberia')),
    ((SELECT id FROM country WHERE name = 'China'), (SELECT id FROM country WHERE name = 'Ural')),
    -- India
    ((SELECT id FROM country WHERE name = 'India'), (SELECT id FROM country WHERE name = 'Afghanistan')),
    ((SELECT id FROM country WHERE name = 'India'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'India'), (SELECT id FROM country WHERE name = 'Middle East')),
    ((SELECT id FROM country WHERE name = 'India'), (SELECT id FROM country WHERE name = 'Siam (Southeast Asia)')),
    -- Irkutsk
    ((SELECT id FROM country WHERE name = 'Irkutsk'), (SELECT id FROM country WHERE name = 'Kamchatka')),
    ((SELECT id FROM country WHERE name = 'Irkutsk'), (SELECT id FROM country WHERE name = 'Mongolia')),
    ((SELECT id FROM country WHERE name = 'Irkutsk'), (SELECT id FROM country WHERE name = 'Siberia')),
    ((SELECT id FROM country WHERE name = 'Irkutsk'), (SELECT id FROM country WHERE name = 'Yakutsk')),
    -- Japan
    ((SELECT id FROM country WHERE name = 'Japan'), (SELECT id FROM country WHERE name = 'Kamchatka')),
    ((SELECT id FROM country WHERE name = 'Japan'), (SELECT id FROM country WHERE name = 'Mongolia')),
    -- Kamchatka
    ((SELECT id FROM country WHERE name = 'Kamchatka'), (SELECT id FROM country WHERE name = 'Alaska')),
    ((SELECT id FROM country WHERE name = 'Kamchatka'), (SELECT id FROM country WHERE name = 'Irkutsk')),
    ((SELECT id FROM country WHERE name = 'Kamchatka'), (SELECT id FROM country WHERE name = 'Japan')),
    ((SELECT id FROM country WHERE name = 'Kamchatka'), (SELECT id FROM country WHERE name = 'Mongolia')),
    ((SELECT id FROM country WHERE name = 'Kamchatka'), (SELECT id FROM country WHERE name = 'Yakutsk')),
    -- Middle East
    ((SELECT id FROM country WHERE name = 'Middle East'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'Middle East'), (SELECT id FROM country WHERE name = 'Egypt')),
    ((SELECT id FROM country WHERE name = 'Middle East'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    ((SELECT id FROM country WHERE name = 'Middle East'), (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
    -- Mongolia
    ((SELECT id FROM country WHERE name = 'Mongolia'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'Mongolia'), (SELECT id FROM country WHERE name = 'Irkutsk')),
    ((SELECT id FROM country WHERE name = 'Mongolia'), (SELECT id FROM country WHERE name = 'Japan')),
    ((SELECT id FROM country WHERE name = 'Mongolia'), (SELECT id FROM country WHERE name = 'Kamchatka')),
    ((SELECT id FROM country WHERE name = 'Mongolia'), (SELECT id FROM country WHERE name = 'Siberia')),
    -- Siam (Southeast Asia)
    ((SELECT id FROM country WHERE name = 'Siam (Southeast Asia)'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'Siam (Southeast Asia)'), (SELECT id FROM country WHERE name = 'India')),
    ((SELECT id FROM country WHERE name = 'Siam (Southeast Asia)'), (SELECT id FROM country WHERE name = 'Indonesia')),
    -- Siberia
    ((SELECT id FROM country WHERE name = 'Siberia'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'Siberia'), (SELECT id FROM country WHERE name = 'Irkutsk')),
    ((SELECT id FROM country WHERE name = 'Siberia'), (SELECT id FROM country WHERE name = 'Mongolia')),
    ((SELECT id FROM country WHERE name = 'Siberia'), (SELECT id FROM country WHERE name = 'Ural')),
    ((SELECT id FROM country WHERE name = 'Siberia'), (SELECT id FROM country WHERE name = 'Yakutsk')),
    -- Ural
    ((SELECT id FROM country WHERE name = 'Ural'), (SELECT id FROM country WHERE name = 'Afghanistan')),
    ((SELECT id FROM country WHERE name = 'Ural'), (SELECT id FROM country WHERE name = 'China')),
    ((SELECT id FROM country WHERE name = 'Ural'), (SELECT id FROM country WHERE name = 'Siberia')),
    ((SELECT id FROM country WHERE name = 'Ural'), (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
    -- Yakutsk
    ((SELECT id FROM country WHERE name = 'Yakutsk'), (SELECT id FROM country WHERE name = 'Irkutsk')),
    ((SELECT id FROM country WHERE name = 'Yakutsk'), (SELECT id FROM country WHERE name = 'Kamchatka')),
    ((SELECT id FROM country WHERE name = 'Yakutsk'), (SELECT id FROM country WHERE name = 'Siberia'));

-- Africa
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Congo (Central Africa)
    ((SELECT id FROM country WHERE name = 'Congo (Central Africa)'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'Congo (Central Africa)'), (SELECT id FROM country WHERE name = 'North Africa')),
    ((SELECT id FROM country WHERE name = 'Congo (Central Africa)'), (SELECT id FROM country WHERE name = 'South Africa')),
    -- East Africa
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'Congo (Central Africa)')),
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'Egypt')),
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'Madagascar')),
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'Middle East')),
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'North Africa')),
    ((SELECT id FROM country WHERE name = 'East Africa'), (SELECT id FROM country WHERE name = 'South Africa')),
    -- Egypt
    ((SELECT id FROM country WHERE name = 'Egypt'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'Egypt'), (SELECT id FROM country WHERE name = 'Middle East')),
    ((SELECT id FROM country WHERE name = 'Egypt'), (SELECT id FROM country WHERE name = 'North Africa')),
    ((SELECT id FROM country WHERE name = 'Egypt'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    -- Madagascar
    ((SELECT id FROM country WHERE name = 'Madagascar'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'Madagascar'), (SELECT id FROM country WHERE name = 'South Africa')),
    -- North Africa
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'Brazil')),
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'Congo (Central Africa)')),
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'Egypt')),
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    ((SELECT id FROM country WHERE name = 'North Africa'), (SELECT id FROM country WHERE name = 'Western Europe')),
    -- South Africa
    ((SELECT id FROM country WHERE name = 'South Africa'), (SELECT id FROM country WHERE name = 'Congo (Central Africa)')),
    ((SELECT id FROM country WHERE name = 'South Africa'), (SELECT id FROM country WHERE name = 'East Africa')),
    ((SELECT id FROM country WHERE name = 'South Africa'), (SELECT id FROM country WHERE name = 'Madagascar'));

-- Europe
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Great Britain (Great Britain & Ireland)
    ((SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)'), (SELECT id FROM country WHERE name = 'Iceland')),
    ((SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)'), (SELECT id FROM country WHERE name = 'Northern Europe')),
    ((SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)'), (SELECT id FROM country WHERE name = 'Scandinavia')),
    ((SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)'), (SELECT id FROM country WHERE name = 'Western Europe')),
    -- Iceland
    ((SELECT id FROM country WHERE name = 'Iceland'), (SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)')),
    ((SELECT id FROM country WHERE name = 'Iceland'), (SELECT id FROM country WHERE name = 'Greenland')),
    ((SELECT id FROM country WHERE name = 'Iceland'), (SELECT id FROM country WHERE name = 'Scandinavia')),
    -- Northern Europe
    ((SELECT id FROM country WHERE name = 'Northern Europe'), (SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)')),
    ((SELECT id FROM country WHERE name = 'Northern Europe'), (SELECT id FROM country WHERE name = 'Scandinavia')),
    ((SELECT id FROM country WHERE name = 'Northern Europe'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    ((SELECT id FROM country WHERE name = 'Northern Europe'), (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
    ((SELECT id FROM country WHERE name = 'Northern Europe'), (SELECT id FROM country WHERE name = 'Western Europe')),
    -- Scandinavia
    ((SELECT id FROM country WHERE name = 'Scandinavia'), (SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)')),
    ((SELECT id FROM country WHERE name = 'Scandinavia'), (SELECT id FROM country WHERE name = 'Iceland')),
    ((SELECT id FROM country WHERE name = 'Scandinavia'), (SELECT id FROM country WHERE name = 'Northern Europe')),
    ((SELECT id FROM country WHERE name = 'Scandinavia'), (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
    -- Southern Europe
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'Egypt')),
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'Middle East')),
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'North Africa')),
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'Northern Europe')),
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
    ((SELECT id FROM country WHERE name = 'Southern Europe'), (SELECT id FROM country WHERE name = 'Western Europe')),
    -- Ukraine (Eastern Europe, Russia)
    ((SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)'), (SELECT id FROM country WHERE name = 'Northern Europe')),
    ((SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)'), (SELECT id FROM country WHERE name = 'Scandinavia')),
    ((SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)'), (SELECT id FROM country WHERE name = 'Southern Europe'));

-- North America
INSERT INTO adjacent_country (country_id, adjacent_country_id)
VALUES
    -- Alaska
    ((SELECT id FROM country WHERE name = 'Alaska'), (SELECT id FROM country WHERE name = 'Alberta (Western Canada)')),
    ((SELECT id FROM country WHERE name = 'Alaska'), (SELECT id FROM country WHERE name = 'Kamchatka')),
    ((SELECT id FROM country WHERE name = 'Alaska'), (SELECT id FROM country WHERE name = 'Northwest Territory')),
    -- Alberta (Western Canada)
    ((SELECT id FROM country WHERE name = 'Alberta (Western Canada)'), (SELECT id FROM country WHERE name = 'Alaska')),
    ((SELECT id FROM country WHERE name = 'Alberta (Western Canada)'), (SELECT id FROM country WHERE name = 'Northwest Territory')),
    ((SELECT id FROM country WHERE name = 'Alberta (Western Canada)'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),
    ((SELECT id FROM country WHERE name = 'Alberta (Western Canada)'), (SELECT id FROM country WHERE name = 'Western United States')),
    -- Central America
    ((SELECT id FROM country WHERE name = 'Central America'), (SELECT id FROM country WHERE name = 'Eastern United States')),
    ((SELECT id FROM country WHERE name = 'Central America'), (SELECT id FROM country WHERE name = 'Peru')),
    ((SELECT id FROM country WHERE name = 'Central America'), (SELECT id FROM country WHERE name = 'Venezuela')),
    ((SELECT id FROM country WHERE name = 'Central America'), (SELECT id FROM country WHERE name = 'Western United States')),
    -- Eastern United States
    ((SELECT id FROM country WHERE name = 'Eastern United States'), (SELECT id FROM country WHERE name = 'Central America')),
    ((SELECT id FROM country WHERE name = 'Eastern United States'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),
    ((SELECT id FROM country WHERE name = 'Eastern United States'), (SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)')),
    ((SELECT id FROM country WHERE name = 'Eastern United States'), (SELECT id FROM country WHERE name = 'Western United States')),
    -- Greenland
    ((SELECT id FROM country WHERE name = 'Greenland'), (SELECT id FROM country WHERE name = 'Iceland')),
    ((SELECT id FROM country WHERE name = 'Greenland'), (SELECT id FROM country WHERE name = 'Northwest Territory')),
    ((SELECT id FROM country WHERE name = 'Greenland'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),
    ((SELECT id FROM country WHERE name = 'Greenland'), (SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)')),
    -- Northwest Territory
    ((SELECT id FROM country WHERE name = 'Northwest Territory'), (SELECT id FROM country WHERE name = 'Alaska')),
    ((SELECT id FROM country WHERE name = 'Northwest Territory'), (SELECT id FROM country WHERE name = 'Alberta (Western Canada)')),
    ((SELECT id FROM country WHERE name = 'Northwest Territory'), (SELECT id FROM country WHERE name = 'Greenland')),
    ((SELECT id FROM country WHERE name = 'Northwest Territory'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),

    -- Ontario (Central Canada) (continued)
    ((SELECT id FROM country WHERE name = 'Ontario (Central Canada)'), (SELECT id FROM country WHERE name = 'Eastern United States')),
    ((SELECT id FROM country WHERE name = 'Ontario (Central Canada)'), (SELECT id FROM country WHERE name = 'Greenland')),
    ((SELECT id FROM country WHERE name = 'Ontario (Central Canada)'), (SELECT id FROM country WHERE name = 'Northwest Territory')),
    ((SELECT id FROM country WHERE name = 'Ontario (Central Canada)'), (SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)')),
    ((SELECT id FROM country WHERE name = 'Ontario (Central Canada)'), (SELECT id FROM country WHERE name = 'Western United States')),
    -- Quebec (Eastern Canada)
    ((SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)'), (SELECT id FROM country WHERE name = 'Eastern United States')),
    ((SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)'), (SELECT id FROM country WHERE name = 'Greenland')),
    ((SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),
    -- Western United States
    ((SELECT id FROM country WHERE name = 'Western United States'), (SELECT id FROM country WHERE name = 'Alberta (Western Canada)')),
    ((SELECT id FROM country WHERE name = 'Western United States'), (SELECT id FROM country WHERE name = 'Central America')),
    ((SELECT id FROM country WHERE name = 'Western United States'), (SELECT id FROM country WHERE name = 'Eastern United States')),
    ((SELECT id FROM country WHERE name = 'Western United States'), (SELECT id FROM country WHERE name = 'Ontario (Central Canada)'));