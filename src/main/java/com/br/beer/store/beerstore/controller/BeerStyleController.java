package com.br.beer.store.beerstore.controller;

import com.br.beer.store.beerstore.dto.TemperatureSearchResponse;
import com.br.beer.store.beerstore.dto.BeerStyleRequest;
import com.br.beer.store.beerstore.dto.BeerStyleResponse;
import com.br.beer.store.beerstore.dto.TemperatureSearchRequest;
import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.br.beer.store.beerstore.service.BeerCRUDService;
import com.br.beer.store.beerstore.service.BeerSpotifyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beer/style")
public class BeerStyleController {

    private BeerCRUDService service;

    private BeerSpotifyService beerSpotifyService;

    private ModelMapper mapper;

    @Autowired
    public BeerStyleController(BeerCRUDService service, BeerSpotifyService beerSpotifyService, ModelMapper mapper) {
        this.service = service;
        this.beerSpotifyService = beerSpotifyService;
        this.mapper = mapper;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBeerStyle(@RequestBody @Valid BeerStyleRequest request) {

        service.save(mapper.map(request, BeerStyle.class));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerStyleId}/update")
    public ResponseEntity<Void> updateBeerStyle(@PathVariable Long beerStyleId, @RequestBody @Valid BeerStyleRequest request) {

        service.update(beerStyleId, mapper.map(request, BeerStyle.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{beerStyle}")
    public ResponseEntity<BeerStyleResponse> getByBeerStyle(@PathVariable(value = "beerStyle") String styleName) {

        BeerStyle beerStyle = service.findByStyle(styleName);

        return new ResponseEntity<>(mapper.map(beerStyle, BeerStyleResponse.class), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BeerStyleResponse>> getByBeerStyle() {

        List<BeerStyle> beerStyleList= service.findAll();

        List<BeerStyleResponse> styleResponses = beerStyleList.stream()
                .map(beerStyle -> mapper.map(beerStyle, BeerStyleResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(styleResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{beerStyleId}/remove")
    public ResponseEntity<Void> deleteBeerStyle(@PathVariable Long beerStyleId) {

        service.delete(beerStyleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/temperature")
    public ResponseEntity<TemperatureSearchResponse> findStyleByTemperature(@RequestBody @Valid TemperatureSearchRequest request) {

        return new ResponseEntity<>(beerSpotifyService.findStyleByTemperature(request.getTemperature()), HttpStatus.ACCEPTED);
    }
}
