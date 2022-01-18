package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.AnswerDto;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Permission;
import edu.akorzh.survey.repository.PermissionRepository;
import edu.akorzh.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnswerMapper {

    private final QuestionRepository questionRepository;
    private final PermissionRepository permissionRepository;

    public AnswerDto to(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .value(answer.getValue())
                .text(answer.getText())
                .build();
    }

    public Answer to(AnswerDto answer, Permission permission) {
        return Answer.builder()
                .value(answer.getValue())
                .text(answer.getText())
                .question(questionRepository.findById(answer.getQuestionId()).orElseThrow(NotFoundException::new))
                .permission(permission)
                .build();
    }

    public List<Answer> to(List<AnswerDto> answers, Permission permission) {
        return answers.stream().map(answer -> to(answer, permission)).collect(Collectors.toList());
    }


}
