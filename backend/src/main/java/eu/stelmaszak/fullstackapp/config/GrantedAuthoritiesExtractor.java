package eu.stelmaszak.fullstackapp.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String ROLES = "roles";
    private static final String REALM_ACCESS = "realm_access";

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return getRolesFromRealmAccess(getRealmAccess(source)).stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Collection<Object>> getRealmAccess(Jwt source) {
        return (Map<String, Collection<Object>>) source.getClaims()
            .getOrDefault(REALM_ACCESS, Collections.emptyMap());
    }

    private static Collection<String> getRolesFromRealmAccess(Map<String, Collection<Object>> realmAccess) {
        return realmAccess
            .getOrDefault(ROLES, Collections.emptyList()).stream()
            .map(Object::toString)
            .toList();
    }

}
