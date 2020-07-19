CREATE TABLE IF NOT EXISTS User (
    user_id    INTEGER               COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    ip varchar
);

CREATE TABLE IF NOT EXISTS Page (
    page_id    INTEGER               COMMENT 'Уникальный идентификатор страницы сайта' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate'
);

CREATE TABLE IF NOT EXISTS Visit (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    user_id    INTEGER        NOT NULL       COMMENT 'Уникальный идентификатор пользователя' ,
    page_id    INTEGER        NOT NULL       COMMENT 'Уникальный идентификатор страницы сайта' ,
    time       DateTime NOT NULL,
    ip varchar
);

ALTER TABLE Visit 	 ADD FOREIGN KEY (user_id) REFERENCES User(user_id);
ALTER TABLE Visit 	 ADD FOREIGN KEY (page_id) REFERENCES Page(page_id);



	
