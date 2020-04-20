CREATE TABLE user (
    id varchar(100) not null PRIMARY KEY,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
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

CREATE TABLE user_to_skill_to_level(
    user_id varchar(100),
    skill_id varchar(100),
    level varchar(100),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id),
    FOREIGN KEY (level) REFERENCES level(level)
);