package com.cookerytech.mapper;

import com.cookerytech.domain.Role;
import com.cookerytech.domain.User;
import com.cookerytech.dto.UserDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T02:07:11+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDTO.setRoles( new LinkedHashSet<Role>( set ) );
        }
        userDTO.setId( user.getId() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPhone( user.getPhone() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setCity( user.getCity() );
        userDTO.setCountry( user.getCountry() );
        userDTO.setBirthDate( user.getBirthDate() );
        userDTO.setTaxNo( user.getTaxNo() );
        userDTO.setStatus( user.getStatus() );
        userDTO.setCreateAt( user.getCreateAt() );
        userDTO.setUpdateAt( user.getUpdateAt() );
        userDTO.setBuiltIn( user.getBuiltIn() );

        return userDTO;
    }
}
