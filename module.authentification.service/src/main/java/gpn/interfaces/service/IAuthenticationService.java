package gpn.interfaces.service;

import gpn.exception.ApplicationException;
import gpn.exception.UserNotFoundException;

import javax.naming.NamingException;

/**Сервис аутентификации пользователей*/
public interface IAuthenticationService {

    /**Получить аутентификационный токен для пользователя*/
    String getAuthToken(String userName, String lastName, String telephone)
            throws UserNotFoundException, NamingException, ApplicationException;

}
