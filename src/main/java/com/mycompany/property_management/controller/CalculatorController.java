package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.CalculatorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Calculator", description = "Operations related to basic calculations")
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @Operation(summary = "Add two numbers", description = "Adds two numbers provided as query parameters. Note: num3 is currently ignored.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully calculated sum"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/add/{num3}")
    public Double add(
            @Parameter(description = "First number to add", required = true) @RequestParam("num1") Double num1,
            @Parameter(description = "Second number to add", required = true) @RequestParam("num2") Double num2,
            @Parameter(description = "Third number (ignored)", required = false) @PathVariable("num3") Double num3) {
        return num1 + num2;
    }

    @Operation(summary = "Subtract two numbers", description = "Subtracts the smaller number from the larger one provided as path variables.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully calculated difference"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/sub/{num1}/{num2}")
    public Double subtract(
            @Parameter(description = "First number", required = true) @PathVariable("num1") Double num1,
            @Parameter(description = "Second number", required = true) @PathVariable("num2") Double num2) {
        return num1 > num2 ? num1 - num2 : num2 - num1;
    }

    @Operation(summary = "Multiply four numbers", description = "Multiplies four numbers provided in the request body.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully calculated product"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(
            @Parameter(description = "Calculator data with four numbers", required = true) @RequestBody CalculatorDTO calculatorDTO) {
        Double result = calculatorDTO.getNum1() * calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}