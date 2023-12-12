CREATE TABLE Accounts
(
    id     INT              NOT NULL GENERATED ALWAYS AS IDENTITY ( START 1000 ),
    type   VARCHAR(20)      NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Users
(
    id         SERIAL,
    name       VARCHAR(100) NOT NULL,
    nickname   VARCHAR(100) NOT NULL,
    birthday   DATE,
    password   VARCHAR(100) NOT NULL,
    account_id INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Accounts (id)
);

CREATE TABLE Games
(
    id           SERIAL,
    name         VARCHAR(100)     NOT NULL,
    release_date DATE,
    rating       INT,
    cost         DOUBLE PRECISION NOT NULL,
    description  VARCHAR(250),
    PRIMARY KEY (id)
);

CREATE TABLE User_Game
(
    user_id INT,
    game_id INT,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (game_id) REFERENCES Games (id)
);

INSERT INTO Accounts (type, amount)
VALUES ('VISA', 10000.0);

INSERT INTO Users (name, nickname, password, account_id)
VALUES ('Shop', 'Shop', 'Shop', 1000);

INSERT INTO Games (name, release_date, rating, cost, description)
VALUES ('Grand Theft Auto V', '2013-09-17', 9, 59.99,
        'Action-adventure game developed by Rockstar North. Set within the fictional state of San Andreas, based on Southern California.'),
       ('The Witcher 3: Wild Hunt', '2015-05-19', 10, 39.99,
        'Action role-playing game developed by CD Projekt. Players control Geralt of Rivia, a monster slayer known as a Witcher.'),
       ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 10, 49.99,
        'Action-adventure game developed and published by Nintendo. Set in an open world, the game follows Link, who awakens from a hundred-year slumber.'),
       ('Red Dead Redemption 2', '2018-10-26', 9, 49.99,
        'Action-adventure game developed and published by Rockstar Games. The game is the third entry in the Red Dead series.'),
       ('Cyberpunk 2077', '2020-12-10', 7, 59.99,
        'Action role-playing game developed by CD Projekt. Set in the dystopian Night City, the open-world game follows the story of V, a mercenary outlaw.'),
       ('Final Fantasy XV', '2016-11-29', 8, 39.99,
        'Action role-playing game developed and published by Square Enix. The story follows Prince Noctis, as he embarks on a journey to reclaim his homeland and its magical Crystal from the clutches of the Niflheim Empire.'),
       ('Minecraft', '2011-11-18', 10, 19.99,
        'Sandbox game developed and published by Mojang. The game has no specific goals for the player to accomplish, allowing players a large amount of freedom in choosing how to play the game.'),
       ('Overwatch', '2016-05-24', 9, 29.99,
        'First-person shooter developed and published by Blizzard Entertainment. The game features a diverse cast of characters known as "heroes", each with their own unique abilities.'),
       ('Dark Souls III', '2016-04-12', 8, 44.99,
        'Action role-playing game developed by FromSoftware. Players are equipped with a variety of weapons to fight against enemies, such as bows, throwable projectiles, and swords.'),
       ('Fortnite', '2017-07-25', 7, 0.00,
        'Battle Royale game developed and published by Epic Games. The game features a cartoonish graphics style and multiple game modes.'),
       ('Assassins Creed Odyssey', '2018-10-05', 9, 39.99,
        'Action role-playing game developed by Ubisoft. Set in the year 431 BCE, the plot tells a fictional history of the Peloponnesian War.'),
       ('League of Legends', '2009-10-27', 8, 0.00,
        'Multiplayer online battle arena game developed and published by Riot Games. Players assume the role of a "champion" with unique abilities and battle against a team of other players.'),
       ('Doom Eternal', '2020-03-20', 9, 59.99,
        'First-person shooter game developed by id Software. The game is the direct sequel to Doom (2016) and focuses on the Doom Slayer as he battles against demonic forces from Hell.'),
       ('FIFA 22', '2021-10-01', 8, 59.99,
        'Football simulation video game published by Electronic Arts. The game features various game modes, including career mode, ultimate team, and Volta Football.'),
       ('Star Wars Jedi: Fallen Order', '2019-11-15', 9, 49.99,
        'Action-adventure game developed by Respawn Entertainment. Players control Cal Kestis, a young Jedi Padawan, as he attempts to survive while being chased by the Galactic Empire.'),
       ('Among Us', '2018-11-16', 8, 4.99,
        'Online multiplayer party game developed and published by InnerSloth. The game takes place in a space-themed setting where players take on one of two roles: Crewmates or Impostors.');



