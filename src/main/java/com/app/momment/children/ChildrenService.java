package com.app.momment.children;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChildrenService {

    @Autowired
    private ChildrenRepository childrenRepository;

    public ResponseEntity saveChild(ChildRequest childRequest) {
        if (childRequest != null) {
            Child child = (childRequest.id == 0) ? new Child() : this.childrenRepository.findById(childRequest.id).get();
            child.setName(childRequest.name);
            child.setDob(childRequest.dob);

            if (this.childrenRepository.save(child) != null) {
                return new ResponseEntity<>("Child save success!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Bad child request", HttpStatus.BAD_REQUEST);
    }

    public List<Child> fetchAll() {
        return this.childrenRepository.findAll();
    }

    // FEATURE IMPLEMENTATION TO CONSIDER
    public void deleteChild(long id) {
        //check if id exists
        //
    }

    private Date formatDate(Date time) {
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = output.format(time);
        return new Date(formattedTime);
    }
}
