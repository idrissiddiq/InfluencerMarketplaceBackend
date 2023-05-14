package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.Notification;
import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.repository.NotificationRepository;
import com.InfluencerMarketpalce.serverside.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Notification> findAllByInfluencer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        return notificationRepository.findAllByInfluencer(user.getId());
    }

    public List<Notification> findAllByInfluencerExpanded(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        return notificationRepository.findAllByInfluencerExpanded(user.getId());
    }

    public ResponseMessage<Long> checkReadByInfluencer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Long check = notificationRepository.checkReadByInfluencer(user.getId());
        if (check >= 1){
            return new ResponseMessage<>("UNREAD", check);
        } else{
            return new ResponseMessage<>("READ", check);
        }
    }

    public ResponseMessage<String> updateStatusToRead(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        notificationRepository.updateStatusToRead(user.getId());
        return new ResponseMessage<>("UPDATE SUCCESS", "SUCCESS");
    }
}
