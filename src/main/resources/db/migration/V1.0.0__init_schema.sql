CREATE TABLE user (
    id varchar(100) not null PRIMARY KEY,
    full_name varchar(100) not null,
    display_name varchar(100),
    email varchar(100) not null unique,
    password varchar(100)
);

CREATE TABLE skill (
    id varchar(100) not null PRIMARY KEY,
    name varchar(100) not null
);

CREATE TABLE user_skill (
    user_id varchar(100) not null,
    skill_id varchar(100) not null,
    level varchar(20),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id),
    PRIMARY KEY (user_id, skill_id)
);
