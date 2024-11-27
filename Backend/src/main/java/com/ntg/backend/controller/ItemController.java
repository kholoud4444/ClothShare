package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.ImageUrl;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.service.imp.ItemServiceImp;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@CrossOrigin("http://localhost:4200")

public class ItemController {

    private final ItemServiceImp itemServiceImp;
    private static final String UPLOAD_DIR = "uploads/";

    public ItemController(ItemServiceImp itemServiceImp) {
        this.itemServiceImp = itemServiceImp;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<MessageDto<ImageUrl>> uploadFile(@RequestPart("image") MultipartFile image) {
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

            // Create ImageUrl object with the file path
            ImageUrl imageUrl = new ImageUrl(filePath.toString());
            ImageUrl imageUrl2 = new ImageUrl(fileName);

            // Wrap ImageUrl in MessageDto with a success message
            MessageDto<ImageUrl> response = new MessageDto<>("image uploaded succesffully",imageUrl2 );

            // Return the response with HttpStatus.OK
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IOException e) {
            // Create MessageDto with an error message and null object
            MessageDto<ImageUrl> response = new MessageDto<>("File upload failed: " + e.getMessage(), null);

            // Return the error response with HttpStatus.INTERNAL_SERVER_ERROR
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // API to save the item with provided data, including the image URL
    @PostMapping("/createItem")
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto itemDto) {
        ItemDto savedItem = itemServiceImp.createItem(itemDto);
        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }

    @GetMapping("/getPhoto/{fileName}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) throws MalformedURLException {
        // Construct the file path
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Check if the file exists, otherwise throw exception
//        if (!Files.exists(filePath)) {
//            throw new ResourceNotFoundException("Photo", "fileName", fileName);
//        }

        // Create a resource for the file
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust based on file type
                .body(resource);

    }


    @GetMapping("{id}")
    public ResponseEntity<ItemDto>getItemById(@PathVariable("id") long id )
    {
        ItemDto itemDto = itemServiceImp.getItemById(id);
        return new ResponseEntity<>(itemDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PageDto<ItemDto>> getAllItems(@RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                     @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<ItemDto> itemsPageDto = itemServiceImp.getAllItems(pageNo, pageSize);
        return new ResponseEntity<>(itemsPageDto,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageDto<String>> deleteItemById(@PathVariable("id") Long id)
    {
        itemServiceImp.deleteItem(id);
        return new ResponseEntity<>(new MessageDto<>("Item Deleted Successfully", null),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto, @PathVariable("id") long id)
    {
        ItemDto updatedItemDto = itemServiceImp.updateItem(itemDto,id);
        return new ResponseEntity<>(updatedItemDto,HttpStatus.OK);
    }
    @GetMapping("/getFilteredItems")
    public ResponseEntity<PageDto<ItemDto>> getFilteredItems(
            @RequestParam(value = "type", required = false) Optional<Item.ClothingType> type,
            @RequestParam(value = "size", required = false) Optional<Item.ClothingSize> size,
            @RequestParam(value = "state", required = false) Optional<Item.ItemState> state,
            @RequestParam(value = "genderSuitability", required = false) Optional<Item.GenderSuitability> genderSuitability,
            @RequestParam(value = "status", required = false) Optional<Item.ItemStatus> status, // Added status filter
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        // Log incoming parameters for debugging
        System.out.println("Received filter parameters:");
        type.ifPresent(t -> System.out.println("Type: " + t));
        size.ifPresent(s -> System.out.println("Size: " + s));
        state.ifPresent(s -> System.out.println("State: " + s));
        genderSuitability.ifPresent(g -> System.out.println("GenderSuitability: " + g));
        status.ifPresent(s -> System.out.println("Status: " + s)); // Log status filter

        PageDto<ItemDto> items = itemServiceImp.getAllItemsByFilters(
                type, size, state, genderSuitability, status, pageNo, pageSize
        );
        return ResponseEntity.ok(items);
    }


}
