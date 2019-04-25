package service;

import pojo.User;

public interface UserService {
    // 传入的user不携带ID、token
    // 返回的user应该补充上这两个字段
    public User registryUser(User user);

    // 如果用户不存在，则返回null
    public User checkAndReturn(String username, String password);

    public User getInfoByUsername(String username);
}
