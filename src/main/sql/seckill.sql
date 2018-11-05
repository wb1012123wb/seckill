-- 秒杀执行的存储过程
DELIMITER $$ --console  ; 转为 $$
-- 定义存储过程
-- 参数：in 输入参数; out 输出参数
-- row_count()：返回上一条修改类型sql（非select）的影响行数
-- row_count：0：未修改数据；> 0：表示修改的行数； < 0：表示错误/未执行修改sql
CREATE PROCEDURE `SECKILL`.`execute_seckill`
  (in v_seckill_id bigint, in v_phone bigint,
    in v_kill_time TIMESTAMP, out r_result int)
  BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION;
    -- 插入记录
    INSERT IGNORE INTO success_killed
      (seckill_id, user_phone, create_time)
    VALUE (v_seckill_id, v_phone, v_kill_time);
    SELECT ROW_COUNT() INTO insert_count;
    -- 验证插入的sql语句
    IF (insert_count = 0) THEN  -- 未修改数据
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (insert_count < 0) THEN  -- 报错/未执行
      ROLLBACK;
      SET r_result = -2;
    ELSE  -- sql生效，接下来更新库存
      UPDATE seckill
      SET `number` = `number` - 1
      WHERE seckill_id = v_seckill_id
        AND end_time > v_kill_time
        AND start_time < v_kill_time
        AND `number` > 0;
      SELECT ROW_COUNT() INTO insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK;
        SET r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK;
        SET r_result = -2;
      ELSE
        COMMIT;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$
-- 存储过程定义结束

DECLARE ; --将换行符改回到分号

SET @r_result=-3;

-- 查看变量
select @r_result;
-- 执行存储过程
call execute_seckill(1002, 13500001111, now(), @r_result)

--获取结果
SELECT @r_result;

-- 存储过程
-- 1：存储过程优化：事务行级锁持有时间
-- 2：不要过度依赖存储过程
-- 3：简单的逻辑可以应用存储过程
-- 4：QPS：一个秒杀单6000/qps