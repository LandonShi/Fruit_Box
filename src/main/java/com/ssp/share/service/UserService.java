package com.ssp.share.service;

import com.ssp.share.dao.KitCollectDAO;
import com.ssp.share.dao.ReviewDAO;
import com.ssp.share.dao.UserDAO;
import com.ssp.share.pojo.User;
import com.ssp.share.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@CacheConfig(cacheNames="users")
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private KitCollectDAO kitCollectDAO;

    public boolean isExist(String name) {
        User user = getByName(name);
        return null!=user;
    }

    @Cacheable(key="'users-one-name-'+ #p0")
    public User getByName(String name) {
        return userDAO.findByUsername(name);
    }

    /*
     登录判断用户账户密码是否正确
     */
    @Cacheable(key="'users-one-name-'+ #p0 +'-password-'+ #p1")
    public User checkUser(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    /*
        降序查询总用户
     */
    @Cacheable(key="'users-page-'+#p0+ '-' + #p1")
    public Page4Navigator<User> list(int start, int size, int navigatePages,String flag) {
        Sort sort;
        if(flag.equals("1")) {  //1  降序排列
            sort = new Sort(Sort.Direction.DESC, "id");
        }else {                //2   升序排列
            sort = new Sort(Sort.Direction.ASC,"id");
        }
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    /*
    增加用户
     */
    @CacheEvict(allEntries=true)
    public void add(User user) {
        userDAO.save(user);
    }

    /*
    更新密码
     */
    @Transactional
    @CacheEvict(allEntries=true)
    public void update(String password, int uid) {
        userDAO.updatePassword(password, uid);
    }

    /*
    更新头像
     */
    @Transactional
    @CacheEvict(allEntries=true)
    public void updateAvatar(String avatar, int uid) {
        userDAO.updateAvatar(avatar, uid);
    }

    @Transactional
    @CacheEvict(allEntries=true)
    public void updateNickname(String nickname, int uid) {
        userDAO.updateNickname(nickname,uid);
    }

    /*
    激活邮箱
     */
    @Transactional
    @CacheEvict(allEntries=true)
    public void updateEmailStatue(String statue, int uid) {
        userDAO.updateEmailStatue(statue,uid);
    }
    /*
        存储临时邮箱
     */
    @Transactional
    @CacheEvict(allEntries=true)
    public void updateTemp(String email, int uid) {
        userDAO.updateTemp(email,uid);
    }

    /*
        更新已经验证过的邮箱
     */
    @Transactional
    @CacheEvict(allEntries=true)
    public void updateEmail(String email, int uid) {
        userDAO.updateEmail(email,uid);
    }
    /*
    删除用户
     */
    @CacheEvict(allEntries=true)
    public void delete(int id) {
        //因为用户关联了评论和收藏
        kitCollectDAO.delete(id);
        reviewDAO.delete(id);
        userDAO.delete(id);
    }

    /*
     条件查询
     */
    @Cacheable(key="'users-one-'+#p0")
    public List<User> search(String keyword) {
        List<User> users = userDAO.findByUser(keyword);
        return users;
    }

    @Cacheable(key="'users-one-'+#p0")
    public User get(int id) {
        return  userDAO.getOne(id);
    }

}
