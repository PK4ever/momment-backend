package com.app.momment.children;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/children/")
public class ChildrenController {

    @Autowired
    private ChildrenService childService;

    @PostMapping("add")
    public ResponseEntity saveChild(@RequestBody ChildRequest childRequest) {
        return this.childService.saveChild(childRequest);
    }

    @GetMapping ("fetchAll")
    public List<Child> fetchAll(@RequestBody long userId) {
        return this.childService.fetchAll(userId);
    }
}
