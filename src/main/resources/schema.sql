
-- 1. Table: project_details
CREATE TABLE IF NOT EXISTS project_details (
    project_id VARCHAR(50) PRIMARY KEY,
    projectname VARCHAR(100),
    describsion TEXT,
    budget DOUBLE,
    status VARCHAR(50),
    available_fund DOUBLE
);

-- 2. Table: employee_details
CREATE TABLE IF NOT EXISTS employee_details (
    employee_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    age INT,
    role VARCHAR(50),
    project_id VARCHAR(50),
    password VARCHAR(100),
    designation VARCHAR(100),
    primary_skill VARCHAR(100),
    secondary_skill VARCHAR(100),
    ternary_skill VARCHAR(100),
    is_interviewer VARCHAR(10),
    FOREIGN KEY (project_id) REFERENCES project_details(project_id)
);

-- 3. Table: job_description
CREATE TABLE IF NOT EXISTS job_description (
    jobdescription_id VARCHAR(50) PRIMARY KEY,
    role VARCHAR(100),
    salary DOUBLE,
    location VARCHAR(100),
    experience VARCHAR(50),
    about TEXT,
    project_id VARCHAR(50),
    required_candidate INT,
    status VARCHAR(50),
    send_to_hr VARCHAR(10),
    postjobdescription VARCHAR(10),
    primary_skill VARCHAR(100),
    secondary_skill VARCHAR(100),
    ternary_skill VARCHAR(100),
    FOREIGN KEY (project_id) REFERENCES project_details(project_id)
);

-- 4. Table: candidate_details
CREATE TABLE IF NOT EXISTS candidate_details (
    candidate_id VARCHAR(50) PRIMARY KEY,
    job_description_id VARCHAR(50),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    experience VARCHAR(50),
    email VARCHAR(150),
    contact_no VARCHAR(20),
    primary_skill VARCHAR(100),
    secondary_skill VARCHAR(100),
    ternary_skill VARCHAR(100),
    interviewer_id VARCHAR(50),
    interview_schedule DATE,
    send_offerletter VARCHAR(50),
    status VARCHAR(50),
    FOREIGN KEY (job_description_id) REFERENCES job_description(jobdescription_id),
    FOREIGN KEY (interviewer_id) REFERENCES employee_details(employee_id)
);

-- 5. Table: assesment_details
CREATE TABLE IF NOT EXISTS assesment_details (
    assesment_id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id VARCHAR(50),
    techSkill VARCHAR(100),
    communication VARCHAR(100),
    softSkills VARCHAR(100),
    status VARCHAR(50),
    interview_date DATE,
    FOREIGN KEY (candidate_id) REFERENCES candidate_details(candidate_id)
);
