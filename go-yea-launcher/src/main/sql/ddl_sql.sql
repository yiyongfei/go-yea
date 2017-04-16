--具体字段完整定义，请参见yea-shiro内的shiro_db.mwb文件
--建表脚本对应postgresql，如果是mysql，直接根据shiro_db.mwb文件生成脚本

CREATE TABLE t_operation_info (
    operation_id bigint NOT NULL,
    operation_name character varying(10) NOT NULL,
    operation_desc character varying(45)
);
ALTER TABLE t_operation_info
    ADD CONSTRAINT t_operation_info_pkey PRIMARY KEY (operation_id);


CREATE TABLE t_resource_info (
    resource_id bigint NOT NULL,
    parent_resource_id bigint,
    resource_name character varying(10) NOT NULL,
    resource_content character varying(120) NOT NULL,
    resource_desc character varying(45)
);
ALTER TABLE t_resource_info
    ADD CONSTRAINT t_resource_info_pkey PRIMARY KEY (resource_id);


CREATE TABLE t_permission_info (
    permission_id bigint NOT NULL,
    resource_id bigint NOT NULL,
    operation_id bigint NOT NULL,
    permission_desc character varying(45),
    is_valid character varying(2) DEFAULT '01'::character varying NOT NULL,
    is_delete character varying(2) DEFAULT '01'::character varying NOT NULL,
    permission_name character varying(30)
);
ALTER TABLE t_permission_info
    ADD CONSTRAINT t_permission_info_pkey PRIMARY KEY (permission_id);


CREATE TABLE t_resource_identifier (
    identifier_id bigint NOT NULL,
    permission_id bigint,
    identifier_name character varying(45),
    identifier_path character varying(500) NOT NULL,
    identifier_type character varying(2),
    access_type character varying(2)
);
ALTER TABLE t_resource_identifier
    ADD CONSTRAINT t_resource_identifier_pkey PRIMARY KEY (identifier_id);


CREATE TABLE t_menu_info (
    menu_id bigint NOT NULL,
    parent_menu_id bigint,
    menu_name character varying(45) NOT NULL,
    identifier_id bigint,
    is_valid character varying(2) DEFAULT '01'::character varying NOT NULL,
    is_delete character varying(2) DEFAULT '01'::character varying NOT NULL,
    menu_sequence integer
);
ALTER TABLE ONLY t_menu_info
    ADD CONSTRAINT t_menu_info_pkey PRIMARY KEY (menu_id);


CREATE TABLE t_role_info (
    role_id bigint NOT NULL,
    role_type character varying(4),
    role_name character varying(30),
    role_desc character varying(45),
    is_valid character varying(2) DEFAULT '01'::character varying,
    is_delete character varying(2) DEFAULT '01'::character varying
);
ALTER TABLE ONLY t_role_info
    ADD CONSTRAINT t_role_info_pkey PRIMARY KEY (role_id);
COMMENT ON COLUMN t_role_info.role_type IS '角色类型';
COMMENT ON COLUMN t_role_info.role_name IS '角色名称';
COMMENT ON COLUMN t_role_info.role_desc IS '角色描述';
COMMENT ON COLUMN t_role_info.is_valid IS '是否有效：01、有效，09、无效';


CREATE TABLE t_role_permission_rela (
    role_permission_id bigint NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint,
    permission_wildcards character varying(100)
);
ALTER TABLE ONLY t_role_permission_rela
    ADD CONSTRAINT t_role_permission_rela_pkey PRIMARY KEY (role_permission_id);


CREATE TABLE t_party_info (
    party_id bigint NOT NULL,
    party_type character varying(4) NOT NULL
);
ALTER TABLE ONLY t_party_info
    ADD CONSTRAINT t_party_info_pkey PRIMARY KEY (party_id);


CREATE TABLE t_party_role_rela (
    party_role_id bigint NOT NULL,
    party_id bigint NOT NULL,
    role_id bigint NOT NULL
);
ALTER TABLE ONLY t_party_role_rela
    ADD CONSTRAINT t_party_role_rela_pkey PRIMARY KEY (party_role_id);


CREATE TABLE t_person_info (
    party_id bigint NOT NULL,
    person_name character varying(50) NOT NULL,
    sex_type_code character varying(10),
    birth_date date,
    marital_type_code character varying(10),
    person_memo character varying(500)
);
ALTER TABLE ONLY t_person_info
    ADD CONSTRAINT t_person_info_pkey PRIMARY KEY (party_id);
    

CREATE TABLE t_user_info (
    party_id bigint NOT NULL,
    login_password character varying(50) NOT NULL,
    login_salt character varying(20) NOT NULL,
    is_lock character varying(2) DEFAULT '01'::character varying NOT NULL
);
ALTER TABLE ONLY t_user_info
    ADD CONSTRAINT t_user_info_pkey PRIMARY KEY (party_id);
    
CREATE TABLE t_login_info (
    login_id bigint NOT NULL,
    party_id bigint NOT NULL,
    login_name character varying(50) NOT NULL,
    is_primary character varying(2) NOT NULL
);
ALTER TABLE t_login_info
    ADD CONSTRAINT t_login_info_pkey PRIMARY KEY (login_id);

    
CREATE TABLE t_party_relationship (
    relationship_id bigint NOT NULL,
    relationship_type_code character varying(4) NOT NULL,
    src_party_role_id bigint NOT NULL,
    tag_party_role_id bigint NOT NULL,
    start_date date,
    end_date date
);
ALTER TABLE ONLY t_party_relationship
    ADD CONSTRAINT t_party_relationship_pkey PRIMARY KEY (relationship_id);


CREATE TABLE t_relationship_type_code (
    relationship_type_code character varying(4) NOT NULL,
    relationship_type_desc character varying(45)
);
ALTER TABLE ONLY t_relationship_type_code
    ADD CONSTRAINT t_relationship_type_code_pkey PRIMARY KEY (relationship_type_code);
