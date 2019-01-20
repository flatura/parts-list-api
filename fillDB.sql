USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part(
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
required BIT(1) NOT NULL DEFAULT b'0',
count int(5) NOT NULL,
PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Материнская плата", 1, 3);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Звуковая карта", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Память", 1, 10);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Процессор", 1, 7);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Корпус", 1, 8);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Видеокарта", 0, 11);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Кулер процессора", 1, 5);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Кулер корпуса, дополнительный", 0, 10);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Подсветка корпуса", 0, 4);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Накопитель HDD", 0, 13);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Накопитель SSD", 1, 15);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Накопитель M.2", 0, 10);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Блок питания", 1, 10);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("ТВ-тюнер, карта захвата", 0, 10);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Кабель SATA", 1, 20);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контейнер для HDD, SSD", 0, 3);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Привод DVD", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Привод BD", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер RAID", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-USB3.0", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-COM", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-LPT", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-e-SATA", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-IEEE1394", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-Ethernet", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Контроллер PCI-WiFi", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Дисковод 3.5'' USB", 0, 2);
INSERT INTO `test`.`part` (`name`, `required`, `count`) VALUES ("Модем PCI", 0, 2);
