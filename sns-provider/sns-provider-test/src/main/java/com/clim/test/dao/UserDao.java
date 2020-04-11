package com.clim.test.dao;

import com.clim.test.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public interface UserDao {
    public User selectAllUser(@Param("user_id")String user_id);
}
