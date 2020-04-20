package com.clim.user.dao;

import com.clim.user.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public interface UserDao {
    /**
     * 登陆
     * @param user_id
     * @param user_password
     * @return
     */
    public User login(@Param("user_logon")String user_logon,
                      @Param("user_password")String user_password);
}
