INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved, skill, c_rank, position, role, deleted)
VALUES ( null, 'admin01', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '161001', '관리자1','admin001@gmail.com', '12345678-00000001', '010-0000-0001', '명지대학교 졸업', 10,
         '2016-03-01 13:10:00', true, null, 'DIRECTOR', 'DIRECTOR', 'ADMIN', false);

INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved,skill, c_rank, position, role, deleted)
VALUES ( null, 'admin02', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '161002', '관리자2','admin002@gmail.com', '12345678-00000002', '010-0000-0002', '명지대학교 졸업', 10,
         '2016-03-01 13:10:00', true, null, 'DIRECTOR', 'DIRECTOR', 'ADMIN', false);

INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved,skill, c_rank, position, role, deleted)
VALUES ( null, 'test001', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '171101', '직원1','tester001@gmail.com', '12345678-00000011', '010-0000-0011', '명지대학교 졸업', 10,
         '2017-03-01 13:10:00', true, 'java spring jpa mysql docker aws', 'SENIOR_MANAGER', 'DEVELOPER', 'USER', false);

INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved,skill, c_rank, position, role, deleted)
VALUES ( null, 'test002', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '201001', '직원2','tester002@gmail.com', '12345678-00000012', '010-0000-0012', '명지대학교 졸업', 10,
         '2020-03-01 13:10:00', true, null, 'SENIOR_ASSISTANCE', 'ADMINISTRATION', 'USER', false);

INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved,skill, c_rank, position, role, deleted)
VALUES ( null, 'test003', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '191101', '직원3','tester003@gmail.com', '12345678-00000013', '010-0000-0013', '명지대학교 졸업', 10,
         '2019-03-01 13:10:00', true, 'java spring jpa mysql docker aws', 'ASSISTANCE', 'DEVELOPER', 'USER', false);

INSERT INTO employee(id, user_id, password, employee_number, name, email,register_number, phone_number, education,
                     experience_year, entering_date, approved,skill, c_rank, position, role, deleted)
VALUES ( null, 'test004', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '191102', '직원4','tester004@gmail.com', '12345678-00000014', '010-0000-0014', '명지대학교 졸업', 10,
         '2019-03-01 13:10:00', true, 'java spring jpa mysql docker aws', 'ASSISTANCE', 'DEVELOPER', 'USER', false);

INSERT INTO employee (id, user_id, password, employee_number, name, email, register_number, phone_number, education, experience_year, entering_date, approved, skill, c_rank, position, role, deleted)
VALUES
    (null, 'test005', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '104101', '직원5', 'test005@gmail.com', '12345678-00000015', '010-0000-0015', '고려대학교 졸업', 3, '2010-01-01 00:00:00', true, 'Java, Spring, MySQL', 'ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test006', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '114101', '직원6', 'test006@gmail.com', '12345678-00000016', '010-0000-0016', '서울대학교 졸업', 4, '2011-02-01 00:00:00', true, 'Python, Django, PostgreSQL', 'ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test007', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '124101', '직원7', 'test007@gmail.com', '12345678-00000017', '010-0000-0017', '한양대학교 졸업', 5, '2012-03-01 00:00:00', true, 'JavaScript, React, Node.js', 'ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test008', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '134101', '직원8', 'test008@gmail.com', '12345678-00000018', '010-0000-0018', '성균관대학교 졸업', 4, '2013-04-01 00:00:00', true, 'C++, Java, Python', 'ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test009', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '144101', '직원9', 'test009@gmail.com', '12345678-00000019', '010-0000-0019', '서강대학교 졸업', 5, '2014-05-01 00:00:00', true, 'Ruby, Rails, MongoDB', 'ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test010', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '150101', '직원10', 'test010@gmail.com', '12345678-00000020', '010-0000-0020', '성균관대학교 대학원 졸업', 6, '2015-06-01 00:00:00', true, 'Java, Spring Boot, PostgreSQL', 'DIRECTOR', 'DEVELOPER', 'USER', false),
    (null, 'test011', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '160103', '직원11', 'test011@gmail.com', '12345678-00000021', '010-0000-0021', '고려대학교 대학원 졸업', 7, '2016-07-01 00:00:00', true, 'Python, Flask, MySQL', 'DIRECTOR', 'DEVELOPER', 'USER', false),
    (null, 'test012', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '171102', '직원12', 'test012@gmail.com', '12345678-00000022', '010-0000-0022', '명지대학교 대학원 졸업', 2, '2017-08-01 00:00:00', true, 'JavaScript, React, Node.js', 'SENIOR_MANAGER', 'DEVELOPER', 'USER', false),
    (null, 'test013', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '181101', '직원13', 'test013@gmail.com', '12345678-00000023', '010-0000-0023', '서울대학교 대학원 졸업', 9, '2018-09-01 00:00:00', true, 'C++, Java, Python', 'SENIOR_MANAGER', 'DEVELOPER', 'USER', false),
    (null, 'test014', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '191103', '직원14', 'test014@gmail.com', '12345678-00000024', '010-0000-0024', '한양대학교 대학원 졸업', 10, '2019-10-01 00:00:00', true, 'Ruby, Rails, MongoDB', 'SENIOR_MANAGER', 'DEVELOPER', 'USER', false),
    (null, 'test015', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '103102', '직원15', 'test015@gmail.com', '12345678-00000025', '010-0000-0025', '성균관대학교 졸업', 11, '2010-11-01 00:00:00', true, 'Java, Spring, MySQL', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test016', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '113102', '직원16', 'test016@gmail.com', '12345678-00000026', '010-0000-0026', '서울대학교 졸업', 12, '2011-12-01 00:00:00', true, 'Python, Django, PostgreSQL', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test017', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '123102', '직원17', 'test017@gmail.com', '12345678-00000027', '010-0000-0027', '한양대학교 졸업', 13, '2012-01-01 00:00:00', true, 'JavaScript, React, Node.js', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test018', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '133102', '직원18', 'test018@gmail.com', '12345678-00000028', '010-0000-0028', '성균관대학교 졸업', 14, '2013-02-01 00:00:00', true, 'C++, Java, Python', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test019', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '143102', '직원19', 'test019@gmail.com', '12345678-00000029', '010-0000-0029', '서강대학교 졸업', 15, '2014-03-01 00:00:00', true, 'Ruby, Rails, MongoDB', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test020', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '153102', '직원20', 'test020@gmail.com', '12345678-00000030', '010-0000-0030', '고려대학교 대학원 졸업', 8, '2015-04-01 00:00:00', true, 'Java, Spring Boot, PostgreSQL', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test021', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '163104', '직원21', 'test021@gmail.com', '12345678-00000031', '010-0000-0031', '한양대학교 대학원 졸업', 12, '2016-05-01 00:00:00', true, 'Python, Flask, MySQL', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test022', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '173103', '직원22', 'test022@gmail.com', '12345678-00000032', '010-0000-0032', '명지대학교 대학원 졸업', 6, '2017-06-01 00:00:00', true, 'JavaScript, React, Node.js', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test023', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '183102', '직원23', 'test023@gmail.com', '12345678-00000033', '010-0000-0033', '서울대학교 대학원 졸업', 13, '2018-07-01 00:00:00', true, 'C++, Java, Python', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false),
    (null, 'test024', '$2a$10$k/JAofWoHWEP1Xz6pW9rseoAOltZRJ4lZga6/aD/REISTuCaZwilS', '193104', '직원24', 'test024@gmail.com', '12345678-00000034', '010-0000-0034', '한양대학교 대학원 졸업', 19, '2019-08-01 00:00:00', true, 'Ruby, Rails, MongoDB', 'SENIOR_ASSISTANCE', 'DEVELOPER', 'USER', false);
