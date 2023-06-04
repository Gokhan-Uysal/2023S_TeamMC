DROP TABLE IF EXISTS game_state_persist cascade;
CREATE TABLE game_state_persist (
    id SERIAL,
    game_state INT,
    last_save TIMESTAMP,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS continent cascade;
CREATE TABLE continent (
    id SERIAL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS country cascade;
CREATE TABLE country (
    id SERIAL,
    name VARCHAR(255) NOT NULL ,
    image_name VARCHAR(255),
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

DROP TABLE IF EXISTS army cascade;
CREATE TABLE army (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    value INT NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS player cascade;
CREATE TABLE player (
    id SERIAL,
    username VARCHAR(255),
    high_score INT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS territory_army cascade;
CREATE TABLE territory_army (
    country_id INT NOT NULL,
    army_id INT NOT NULL,
    count INT DEFAULT 0,
    PRIMARY KEY (country_id, army_id),
    FOREIGN KEY (country_id) REFERENCES country(id),
    FOREIGN KEY (army_id) REFERENCES army(id),
    CHECK (count >= 0)
);

DROP TABLE IF EXISTS player_game cascade;
CREATE TABLE player_game (
    player_id INT NOT NULL,
    game_state_persist_id INT NOT NULL,
    PRIMARY KEY (player_id, game_state_persist_id),
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (game_state_persist_id) REFERENCES game_state_persist(id)
);

DROP TABLE IF EXISTS army_card cascade;
CREATE TABLE army_card (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    value INT NOT NULL,
    image VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS territory_card cascade;
CREATE TABLE territory_card (
    id SERIAL,
    description TEXT,
    image VARCHAR(255),
    country_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES country(id)
);

DROP TABLE IF EXISTS player_army_card cascade;
CREATE TABLE player_army_card (
    player_id INT NOT NULL,
    count INT DEFAULT 0,
    army_card_id INT NOT NULL,
    PRIMARY KEY (player_id, army_card_id),
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (army_card_id) REFERENCES army_card(id),
    CHECK (count >= 0)
);

DROP TABLE IF EXISTS player_territory_card cascade;
CREATE TABLE player_territory_card (
    player_id INT NOT NULL,
    territory_card_id INT NOT NULL,
    PRIMARY KEY (player_id, territory_card_id),
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (territory_card_id) REFERENCES territory_card(id)
);

-- Continents
INSERT INTO continent(name)  VALUES ('Australia');
INSERT INTO continent(name)  VALUES ('Asia');
INSERT INTO continent(name)  VALUES ('Africa');
INSERT INTO continent(name)  VALUES ('Europe');
INSERT INTO continent(name)  VALUES ('North America');
INSERT INTO continent(name)  VALUES ('South America');

-- Insert countries
INSERT INTO country (name, position_x, position_y, continent_id, image_name)
VALUES
    -- Australia
    ('Eastern Australia', 810, 445, 1, 'eastern_australia.png'),
    ('Indonesia', 696, 393, 1, 'indonesia.png'),
    ('New Guinea', 789, 383, 1, 'new_guinea.png'),
    ('Western Australia', 752, 459, 1, 'western_australia.png'),
    -- South America
    ('Argentina', 208, 419, 6, 'argentina.png'),
    ('Brazil', 195, 337, 6, 'brazil.png'),
    ('Peru', 175, 350, 6, 'peru.png'),
    ('Venezuela', 181, 309, 6, 'venezuela.png'),
    -- Asia
    ('Afghanistan', 563, 168, 2, 'afghanistan.png'),
    ('China', 644, 185, 2, 'china.png'),
    ('India', 613, 248, 2, 'india.png'),
    ('Irkutsk', 686, 105, 2, 'irkutsk.png'),
    ('Japan', 797, 156, 2, 'japan.png'),
    ('Kamchatka', 746, 51, 2, 'kamchatka.png'),
    ('Middle East', 493, 257, 2, 'middle_east.png'),
    ('Mongolia', 693, 156, 2, 'mongolia.png'),
    ('Siam (Southeast Asia)', 700, 294, 2, 'siam.png'),
    ('Siberia', 618, 24, 2, 'siberia.png'),
    ('Ural', 599, 52, 2, 'ural.png'),
    ('Yakutsk', 699, 37, 2, 'yakutsk.png'),
    -- Africa
    ('Congo (Central Africa)', 452, 394, 3, 'congo.png'),
    ('East Africa', 495, 355, 3, 'east_africa.png'),
    ('Egypt', 454, 311, 3, 'egypt.png'),
    ('Madagascar', 556, 480, 3, 'madagascar.png'),
    ('North Africa', 376, 293, 3, 'north_africa.png'),
    ('South Africa', 462, 451, 3, 'south_africa.png'),
    -- Europe
    ('Great Britain (Great Britain & Ireland)', 345, 140, 4, 'great_britain.png'),
    ('Iceland', 370, 100, 4, 'iceland.png'),
    ('Northern Europe', 420, 152, 4, 'northern_europe.png'),
    ('Scandinavia', 430, 70, 4, 'scandinavia.png'),
    ('Southern Europe', 428, 210, 4, 'southern_europe.png'),
    ('Ukraine (Eastern Europe, Russia)', 483, 73, 4, 'ukraine.png'),
    ('Western Europe',   368, 210, 4, 'western_europe.png'),
    -- North America
    ('Alaska', 30, 60, 5, 'alaska.png'),
    ('Alberta (Western Canada)', 103, 108, 5, 'alberta.png'),
    ('Central America', 117, 234, 5, 'central_america.png'),
    ('Eastern United States', 160, 168, 5, 'eastern_united_states.png'),
    ('Greenland', 250, 15, 5, 'greenland.png'),
    ('Northwest Territory', 95, 47, 5, 'northwest_territory.png'),
    ('Ontario (Central Canada)', 175, 110, 5, 'ontario.png'),
    ('Quebec (Eastern Canada)', 230, 106, 5, 'quebec.png'),
    ('Western United States', 110, 166, 5, 'western_united_states.png');

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
    ((SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    ((SELECT id FROM country WHERE name = 'Western Europe'), (SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)')),
    -- Western Europe
    ((SELECT id FROM country WHERE name = 'Western Europe'), (SELECT id FROM country WHERE name = 'Northern Europe')),
    ((SELECT id FROM country WHERE name = 'Western Europe'), (SELECT id FROM country WHERE name = 'Southern Europe')),
    ((SELECT id FROM country WHERE name = 'Western Europe'), (SELECT id FROM country WHERE name = 'North Africa')),
    ((SELECT id FROM country WHERE name = 'Western Europe'), (SELECT id FROM country WHERE name = 'Western United States'));

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

-- Australia
INSERT INTO territory_card (description, image, country_id)
VALUES ('Eastern Australia', 'eastern_australia_card.png', (SELECT id FROM country WHERE name = 'Eastern Australia')),
       ('Indonesia', 'indonesia_card.png', (SELECT id FROM country WHERE name = 'Indonesia')),
       ('New Guinea', 'new_guinea_card.png', (SELECT id FROM country WHERE name = 'New Guinea')),
       ('Western Australia', 'western_australia_card.png', (SELECT id FROM country WHERE name = 'Western Australia'));

-- South America
INSERT INTO territory_card (description, image, country_id)
VALUES ('Argentina', 'argentina_card.png', (SELECT id FROM country WHERE name = 'Argentina')),
       ('Brazil', 'brazil_card.png', (SELECT id FROM country WHERE name = 'Brazil')),
       ('Peru', 'peru_card.png', (SELECT id FROM country WHERE name = 'Peru')),
       ('Venezuela', 'venezuela_card.png', (SELECT id FROM country WHERE name = 'Venezuela'));

-- Asia
INSERT INTO territory_card (description, image, country_id)
VALUES ('Afghanistan', 'afghanistan_card.png', (SELECT id FROM country WHERE name = 'Afghanistan')),
       ('China', 'china_card.png', (SELECT id FROM country WHERE name = 'China')),
       ('India', 'india_card.png', (SELECT id FROM country WHERE name = 'India')),
       ('Irkutsk', 'irkutsk_card.png', (SELECT id FROM country WHERE name = 'Irkutsk')),
       ('Japan', 'japan_card.png', (SELECT id FROM country WHERE name = 'Japan')),
       ('Kamchatka', 'kamchatka_card.png', (SELECT id FROM country WHERE name = 'Kamchatka')),
       ('Middle East', 'middle_east_card.png', (SELECT id FROM country WHERE name = 'Middle East')),
       ('Mongolia', 'mongolia_card.png', (SELECT id FROM country WHERE name = 'Mongolia')),
       ('Siam (Southeast Asia)', 'siam_card.png', (SELECT id FROM country WHERE name = 'Siam (Southeast Asia)')),
       ('Siberia', 'siberia_card.png', (SELECT id FROM country WHERE name = 'Siberia')),
       ('Ural', 'ural_card.png', (SELECT id FROM country WHERE name = 'Ural')),
       ('Yakutsk', 'yakutsk_card.png', (SELECT id FROM country WHERE name = 'Yakutsk'));

-- Africa
INSERT INTO territory_card (description, image, country_id)
VALUES ('Congo (Central Africa)', 'congo_card.png', (SELECT id FROM country WHERE name = 'Congo (Central Africa)')),
       ('East Africa', 'east_africa_card.png', (SELECT id FROM country WHERE name = 'East Africa')),
       ('Egypt', 'egypt_card.png', (SELECT id FROM country WHERE name = 'Egypt')),
       ('Madagascar', 'madagascar_card.png', (SELECT id FROM country WHERE name = 'Madagascar')),
       ('North Africa', 'north_africa_card.png', (SELECT id FROM country WHERE name = 'North Africa')),
       ('South Africa', 'south_africa_card.png', (SELECT id FROM country WHERE name = 'South Africa'));

-- Europe
INSERT INTO territory_card (description, image, country_id)
VALUES ('Great Britain (Great Britain & Ireland)', 'great_britain_card.png', (SELECT id FROM country WHERE name = 'Great Britain (Great Britain & Ireland)')),
        ('Iceland', 'iceland_card.png', (SELECT id FROM country WHERE name = 'Iceland')),
       ('Northern Europe', 'northern_europe_card.png', (SELECT id FROM country WHERE name = 'Northern Europe')),
       ('Scandinavia', 'scandinavia_card.png', (SELECT id FROM country WHERE name = 'Scandinavia')),
       ('Southern Europe', 'southern_europe_card.png', (SELECT id FROM country WHERE name = 'Southern Europe')),
       ('Ukraine (Eastern Europe, Russia)', 'ukraine_card.png', (SELECT id FROM country WHERE name = 'Ukraine (Eastern Europe, Russia)')),
       ('Western Europe', 'western_europe_card.png', (SELECT id FROM country WHERE name = 'Western Europe'));

-- North America
INSERT INTO territory_card (description, image, country_id)
VALUES ('Alaska', 'alaska_card.png', (SELECT id FROM country WHERE name = 'Alaska')),
       ('Alberta (Western Canada)', 'alberta_card.png', (SELECT id FROM country WHERE name = 'Alberta (Western Canada)')),
       ('Central America', 'central_america_card.png', (SELECT id FROM country WHERE name = 'Central America')),
       ('Eastern United States', 'eastern_united_states_card.png', (SELECT id FROM country WHERE name = 'Eastern United States')),
       ('Greenland', 'greenland_card.png', (SELECT id FROM country WHERE name = 'Greenland')),
       ('Northwest Territory', 'northwest_territory_card.png', (SELECT id FROM country WHERE name = 'Northwest Territory')),
       ('Ontario (Central Canada)', 'ontario_card.png', (SELECT id FROM country WHERE name = 'Ontario (Central Canada)')),
       ('Quebec (Eastern Canada)', 'quebec_card.png', (SELECT id FROM country WHERE name = 'Quebec (Eastern Canada)')),
       ('Western United States', 'western_united_states_card.png', (SELECT id FROM country WHERE name = 'Western United States'));

INSERT INTO army_card (name, value, image)
VALUES ('Infantry', 10, 'infantry.png'),
       ('Cavalry', 40, 'cavalry.png'),
       ('Artillery', 60, 'artillery.png');

INSERT INTO army (name, value)
VALUES ('Infantry', 1),
       ('Cavalry', 5),
       ('Artillery', 10);

-- Inserting territory_army records for all territories and armies
INSERT INTO territory_army (country_id, army_id)
SELECT c.id, a.id
FROM country c, army a;