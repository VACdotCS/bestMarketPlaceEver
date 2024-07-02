package com.example.project.service;

import com.example.project.common.Constants;
import com.example.project.dto.mapper.PickupPointDTOMapper;
import com.example.project.dto.request.PickupPointRequest;
import com.example.project.dto.response.PickupPointDTO;
import com.example.project.entity.PickupPoint;
import com.example.project.entity.User;
import com.example.project.exception.CannotUseUserException;
import com.example.project.exception.NoSuchElementFoundException;
import com.example.project.repository.PickupPointRepo;
import com.example.project.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.project.entity.Role.MANAGER;

@Service
@AllArgsConstructor
public class PickupPointService {
    private PickupPointRepo pickupPointRepo;
    private UserRepo userRepository;
    private PickupPointDTOMapper dtoMapper;

    public List<PickupPointDTO> findAll() {
        return pickupPointRepo.findAllWithManager()
                .stream()
                .map(dtoMapper)
                .collect(Collectors.toList());
    }

    public PickupPointDTO findByManagerId(Integer managerId) throws NoSuchElementFoundException, CannotUseUserException {
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_MANAGER));

        if (!manager.getRole().name().equals(MANAGER.name())) {
            throw new CannotUseUserException(Constants.USER_IS_NOT_MANAGER);
        }

        if (!manager.isLinkedToPickupPoint()) {
            throw new CannotUseUserException(Constants.USER_IS_NOT_LINKED_TO_POINT);
        }

        return dtoMapper.apply(pickupPointRepo.findByManager(manager));
    }

    public PickupPointDTO create(PickupPointRequest request) throws NoSuchElementFoundException, CannotUseUserException {
        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_MANAGER));

        if (!manager.getRole().name().equals(MANAGER.name())) {
            throw new CannotUseUserException(Constants.USER_IS_NOT_MANAGER);
        }

        if (manager.isLinkedToPickupPoint()) {
            throw new CannotUseUserException(Constants.CANNOT_USE_MANAGER);
        }

        PickupPoint pickupPoint = PickupPoint.builder()
                .address(request.getAddress())
                .income(0.0)
                .manager(manager)
                .build();

        pickupPointRepo.save(pickupPoint);
        return dtoMapper.apply(pickupPoint);
    }

    public PickupPointDTO update(Integer id, PickupPointRequest request) throws NoSuchElementFoundException, CannotUseUserException {
        PickupPoint pickupPoint = pickupPointRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_PICKUPPOINT));

        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_MANAGER));

        if (!manager.getRole().name().equals(MANAGER.name())) {
            throw new CannotUseUserException(Constants.USER_IS_NOT_MANAGER);
        }

        if (manager.isLinkedToPickupPoint() && !Objects.equals(manager.getUser_pickup_points().getId(), id)) {
            throw new CannotUseUserException(Constants.CANNOT_USE_MANAGER);
        }

        pickupPoint.setAddress(request.getAddress());
        pickupPoint.setManager(manager);

        pickupPointRepo.save(pickupPoint);
        return dtoMapper.apply(pickupPoint);
    }

    public void delete(Integer id) throws NoSuchElementFoundException {
        if (!pickupPointRepo.existsById(id)) {
            throw new NoSuchElementFoundException(Constants.NOT_FOUND_PICKUPPOINT);
        }

        pickupPointRepo.deleteById(id);
    }
}
