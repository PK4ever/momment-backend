package com.app.momment.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/child/")
public class ChildController {

    @Autowired
    private ChildService childService;

    @PostMapping("add")
    public ResponseEntity saveChild(@RequestBody ChildRequest childRequest) {
        return this.childService.saveChild(childRequest);
    }
}
