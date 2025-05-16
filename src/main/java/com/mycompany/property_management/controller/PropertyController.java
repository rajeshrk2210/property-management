package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Properties", description = "Operations related to property management")
@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    private final PropertyService propertyService;

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Operation(summary = "Test endpoint", description = "Returns a simple hello message for testing.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully returned hello message")
    })
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @Operation(summary = "Save a property", description = "Creates a new property with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Property created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property data")
    })
    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(
            @Parameter(description = "Property details", required = true) @RequestBody PropertyDTO propertyDTO) {

        propertyDTO = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all properties", description = "Retrieves a list of all properties.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved properties"),
            @ApiResponse(responseCode = "204", description = "No properties found")
    })
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
//        System.out.println(dummy);
//        System.out.println(dbUrl);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId) {
//        System.out.println(dummy);
//        System.out.println(dbUrl);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @Operation(summary = "Update a property", description = "Updates an existing property with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property data"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @Parameter(description = "Updated property details", required = true) @RequestBody PropertyDTO propertyDTO,
            @Parameter(description = "ID of the property to update", required = true) @PathVariable Long propertyId) {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update property description", description = "Updates the description of an existing property.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Description updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid description data"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(
            @Parameter(description = "Property description", required = true) @RequestBody PropertyDTO propertyDTO,
            @Parameter(description = "ID of the property to update", required = true) @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update property price", description = "Updates the price of an existing property.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid price data"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(
            @Parameter(description = "Property price", required = true) @RequestBody PropertyDTO propertyDTO,
            @Parameter(description = "ID of the property to update", required = true) @PathVariable Long propertyId) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete a property", description = "Deletes a property by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @Parameter(description = "ID of the property to delete", required = true) @PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}