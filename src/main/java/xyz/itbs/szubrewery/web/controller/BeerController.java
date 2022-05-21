package xyz.itbs.szubrewery.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.itbs.szubrewery.services.BeerService;
import xyz.itbs.szubrewery.web.model.BeerDTO;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }


    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> handlePost(@RequestBody BeerDTO beerDTO) {

        BeerDTO savedDto = beerService.saveNewBeer(beerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "192.168.100.2:8080/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity<>(headers,HttpStatus.OK);
    }

    @PutMapping({"/{beerId}"})
    public  ResponseEntity<BeerDTO> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO){

        beerService.updateBeer(beerId, beerDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }

}
