package com.example.learn_space.service.imp;

import com.example.learn_space.constain.RoleType;
import com.example.learn_space.dto.request.ClassCreationRequest;
import com.example.learn_space.dto.response.ClassRoomResponse;
import com.example.learn_space.exceptions.AppException;
import com.example.learn_space.exceptions.ErrorCode;
import com.example.learn_space.mapper.ClassRoomMapper;
import com.example.learn_space.models.ClassRoom;
import com.example.learn_space.models.User;
import com.example.learn_space.repository.ClassRoomRepository;
import com.example.learn_space.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ClassRoomService {
    ClassRoomRepository classRoomRepository;
    ClassRoomMapper classRoomMapper;
    UserRepository userRepository;
    public ClassRoomResponse createClass(ClassCreationRequest request) {
        ClassRoom classRoom = classRoomMapper.toClassRoom(request);
        Optional<User> user = userRepository.findById(request.getIdOwner());
        if (user.isEmpty()) throw new AppException(ErrorCode.USER_EXISTED);
        classRoom.setOwner(user.get());
        if(!user.get().getRole().equals(RoleType.ROLE_ADMIN))  throw new AppException(ErrorCode.NOT_ENOUGH_RIGHTS);

        try{
            classRoom = classRoomRepository.save(classRoom);
        } catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return classRoomMapper.toClassRoomResponse(classRoom);
    }
}
