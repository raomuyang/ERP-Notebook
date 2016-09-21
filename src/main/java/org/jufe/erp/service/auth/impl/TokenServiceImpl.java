package org.jufe.erp.service.auth.impl;

import org.jufe.erp.entity.TokenInfo;
import org.jufe.erp.entity.User;
import org.jufe.erp.repository.auth.TokenRepository;
import org.jufe.erp.repository.user.UserRepository;
import org.jufe.erp.service.auth.TocknService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by raomengnan on 16-9-21.
 */
@Service
public class TokenServiceImpl implements TocknService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenInfo get(String id) {
        return tokenRepository.findById(id);
    }

    @Override
    public User getUser(String id) {
        TokenInfo token = get(id);
        if(token != null)
            return null;
        return userRepository.findById(token.getUserId());
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
