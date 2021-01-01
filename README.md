本项目，主要学习搭建SpringCloud项目模板，以及研究相关springBoot的源码。


建表sql
Table structure for payment
----------------------------
-- DROP TABLE IF EXISTS `payment`;
-- CREATE TABLE `payment`  (
  -- `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  -- `serial` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  -- PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- SET FOREIGN_KEY_CHECKS = 1;


--在创建commons模块时候，并在本地mvn install clean 的时候需要在本地setting文件加入下面内容
 <profile>
        <id>jdk-1.8</id>
        <activation>
            <activeByDefault>true</activeByDefault>
            <jdk>1.8</jdk>
        </activation>
        <properties>
            <maven.compiler.source>1.8</maven.compiler.source>
            <maven.compiler.target>1.8</maven.compiler.target>
            <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
        </properties>
    </profile>