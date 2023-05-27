
CREATE TABLE employee
(
    id int NOT NULL AUTO_INCREMENT ,
    user_id varchar(20) NOT NULL ,
    password varchar(80) NOT NULL ,
    employee_number varchar(10) NOT NULL ,
    name varchar(10) NOT NULL ,
    email varchar(50) NOT NULL ,
    registration_number varchar(20) NOT NULL ,
    phone_number varchar(20) NOT NULL ,
    education varchar(20) NOT NULL ,
    experience_year int NOT NULL ,
    entering_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    approved boolean NOT NULL DEFAULT false,
    skill varchar(255) DEFAULT NULL ,
    c_rank varchar(20) DEFAULT NULL ,
    position varchar(20) DEFAULT NULL ,
    role varchar(10) DEFAULT NULL ,
    deleted boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT unq_user_id UNIQUE (user_id),
    CONSTRAINT unq_employee_no UNIQUE (employee_number),
    CONSTRAINT unq_registration_number UNIQUE (registration_number),
    CONSTRAINT unq_email UNIQUE (email)
);

CREATE TABLE project
(
    id int NOT NULL AUTO_INCREMENT ,
    project_number varchar(10) NOT NULL ,
    name varchar(30) NOT NULL ,
    create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP() ,
    start_date datetime DEFAULT NULL ,
    end_date datetime DEFAULT NULL ,
    client varchar(30) NOT NULL ,
    budget int NOT NULL DEFAULT 0,
    state varchar(30) NOT NULL DEFAULT 0,
    description varchar(50) DEFAULT NULL,
    deleted boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT unq_project_number UNIQUE (project_number)
);

CREATE TABLE man_power
(
    id int NOT NULL AUTO_INCREMENT ,
    employee_id int NOT NULL ,
    project_id int NOT NULL ,
    task varchar(20) NOT NULL ,
    start_date datetime NOT NULL,
    end_date datetime NOT NULL ,
    PRIMARY KEY (id),
    CONSTRAINT fk_manpower_to_employee FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_manpower_to_project FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);


CREATE TABLE evaluation
(
    id int NOT NULL AUTO_INCREMENT ,
    project_id int NOT NULL ,
    evaluated_id int NOT NULL ,
    contents varchar(80) DEFAULT NULL,
    performance varchar(30) NOT NULL ,
    communication varchar(30) NOT NULL ,
    type varchar(20) NOT NULL ,
    end_date datetime NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_evaluation_to_manpower FOREIGN KEY (evaluated_id) REFERENCES man_power (id) ON DELETE RESTRICT ON UPDATE RESTRICT ,
    CONSTRAINT fk_evaluation_to_project FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
