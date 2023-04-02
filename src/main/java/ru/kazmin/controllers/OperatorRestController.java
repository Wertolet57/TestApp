package ru.kazmin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazmin.models.Bid;
import ru.kazmin.service.abstracts.BidService;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorRestController {
    private final BidService bidService;

    public OperatorRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @PatchMapping("/acceptOrReject{id}/{action}")
    public ResponseEntity<HttpStatus> acceptBid(@PathVariable("id") Long id,
                                                @PathVariable("action") int action) {
        return bidService.acceptedOrRejectBid(id, action)?ResponseEntity.ok(HttpStatus.OK):ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/bids/{sort}/{first}")
    public List<Bid> getBids(@PathVariable("sort") int sort, @PathVariable("first") int first) {
        return bidService.getSentBids(sort, first);
    }
    @GetMapping("/bids/{username}/{sort}/{first}")
    public List<Bid> getSentBidsByUsername(@PathVariable("sort") int sort,
                             @PathVariable("first") int first,
                             @PathVariable("username") String username) {
        return bidService.getSentBidsByUsername(username, sort, first);
    }

}
