package com.ntg.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.service.imp.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemServiceImp itemServiceImp;


    // Specify the directory where you want to save uploaded files
    private static final String UPLOAD_DIR = "uploads/";

    // Ensure the upload directory exists
    @PostMapping
    public ResponseEntity<Item> addItem(
            @RequestParam("image") MultipartFile image,
            @RequestParam("data") String dateJson // JSON string that we will parse
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ItemDto itemDto = null;
        try {
            itemDto = objectMapper.readValue(dateJson, ItemDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // Save the file to the specified location
        Files.write(filePath, image.getBytes());

        itemDto.setImageUrl(filePath.toString());

        itemDto.setVolunteerId(1L);
        itemDto.setGenderSuitability(Item.GenderSuitability.ذكر);
        itemDto.setStatus(Item.ItemStatus.معلق);

        Item savedItemDto = itemServiceImp.createItem(itemDto);
        return new ResponseEntity<>(savedItemDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item>getItemById(@PathVariable("id") long id )
    {
       Item itemDto = itemServiceImp.getItemById(id);
        return new ResponseEntity<>(itemDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems()
    {
        List<Item> ItemDTOs = itemServiceImp.getAllItems() ;
        return new ResponseEntity<>(ItemDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable("id") Long id)
    {
        itemServiceImp.deleteItem(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> VolunteerDto(@RequestBody ItemDto itemDto,@PathVariable("id") long id)
    {
        Item updatedItemDto = itemServiceImp.updateItem(itemDto,id);
        return new ResponseEntity<>(updatedItemDto,HttpStatus.OK);
    }

}
