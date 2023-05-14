package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Notification;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseListData<Notification> findAllByInfluencer(){
        return new ResponseListData(notificationService.findAllByInfluencer());
    }

    @GetMapping("/expanded")
    public ResponseListData<Notification> findAllByInfluencerExpanded(){
        return new ResponseListData(notificationService.findAllByInfluencerExpanded());
    }

    @GetMapping("/read")
    public ResponseMessage<Long> checkReadByInfluencer(){
        return notificationService.checkReadByInfluencer();
    }

    @PostMapping("/update")
    public ResponseMessage<String> updateStatusToRead(){
        return notificationService.updateStatusToRead();
    }
}
