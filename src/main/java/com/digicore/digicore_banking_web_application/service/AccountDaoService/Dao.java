package com.digicore.digicore_banking_web_application.service.AccountDaoService;

import java.util.Optional;

public interface Dao<T, M> {

    T save(T t);

}
