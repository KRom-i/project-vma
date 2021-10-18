package gpn.service;

import gpn.contract.Claim;
import gpn.contract.SystemUser;
import gpn.exception.ApplicationException;
import gpn.exception.UserNotFoundException;
import gpn.interfaces.service.IAuthenticationService;
import gpn.interfaces.service.IClaimsService;
import gpn.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
//    @Autowired
//    private ILdapService ldapService;
    @Autowired
    private UserService userService;
    @Autowired
    private IClaimsService claimsService;

    @Override
    public String getAuthToken(String userName) throws UserNotFoundException, ApplicationException {

        SystemUser sUser = new SystemUser();
        sUser.setUserName(userName);
        sUser.setDomainName("-");
        sUser.setDisplayName("-");
        sUser.setEmail("-");
        sUser.setGuid("-");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenUtil.generateToken(userDetails, sUser);
    }

    /**
     * запонение контракта из ldap
     * @param ldapUser контракт
     * @param searchResult результат поиска из Ldap
     */
    private void fillAttributes(SystemUser ldapUser, SearchResult searchResult) throws NamingException {
        Attributes attributes = searchResult.getAttributes();
        ldapUser.setUserName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDomainName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDisplayName(attributes.get("displayname").get(0).toString());
        ldapUser.setEmail(attributes.get("mail").get(0).toString());
        ldapUser.setGuid(attributes.get("objectguid").get(0).toString());
    }
}
