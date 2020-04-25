CREATE TABLE team (
  id varchar(100) not null PRIMARY KEY,
  name varchar(100) not null
);

CREATE TABLE user (
    id varchar(100) not null PRIMARY KEY,
    teamId varchar(100),
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) unique not null,
    password varchar(100),
    FOREIGN KEY (teamId) REFERENCES team(id)
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

CREATE TABLE user_team (
    user_id varchar(100) not null,
    team_id varchar(100) not null,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (team_id) REFERENCES team(id),
    PRIMARY KEY (user_id, team_id)
);
