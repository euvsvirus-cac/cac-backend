INSERT INTO team VALUES('xyz-abc-13', 'super-team');
INSERT INTO team VALUES('1312-hype-team', 'team 1312');

INSERT INTO user VALUES ('21df8714-8349-11ea-bc55-0242ac130003', null, 'Ramesh Ahmedabad', 'RAM!', null, 0,  'nk@abc.de', '$2y$12$QSsSSMRDGtBIJOZL0f7ZHuSCZXP./giQu4lYJv1gTkckiUiAHWQl.');
INSERT INTO user VALUES ('abc-def-asudndadsad-12379', null, 'Ramesh Ahmedabad', 'RAM!',  null, 0, 'od@abc.de','$2y$12$QSsSSMRDGtBIJOZL0f7ZHuSCZXP./giQu4lYJv1gTkckiUiAHWQl.');
INSERT INTO user VALUES ('8468-asopkdasd-12-32ascas', null, 'Ramesh Ahmedabad', 'RAM!',  null, 0, 'nkod@abc.de', '$2y$12$QSsSSMRDGtBIJOZL0f7ZHuSCZXP./giQu4lYJv1gTkckiUiAHWQl.');

INSERT INTO skill VALUES ('cfb19a72-9908-4a5e-9935-a9096eb7749c', 'java');

INSERT INTO user_skill VALUES ('21df8714-8349-11ea-bc55-0242ac130003', 'cfb19a72-9908-4a5e-9935-a9096eb7749c', 'GOLD');

INSERT INTO user_team VALUES ('21df8714-8349-11ea-bc55-0242ac130003', 'xyz-abc-13');
INSERT INTO user_team VALUES ('abc-def-asudndadsad-12379', 'xyz-abc-13');

INSERT INTO team_user VALUES ('21df8714-8349-11ea-bc55-0242ac130003', 'xyz-abc-13');
INSERT INTO team_user VALUES ('abc-def-asudndadsad-12379', 'xyz-abc-13');