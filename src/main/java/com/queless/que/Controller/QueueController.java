package com.queless.que.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.queless.que.Entity.QueueEntity;
import com.queless.que.Service.QueueService;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueService service;

    // Test API
    @GetMapping
    public String testApi() {
        return "Queue API is working";
    }

 // Create Queue for a User
    @PostMapping("/user/{userId}")
    public QueueEntity saveQueue(@PathVariable Integer userId,
                                 @RequestBody QueueEntity queue) {

        return service.saveQueue(userId, queue);
    }

    // Get All Queues
    @GetMapping("/getAll")
    public List<QueueEntity> getAllQueues() {
        return service.getAllQueue();
    }

    // Get Queue By ID
    @GetMapping("/getById/{id}")
    public QueueEntity getQueueById(@PathVariable("id") Integer id) {
        return service.getQueueById(id);
    }

    // Update Queue
    @PutMapping("/update")
    public QueueEntity updateQueue(@RequestBody QueueEntity queue) {
        return service.updateQueue(queue);
    }

    // Delete Queue
    @DeleteMapping("/delete/{id}")
    public String deleteQueue(@PathVariable("id") Integer id) {
        service.deleteQueue(id);
        return "Queue Deleted Successfully";
    }
}