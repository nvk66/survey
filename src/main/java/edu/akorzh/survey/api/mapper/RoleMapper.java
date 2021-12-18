package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.RoleDto;
import edu.akorzh.survey.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDto to(Role role) {
        return RoleDto.builder()
                .name(role.getName().name())
                .build();
    }

    public List<RoleDto> to(Set<Role> roles) {
        return roles.stream().map(this::to).collect(Collectors.toList());
    }
}
