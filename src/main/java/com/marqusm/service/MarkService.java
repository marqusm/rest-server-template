package com.marqusm.service;

import com.marqusm.model.dto.MarkDto;
import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.request.MarkRequest;
import com.marqusm.model.response.MarkResponse;
import com.marqusm.repository.MarkRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
@Service
@Transactional
public class MarkService {

  private final TokenService tokenService;
  private final MarkRepository markRepository;

  @Autowired
  public MarkService(TokenService tokenService, MarkRepository markRepository) {
    this.tokenService = tokenService;
    this.markRepository = markRepository;
  }

  public void saveMark(String token, MarkRequest markRequest) {
    StudentDto studentDto = tokenService.getTokenByValue(token).getStudentDto();
    MarkDto markDto = markRequest.export();
    markDto.setStudentDto(studentDto);
    markDto.setCreationMetadata(true, LocalDateTime.now(), studentDto.getId());
    markRepository.save(markDto);
  }

  public List<MarkResponse> getAllMarks(String token) {
    StudentDto studentDto = tokenService.getUserByTokenValue(token);
    List<MarkDto> marks = markRepository.findAll(Example.of(new MarkDto(studentDto, null)));
    return marks.stream()
        .map(markDto -> new MarkResponse(markDto))
        .collect(Collectors.toList());
  }

}
