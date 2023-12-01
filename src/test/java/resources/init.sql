CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS games (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    release_date DATE,
    rating INTEGER,
    cost INTEGER,
    description TEXT
);

CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    amount INTEGER NOT NULL,
    type VARCHAR(50) NOT NULL,
    user_id INTEGER UNIQUE REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users_games (
    USER_ID INTEGER REFERENCES users(id) ON DELETE CASCADE,
    GAME_ID INTEGER REFERENCES games(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, game_id)
);

INSERT INTO users (name, nickname, birthday, password) VALUES
    ('Alex', 'alexis', '2000-01-01', '123'),
    ('John', 'johnny', '1995-05-15', '456'),
    ('Anna', 'anna123', '1988-09-30', '789'),
    ('Mike', 'mikie', '1992-03-20', 'abc'),
    ('Eva', 'evita', '2005-12-10', 'def');


INSERT INTO games (name, release_date, rating, cost, description) VALUES
    ('Game 1', '2022-01-01', 4, 50, 'Description of Game 1'),
    ('Game 2', '2022-02-01', 3, 40, 'Description of Game 2'),
    ('Game 3', '2022-03-01', 5, 60, 'Description of Game 3'),
    ('Game 4', '2022-04-01', 2, 30, 'Description of Game 4'),
    ('Game 5', '2022-05-01', 4, 55, 'Description of Game 5');

INSERT INTO accounts (amount, type, user_id) VALUES
    (50, 'Visa', 1),
    (150, 'Mastercard', 2),
    (75, 'Visa', 3),
    (200, 'Mastercard', 4);


INSERT INTO users_games (USER_ID, GAME_ID) VALUES
    (1, 4),
    (1, 3),
    (1, 1);
