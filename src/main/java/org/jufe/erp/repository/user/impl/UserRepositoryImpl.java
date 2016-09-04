package org.jufe.erp.repository.user.impl;

import org.jufe.erp.entity.User;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.user.UserRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raomengnan on 16-8-29.
 * Option with DB about User
 */
@Repository
public class UserRepositoryImpl extends BaseRepository<User> implements UserRepository{

    public User findById(String id){
        return super.findById(id);
    }

    public List<User> findByName(String name){
        return super.find(new Query(new Criteria("userName").is(name)));
    }

    public List<User> findByGrade(int grade){
        return super.find(new Query(new Criteria("userGrade").is(grade)));
    }

    public List<User> findByLocation(String location){
        return super.find(new Query(new Criteria("nowInWhere").is(location )));
    }

    public User findUserByIdAndPwd(String id, String pwd){
        List<User> ret = super.find(new Query(new Criteria("id").is(id).and("pwd").is(pwd)));
        if( ret == null || ret.size() == 0 )
            return null;
        return ret.get(0);
    }

    public boolean addUser(User user){
        if(user.getId() == null)
            return false;
        return super.insert(user);
    }

    public boolean updateUserName(User user){
        Query query = new Query(new Criteria("id").is(user.getId()));
        Update update = new Update().set("userName", user.getUserName());
        return super.update(query, update);
    }

    public boolean updateUserPwd(User user){
        Query query = new Query(new Criteria("id").is(user.getId()));
        Update update = new Update().set("pwd", user.getPwd());
        return super.update(query, update);
    }

    public boolean updateLocation(User user){
        Query query = new Query(new Criteria("id").is(user.getId()));
        Update update = new Update().set("nowInWhere", user.getNowInWhere());
        return super.update(query, update);
    }

    /**
     * 更新全部信息时一定要检查用户ID是否属实
     * @param user
     * @return
     */
    public boolean update(User user){
        if(user.getId() != null && user.getRegisterTime() != null)
            return super.save(user);
        return false;
    }


}
