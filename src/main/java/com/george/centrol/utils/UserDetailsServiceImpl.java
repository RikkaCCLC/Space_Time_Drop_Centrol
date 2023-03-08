package com.george.centrol.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.george.centrol.mapper.CentrolMapper;
import com.george.centrol.pojo.Centrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CentrolMapper centrolMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Centrol> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Centrol centrolUser = centrolMapper.selectOne(queryWrapper);
        if (centrolUser == null) {
            throw new RuntimeException("用户不存在");
        }

        return new UserDetailsImpl(centrolUser);
    }
}
