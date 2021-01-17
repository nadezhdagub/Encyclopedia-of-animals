package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.api.model.type.UpdateTypeDto;
import com.trkpo.animal.encyclopedia.entity.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(value = "Type API", tags = "Type API")
public interface TypeApi {
    @GetMapping("/type")
    @ApiOperation(value = "List types",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
    ResponseEntity<List<TypeDto>> list();

    @PutMapping("/type")
    @ApiOperation(value = "Add new type",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })

    ResponseEntity<TypeDto> create(Type type) throws Exception;

    @PutMapping("/type/{id}")
    @ApiOperation(value = "Update type",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })


    ResponseEntity<TypeDto> update(UUID typeId, TypeDto typeDto) throws Exception;

    @DeleteMapping("/type/{id}")
    @ApiOperation(value = "Remove type",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws Exception;


}
