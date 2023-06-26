package com.app.momment.highlight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/highlight/")
public class HighlightController {

    @Autowired
    HighlightService highlightService;

    @PostMapping("add")
    public ResponseEntity saveHighlight(@RequestBody HighlightRequest highlightRequest) {
        return this.highlightService.saveHighlight(highlightRequest);
    }

    @PostMapping("delete")
    public ResponseEntity deleteHighlight(@RequestParam long id) {
        return this.highlightService.deleteHighlight(id);
    }
}
