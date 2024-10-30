package com.ntg.backend.controller;

import com.ntg.backend.dto.ItemDto;
import com.ntg.backend.service.imp.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemServiceImp itemServiceImp;

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        ItemDto savedItemDto = itemServiceImp.createItem(itemDto);
        return new ResponseEntity<>(savedItemDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDto>getItemById(@PathVariable("id") long id )
    {
        ItemDto itemDto = itemServiceImp.getItemById(id);
        return new ResponseEntity<>(itemDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems()
    {
        List<ItemDto> ItemDTOs = itemServiceImp.getAllItems() ;
        return new ResponseEntity<>(ItemDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable("id") Long id)
    {
        itemServiceImp.deleteItem(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDto> VolunteerDto(@RequestBody ItemDto itemDto,@PathVariable("id") long id)
    {
        ItemDto updatedItemDto = itemServiceImp.updateItem(itemDto,id);
        return new ResponseEntity<>(updatedItemDto,HttpStatus.OK);
    }

}
