# mystudy
## 1. 基本注解
### 1.1 @TableId
```
/*
	 * @TableId:
	 * 	 value: 指定表中的主键列的列名， 如果实体属性名与列名一致，可以省略不指定. 
	 *   type: 指定主键策略. 
	 */
```
### 1.2 @TableName
没什么说的
### 1.3 @TableField
* value 字段名
* el 不懂
* exist 是否在数据库中
* condition 查询条件  也就是当CoulumMap的时候   这个的条件  默认是“=”
* update 这个注释很清楚 但是我失败了
* fill 注入条件 FieldFill 自己看
* select 是否查询出来
## 2. 条件查询
QueryWrapper
## 3. 分页查询
## 4. 删除
### 4.1 删除
### 4.2 逻辑删除
除了
## 4. 扩展
### 4.1 AR模式
### 4.2 插件
