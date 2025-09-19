--full customization
INSERT INTO member(name, email, password) VALUES('조홍규', 'hongkyu@naver.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('카리나', 'Karina@naver.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('윈터', 'Win@naver.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('장원영', 'Jang@naver.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO authority(authority, member_id) VALUES('ROLE_USER', 1);
INSERT INTO authority(authority, member_id) VALUES('ROLE_USER', 2);
INSERT INTO authority(authority, member_id) VALUES('ROLE_ADMIN', 2);

