package com.app.momment.children.controller;

import com.app.momment.children.model.Child;
import com.app.momment.children.model.ChildRequest;
import com.app.momment.children.service.ChildrenService;
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
    public ResponseEntity addChild(@RequestBody ChildRequest childRequest) {
        return this.childService.saveChild(childRequest);
    }

    @PostMapping("edit")
    public ResponseEntity editChild(@RequestBody ChildRequest childRequest) {
        return this.childService.saveChild(childRequest);
    }

    @PostMapping ("fetch")
    public List<Child> fetch(@RequestBody long userId) {
        return this.childService.fetch(userId);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        return this.childService.delete(id);
    }
}
