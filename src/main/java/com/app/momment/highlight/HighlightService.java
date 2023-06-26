package com.app.momment.highlight;

import com.app.momment.child.Child;
import com.app.momment.child.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HighlightService {

    @Autowired
    HighlightRepository highlightRepository;

    @Autowired
    ChildRepository childRepository;

    public ResponseEntity<String> saveHighlight(HighlightRequest highlightRequest) {

        //check if param has if
        if(highlightRequest != null) {
            Highlight highlight = (highlightRequest.id != 0) ?
                    this.highlightRepository.findById(highlightRequest.getId()).get()
                    : new Highlight();
            highlight.setDate(highlightRequest.getDate());
            highlight.setDescription(highlightRequest.getDescription());

            Child child = this.childRepository.findById(highlightRequest.getChild_id()).get();
            highlight.setChild(child);
            if (this.highlightRepository.save(highlight) != null) {
                return new ResponseEntity<>("Highlight save success!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Bad Highlight Request", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity deleteHighlight(long id) {
        if (id != 0) {
            if (this.highlightRepository.findById(id).get() != null) {
                this.highlightRepository.deleteById(id);
            }
            if (this.highlightRepository.findById(id).get() == null) {
                return new ResponseEntity<>("Highlight deleted successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Bad highlight Request", HttpStatus.BAD_REQUEST);
    }
}
