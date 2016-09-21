package org.jufe.erp.service.auth.impl;

import org.jufe.erp.entity.TockenInfo;
import org.jufe.erp.entity.User;
import org.jufe.erp.repository.auth.TockenRepository;
import org.jufe.erp.repository.user.UserRepository;
import org.jufe.erp.service.auth.TockenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by raomengnan on 16-9-21.
 */
@Service
public class TockenServiceImpl implements TockenService{

    @Autowired
    private TockenRepository tockenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TockenInfo get(String id) {
        return tockenRepository.findById(id);
    }

    @Override
    public User getUser(String id) {
        TockenInfo tocken = get(id);
        if(tocken != null)
            return null;
        return userRepository.findById(tocken.getUserId());
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
