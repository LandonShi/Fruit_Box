package com.ssp.share.dao;

import com.ssp.share.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface UserDAO extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    @Query(value="select * from user where username like %?1%",nativeQuery=true)
    List<User> findByUser(String keyword);

    @Modifying
    @Query(value="update user set password = ?1 where id = ?2",nativeQuery = true)
    void updatePassword(String password, int uid);

    @Modifying
    @Query(value="update user set avatar = ?1 where id = ?2",nativeQuery = true)
    void updateAvatar(String avatar, int uid);

    @Modifying
    @Query(value="update user set nickname = ?1 where id = ?2",nativeQuery = true)
    void updateNickname(String nickname, int uid);

    @Modifying
    @Query(value="update user set statue = ?1 where id = ?2",nativeQuery = true)
    void updateEmailStatue(String statue, int uid);

    @Modifying
    @Query(value="update user set temp = ?1 where id = ?2",nativeQuery = true)
    void updateTemp(String email, int uid);

    @Modifying
    @Query(value="update user set email = ?1 where id = ?2",nativeQuery = true)
    void updateEmail(String email, int uid);
}
