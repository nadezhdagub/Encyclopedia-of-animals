package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import com.trkpo.animal.encyclopedia.entity.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Area API", tags = "Area API")
public interface AreaPhotoApi {
    @GetMapping("/area")
    @ApiOperation(value = "List area",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
    ResponseEntity<List<AreaPhotoDto>> list();

    @PutMapping("/area")
    @ApiOperation(value = "Add new area",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })

    ResponseEntity<AreaPhotoDto> create(AreaPhoto areaPhoto) throws Exception;

    @DeleteMapping("/area/{id}")
    @ApiOperation(value = "Remove area",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws Exception;


}
