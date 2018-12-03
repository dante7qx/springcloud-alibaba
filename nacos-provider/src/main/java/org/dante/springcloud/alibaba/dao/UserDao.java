package org.dante.springcloud.alibaba.dao;

import org.dante.springcloud.alibaba.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
