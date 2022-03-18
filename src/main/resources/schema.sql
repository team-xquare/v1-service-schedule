DROP TABLE IF EXISTS `tbl_schedule_group`;
DROP TABLE IF EXISTS `tbl_schedule`;
DROP TABLE IF EXISTS `tbl_schedule_group_member`;
DROP TABLE IF EXISTS `tbl_school_schedule`;

CREATE TABLE `tbl_schedule_group` (
    `id`	BINARY(16)	NOT NULL,
    `type`	VARCHAR(20)	NOT NULL,
    `color`	CHAR(6)	NOT NULL
);

CREATE TABLE `tbl_schedule` (
    `id`	BINARY(16)	NOT NULL,
    `date`	DATE	NOT NULL	DEFAULT (CURRENT_DATE) ,
    `name`	VARCHAR(20)	NOT NULL,
    `period`	TINYINT	NOT NULL	DEFAULT 0,
    `schedule_id`	BINARY(16)	NOT NULL,
    `user_id`	BINARY(16)	NOT NULL
);

CREATE TABLE `tbl_schedule_group_member` (
    `id`	BINARY(16)	NOT NULL,
    `user_id`	BINARY(16)	NOT NULL,
    `schedule_group_id`	BINARY(16)	NOT NULL,
    `is_updatable`	TINYINT(1)	NOT NULL	DEFAULT 0
);

CREATE TABLE `tbl_school_schedule` (
    `id`	BINARY(16)	NOT NULL,
    `date`	DATE	NOT NULL	DEFAULT (CURRENT_DATE) ,
    `name`	VARCHAR(20)	NOT NULL
);

ALTER TABLE `tbl_schedule_group` ADD CONSTRAINT `PK_TBL_SCHEDULE_GROUP` PRIMARY KEY (
    `id`
);

ALTER TABLE `tbl_schedule` ADD CONSTRAINT `PK_TBL_SCHEDULE` PRIMARY KEY (
    `id`
);

ALTER TABLE `tbl_schedule_group_member` ADD CONSTRAINT `PK_TBL_SCHEDULE_GROUP_MEMBER` PRIMARY KEY (
    `id`
);

ALTER TABLE `tbl_school_schedule` ADD CONSTRAINT `PK_TBL_SCHOOL_SCHEDULE` PRIMARY KEY (
    `id`
);