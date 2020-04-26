CREATE TABLE team (
  id varchar(100) not null PRIMARY KEY,
  name varchar(100) not null,
  invitation_id varchar(100)
);

CREATE TABLE user (
    id varchar(100) not null PRIMARY KEY,
    full_name varchar(100) not null,
    display_name varchar(100),
    title varchar(100),
    available bit default 0,
    email varchar(100) not null unique,
    password varchar(100),
    team_id varchar(100),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE skill (
    id varchar(100) not null PRIMARY KEY,
    name varchar(100) not null,
    team_id varchar(100) not null,
    FOREIGN KEY (team_id) REFERENCES team(id),
    CONSTRAINT unique_name_for_team UNIQUE(name, team_id)
);

CREATE TABLE user_skill (
    user_id varchar(100) not null,
    skill_id varchar(100) not null,
    level varchar(20),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id),
    PRIMARY KEY (user_id, skill_id)
);
