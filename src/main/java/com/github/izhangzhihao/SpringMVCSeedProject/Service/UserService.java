package com.github.izhangzhihao.SpringMVCSeedProject.Service;

import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.izhangzhihao.SpringMVCSeedProject.Utils.SHAUtils.getSHA_256;


@Service
public class UserService extends BaseService<User> {

    /**
     * 创建加盐的密码SHA256值
     *
     * @param user 用户实体
     * @return 密码加盐后的实体
     */
    public static User makeSHA256PasswordWithSalt(@NotNull User user) {
        user.setPassWord(
                getSHA_256(
                        getSHA_256(
                                user.getUserName() + user.getPassWord()
                        )
                )
        );
        return user;
    }

    /**
     * 保存对象
     *
     * @param user 需要添加的对象
     */
    @Override
    public void save(@NotNull User user){
        super.save(makeSHA256PasswordWithSalt(user));
    }

    /**
     * 批量保存对象
     *
     * @param userList 需要增加的对象的集合
     *                  失败会抛异常
     */
    @Override
    public void saveAll(@NotNull List<User> userList){
        userList.forEach(
                UserService::makeSHA256PasswordWithSalt
        );
        super.saveAll(userList);
    }


    /**
     * 更新或保存对象的PassWord
     *
     * @param user 需要更新的对象
     *              失败会抛出异常
     */
    public void updatePassWord(@NotNull User user){
        super.saveOrUpdate(makeSHA256PasswordWithSalt(user));
    }

    /**
     * 批量更新或保存对象的PassWord
     *
     * @param userList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    public void updateAllPassWord(@NotNull List<User> userList){
        userList.forEach(
                UserService::makeSHA256PasswordWithSalt
        );
        super.saveOrUpdateAll(userList);
    }

    /**
     * 更新或保存对象，不允许通过此方法修改密码！
     *
     * @param user 需要更新的对象
     *              失败会抛出异常
     */
    @Override
    public void saveOrUpdate(@NotNull User user){
        super.saveOrUpdate(user);
    }

    /**
     * 批量更新或保存对象，不允许通过此方法修改密码！
     *
     * @param userList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    @Override
    public void saveOrUpdateAll(@NotNull List<User> userList){
        super.saveOrUpdateAll(userList);
    }
}
