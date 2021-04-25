-- 声明一个通用的SQL Function，并调用
DELIMITER
//

-- 函数声明的命名规范：f_函数名
CREATE FUNCTION f_get_fullname(fname CHAR (250), lname CHAR (250)) RETURNS CHAR(250)
BEGIN
  DECLARE
fullname CHAR(250);
  SET
fullname=CONCAT(fname," -- ",lname);
RETURN fullname;
END
//

DELIMITER ;

SELECT GETFULLNAME1("chen", "tong") AS MyName;


-- 声明并调用一个SQL Procedure, 用于执行指定的SQL查询
-- A stored Procedure is a prepared SQL code, 通过传递不同的参数，反复的执行同样的代码 !!
DELIMITER
//

DROP PROCEDURE IF EXISTS myProcedure //

-- 存储过程命名规范：p_存储过程名
CREATE PROCEDURE myProcedure(username CHAR (10))
BEGIN
SELECT *
FROM spring_db.information
WHERE name = username;
END
//
DELIMITER ;
CALL myProcedure("vitor");

-- 可以使用drop来删除procedure