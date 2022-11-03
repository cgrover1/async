package com.skillsoft.springboot.controller;

import com.skillsoft.springboot.model.User;
import com.skillsoft.springboot.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class LookupController {

    private static final Logger logger = LoggerFactory.getLogger(LookupController.class);

    @Autowired
    private LookupService lookupService;

    private static int userIndex = 0;

    private static final List<String> usersList = new ArrayList<String>();

    static {
        usersList.add("Pytorch");
        usersList.add("Tensorflow");
        usersList.add("Scikit-learn");
        usersList.add("spring-boot");
        usersList.add("spring-mvc");
        usersList.add("spring-security");
    }

    @Scheduled(fixedRate = 2000)
    public void scheduledTasks() throws Exception {

        CompletableFuture<User> info = lookupService.findUser(usersList.get(userIndex));

        userIndex = (userIndex + 1) % usersList.size();

        logger.info("--> " + info.get());
    }
}
