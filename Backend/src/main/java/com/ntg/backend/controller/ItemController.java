package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
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
    private static final String UPLOAD_DIR = "uploads/";
    @PostMapping("/image")
    public ResponseEntity<String> uploadFile(@RequestPart("image") MultipartFile image) {
        try {
            // Ensure the directory exists or create it
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate a unique file name to prevent overwriting existing files
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Save the file to the server
            Files.copy(image.getInputStream(), filePath);

            // Return success response
            return ResponseEntity.ok("File uploaded successfully: " + filePath.toString());

        } catch (IOException e) {
            // Handle file upload failure
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }

    }
    // API to save the item with provided data, including the image URL
    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody ItemDto itemDto) {

            Item savedItem = itemServiceImp.createItem(itemDto);
            return new ResponseEntity<>(savedItem, HttpStatus.OK);

    }

//    @PostMapping
//    public ResponseEntity<Item> addItem(@RequestBody ItemDto itemDto) {
//        Item savedItemDto = itemServiceImp.createItem(itemDto);
//        return new ResponseEntity<>(savedItemDto, HttpStatus.OK);
//    }

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
    @GetMapping("requestsWithNeedyDetails/{id}")
    public ResponseEntity<List<RequestWithNeedyDetails>> getAllRequestsNeedyDetails(@PathVariable("id") long itemId)
    {
        List<RequestWithNeedyDetails> requestWithNeedyDetails = itemServiceImp.requestWithNeedyDetails(itemId);
        return new ResponseEntity<>(requestWithNeedyDetails, HttpStatus.OK);
    }



}
