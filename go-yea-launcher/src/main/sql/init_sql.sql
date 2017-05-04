/*初始化数据，包含资源、菜单、用户等等*/
/*管理员用户admin，密码admin*/

INSERT INTO t_operation_info (operation_id, operation_name, operation_desc) VALUES (6398009414274949388, '新增', '添加某个指定资源');
INSERT INTO t_operation_info (operation_id, operation_name, operation_desc) VALUES (6398009507734840419, '修改', '修改某个指定资源');
INSERT INTO t_operation_info (operation_id, operation_name, operation_desc) VALUES (6398009554353499485, '删除', '删除某个指定资源');
INSERT INTO t_operation_info (operation_id, operation_name, operation_desc) VALUES (6398009628251990121, '查看', '查看某个指定资源的内容');
INSERT INTO t_operation_info (operation_id, operation_name, operation_desc) VALUES (6398009715678467718, '查询', '查找一批指定条件的资源');


INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010006957147679, NULL, '运营平台', '运营平台', '运营平台（系统层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010103212843928, 6398010006957147679, '开发管理', '运营平台:开发管理', '开发管理（模块层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010238630017444, 6398010006957147679, '权限管理', '运营平台:权限管理', '权限管理（模块层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010342114914195, 6398010006957147679, '授权管理', '运营平台:授权管理', '授权管理（模块层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010411954591269, 6398010103212843928, '生成工具', '运营平台:开发管理:生成工具', '生成工具（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010569890409806, 6398010238630017444, '操作设置', '运营平台:权限管理:操作设置', '操作设置（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010665460247586, 6398010238630017444, '资源设置', '运营平台:权限管理:资源设置', '资源设置（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010732713503713, 6398010238630017444, '权限设置', '运营平台:权限管理:权限设置', '权限设置（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398010819124445959, 6398010342114914195, '角色管理', '运营平台:授权管理:角色管理', '角色管理（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6398011376964118256, 6398010342114914195, '角色赋权', '运营平台:授权管理:角色赋权', '角色赋权，定义完角色后，可对角色赋予相应权限（功能层面）');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6405389055104241314, 6398010238630017444, '资源标识', '运营平台:权限管理:资源标识', '资源标识，针对URL、方法、菜单等，通过它可与权限关联');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6405819933001677522, 6398010342114914195, '人员管理', '运营平台:授权管理:人员管理', '人员管理');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6405820101351228405, 6398010342114914195, '人员授权', '运营平台:授权管理:人员授权', '人员授权');
INSERT INTO t_resource_info (resource_id, parent_resource_id, resource_name, resource_content, resource_desc) VALUES (6406175865012580712, 6398010238630017444, '菜单设置', '运营平台:权限管理:菜单设置', '');


INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398011760658268736, 6398010411954591269, 6398009628251990121, '进入生成工具页面的权限', '01', '01', '进入生成工具');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398011947565074347, 6398010411954591269, 6398009414274949388, '通过生成工具指定相关表生成相应类', '01', '01', '通过生成工具生成类');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398012219173333265, 6398010569890409806, 6398009414274949388, '新增权限里的资源操作名', '01', '01', '新增权限里的资源操作名');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398012425956167057, 6398010569890409806, 6398009715678467718, '查找权限里所有操作名', '01', '01', '查找权限里所有操作名');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398012561582779099, 6398010665460247586, 6398009414274949388, '新增权限里的资源项', '01', '01', '新增权限里的资源项');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398012756589852276, 6398010665460247586, 6398009715678467718, '查找权限里的所有资源项', '01', '01', '查找权限里的所有资源项');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398012963327697532, 6398010732713503713, 6398009414274949388, '根据所选的资源和操作新增权限', '01', '01', '根据资源和操作新增权限');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398013187857065038, 6398010732713503713, 6398009715678467718, '查找所有已定义的权限', '01', '01', '查找所有已定义的权限');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398013500678375340, 6398010819124445959, 6398009414274949388, '新增欲授权的角色', '01', '01', '新增欲授权的角色');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398013636696842447, 6398010819124445959, 6398009628251990121, '查看欲授权的角色', '01', '01', '查看欲授权的角色');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398013749002150242, 6398010819124445959, 6398009715678467718, '查找所有角色', '01', '01', '查找所有角色');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398014132825732176, 6398011376964118256, 6398009414274949388, '为指定角色赋予权限（赋权有二种方式，直接授予权限，或指定资源的所有相关权限）', '01', '01', '为指定角色赋予权限');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398014473326070469, 6398011376964118256, 6398009554353499485, '删除角色中某项已赋的权限', '01', '01', '删除角色中某项已赋的权限');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6398014593318640766, 6398011376964118256, 6398009628251990121, '查看角色的赋权情况', '01', '01', '查看角色的赋权情况');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405389720996578990, 6405389055104241314, 6398009715678467718, '', '01', '01', '查询已定义的资源标识');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405389886294424306, 6405389055104241314, 6398009628251990121, '', '01', '01', '查看资源标识内容');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405389973234469222, 6405389055104241314, 6398009414274949388, '', '01', '01', '保存资源标识');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405820313340590347, 6405819933001677522, 6398009715678467718, '', '01', '01', '进入人员管理');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405820398730705225, 6405819933001677522, 6398009628251990121, '', '01', '01', '查看人员信息');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405820611666682427, 6405819933001677522, 6398009414274949388, '', '01', '01', '新增或更新人员信息');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405820781138342857, 6405820101351228405, 6398009628251990121, '', '01', '01', '查看人员被授予的角色内容');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6405820972564171813, 6405820101351228405, 6398009414274949388, '', '01', '01', '为人员选择角色并授予');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6406175996771309745, 6406175865012580712, 6398009715678467718, '', '01', '01', '查询所设置的菜单');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6406176084622783887, 6406175865012580712, 6398009628251990121, '', '01', '01', '查看所设置的菜单内容');
INSERT INTO t_permission_info (permission_id, resource_id, operation_id, permission_desc, is_valid, is_delete, permission_name) VALUES (6406176158406380054, 6406175865012580712, 6398009414274949388, '', '01', '01', '设置菜单内容');


INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6404757550852099151, NULL, '登录页面', '/login.html', '01', '01');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6404757635476941621, NULL, '首页', '/index.html', '01', '02');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405387947750671282, 6398011947565074347, '通过生成工具生成', '/developer/generator/generator.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405390288927469955, 6398012219173333265, '通过操作设置新增操作', '/permission/operation/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405390640360451773, 6398012561582779099, '通过资源设置保存资源', '/permission/resource/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405390893200523558, 6398013187857065038, '打开权限设置的页面', '/permission/permission/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405391100327275250, 6398012963327697532, '通过权限设置保存权限', '/permission/permission/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405391292522963498, 6405389720996578990, '打开资源标识页面', '/permission/identifier/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405391465128224033, 6405389973234469222, '通过资源标识设置保存标识', '/permission/identifier/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405391812091529783, 6398013749002150242, '打开角色设置的页面', '/authorization/role/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405391994354740131, 6398013636696842447, '查看角色设置里的角色内容', '/authorization/role/load.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405392176348174244, 6398013500678375340, '保存角色设置里的角色', '/authorization/role/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405392441191878545, 6398014593318640766, '打开角色赋权页面', '/authorization/role/permission/load.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405392574330891967, 6398014132825732176, '为角色赋权限', '/authorization/role/permission/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405392758043006586, 6398014473326070469, '为角色取消之前所赋的权限', '/authorization/role/permission/delete.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405534905901770051, NULL, '登录认证页面', '/authenticed.html', '01', '03');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405821875263949078, 6405820398730705225, '查看指定人员信息', '/authorization/user/load.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405822022013078523, 6405820611666682427, '保存人员信息', '/authorization/user/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405822187986618523, 6405820781138342857, '查看人员被授予的角色信息', '/authorization/user/role/load.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405822401062035172, 6405820972564171813, '为人员授予角色内容', '/authorization/user/role/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405821510668168457, 6405820313340590347, '查询系统内的人员信息', '/authorization/user/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6406176519643292261, 6406175996771309745, '查询所设置的菜单', '/permission/menu/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6406176716777731071, 6406176084622783887, '查看所设置的菜单内容', '/permission/menu/load.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6406176879335146476, 6406176158406380054, '保存菜单设置内容', '/permission/menu/save.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405390466238566988, 6398012756589852276, '打开资源设置的页面', '/permission/resource/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405388555655256552, 6398012425956167057, '打开操作设置的页面', '/permission/operation/query.html', '01', '04');
INSERT INTO t_resource_identifier (identifier_id, permission_id, identifier_name, identifier_path, identifier_type, access_type) VALUES (6405387357819740961, 6398011760658268736, '打开生成工具的页面', '/developer/generator/load.html', '01', '04');


INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406165815866060443, NULL, '首页', 6404757635476941621, '01', '01', 1);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406174970261521474, NULL, '授权管理', NULL, '01', '01', 2);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406174929924518842, NULL, '权限管理', NULL, '01', '01', 3);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406172276087957972, NULL, '开发管理', NULL, '01', '01', 4);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406174436787367533, 6406172276087957972, '生成工具', 6405387357819740961, '01', '01', 1);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406177295926356488, 6406174970261521474, '人员管理', 6405821510668168457, '01', '01', 1);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406177387416743745, 6406174970261521474, '角色管理', 6405391812091529783, '01', '01', 2);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406177173595395290, 6406174929924518842, '菜单设置', 6406176519643292261, '01', '01', 1);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406175325863347751, 6406174929924518842, '资源标识设置', 6405391292522963498, '01', '01', 2);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406175491910390182, 6406174929924518842, '权限设置', 6405390893200523558, '01', '01', 3);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406175636896461228, 6406174929924518842, '资源设置', 6405390466238566988, '01', '01', 4);
INSERT INTO t_menu_info (menu_id, parent_menu_id, menu_name, identifier_id, is_valid, is_delete, menu_sequence) VALUES (6406175778020792160, 6406174929924518842, '操作设置', 6405388555655256552, '01', '01', 5);


INSERT INTO t_role_info (role_id, role_type, role_name, role_desc, is_valid, is_delete) VALUES (6398011142407052070, '0100', '开发人员', '开发人员角色（可授开发管理模块的权限）', '01', '01');
INSERT INTO t_role_info (role_id, role_type, role_name, role_desc, is_valid, is_delete) VALUES (6398014845481303604, '0100', '管理员', '管理员角色', '01', '01');
INSERT INTO t_role_info (role_id, role_type, role_name, role_desc, is_valid, is_delete) VALUES (6406130416017760106, '0100', '超级管理员', '拥有所有角色', '01', '01');
INSERT INTO t_role_info (role_id, role_type, role_name, role_desc, is_valid, is_delete) VALUES (6400913023621718027, '0100', '临时角色', '临时角色的内容', '01', '01');


INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6405827574010276213, 6398014845481303604, NULL, '运营平台:授权管理:*');
INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6406130453421405002, 6406130416017760106, NULL, '运营平台:*');
INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6406130521086505064, 6398011142407052070, NULL, '运营平台:开发管理:*');
INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6406192470856736086, 6400913023621718027, 6406175996771309745, NULL);
INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6407590434435831536, 6398011142407052070, NULL, '运营平台:权限管理:*');
INSERT INTO t_role_permission_rela (role_permission_id, role_id, permission_id, permission_wildcards) VALUES (6408328946296688087, 6400913023621718027, NULL, '运营平台:授权管理:*');


INSERT INTO t_party_info (party_id, party_type) VALUES (6406191811811870964, '0100');

INSERT INTO t_party_role_rela (party_role_id, party_id, role_id) VALUES (6406191863241080834, 6406191811811870964, 6406130416017760106);

INSERT INTO t_person_info (party_id, person_name, sex_type_code, birth_date, marital_type_code, person_memo) VALUES (6406191811811870964, '超级管理员', '01', '2017-01-01', NULL, '全权限');

INSERT INTO t_user_info (party_id, login_password, login_salt, is_lock) VALUES (6406191811811870964, 'f8a1192dbeaeecc480e5ee675205df0f', '58e75d827e3adabb', '01');

INSERT INTO t_login_info (login_id, party_id, login_name, is_primary) VALUES (6406191810282291377, 6406191811811870964, 'admin', '01');


