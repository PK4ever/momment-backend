package com.app.momment.children.service;

import com.app.momment.children.model.Child;
import com.app.momment.children.model.ChildRequest;
import com.app.momment.children.repository.ChildrenRepository;
import com.app.momment.users.model.User;
import com.app.momment.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenService {

    @Autowired
    private ChildrenRepository childrenRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity saveChild(ChildRequest childRequest) {
        if (childRequest != null) {
            Child child;

            if (childRequest.getId() == 0) {
                 child =  new Child();
                User user = userRepository.findByEmail(childRequest.getUserEmail());
                child.setUser(user);
            } else {
                child  = this.childrenRepository.findById(childRequest.getId()).get();
            }
            child.setName(childRequest.getName());
            child.setDob(childRequest.getDob());

            this.childrenRepository.save(child);
            return new ResponseEntity<>(this.childrenRepository.findAllByUserId(child.getUser().getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad child request", HttpStatus.BAD_REQUEST);
    }

    public List<Child> fetch(long userId) {
        return this.childrenRepository.findAllByUserId(userId);
    }

    public ResponseEntity<Long> delete(long id) {
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.childrenRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
