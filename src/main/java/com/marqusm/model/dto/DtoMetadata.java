package com.marqusm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 01-Sep-17
 */
@MappedSuperclass
public abstract class DtoMetadata implements Dto {

  @JsonIgnore
  @Column(name = "meta_active", nullable = false)
  private Boolean metaActive;
  @JsonIgnore
  @Column(name = "meta_creation_date", nullable = false)
  private LocalDateTime metaCreationDate;
  @JsonIgnore
  @Column(name = "meta_modified_date", nullable = false)
  private LocalDateTime metaModifiedDate;
  @JsonIgnore
  @Column(name = "meta_creation_user_id")
  private Long metaCreationUserAccount;
  @JsonIgnore
  @Column(name = "meta_modified_user_id")
  private Long metaModifiedUserAccount;

  public Boolean getMetaActive() {
    return metaActive;
  }

  public void setMetaActive(Boolean active) {
    this.metaActive = active;
  }

  public LocalDateTime getMetaCreationDate() {
    return metaCreationDate;
  }

  public void setMetaCreationDate(LocalDateTime metaCreationDate) {
    this.metaCreationDate = metaCreationDate;
  }

  public LocalDateTime getMetaModifiedDate() {
    return metaModifiedDate;
  }

  public void setMetaModifiedDate(LocalDateTime metaModifiedDate) {
    this.metaModifiedDate = metaModifiedDate;
  }

  public Long getMetaCreationUserAccount() {
    return metaCreationUserAccount;
  }

  public void setMetaCreationUserAccount(Long metaCreationUserAccount) {
    this.metaCreationUserAccount = metaCreationUserAccount;
  }

  public Long getMetaModifiedUserAccount() {
    return metaModifiedUserAccount;
  }

  public void setMetaModifiedUserAccount(Long metaModifiedUserAccount) {
    this.metaModifiedUserAccount = metaModifiedUserAccount;
  }

  public void setMetadata(Boolean active, LocalDateTime metaCreationDate,
      LocalDateTime metaModifiedDate,
      Long metaCreationUserAccount, Long metaModifiedUserAccount) {
    this.metaActive = active;
    this.metaCreationDate = metaCreationDate;
    this.metaModifiedDate = metaModifiedDate;
    this.metaCreationUserAccount = metaCreationUserAccount;
    this.metaModifiedUserAccount = metaModifiedUserAccount;
  }

  public void setMetadata(DtoMetadata entityMetadata) {
    this.metaActive = entityMetadata.metaActive;
    this.metaCreationDate = entityMetadata.metaCreationDate;
    this.metaModifiedDate = entityMetadata.metaModifiedDate;
    this.metaCreationUserAccount = entityMetadata.metaCreationUserAccount;
    this.metaModifiedUserAccount = entityMetadata.metaModifiedUserAccount;
  }

  public void setCreationMetadata(Boolean active, LocalDateTime metaCreationDate,
      Long metaCreationUserAccount) {
    this.metaActive = active;
    this.metaCreationDate = metaCreationDate;
    this.metaModifiedDate = metaCreationDate;
    this.metaCreationUserAccount = metaCreationUserAccount;
    this.metaModifiedUserAccount = metaCreationUserAccount;
  }

  public void setModifiedMetadata(Boolean active, LocalDateTime metaModifiedDate,
      Long metaModifiedUserAccount) {
    this.metaActive = active;
    this.metaModifiedDate = metaModifiedDate;
    this.metaModifiedUserAccount = metaModifiedUserAccount;
  }

  public void removeMetadata() {
    metaActive = null;
    metaCreationDate = null;
    metaModifiedDate = null;
    metaCreationUserAccount = null;
    metaModifiedUserAccount = null;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("metaActive", metaActive)
        .append("metaCreationDate", metaCreationDate)
        .append("metaModifiedDate", metaModifiedDate)
        .append("metaCreationUserAccount", metaCreationUserAccount)
        .append("metaModifiedUserAccount", metaModifiedUserAccount)
        .toString();
  }
}
