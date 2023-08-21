package com.app.momment.highlight.services;

import com.app.momment.children.model.Child;
import com.app.momment.children.repository.ChildrenRepository;
import com.app.momment.highlight.model.Highlight;
import com.app.momment.highlight.model.HighlightRequest;
import com.app.momment.highlight.repository.HighlightRepository;
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

    public ResponseEntity<List<Highlight>> saveHighlight(HighlightRequest highlightRequest) {

        //check if param has id
        if(highlightRequest != null) {
            Highlight highlight = (highlightRequest.getId() == 0) ? new Highlight() :
                    this.highlightRepository.findById(highlightRequest.getId()).get();

            highlight.setDate(highlightRequest.getDate());
            highlight.setDescription(highlightRequest.getDescription());

            Child child = this.childrenRepository.findById(highlightRequest.getChild_id()).get();
            highlight.setChild(child);
            this.highlightRepository.save(highlight);
            List<Highlight> highlightList = this.highlightRepository.findAllByChildId(child.getId());
            return new ResponseEntity<>(highlightList, HttpStatus.OK);

        }
        return new ResponseEntity("Bad Highlight Request", HttpStatus.BAD_REQUEST);
    }

    public List<Highlight> fetchAll(long childId) {
        return highlightRepository.findAllByChildId(childId);
    }
    public ResponseEntity<Long> deleteHighlight(long id) {
       if (id == 0) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

       this.highlightRepository.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
