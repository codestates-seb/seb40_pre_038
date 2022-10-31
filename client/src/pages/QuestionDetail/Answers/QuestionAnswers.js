import styled from 'styled-components';
import AnswersInfo from './AnswersInfo';
import Answer from '../MainQuestion/MainQuestion';
import PostAnswerBox from './PostAnswerBox';

const QuestionAnswersContainer = styled.div``;
const AnswerConatiner = styled.div`
  padding: 16px 0;
  border-bottom: 1px solid #e3e6e8;
`;

//GET 요청: '/questions/{question-id}/answers?page=1

const answersData = [0, 0, 0, 0, 0, 0, 0];

const QuestionAnswers = () => {
  return (
    <QuestionAnswersContainer>
      <AnswersInfo answersCnt={17} />
      {answersData.map((el, idx) => {
        return (
          <AnswerConatiner key={idx}>
            <Answer />
          </AnswerConatiner>
        );
      })}
      <PostAnswerBox />
    </QuestionAnswersContainer>
  );
};

export default QuestionAnswers;
