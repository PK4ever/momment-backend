package com.app.momment.highlight;

import com.app.momment.children.Child;
import com.app.momment.children.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighlightService {

    @Autowired
    HighlightRepository highlightRepository;

    @Autowired
    ChildrenRepository childrenRepository;

    public ResponseEntity<String> saveHighlight(HighlightRequest highlightRequest) {

        //check if param has if
        if(highlightRequest != null) {
            Highlight highlight = (highlightRequest.id != 0) ?
                    this.highlightRepository.findById(highlightRequest.getId()).get()
                    : new Highlight();
            highlight.setDate(highlightRequest.getDate());
            highlight.setDescription(highlightRequest.getDescription());

            Child child = this.childrenRepository.findById(highlightRequest.getChild_id()).get();
            highlight.setChild(child);
            if (this.highlightRepository.save(highlight) != null) {
                return new ResponseEntity<>("Highlight save success!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Bad Highlight Request", HttpStatus.BAD_REQUEST);
    }

    public List<Highlight> fetchAll(long childId) {
        return highlightRepository.findAllByChildId(childId);
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
