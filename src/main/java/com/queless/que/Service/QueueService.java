package com.queless.que.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.queless.que.Entity.QueueEntity;
import com.queless.que.Entity.User;
import com.queless.que.Repository.QueueRepository;
import com.queless.que.Repository.UserRepository;

@Service
public class QueueService {
	
	@Autowired
	private QueueRepository repository;
	
	 @Autowired
	 private UserRepository userRepository;
//	insert  save
//	public QueueEntity saveQueue(QueueEntity queue)
//	{
//		return repository.save(queue);
//	}
//	
	  // Create Queue for a User
    public QueueEntity saveQueue(Integer userId, QueueEntity queue) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        queue.setUser(user);

        return repository.save(queue);
    }
	
//	get queue
	public List<QueueEntity> getAllQueue()
	{
		return repository.findAll();
	}
// getQueue by id 
	public QueueEntity getQueueById(Integer id)
	{
		return repository.findById(id).orElse(null);
		
	}
//	update queue
	public QueueEntity updateQueue(QueueEntity queue) {
        return repository.save(queue);
    }
//	// Delete User
    public void deleteQueue(Integer id) {
        repository.deleteById(id);
    }
}


//insert getall getbyid update delete