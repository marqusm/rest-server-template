package com.marqusm.repository;

import com.marqusm.model.dto.MarkDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
@Component
public interface MarkRepository extends JpaRepository<MarkDto, Long> {

}
