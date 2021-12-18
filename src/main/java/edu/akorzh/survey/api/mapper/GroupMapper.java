package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.GroupDto;
import edu.akorzh.survey.model.Group;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public GroupDto to(Group group) {
        return GroupDto.builder()
                .name(group.getName())
                .year(group.getYear())
                .guid(group.getGuid())
                .build();
    }

    public Group to(GroupDto group) {
        return Group.builder()
                .name(group.getName())
                .year(group.getYear())
                .guid(group.getGuid())
                .build();
    }

    public List<GroupDto> to(List<Group> groups) {
        return groups.stream().map(this::to).collect(Collectors.toList());
    }

}
