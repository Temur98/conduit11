insert into article(title, description, body, create_at, update_at, profile_id) values
('1-article','1-description','1-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('2-article','2-description','2-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('3-article','3-description','3-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('4-article','4-description','4-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('5-article','5-description','5-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('6-article','6-description','6-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3),
('7-article','7-description','7-body','2023-06-16 12:10:56','2023-06-16 12:10:56',3);

insert into tag (name) values
                           ('tag1'),
                           ('tag2');
insert into article_tag (article_id, tag_id) values
                                                 (1,1),
                                                 (2,2),
                                                 (3,2),
                                                 (4,1),
                                                 (5,1),
                                                 (6,2),
                                                 (7,1);
insert into article_tag (article_id, tag_id) values
                                                 (1,2);

insert into comment(profile_id, article_id, body, create_at, update_at) values
(1,1,'comment1','2023-06-18 15:45:23','2023-06-18 15:45:23'),
(1,1,'comment2','2023-06-18 15:45:23','2023-06-18 15:45:23'),
(1,1,'comment3','2023-06-18 15:45:23','2023-06-18 15:45:23'),
(1,1,'comment4','2023-06-18 15:45:23','2023-06-18 15:45:23');