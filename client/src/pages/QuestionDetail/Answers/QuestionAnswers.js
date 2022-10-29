import styled from 'styled-components';
import AnswersInfo from './AnswersInfo';

const QuestionAnswersContainer = styled.div``;

//GET 요청: '/questions/{question-id}/answers?page=1

const QuestionAnswers = () => {
  return (
    <QuestionAnswersContainer>
      <AnswersInfo answersCnt={17} />
    </QuestionAnswersContainer>
  );
};

export default QuestionAnswers;
