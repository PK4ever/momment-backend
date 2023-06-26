package com.app.momment.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    public ResponseEntity saveChild(ChildRequest childRequest) {
        if (childRequest != null) {
            Child child = (childRequest.id == 0) ? new Child() : this.childRepository.findById(childRequest.id).get();
            child.setName(childRequest.name);
            child.setDob(childRequest.dob);

            if (this.childRepository.save(child) != null) {
                return new ResponseEntity<>("Child save success!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Bad child request", HttpStatus.BAD_REQUEST);
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
