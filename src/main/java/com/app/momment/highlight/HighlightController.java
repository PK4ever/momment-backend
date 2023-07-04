package com.app.momment.highlight;

import com.app.momment.children.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/highlight/")
public class HighlightController {

    @Autowired
    HighlightService highlightService;

    @PostMapping("add")
    public ResponseEntity saveHighlight(@RequestBody HighlightRequest highlightRequest) {
        return this.highlightService.saveHighlight(highlightRequest);
    }

    @GetMapping ("fetchAll")
    public List<Highlight> fetchAll(@RequestBody long childId) {
        return this.highlightService.fetchAll(childId);
    }

    @PostMapping("delete")
    public ResponseEntity deleteHighlight(@RequestParam long id) {
        return this.highlightService.deleteHighlight(id);
    }
}
