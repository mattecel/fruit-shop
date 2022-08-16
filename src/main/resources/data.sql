CREATE TABLE IF NOT EXISTS FruitOrder (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    numOfApples INTEGER,
    numOfOranges INTEGER
);

INSERT INTO FruitOrder (numOfApples, numOfOranges) VALUES (6, 20);
INSERT INTO FruitOrder (numOfApples, numOfOranges) VALUES (36, 8);