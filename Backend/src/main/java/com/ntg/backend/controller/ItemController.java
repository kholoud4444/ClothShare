package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.ImageUrl;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
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
import java.util.UUID;

@RestController
@RequestMapping("/item")
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

            // Wrap ImageUrl in MessageDto with a success message
            MessageDto<ImageUrl> response = new MessageDto<>(fileName,null );

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

}
