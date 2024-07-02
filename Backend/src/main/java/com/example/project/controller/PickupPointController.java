package com.example.project.controller;

import com.example.project.dto.request.PickupPointRequest;
import com.example.project.dto.response.PickupPointDTO;
import com.example.project.exception.CannotUseUserException;
import com.example.project.exception.NoSuchElementFoundException;
import com.example.project.service.PickupPointService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PickupPointController {

    private final PickupPointService pickupPointService;

    @GetMapping
    public ResponseEntity<List<PickupPointDTO>> getAll() {
        return ResponseEntity.ok(pickupPointService.findAll());
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<PickupPointDTO> getByManagerId(@PathVariable Integer managerId) throws NoSuchElementFoundException, CannotUseUserException {
        return ResponseEntity.ok(pickupPointService.findByManagerId(managerId));
    }

    @PostMapping
    public ResponseEntity<?> createPoint(@Valid @RequestBody PickupPointRequest request) throws NoSuchElementFoundException, CannotUseUserException {
        PickupPointDTO pickupPointDTO = pickupPointService.create(request);
        return ResponseEntity.ok(pickupPointDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePoint(@PathVariable Integer id, @Valid @RequestBody PickupPointRequest request) throws NoSuchElementFoundException, CannotUseUserException {
        PickupPointDTO pickupPointDTO = pickupPointService.update(id, request);
        return ResponseEntity.ok(pickupPointDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable Integer id) throws NoSuchElementFoundException {
        pickupPointService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({NoSuchElementFoundException.class, CannotUseUserException.class,})
    public ResponseEntity<String> handleBadRequestException(Exception e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e, WebRequest request) {
        return new ResponseEntity<>("Возникла неизвестная ошибка: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
