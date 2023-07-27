create database flm_db;
use flm_db;
-- User
create table user(
user_id int primary key,
full_name nvarchar(50) not null,
email char(50) unique,
mobile char(20) unique,
user_name nvarchar(50) unique,
password varchar(100) ,
picture varchar(255),
status bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id)
);
-- Setting
create table setting (
setting_id int primary key,
setting_name nvarchar(30) not null,
setting_type nvarchar(30) not null,
setting_value nvarchar(30),
display_order nvarchar(30),
setting_description nvarchar(255),
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id)
);
-- user_role
create table user_role (
role_id int,
user_id int,
is_active bit not null,
FOREIGN KEY (role_id) REFERENCES setting (setting_id),
FOREIGN KEY (user_id) REFERENCES user (user_id),
primary key(role_id,user_id)
);

-- decision
create table decision (
decision_id int not null,
decision_no varchar(255) not null,
decision_name varchar(255) not null,
decision_ApprovedDate date,
decision_CreateDate	date not null,
creator_id int not null,
notes varchar(255),
primary key (decision_id)
);
-- curriculum
create table curriculum (
curriculum_id int not null,
code varchar(20) not null, 
name nvarchar(255) not null, 
description text not null,
decision_id int ,
total_credit int ,
owner_id int ,
is_active bit ,
english_name nvarchar(255), 
create_id int ,
FOREIGN KEY (decision_id) REFERENCES decision(decision_id),
FOREIGN KEY (owner_id) REFERENCES user(user_id),
FOREIGN KEY (create_id) REFERENCES user(user_id),
primary key (curriculum_id)
);
create table plo(
plo_id int not null,
plo_name nvarchar(20) ,
plo_description text,
curriculum_id int,
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY(curriculum_id) REFERENCES curriculum(curriculum_id),
PRIMARY KEY(plo_id)
);
create table po(
po_id int not null,
po_name varchar(20) ,
po_description text,
curriculum_id int,
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY(curriculum_id) REFERENCES curriculum(curriculum_id),
PRIMARY KEY(po_id)
);
create table po_plo(
po_id int not null,
plo_id int not null,
FOREIGN KEY (po_id) REFERENCES po (po_id),
FOREIGN KEY (plo_id) REFERENCES plo (plo_id),
PRIMARY KEY (po_id,plo_id)
);

-- group
create table `group`(
group_id int primary key,
group_name nvarchar(255) not null,
combo_subject bit ,
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id)
);

-- group-curriculum
create table `group_curriculum`(
group_id int not null,
curriculum_id int not null,
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY(group_id) REFERENCES `group`(group_id),
FOREIGN KEY(curriculum_id) REFERENCES `curriculum`(curriculum_id),
primary key (group_id, curriculum_id)
);

-- subject
create table subject(
subject_id int primary key,
subject_code varchar(20) unique not null, 
subject_name nvarchar(255) not null,
subject_type_id int,
subject_is_active bit not null,
subject_description text,
subject_parent_id int,
subject_group_id int,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY(subject_parent_id) REFERENCES subject(subject_id),
FOREIGN KEY(subject_group_id) REFERENCES `group_curriculum`(group_id),
FOREIGN KEY (subject_type_id) REFERENCES setting (setting_id)
);

-- syllabus
create table syllabus (
syllabus_id int primary key,
`name` Nvarchar(255),
no_of_credit varchar(20), 
is_active bit, 
is_approved bit,
degree_level int,
scoringScale int, 
minAvgMarkToPass int,
note text,
decision_id int,
subject_id int,
designer_id int,
created_by int,
created_at DATETIME, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(degree_level) REFERENCES setting(setting_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY (decision_id) REFERENCES decision(decision_id),
FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
FOREIGN KEY (designer_id) REFERENCES user(user_id),
FOREIGN KEY (created_by) REFERENCES user(user_id)
);

-- submit
create table submit(
submit_id int primary key,
syllabus_id int,
submitter_id int,
submit_file_url varchar(255),
submit_note text,
FOREIGN KEY (syllabus_id) REFERENCES syllabus(syllabus_id),
FOREIGN KEY (submitter_id) REFERENCES user(user_id)
);

-- reviewer
create table reviewer(
syllabus_id int,
reviewer_id int,
is_active bit,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY (syllabus_id) REFERENCES syllabus(syllabus_id),
FOREIGN KEY (reviewer_id) REFERENCES user(user_id)
);

-- curriculum_subject
create table curriculum_subject(
curriculum_id int,
subject_id int,
syllabus_id int,
group_id int,
semester int, 
no_credit int,
content_id int,
curriculum_subject_is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY(curriculum_id) REFERENCES curriculum(curriculum_id),
FOREIGN KEY(subject_id) REFERENCES subject(subject_id),
FOREIGN KEY(content_id,curriculum_id) REFERENCES `group_curriculum`(group_id,curriculum_id),
FOREIGN KEY(group_id,curriculum_id) REFERENCES `group_curriculum`(group_id,curriculum_id),
FOREIGN KEY(syllabus_id) REFERENCES syllabus(syllabus_id),
PRIMARY KEY (curriculum_id,subject_id)
);
-- plo_subject
create table plo_subject(
plo_id int,
curriculum_id int,
subject_id int,
FOREIGN KEY(curriculum_id) REFERENCES curriculum(curriculum_id),
FOREIGN KEY(subject_id) REFERENCES subject(subject_id),
FOREIGN KEY(plo_id) REFERENCES plo(plo_id),
PRIMARY KEY (plo_id,curriculum_id,subject_id)
);
-- prerequisite
create table prerequisite (
prerequisite_id int,
curriculum_id int, 
subject_id int, 
is_active bit not null,
description Nvarchar(255),
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
FOREIGN KEY (prerequisite_id) REFERENCES subject(subject_id),
FOREIGN KEY (curriculum_id, subject_id) REFERENCES curriculum_subject(curriculum_id, subject_id)
);
-- clo
create table clo (
clo_id int,
clo_name nvarchar(20),
clo_description text,
syllabus_id int,
is_active bit not null,
created_at DATETIME, 
created_by int, 
updated_at DATETIME, 
updated_by int,
FOREIGN KEY(created_by) REFERENCES user (user_id),
FOREIGN KEY(updated_by) REFERENCES user (user_id),
primary key(clo_id),
foreign key (syllabus_id) references syllabus(syllabus_id) 
);
create table clo_plo(
clo_id int,
plo_id int,
primary key(clo_id,plo_id),
FOREIGN KEY (clo_id) REFERENCES clo(clo_id),
FOREIGN KEY (plo_id) REFERENCES plo(plo_id)
);

create table session (
session_id int primary key,
syllabus_id int,
name nvarchar(255),
details nvarchar(255),
learning_type int,
is_introduce bit,
is_teach bit,
is_utilize bit,
student_materials bit,
teacher_materials bit,
FOREIGN KEY(syllabus_id) REFERENCES syllabus (syllabus_id)
);

create table session_lo(
session_id int,
clo_id int,
FOREIGN KEY (session_id) REFERENCES session (session_id),
FOREIGN KEY (clo_id) REFERENCES clo (clo_id),
primary key(session_id,clo_id )
);

create table question(
question_id int primary key,
session_id int,
name nvarchar(255),
details nvarchar(255),
FOREIGN KEY (session_id) REFERENCES session (session_id)
);


-- Insert User
INSERT INTO `flm_db`.`user` (`user_id`, `full_name`, `email`, `mobile`, `user_name`, `password`, `status`,`picture`) 
VALUES ('1', 'thanh dep trai', 'tienthanhcute2k2@gmail.com', '0587061111', 'thanh123', 'Thanh123', b'1','avata-user.png');
INSERT INTO `flm_db`.`user` (`user_id`, `full_name`, `email`, `mobile`, `user_name`, `password`, `status`,`picture`) 
VALUES ('2', 'ADMIN', 'admin@gmail.com', '0123456789', 'admin', 'e64b78fc3bc91bcbc7dc232ba8ec59e0', b'1','avata-user.png');
INSERT INTO `flm_db`.`user` (`user_id`, `full_name`, `email`, `mobile`, `user_name`, `password`, `status`,`picture`) 
values ('3', 'Head 1', null, null, 'head1', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('4', 'Staff 1', null, null, 'staff1', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('5', 'Staff 2', null, null, 'staff2', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('6', 'Designer 1', null, null, 'designer1', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('7', 'Designer 2', null, null, 'designer2', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('8', 'Reviewer', null, null, 'reviewer', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('9', 'student', null, null, 'student', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png'),
('10', 'teacher', null, null, 'teacher', 'e133bb43b4da5526421c5d7d13580de6', b'1','avata-user.png');
-- Insert Setting
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) VALUES ('2', 'STUDENT', 'User Role',b'1');
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) VALUES ('1', 'ADMIN', 'User Role',b'1');
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) VALUES ('3', 'GUEST', 'User Role',b'1');
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) VALUES 
('4', 'default', 'subject type',b'1'),('5', 'combo', 'subject type',b'1'),('6', 'elective', 'subject type',b'1');
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) 
VALUES ('7', 'SYLLABUS DESIGNER', 'User Role',b'1'),
('8', 'CRDD HEAD', 'User Role',b'1'),
('9', 'CRDD STAFF', 'User Role',b'1'),
('10', 'TEACHER', 'User Role',b'1'),
('11', 'SYLLABUS REVIEWER', 'User Role',b'1');
INSERT INTO `flm_db`.`setting` (`setting_id`, `setting_name`, `setting_type`,`is_active`) 
VALUES ('12', 'Bachelor', 'Degree Level',b'1'),
('13', 'Bachelor_2', 'Degree Level',b'1');

-- Insert UserRole
INSERT INTO `flm_db`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('1', '1', b'1');
INSERT INTO `flm_db`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('1', '2', b'1');
INSERT INTO `flm_db`.`user_role` (`role_id`, `user_id`, `is_active`) 
VALUES ('7', '6', b'1'),
('7', '7', b'1'),
('8', '3', b'1'),
('9', '4', b'1'),
('9', '5', b'1'),
('11', '8', b'1'),
('2', '9', b'1'),
('10', '10', b'1');

-- Insert Subject
-- INSERT INTO `flm_db`.`subject` (`subject_id`, `subject_code`, `subject_name`, `subject_type_id`, `subject_is_active`, `subject_description`, `subject_parent_id`) 
-- VALUES ('3', 'PHE_COM*1', 'Physical Education 1_Giáo dục thể chất 1', '5', b'1', 'new subject', null),
-- ('4', 'VOV114', 'Vovinam 1', '4', b'1', 'new subject', '3'),
-- ('5', 'COV111', 'Cờ Vua 1_Chess 1', '4', b'1', 'new subject', '3'),
-- ('6', 'CEA201', 'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính', '4', b'1', 'new subject', null),
-- ('7', 'CSI104', 'Introduction to Computer_Nhập môn khoa học máy tính', '4', b'1', 'new subject', null),
-- ('9', 'SE_COM*1', 'Subject 1 of Combo*_Học phần 1 của combo*', '5', b'1', 'new subject', null),
-- ('10', 'PRN211', 'Basis Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng cơ bản với .NET', '4', b'1', 'new subject', 9),
-- ('11', 'JPD133', 'Tiếng Nhật sơ cấp 1-A1/A2', '4', b'1', 'new subject', 9),
-- ('12', 'PRP201c', 'Python programming_Lập trình Python', '4', b'1', 'new subject', 9),
-- ('13', 'FER201m', 'Front-End web development with React_Phát triển web Front-End với React', '4', b'1', 'new subject', 9);

-- add desion check
INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_ApprovedDate`,`decision_CreateDate`, `creator_id`) 
VALUES ('1', '495/QĐ-ĐHFPT','Quyết định điều chỉnh CTĐT từ kì Fall 2023', '2023/2/2','2023/2/2',  '1');



 
-- Insert Curriculum
INSERT INTO flm_db.`curriculum` (curriculum_id, code, name, description, decision_id, total_credit, owner_id, is_active,create_id) VALUES 
('1', 'BBA_MKT_K16D17A', 'Bachelor Program of Business Adminstration, Digital Marketing Major (CTĐT ngành QTKD, chuyên ngành Marketing Số)', 'The objective of the Bachelor of Business Administration – Digital Marketing program of FPT University is to train students into specialists in marketing management, managers, and entrepreneurs. Students will be equipped with all essential knowledge and s...', '1', '0', '5', b'1',3),
('2', 'BIT_SE_K17B', 'Bachelor Program of Information Technology, Software Engineering Major (Chương trình cử nhân ngành CNTT, chuyên ngành Kỹ thuật phần mềm)', '1. Training Objectives 1.1 General objective: Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organ...', '1', '104', '4', b'1',3),
('3', 'BIT_SE_K16D_K17A', 'Bachelor Program of Information Technology, Software Engineering Major (Chương trình cử nhân ngành CNTT, chuyên ngành Kỹ thuật phần mềm)', 'Training Objectives 1.1 General objective: Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organ...', '1', '0', '4', b'1',3),
('4', 'BBA_FIN_K16D17A', 'Bachelor Program of Business Adminstration, Finance Major (CTĐT ngành QTKD, chuyên ngành Tài chính)', 'The objective of the Bachelor of Business Administration – Finance program of FPT University is to train students into specialists in financial management, managers, and entrepreneurs. Students will be equipped with all essential knowledge and skills to w...', '1', '0', '4', b'1',3);

-- Insert Group
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2535', 'MKT_COM1: Digital Marketing Tools_Công cụ Marketing số', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2536', 'MKT_COM2: Brand and Event Management_Quản trị thương hiệu và sự kiện', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2537', 'MKT_COM3: Finance_Tài chính', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2538', 'MKT_COM4: International Business_Kinh doanh quốc tế', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2539', 'MKT_COM5: Hotel Management_Quản trị khách sạn', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2540', 'MKT_COM6: Communication and Multimedia_Quản trị truyền thông đa phương tiện', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('268', 'PHE_COM1: Vovinam', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('269', 'PHE_COM2: Cờ Vua', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2555', 'MKT_COM7: SAP (Systems Applications and Products in Data Processing)', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1000', 'General knowledge and skills_Khối Kiến thức chung', b'0',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1001', 'Elective combo knowledge and skills_Khối kiến thức combo lựa chọn', b'0',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1002', 'Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)', b'0',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1003', 'Specialized knowledge and skills_Khối kiến thức chuyên ngành', b'0',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1004', 'Elective combo knowledge and skills FIN_Khối kiến thức combo lựa chọn FIN', b'0',b'1');

INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('26', 'PHE_COM1: Vovinam BIT_GD_K16D,K17A', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('334', 'PHE_COM2: Cờ vua BIT_SE_K15A', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('337', 'SE_COM3: Topic on .NET Programming_Chủ đề lập trình .NET BIT_SE_K15A', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('340', 'SE_COM5.2: Topic on Japanese Bridge Engineer_Chủ đề Kỹ sư cầu nối Nhật Bản (Định hướng Tiếng Nhật nâng cao cho kỹ sư CNTT) BIT_SE_K15A', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('402', 'SE_COM8: Topic on self-driving car_Chủ đề xe tự hành BIT_SE_K15C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('419', 'SE_COM4: Topic on React/NodeJS_Chủ đề React/NodeJS BIT_SE_K15D, K16A', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1469', 'SE_COM5.1.1:Topic on Japanese Bridge Engineer_Chủ đề Kỹ sư cầu nối Nhật Bản (Định hướng Tiếng Nhật CNTT: Lựa chọn JFE301 và 1 trong 2 học phần JIS401, JIT401 để triển khai ở kỳ 8) BIT_SE_K15C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2553', 'SE_COM9: Topic on SAP_Chủ đề SAP', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('2566', 'SE_COM7.1:Topic on AI_Chủ đề AI', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1006', 'Major knowledge and skills_Khối kiến thức ngành', b'0',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1008', 'Elective combo knowledge and skills_Khối kiến thức combo lựa chọn', b'0',b'1');
 INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1111', 'Elective combo knoavasv and skills_Khối kiến thức combo lựa chọn', b'0',b'1');
 INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('1112', 'Elective combo knosvasvasvd skills_Khối kiến thức combo lựa chọn', b'0',b'1');



INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('881', 'PHE_COM1: Vovinam BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('882', 'PHE_COM2: Chess_Cờ Vua BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('884', 'FIN_COM2: Corporate Finance_Tài chính doanh nghiệp BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('885', 'FIN_COM3: Marketing BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('886', 'FIN_COM4: International Business_Kinh doanh quốc tế BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('887', 'FIN_COM5: Hotel Management_Quản trị khách sạn BBA_FIN_K16C', b'1',b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`, `is_active`) VALUES ('888', 'FIN_COM6: Communication and Multimedia_Quản trị truyền thông đa phương tiện BBA_FIN_K16C', b'1',b'1');

INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `is_active`) VALUES ('156', 'Traditional musical instrument_Nhạc cụ truyền thống', b'1');
INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `is_active`) 
VALUES ('9', 'Traditional musical instrument_Nhạc cụ truyền thống', b'1'),
('225', 'Traditional musical instrument_Nhạc cụ truyền thống', b'1'),
('359', 'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1', b'1'),
('364', 'SE_Entrepreneurship 2 and Combos',  b'1'),
('385', 'Học phần 4 của combo SE',  b'1'),
('397', 'Graduation Elective - Finance',  b'1'),
('396', 'Graduation Elective - Digital Marketing', b'1');



-- Insert Group_Curriculum
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2535', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2536', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2537', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2538', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2539', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2540', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('268', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('269', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2555', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1000', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1001', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1002', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1003', '1',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1004', '1',b'1');

INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('26', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('334', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('337', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('340', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('402', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1469', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2553', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2566', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1000', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1003', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1006', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1008', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('419', '2',b'1');

 INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1111', '2',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1112', '2',b'1');


INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('26', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('334', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('337', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('340', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('402', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('419', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('1469', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2553', '3',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('2566', '3',b'1');

INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('881', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('882', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('884', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('885', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('886', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('887', '4',b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('888', '4',b'1');

INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('156', '1', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('396', '1', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('9', '2', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('359', '2', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('364', '2', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('385', '2', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('9', '3', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('359', '3', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('364', '3', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('385', '3', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('225', '4', b'1');
INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`) VALUES ('397', '4', b'1');


-- insert subject group ( content )
INSERT INTO `flm_db`.`subject` (`subject_id`, `subject_code`, `subject_name`, `subject_type_id`, `subject_is_active`, `subject_description`, `subject_parent_id`, `subject_group_id`) 
VALUES 
('1', 'OTP101', 'Orientation and General Training Program_Định hướng và Rèn luyện tập trung', '4', b'1', 'new subject', null, '1000'),
('2', 'PEN', 'Preparation English_Tiếng Anh chuẩn bị', '4', b'1', 'new subject', null, '1000'),
('3', 'PHE_COM*1', 'Physical Education 1_Giáo dục thể chất 1', '5', b'1', 'new subject', null , null),
('4', 'VOV114', 'Vovinam 1', '4', b'1', 'new subject', '3', '1000'),
('5', 'COV111', 'Cờ Vua 1_Chess 1', '4', b'1', 'new subject', '3', '1000'),
('9', 'SE_COM*1', 'Subject 1 of Combo*_Học phần 1 của combo*', '5', b'1', 'new subject', null, null),

('15', 'TMI_ELE', 'Traditional musical instrument_Nhạc cụ truyền thống', '4', b'1', 'new subject', null, '1000'),
('16', 'MLN111', 'Philosophy of Marxism – Leninism_Triết học Mác - Lê-nin', '4', b'1', 'new subject', null, '1000'),
('17', 'MLN121', 'Political economics of Marxism – Leninism - Kinh tế chính trị Mác - Lênin', '4', b'1', 'new subject', null, '1000'),
('18', 'HCM201', 'Ho Chi Minh Ideology', '4', b'1', 'new subject', null, '1000'),	
('19', 'ENW492c', 'Academic Writing Skills_Kỹ năng viết học thuật', '4', b'1', 'new subject', null, '1000'),
('20', 'SSG104', 'Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác', '4', b'1', 'new subject', null, '1000'),

('21', 'WDU202c', 'UI/UX Design', '4', b'1', 'new subject', null, '1001'),
('22', 'BRA301', 'Brand Management_Quản trị thương hiệu', '4', b'1', 'new subject', null, '1001'),
('23', 'ADS301m', 'Google ads and SEO_Google ads và SEO', '4', b'1', 'new subject', null, '1001'),
('24', 'MKT309m', 'Omnichannel Marketing_Bán hàng đa kênh', '4', b'1', 'new subject', null, '1001'),
('25', 'MKT209m', 'Content Marketing_Marketing Nội dung', '4', b'1', 'new subject', null, '1001'),
('26', 'CCM201', 'Crisis Communications Management_Quản trị truyền thông khủng hoảng', '4', b'1', 'new subject', null, '1001'),
('27', 'EVN201', 'Event Planning_Tổ chức sự kiện', '4', b'1', 'new subject', null, '1001'),
('28', 'EXE101', 'Experiential Entrepreneurship 1_Trải nghiệm khởi nghiệp 1', '4', b'1', 'new subject', null, '1001'),

('29', 'OJB202', 'On-the-job training_Đào tạo trong môi trường thực tế', '4', b'1', 'new subject', null, '1002'),
('30', 'SSB201', 'Advanced Business Communication/Kỹ năng giao tiếp nâng cao trong kinh doanh', '4', b'1', 'new subject', null, '1002'),
('31', 'MAS202', 'Applied Statistics for Business_Thống kê ứng dụng trong kinh doanh', '4', b'1', 'new subject', null, '1002'),
('32', 'ECO111', 'Microeconomics_Kinh tế vi mô', '4', b'1', 'new subject', null, '1002'),
('33', 'ECO121', 'Macroeconomics_Kinh tế vĩ mô', '4', b'1', 'new subject', null, '1002'),
('34', 'ACC101', 'Principles of Accounting_Nguyên lý kế toán', '4', b'1', 'new subject', null, '1002'),
('35', 'FIN202', 'Principles of Corporate Finance_Tài chính doanh nghiệp', '4', b'1', 'new subject', null, '1002'),
('36', 'MKT101', 'Marketing Principles_Nguyên lý Marketing', '4', b'1', 'new subject', null, '1002'),
('37', 'OBE102', 'Hành vi tổ chức', '4', b'1', 'new subject', null, '1002'),
('38', 'MGT101', 'Introduction to Management', '4', b'1', 'new subject', null, '1002'),
('39', 'PMG201c', 'Project Management', '4', b'1', 'new subject', null, '1002'),
('40', 'ITA203c', 'Management Information Systems/Hệ thống thông tin quản lý', '4', b'1', 'new subject', null, '1002'),
('41', 'LAW102', 'Business Law and Ethics Fundamentals_Luật và Đạo đức kinh doanh', '4', b'1', 'new subject', null, '1002'),

('42', 'DTG102', 'Visual Design Tools_Công cụ thiết kế trực quan', '4', b'1', 'new subject', null, '1003'),
('43', 'MKT301', 'Marketing Research_Nghiên cứu Marketing', '4', b'1', 'new subject', null, '1003'),
('44', 'MKT202', 'Services Marketing Management_Quản trị Marketing dịch vụ', '4', b'1', 'new subject', null, '1003'),
('45', 'MKT304', 'Integrated Marketing Communications_Truyền thông marketing tích hợp', '4', b'1', 'new subject', null, '1003'),
('46', 'MKT318', 'Digital marketing 1', '4', b'1', 'new subject', null, '1003'),
('47', 'MKT328', 'Digital marketing 2', '4', b'1', 'new subject', null, '1003'),
('48', 'MKT201', 'Consumer Behavior_Hành vi người tiêu dùng', '4', b'1', 'new subject', null, '1003'),
('49', 'MKT208c', 'Social media marketing_Marketing mạng xã hội', '4', b'1', 'new subject', null, '1003'),
('50', 'SAL301', 'Professional Selling and Management_Kỹ năng bán hàng chuyên nghiệp', '4', b'1', 'new subject', null, '1003'),
('51', 'GRM491', 'Graduation Thesis (Business Plan)_Khóa luận tốt nghiệp (theo hướng kế hoạch kinh doanh)', '4', b'1', 'new subject', null, '1003'),

('52', 'FIN201', 'Monetary Economics and Global Economy_Kinh tế tiền tệ và kinh tế toàn cầu', '4', b'1', 'new subject', null, '1004'),
('53', 'FIN301', 'Financial Markets and Institutions / Thị trường tài chính và các định chế tài chính', '4', b'1', 'new subject', null, '1004'),
('54', 'ACC305', 'Financial Statement Analysis_Phân tích báo cáo tài chính', '4', b'1', 'new subject', null, '1004'),
('55', 'FIN303', 'Advanced Corporate Finance_Tài chính doanh nghiệp nâng cao', '4', b'1', 'new subject', null, '1004'),
('56', 'RMB302', 'Research Methods & Quantitative Analysis_Phương pháp nghiên cứu và phân tích định lượng', '4', b'1', 'new subject', null, '1004'),

('60', 'MLN122', 'Political economics of Marxism – Leninism_Kinh tế chính trị Mác - Lê-nin', '4', b'1', 'new subject', null, '1000'),
('61', 'MLN131', 'Scientific socialism_Chủ nghĩa xã hội khoa học', '4', b'1', 'new subject', null, '1000'),
('62', 'HCM202', 'HCM Ideology_Tư tưởng Hồ Chí Minh', '4', b'1', 'new subject', null, '1000'),
('63', 'VNR202', 'History of CPV_Lịch sử Đảng Cộng sản Việt Nam', '4', b'1', 'new subject', null, '1000'),
('66', 'JPD113', 'Elementary Japanese 1- A1.1_Tiếng Nhật sơ cấp 1-A1.1', '4', b'1', 'new subject', null, '1000'),

('67', 'MAE101', 'Mathematics for Engineering_Toán cho ngành kỹ thuật', '4', b'1', 'new subject', null, '1006'),
('68', 'MAD101', 'Discrete mathematics_Toán rời rạc', '4', b'1', 'new subject', null, '1006'),
('69', 'MAS291', 'Statistics & Probability_Xác suất thống kê', '4', b'1', 'new subject', null, '1006'),
('70', 'CSI104', 'Introduction to Computer_Nhập môn khoa học máy tính', '4', b'1', 'new subject', null, '1006'),
('71', 'CEA201', 'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính', '4', b'1', 'new subject', null, '1006'),
('72', 'PRF192', 'Programming Fundamentals_Cơ sở lập trình', '4', b'1', 'new subject', null, '1006'),
('73', 'PRO192', 'Object-Oriented Programming_Lập trình hướng đối tượng', '4', b'1', 'new subject', null, '1006'),
('74', 'CSD201', 'Data Structures and Algorithms_Cấu trúc dữ liệu và giải thuật', '4', b'1', 'new subject', null, '1006'),
('75', 'OSG202', 'Operating Systems_Hệ điều hành', '4', b'1', 'new subject', null, '1006'),
('76', 'NWC203c', 'Computer Networking_Mạng máy tính', '4', b'1', 'new subject', null, '1006'),
('78', 'DBI202', 'Introduction to Databases_Các hệ cơ sở dữ liệu', '4', b'1', 'new subject', null, '1006'),
('80', 'PMG202c', 'Project management_Quản trị dự án', '4', b'1', 'new subject', null, '1006'),
('81', 'ITE302c', 'Ethics in IT_Đạo đức trong CNTT', '4', b'1', 'new subject', null, '1006'),
('82', 'LAB211', 'OOP with Java Lab_Thực hành OOP với Java', '4', b'1', 'new subject', null, '1006'),
('83', 'OJT202', 'On-The-Job Training_Đào tạo trong môi trường thực tế', '4', b'1', 'new subject', null, '1006'),

('84', 'PRN211', 'Basic Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng cơ bản với .NET', '4', b'1', 'new subject', null, '1008'),
('85', 'PRN221', 'Advanced Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng nâng cao với .NET', '4', b'1', 'new subject', '9', '1008'),
('86', 'PRU211c', '(Combo .Net: Option 3) C# Programming and Unity', '4', b'1', 'new subject', null, '1008'),
('87', 'PRN231', 'Building Cross-Platform Back-End Application With .NET_Xây dựng ứng dụng back-end với .NET', '4', b'1', 'new subject', null, '1008'),
('88', 'JPD133', 'Elementary Japanese 1-A1/A2_Tiếng Nhật sơ cấp 1-A1/A2', '4', b'1', 'new subject', '9', '1008'),
('89', 'PRP201c', 'Python Programming_Lập trình Python', '4', b'1', 'new subject', null, '1008'),
('90', 'FER201m', 'Front-End web development with React_Phát triển web Front-End với React', '4', b'1', 'new subject', '9', '1008'),

('91', 'PRM392', 'Mobile Programming_Lập trình di động', '4', b'1', 'new subject', null, '1003'),
('92', 'WED201c', 'Web Design_Thiết kế web', '4', b'1', 'new subject', null, '1003'),
('93', 'PRJ301', 'Java Web Application Development_Phát triển ứng dụng Java web', '4', b'1', 'new subject', null, '1003'),
('94', 'SWP391', 'Software development project_Dự án phát triển phần mềm', '4', b'1', 'new subject', null, '1003'),
('95', 'SWE201c', 'Introduction to Software Engineering_Nhập môn kĩ thuật phần mềm', '4', b'1', 'new subject', null, '1003'),
('96', 'SWR302', 'Software Requirement_Yêu cầu phần mềm', '4', b'1', 'new subject', null, '1003'),
('97', 'SWT301', 'Software Testing_Kiểm thử phần mềm', '4', b'1', 'new subject', null, '1003'),
('98', 'WDU203c', 'UI/UX Design_Thiết kế trải nghiệm người dùng', '4', b'1', 'new subject', null, '1003'),
('99', 'SWD392', 'Software Architecture and Design_Kiến trúc và thiết kế phần mềm', '4', b'1', 'new subject', null, '1003'),
('100', 'IOT102', 'Internet of Things_Internet vạn vật', '4', b'1', 'new subject', null, '1003');

INSERT INTO `flm_db`.`subject` (`subject_id`, `subject_code`, `subject_name`, `subject_type_id`, `subject_is_active`, `subject_description`, `subject_group_id`) VALUES ('103', 'ĐNH102', '	Traditional musical instrument_Nhạc cụ truyền thống-Đàn Nhị', '6', b'1', 'subject elective', '156'),
('101', 'ĐTR102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Tranh', '6', b'1', 'subject elective', '156'),
('102', 'ĐTB102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Tỳ bà', '6', b'1', 'subject elective', '156'),
('104', 'ĐNG102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Nguyệt', '6', b'1', 'subject elective', '156'),
('105', 'ĐBA102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Bầu', '6', b'1', 'subject elective', '156'),
('106', 'ĐSA102', 'Traditional musical instrument_Nhạc cụ truyền thống-Sáo Trúc', '6', b'1', 'subject elective', '156'),
('107', 'TRG102', 'Traditional musical instrument_Nhạc cụ truyền thống-Trống dân tộc', '6', b'1', 'subject elective', '156');
INSERT INTO `flm_db`.`subject` (`subject_id`, `subject_code`, `subject_name`, `subject_type_id`, `subject_is_active`, `subject_description`, `subject_group_id`) VALUES 
('108', 'SYB302c', 'Entrepreneurship_Khởi sự doanh nghiệp', '6', b'1', 'subject elective', '359'),
('109', 'EXE201', 'Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2', '6', b'1', 'subject elective', '364'),
('110', 'PRU211m', 'C# Programming and Unity_Lập trình C# và Unity', '6', b'1', 'subject elective', '364'),
('111', 'MMA301', 'Multiplatform Mobile App Development_Phát triển ứng dụng di động đa nền tảng', '6', b'1', 'subject elective', '364'),
('112', 'WDP301', 'Web Development Project_Dự án phát triển web', '6', b'1', 'subject elective', '364'),
('113', 'DMT301', 'Data Mining with Tensorflow_Khai phá dữ liệu với Tensorflow', '6', b'1', 'subject elective', '364'),
('114', 'AIT301', 'Artificial Intelligence with TensorFlow_TTNT với TensorFlow', '6', b'1', 'subject elective', '364'),
('115', 'CPV301', 'Computer Vision_Thị giác máy tính', '6', b'1', 'subject elective', '364'),
('116', 'SDE301', 'Self-driving Car Engineering_Công nghệ ôtô tự lái', '6', b'1', 'subject elective', '364'),
('117', 'JIS401', 'Tiếng Nhật CNTT trong ngành phần mềm', '6', b'1', 'subject elective', '364'),
('118', 'SAP331', 'SAP Configuration - Cấu hình SAP', '6', b'1', 'subject elective', '364'),
('119', 'KOR411', 'Intermediate Korean Language 3_Hàn ngữ trung cấp 3', '6', b'1', 'subject elective', '364'),
('120', 'AIP392', 'AI project_Dự án trí tuệ nhân tạo', '6', b'1', 'subject elective', '385'),
('121', 'DBM301', 'Data mining_Khai phá dữ liệu', '6', b'1', 'subject elective', '385'),
('122', 'GRF491', 'Graduation Thesis - Finance_Khóa luận tốt nghiệp -Tài chính', '6', b'1', 'subject elective', '397'),
('123', 'GRP490', '	Graduation Thesis (Business Plan)_Khóa luận tốt nghiệp (theo hướng kế hoạch kinh doanh)', '6', b'1', 'subject elective', '397')
;

-- insert Syllabus
INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`, `created_by`) 
VALUES ('1', 'Định hướng và Rèn luyện tập trung(Orientaiton and General Training Program)', '0', b'1', b'1', '12', '10', '0', 'Min to pass: Students must pass the examination and achieve the Military training certificate', '1', '1', '2'),
('2', 'Vovinam 1', '2', b'1', b'1', '12', '10', '5', '', '1', '4', '2'),
('3', 'Cờ Vua 1_Chess 1', '2', b'1', b'1', '12', '10', '5', '', '1', '5', '2'),
('4', 'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính', '3', b'1', b'1', '12', '10', '5', '', '1', '71', '2'),
('5', 'Introduction to Computer Science_Nhập môn khoa học máy tính', '3', b'1', b'1', '12', '10', '5', '', '1', '70', '2'),
('6', 'Mathematics for Engineering_Toán cho ngành kỹ thuật', '3', b'1', b'1', '12', '10', '5','', '1', '67', '2'),
('7', 'Basis Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng cơ bản với .NET', '3', b'1', b'1', '12', '10', '5', '', '1', '84', '2'),
('8', 'Elementary Japanese 1-A1/A2', '3', b'1', b'1', '12', '10', '5', '1) Tổng điểm chung cho toàn môn học: 100% 
1.1. Điểm quá trình: 30%
1.1.1. Điểm chuyên cần: 10%
1.1.2. Điểm bài kiểm tra: 20% (gồm 2 bài ST)
1.2. Điểm giữa kỳ (MT) : 30%
1.3: Điểm cuối kỳ (FE): 40%
2) Khung chuẩn điểm cho từng hạng mục:
2.1.Các điểm trong mục 1.1 phải lớn hơn 0 điểm. Điểm cuối kỳ (FE) phải lớn hơn hoặc bằng 4 và Tổng điểm chung toàn môn học phải lớn hơn hoặc bằng 5.
2.2. Thi FE không có kỹ năng nào bị 0
2.3. Nếu FE < 4/10, được thi lại một lần tất cả các kỹ năng
2.4: Nếu đã đạt 2.1 nhưng chưa đạt 2.2, thì chỉ phải thi lại một lần những kỹ năng bị 0 điểm.', '1', '66', '2'),
('9', 'Python programming_Lập trình Python', '3', b'1', b'1', '12', '10', '5', 'Assessment scheme:
1. Complete the online course to be allowed to take Final Exam
2. Final Exam is included of Final Theory Exam (TE) & Final Practical Exam (PE): 100%
3. Student gets 0.2 bonus points for each course completed on time. The total bonus point is not greater than 1.
4. Completion Criteria: Final TE Score >=4 & Final PE Score >=4 & ((Final TE Score + Final PE Score)/2 + bonus) >= 5
5. In the case: (5 > Final TE Score >=4) & (5 > Final PE Score >=4) & ((Final TE Score + Final PE Score)/2 + bonus) < 5, the student can choose to take the resit of both TE & PE OR just either TE or PE.', '1', '89', '2'),
('10', 'Front-End web development with React_Phát triển web Front-End với React', '3', b'1', b'1', '12', '10', '5', '1) On-going Assessment
- At least 2 progress tests: 15%
- 8 labs: 20%
- 1 assignment: 15%
- 1 practical exam: 20%
2) Final exam (60): 30%
3) Final Result: 100%
Completion Criteria:
1) Every on-going assessment component >0; Practical Exam Score >=4
2) Final Exam Score >=4 & Final Result >=5', '1', '90', '2');

-- data of table curriculum_Subject
INSERT INTO flm_db.`curriculum_subject` (curriculum_id, subject_id, syllabus_id, group_id, semester, no_credit, curriculum_subject_is_active) 
VALUES 

-- curriculum id 2
-- ('2', '1', '1', null, '0', '0', b'1'),
-- ('2', '2', null, null, '0', '0', b'1'),
-- ('2', '3', null, null, '0', '2', b'1'),
-- ('2', '4', '2', '26', '0', null, b'1'),
-- ('2', '5', '3', '334', '0', null, b'1'),
-- ('2', '9', '2', null, '5', '5', b'1'),
-- ('2', '60', '9253', null, '8', '2', b'1'),
-- ('2', '61', '9258', null, '9', '2', b'1'),
-- ('2', '62', '9721', null, '9', '2', b'1'),
-- ('2', '63', '9255', null, '9', '2', b'1'),
-- ('2', '66', '8', '340', '5', null, b'1'),
-- ('2', '67', '9574', null, '1', '3', b'1'),
-- ('2', '68', '9241', null, '4', '3', b'1'),
-- ('2', '69', '9243', null, '4', '3', b'1'),
-- ('2', '70', '9169', null, '1', '3', b'1'),
-- ('2', '71', '9297', null, '1', '3', b'1'),
-- ('2', '72', '9569', null, '1', '3', b'1'),
-- ('2', '73', '9433', null, '2', '5', b'1'),
-- ('2', '74', '9298', null, '3', '3', b'1'),
-- ('2', '75', '9200', null, '2', '3', b'1'),
-- ('2', '76', '9779', null, '2', '3', b'1'),
-- ('2', '78', '9632', null, '3', '3', b'1'),
-- ('2', '80', '9783', null, '8', '3', b'1'),
-- ('2', '81', '9789', null, '5', '3', b'1'),
-- ('2', '82', '6697', null, '3', '3', b'1'), 	
-- ('2', '83', '7819', null, '6', '3', b'1'),
-- ('2', '84', '7', '337', '5', null, b'1'),
-- ('2', '85', '9447', '337', '7', '3', b'1'),
-- ('2', '86', '9448', null, '8', '3', b'1'),
-- ('2', '87', '3674', '337', '7', '3', b'1'),
-- ('2', '88', '6021', '340', '7', '3', b'1'),
-- ('2', '89', '9', '402', '5', null, b'1'),
-- ('2', '90', '10', '419', '5', null, b'1'),

-- ('2', '91', '9435', null, '7', '3', b'1'),
-- ('2', '92', '9785', null, '3', '3', b'1'),
-- ('2', '93', '6617', null, '4', '3', b'1'), 	
-- ('2', '94', '9641', null, '5', '3', b'1'),
-- ('2', '95', '9777', null, '4', '3', b'1'),
-- ('2', '96', '9506', null, '5', '3', b'1'),
-- ('2', '97', '9792', null, '5', '3', b'1'),
-- ('2', '98', '9791', null, '8', '3', b'1'),
-- ('2', '99', '9346', null, '7', '3', b'1'),
-- ('2', '100', '9301', null, '4', '3', b'1');

('2', '1', '1', null, '0', '0', b'1'),
('2', '2', null, null, '0', '0', b'1'),
('2', '3', null, null, '0', '2', b'1'),
('2', '4', '2', '26', '0', null, b'1'),
('2', '5', '3', '334', '0', null, b'1'),
('2', '9', null, null, '5', '5', b'1'),
('2', '60', null, null, '8', '2', b'1'),
('2', '61', null, null, '9', '2', b'1'),
('2', '62', null, null, '9', '2', b'1'),
('2', '63', null, null, '9', '2', b'1'),
('2', '66', '8', '340', '5', null, b'1'),
('2', '67', null, null, '1', '3', b'1'),
('2', '68', null, null, '4', '3', b'1'),
('2', '69',null, null, '4', '3', b'1'),
('2', '70', null, null, '1', '3', b'1'),
('2', '71', null, null, '1', '3', b'1'),
('2', '72', null, null, '1', '3', b'1'),
('2', '73', null, null, '2', '5', b'1'),
('2', '74', null, null, '3', '3', b'1'),
('2', '75', null, null, '2', '3', b'1'),
('2', '76', null, null, '2', '3', b'1'),
('2', '78', null, null, '3', '3', b'1'),
('2', '80', null, null, '8', '3', b'1'),
('2', '81', null, null, '5', '3', b'1'),
('2', '82', null, null, '3', '3', b'1'), 	
('2', '83', null, null, '6', '3', b'1'),
('2', '84', '7', '337', '5', null, b'1'),
('2', '85', null, '337', '7', '3', b'1'),
('2', '86', null, null, '8', '3', b'1'),
('2', '87', null, '337', '7', '3', b'1'),
('2', '88', null, '340', '7', '3', b'1'),
('2', '89', '9', '402', '5', null, b'1'),
('2', '90', '10', '419', '5', null, b'1'),

('2', '91', null, null, '7', '3', b'1'),
('2', '92', null, null, '3', '3', b'1'),
('2', '93', null, null, '4', '3', b'1'), 	
('2', '94', null, null, '5', '3', b'1'),
('2', '95', null, null, '4', '3', b'1'),
('2', '96', null, null, '5', '3', b'1'),
('2', '97', null, null, '5', '3', b'1'),
('2', '98', null, null, '8', '3', b'1'),
('2', '99', null, null, '7', '3', b'1'),
('2', '100', null, null, '4', '3', b'1');

INSERT INTO `flm_db`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `curriculum_subject_is_active`) VALUES
('1', '101', '156', b'1'),
('1', '102', '156', b'1'),
('1', '103', '156', b'1'),
('1', '104', '156', b'1'),
('1', '105', '156', b'1'),
('1', '106', '156', b'1'),
('1', '107', '156', b'1'),
('2', '101', '9', b'1'),
('2', '102', '9', b'1'),
('2', '103', '9', b'1'),
('2', '104', '9', b'1'),
('2', '105', '9', b'1'),
('2', '106', '9', b'1'),
('2', '107', '9', b'1'),
('3', '101', '9', b'1'),
('3', '102', '9', b'1'),
('3', '103', '9', b'1'),
('3', '104', '9', b'1'),
('3', '105', '9', b'1'),
('3', '106', '9', b'1'),
('3', '107', '9', b'1'),
('4', '101', '225', b'1'),
('4', '102', '225', b'1'),
('4', '103', '225', b'1'),
('4', '104', '225', b'1'),
('4', '105', '225', b'1'),
('4', '106', '225', b'1'),
('4', '107', '225', b'1'),
('2', '108', '359', b'1'),
('3', '108', '359', b'1'),
('2', '109', '364', b'1'),
('2', '110', '364', b'1'),
('2', '111', '364', b'1'),
('2', '112', '364', b'1'),
('2', '113', '364', b'1'),
('2', '114', '364', b'1'),
('2', '115', '364', b'1'),
('2', '116', '364', b'1'),
('2', '117', '364', b'1'),
('2', '118', '364', b'1'),
('2', '119', '364', b'1'),
('3', '109', '364', b'1'),
('3', '110', '364', b'1'),
('3', '111', '364', b'1'),
('3', '112', '364', b'1'),
('3', '113', '364', b'1'),
('3', '114', '364', b'1'),
('3', '115', '364', b'1'),
('3', '116', '364', b'1'),
('3', '117', '364', b'1'),
('3', '118', '364', b'1'),
('3', '119', '364', b'1'),
('2', '120', '385', b'1'),
('2', '121', '385', b'1'),
('3', '120', '385', b'1'),
('3', '121', '385', b'1'),
('4', '122', '397', b'1'),
('4', '123', '397', b'1')
;


INSERT INTO `flm_db`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`,`description`, `is_active`) 
VALUES 
('93', '2', '94','PRJ301: DBI202, PRO192', b'1'),
('95', '2', '94','SWE201c: PRO192 (not applied to the BIT_AI17 and BA programs)', b'1'),

('78', '2', '93','DBI202: (No pre-requisite)', b'1'),
('73', '2', '93','PRO192: PRF192', b'1'),

('73', '2', '95','PRO192: PRF192', b'1'),

('72', '2', '73','PRF192: None', b'1');


INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_ApprovedDate`,`decision_CreateDate`, `creator_id`) 
VALUES ('94', '500/QĐ-ĐHFPT','Quyết định điều chỉnh CTĐT từ kì Fall 2023', '2022/2/2','2022/2/2',  '1');
INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_ApprovedDate`,`decision_CreateDate`, `creator_id`) 
VALUES ('95', '495/QĐ-ĐHFPT','Quyết định điều chỉnh CTĐT từ kì Fall 2023', '2022/8/17','2022/2/2',  '1');
INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_ApprovedDate`,`decision_CreateDate`, `creator_id`) 
VALUES ('96', '1341/QĐ-ĐHFPT','Quyết định điều chỉnh CTĐT từ kì Fall 2023', '2022/4/21','2022/2/2',  '1');
INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_ApprovedDate`,`decision_CreateDate`, `creator_id`) 
VALUES ('97', '1341/QĐ-ĐHFPT','Quyết định điều chỉnh CTĐT từ kì Fall 2023', '2022/4/7','2022/2/2',  '1');


INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, 
`degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`, `created_by`) 
VALUES ('9641', 'Software development project_Dự án phát triển phần mềm', '1', b'1', b'1',
 '12', '10', '5', '1) On-going Assessment
- On-going Assessment 1: 20%
- On-going Assessment 2: 20%
- On-going Assessment 3: 20%
2) Final Project Presentation: 40%
To be selected to the Final Presentation, each student needs:
* Average on-going grade assessment >=5/10
* No cheating during the project progress', '94', '94', '2');

INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, 
`degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`,  `created_by`) 
VALUES ('6617', 'Java Web Application Development_Phát triển ứng dụng Java web', '3', b'1', b'1',
 '12', '10', '5', '	1. On-going assessment:
Workshop 1: 5%
Progress test 1: 5%
Workshop 2: 5%
Progress test 2: 5%
Assignment : 40%
Practical Exam : 20%
2. Final Exam: 20%
Total 100%
Completion Criteria:
1) Every on-going assessment component >0
2) Final Exam Score >=4 & Final Result >=5', '95', '93', '2');

INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, 
`degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`, `created_by`) 
VALUES ('9777', 'Introduction to Software Engineering_Nhập môn kỹ thuật phần mềm', '3', b'1', b'1',
 12, '10', '5', '1. Complete the online course to be allowed to take Final Exam
2. Final Exam is included of Final Theory Exam (TE) & Final Practical Exam (PE): 100%
3. Student gets 0.25 bonus points for each course completed on time. The total bonus point is not greater than 1.
4. Completion Criteria: Final TE Score >=4 & Final PE Score >=4 & (Final TE Score*weigh of Final TE + Final PE Score*weigh of Final PE + bonus) >= 5
5. In the case: (5 > Final TE Score >=4) & (5 > Final PE Score >=4) & (Final TE Score*weigh of Final TE + Final PE Score*weigh of Final PE + bonus) < 5, the student can choose to take the resit of both TE & PE OR just either TE or PE.
', '96', '95', '2');

INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, 
`degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`, `created_by`) 
VALUES ('9433', 'Object-Oriented Programming_Lập trình hướng đối tượng', '3', b'1', b'1',
 12, '10', '5', 'None', '97', '73', '2');

INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, 
`degree_level`, `scoringScale`, `minAvgMarkToPass`, `note`, `decision_id`, `subject_id`, `created_by`) 
VALUES ('9569', 'Programming Fundamentals_Cơ sở lập trình', '3', b'1', b'1',
 12, '10', '5', 'None', '97', '72', '2');

-- Insert plo
INSERT INTO flm_db.`plo` (plo_id, plo_name, plo_description, curriculum_id , is_active,created_at, created_by)
SELECT '1', 'PLO1', 'Demonstrate basic knowledge of social sciences, politics and law, national security and defense, contributing to the formation of worldview and scientific methodology.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '2', 'PLO2', 'Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '3', 'PLO3', 'Communicating and working in groups effectively in academic and practical environments.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '4', 'PLO4', 'Use English proficiently in communication and learning (equivalent to level 4 according to the 6-level Foreign Language Proficiency Framework for Vietnam, IELTS 6.0 or TOEFL (paper) 575-600 or TOEFL (iBT) 90 -100); and be able to communicate simply in Japanese.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '5', 'PLO5', 'Demonstrate professional behaviors, morality, social responsibilities and a sense of dedication to community.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '6', 'PLO1', 'Demonstrate basic knowledge of social sciences, politics and law, national security and defense, contributing to the formation of worldview and scientific methodology.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '7', 'PLO2', 'Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '8', 'PLO3', 'Communicating and working in groups effectively in academic and practical environments.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '9', 'PLO4', 'Use English proficiently in communication and learning (equivalent to level 4 according to the 6-level Foreign Language Proficiency Framework for Vietnam, IELTS 6.0 or TOEFL (paper) 575-600 or TOEFL (iBT) 90 -100); and be able to communicate simply in Japanese.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '10', 'PLO5', 'Demonstrate professional behaviors, morality, social responsibilities and a sense of dedication to community.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%';
INSERT INTO flm_db.`plo` (plo_id, plo_name, plo_description, curriculum_id, is_active,created_at, created_by)
SELECT '50', 'PLO1', 'Have basic knowledge of social science, law and politics, national security and defense, contribute to form a worldview and scientific methodology.', c.curriculum_id, b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '51', 'PLO2', 'Have the mindset of entrepreneurship, creativity, critical thinking, problem solving.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '52', 'PLO3', 'Communicate and team work effectively in academic and practical environments.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '53', 'PLO4', 'Good at English to communicate and study; can simply communicate in a second foreign language.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '54', 'PLO5', 'Be professional, ethical, have social responsibility, have a spirit of service to the community.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '55', 'PLO1', 'Have basic knowledge of social science, law and politics, national security and defense, contribute to form a worldview and scientific methodology.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_FIN_K16D17A%'
UNION ALL
SELECT '56', 'PLO2', 'Have the mindset of entrepreneurship, creativity, critical thinking, problem solving.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_FIN_K16D17A%'
UNION ALL
SELECT '57', 'PLO3', 'Communicate and team work effectively in academic and practical environments.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_FIN_K16D17A%'
UNION ALL
SELECT '58', 'PLO4', 'Good at English to communicate and study; can simply communicate in a second foreign language', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_FIN_K16D17A%'
UNION ALL
SELECT '59', 'PLO5', 'Be professional, ethical, have social responsibility, have a spirit of service to the community.', c.curriculum_id,b'1','2023/07/21','2'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_FIN_K16D17A%';

-- insert data po
INSERT INTO flm_db.`po` (po_id, po_name, po_description, curriculum_id,is_active)
SELECT '1', 'PO1', 'Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '2', 'PO2', 'Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '3', 'PO3', 'Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society.)', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '4', 'PO4', 'Can use English well in study and work and a second foreign language in normal communication', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '5', 'PO5', 'Honesty, high discipline in study and work, know how to work effectively in groups; know how to behave culturally at work and in society; dynamic, creative and willing to learn constantly. Demonstrate professional attitude and behavior with the ability to conceive of ideas, design, implement and operate systems in the context of corporation and society.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K17B%'
UNION ALL
SELECT '6', 'PO1', 'Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '7', 'PO2', 'Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '8', 'PO3', 'Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '9', 'PO4', 'Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society.)', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%'
UNION ALL
SELECT '10', 'PO5', 'Can use English well in study and work and a second foreign language in normal communication.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BIT_SE_K16D_K17A%';
INSERT INTO flm_db.`po` (po_id, po_name, po_description, curriculum_id,is_active)
SELECT '50', 'BBA_PO1', 'Help students develop physically, mentally, intellectually, morally and deepen national pride by equipping them with general knowledge of politics, law, economy, society, physical education, music and national defence education.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '51', 'BBA_PO2', 'Provide students with foundational and in-depth knowledge of the economics, business environment, business administration in general, specialized majors and management, analysis tools in this field.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '52', 'BBA_PO3', 'Develop entrepreneurship skills to take advantage of global business opportunities; develop multi-dimensional thinking, problem-solving and decision-making skills based on quantitative and qualitative analytical tools.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '53', 'BBA_PO4', 'Orientate students towards the right attitudes and work ethics, abilities to think creatively, work well in groups and independently and abilities to solve problems related to jobs and industries using English and combining knowledge of International Business, Communication or Language Teaching effectively, and be capable of lifelong learning for personal and professional development.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%'
UNION ALL
SELECT '54', 'BBA_PO5', 'Help students use English fluently and use Chinese as the second language at a basic level.', c.curriculum_id,b'1'
FROM flm_db.curriculum c
WHERE c.code LIKE '%BBA_MKT_K16D17A%';


DELIMITER //

CREATE TRIGGER calculate_total_credit_1 AFTER INSERT ON curriculum_subject
FOR EACH ROW
BEGIN
    DECLARE total_credits INT;
    
    -- Tính tổng credit cho curriculum_id hiện tại
    SELECT SUM(no_credit) INTO total_credits
    FROM curriculum_subject
    WHERE curriculum_id = NEW.curriculum_id;
    
    -- Cập nhật giá trị total_credit trong bảng curriculum
    UPDATE curriculum
    SET total_credit = total_credits
    WHERE curriculum_id = NEW.curriculum_id;
END //
  
DELIMITER ;

drop trigger calculate_total_credit

DELIMITER //

CREATE TRIGGER calculate_total_credit AFTER UPDATE ON curriculum_subject
FOR EACH ROW
BEGIN
    DECLARE total_credits INT;
    
    -- Tính tổng credit cho curriculum_id hiện tại
    SELECT SUM(no_credit) INTO total_credits
    FROM curriculum_subject
    WHERE curriculum_id = NEW.curriculum_id;
    
    -- Cập nhật giá trị total_credit trong bảng curriculum
    UPDATE curriculum
    SET total_credit = total_credits
    WHERE curriculum_id = NEW.curriculum_id;
END //

DELIMITER ;




DELIMITER //

CREATE TRIGGER calculate_total_credit_2 AFTER DELETE ON curriculum_subject
FOR EACH ROW
BEGIN
    DECLARE total_credits INT;
    
    -- Tính tổng credit cho curriculum_id hiện tại
    SELECT SUM(no_credit) INTO total_credits
    FROM curriculum_subject
    WHERE curriculum_id = OLD.curriculum_id;
    
    -- Cập nhật giá trị total_credit trong bảng curriculum
    UPDATE curriculum
    SET total_credit = total_credits
    WHERE curriculum_id = OLD.curriculum_id;
END //

DELIMITER ;

-- insert session
INSERT INTO `flm_db`.`session` (`session_id`, `syllabus_id`) VALUES ('1', '1');
INSERT INTO `flm_db`.`session` (`session_id`, `syllabus_id`) VALUES ('2', '1');
-- insert question
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('1', '1', 'HCM_CQ1.1', 'What is sentence meaning? Give examples.');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('2', '1', '	HCM_CQ1.2', 'What is speaker meaning? Give examples.');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('3', '1', 'HN_CQ1.1', 'What is semantics?');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('4', '2', 'CT_CQ1.1', 'How are sentence meaning and speaker meaning different? Provide examples.');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('5', '2', 'HCM_CQ1.3', 'Distinguish sentence meaning and speaker meaning - Exercise 2, p.14 in the coursebook');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('6', '2', 'HCM_CQ1.4', 'Distinguish sentence meaning and speaker meaning - Exercise 5, p.15 in the coursebook');
INSERT INTO `flm_db`.`question` (`question_id`, `session_id`, `name`, `details`) VALUES ('7', '2', 'HN_CQ1.2', 'What is semantics?');

