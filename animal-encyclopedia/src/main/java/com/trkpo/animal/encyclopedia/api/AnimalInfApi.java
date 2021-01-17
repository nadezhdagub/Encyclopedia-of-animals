package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "AnimalInf API", tags = "AnimalInf API")
@Validated
public interface AnimalInfApi {
    @GetMapping("/animalInf")
    @ApiOperation(value = "list animalInf",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
    ResponseEntity<List<AnimalInfDto>> list();

    @PutMapping("/animalInf")
    @ApiOperation(value = "Create animalInf",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    ResponseEntity<AnimalInfDto> create(@Valid @NotNull @RequestBody AnimalInfDto dto);

    @PutMapping("/animalInf/{id}")
    @ApiOperation(value = "Update animalInf",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    ResponseEntity<AnimalInfDto> update(
            @Valid @NotNull @PathVariable("id") UUID id,
            @Valid @NotNull @RequestBody AnimalInfDto animalInfDto);

    @DeleteMapping("/animalInf/{id}")
    @ApiOperation(value = "Remove animalInf",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") UUID id);
}
