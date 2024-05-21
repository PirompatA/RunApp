CREATE TABLE IF NOT EXISTS Run(
    id INT NOT NULL,
    title varchar(250) NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    miles INT NOT NULL,
    location varchar(10) NOT NULL,
    PRIMARY KEY(id)
);
-- INSERT INTO Run(id, title, started_on, completed_on, miles, location)
-- VALUES (1,'Morning Run', '2017-01-01 06:00:00', '2017-01-01 07:00:00', 5, 'OUTDOOR');
-- INSERT INTO Run(id, title, started_on, completed_on, miles, location)
-- VALUES (2,'Evening Run', '2017-01-01 18:00:00', '2017-01-01 19:00:00', 5, 'INDOOR');