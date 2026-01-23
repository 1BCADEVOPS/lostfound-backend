package com.example.lostfound.controller;

import com.example.lostfound.entity.LostFoundItem;
import com.example.lostfound.repository.LostFoundRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin("*")
public class LostFoundController {

    private final LostFoundRepository repo;

    public LostFoundController(LostFoundRepository repo) {
        this.repo = repo;
    }

    // CREATE — Add new Lost/Found item
    @PostMapping
    public LostFoundItem addItem(@RequestBody LostFoundItem item) {
        item.setStatus(LostFoundItem.Status.NOT_RETURNED); // default status
        return repo.save(item);
    }

    // READ — Get all items
    @GetMapping
    public List<LostFoundItem> getAll() {
        return repo.findAll();
    }

    // UPDATE — Full update for any field
    @PutMapping("/{id}")
    public LostFoundItem updateItem(@PathVariable Long id, @RequestBody LostFoundItem updatedItem) {
        LostFoundItem existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        existing.setUserName(updatedItem.getUserName());
        existing.setPhoneNumber(updatedItem.getPhoneNumber()); // phone number
        existing.setItemName(updatedItem.getItemName());
        existing.setDescription(updatedItem.getDescription());
        existing.setType(updatedItem.getType());
        existing.setStatus(updatedItem.getStatus());

        return repo.save(existing);
    }

    // UPDATE — Mark item as returned
    @PutMapping("/{id}/return")
    public LostFoundItem markReturned(@PathVariable Long id) {
        LostFoundItem item = repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        item.setStatus(LostFoundItem.Status.RETURNED);
        return repo.save(item);
    }

    // DELETE — Remove an item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
