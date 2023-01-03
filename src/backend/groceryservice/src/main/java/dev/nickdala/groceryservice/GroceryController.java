package dev.nickdala.groceryservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/groceries")
public class GroceryController {

    private static final List<GroceryItem> groceryList = new ArrayList<>();

    @GetMapping
    Flux<GroceryItem> getGroceries() {
        return Flux.fromIterable(groceryList);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GroceryItem e) {
        groceryList.add(e);
    }
}
