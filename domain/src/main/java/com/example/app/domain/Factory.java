package com.example.app.domain;

import com.example.app.domain.services.DomainService;

public class Factory {

  public DomainService domainService() {
    return new DomainService();
  }

}
