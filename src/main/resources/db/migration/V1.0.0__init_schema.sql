CREATE TABLE "user" (
    id varchar(100) not null PRIMARY KEY,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    email varchar(100) not null,
    password varchar(100)
);

CREATE TABLE skill (
    id varchar(100) not null PRIMARY KEY,
    name varchar(100) not null
);

CREATE TABLE level (
    level varchar(100) not null
);

CREATE TABLE "user_to_skill_to_level"(
    userId varchar(100),
    skillId varchar(100),
    level varchar(100),
    FOREIGN KEY (userId) REFERENCES "user"(id),
    FOREIGN KEY (skillId) REFERENCES skill(id),
    FOREIGN KEY (level) REFERENCES level(level)
);