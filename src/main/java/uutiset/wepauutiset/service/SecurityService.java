package uutiset.wepauutiset.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityService {


    public boolean checkCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Object[] auth = authorities.toArray();
        SimpleGrantedAuthority s = (SimpleGrantedAuthority) auth[0];
        return(s.toString().equals("USER"));


    }
}
