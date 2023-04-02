package ru.kazmin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazmin.models.Bid;
import ru.kazmin.service.abstracts.BidService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final BidService bidService;

    public UserRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/{id}")
    public Bid getBid(@PathVariable("id") Long id) {
        return bidService.getBid(id);
    }

    @GetMapping("/bids/{sort}/{first}")
    public List<Bid> getBids(@PathVariable("sort") int sort, @PathVariable("first") int first) {
        return bidService.getBids(sort, first);
    }

    @PatchMapping("/sent{id}")
    public ResponseEntity<HttpStatus> sentBid(@PathVariable("id") Long id) {
        return bidService.sendBid(id)?ResponseEntity.ok(HttpStatus.OK):ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateBid(@RequestBody Bid bid) {
        return bidService.updateBid(bid)?ResponseEntity.ok(HttpStatus.OK):ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createBid(@RequestBody Bid bid) {
        bidService.createBid(bid);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
