package com.app.momment.highlight.controller;

import com.app.momment.children.model.Child;
import com.app.momment.highlight.model.Highlight;
import com.app.momment.highlight.model.HighlightRequest;
import com.app.momment.highlight.services.HighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/highlight/")
public class HighlightController {

    @Autowired
    HighlightService highlightService;


    @PostMapping("save")
    public ResponseEntity<List<Highlight>> saveHighlight(@RequestBody HighlightRequest highlightRequest) {
        return this.highlightService.saveHighlight(highlightRequest);
    }
//    @PostMapping("add")
//    public ResponseEntity<List<Highlight>> saveHighlight(@RequestBody HighlightRequest highlightRequest) {
//        return this.highlightService.saveHighlight(highlightRequest);
//    }

//    @PostMapping("edit")
//    public ResponseEntity<List<Highlight>> editHighlight(@RequestBody HighlightRequest highlightRequest) {
//        return this.highlightService.saveHighlight(highlightRequest);
//    }

    @PostMapping ("fetch")
    public List<Highlight> fetch(@RequestBody long childId) {
        return this.highlightService.fetchAll(childId);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        return this.highlightService.deleteHighlight(id);
    }
}
