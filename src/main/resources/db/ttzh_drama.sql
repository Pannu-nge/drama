-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dramaland
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dramaland
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dramaland` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dramaland` ;

-- -----------------------------------------------------
-- Table `dramaland`.`dramas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`dramas` (
  `drama_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `released_date` DATE NOT NULL,
  PRIMARY KEY (`drama_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`genre` (
  `genre_id` INT NOT NULL AUTO_INCREMENT,
  `genre_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`drama_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`drama_genre` (
  `drama_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  INDEX `fk_dg_genre_id_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_dg_drama_id_idx` (`drama_id` ASC) VISIBLE,
  CONSTRAINT `fk_dg_drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dg_genre_id`
    FOREIGN KEY (`genre_id`)
    REFERENCES `dramaland`.`genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `nationality` VARCHAR(100) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  PRIMARY KEY (`person_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`drama_cast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`drama_cast` (
  `drama_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `character_name` VARCHAR(100) NOT NULL,
  INDEX `fk_drama_id1_idx` (`drama_id` ASC) VISIBLE,
  INDEX `fk_person_id_idx` (`person_id` ASC) VISIBLE,
  INDEX `fk_role_id_idx` (`role_id` ASC) VISIBLE,
  PRIMARY KEY (`drama_id`, `person_id`, `role_id`),
  CONSTRAINT `fk_drama_id1`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_id1`
    FOREIGN KEY (`person_id`)
    REFERENCES `dramaland`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `dramaland`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`drama_crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`drama_crew` (
  `drama_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  INDEX `dc_drama_id_idx` (`drama_id` ASC) VISIBLE,
  INDEX `dc_person_id_idx` (`person_id` ASC) VISIBLE,
  INDEX `dc_role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `dc_drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dc_person_id`
    FOREIGN KEY (`person_id`)
    REFERENCES `dramaland`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dc_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `dramaland`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`seasons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`seasons` (
  `season_id` INT NOT NULL,
  `season_name` VARCHAR(50) NOT NULL,
  `drama_id` INT NOT NULL,
  PRIMARY KEY (`season_id`),
  INDEX `drama_id_idx` (`drama_id` ASC) VISIBLE,
  CONSTRAINT `fk_s_drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`episodes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`episodes` (
  `episode_id` INT NOT NULL,
  `episode_name` VARCHAR(100) NOT NULL,
  `runtime` INT NOT NULL,
  `season_id` INT NOT NULL,
  PRIMARY KEY (`episode_id`),
  INDEX `fk_season_id_idx` (`season_id` ASC) VISIBLE,
  CONSTRAINT `fk_season_id`
    FOREIGN KEY (`season_id`)
    REFERENCES `dramaland`.`seasons` (`season_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`drama_photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`drama_photo` (
  `photo_id` INT NULL,
  `photo_size` BLOB NOT NULL,
  `photo_name` VARCHAR(100) NOT NULL,
  `drama_id` INT NOT NULL,
  PRIMARY KEY (`photo_id`),
  INDEX `fk_dp_drama_id_idx` (`drama_id` ASC) VISIBLE,
  CONSTRAINT `fk_dp_drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`person_photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`person_photo` (
  `photo_id` INT NOT NULL,
  `photo_size` BLOB NOT NULL,
  `photo_name` VARCHAR(50) NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`photo_id`),
  INDEX `fk_person_id_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_pp_person_id`
    FOREIGN KEY (`person_id`)
    REFERENCES `dramaland`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`company` (
  `company_id` INT NOT NULL,
  `company_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`company_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dramaland`.`production_company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dramaland`.`production_company` (
  `company_id` INT NOT NULL,
  `drama_id` INT NOT NULL,
  INDEX `fk_company_id_idx` (`company_id` ASC) VISIBLE,
  INDEX `fk_drama_id_idx` (`drama_id` ASC) VISIBLE,
  CONSTRAINT `fk_company_id`
    FOREIGN KEY (`company_id`)
    REFERENCES `dramaland`.`company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pc_drama_id`
    FOREIGN KEY (`drama_id`)
    REFERENCES `dramaland`.`dramas` (`drama_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
