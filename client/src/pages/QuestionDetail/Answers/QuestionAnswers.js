import { useSelector } from 'react-redux';
import styled from 'styled-components';
import AnswersInfo from './AnswersInfo';
import Answer from '../MainQuestion/MainQuestion';
import PostAnswerBox from './PostAnswerBox';

const QuestionAnswersContainer = styled.div``;
const AnswerConatiner = styled.div`
  padding: 16px 0;
  border-bottom: 1px solid #e3e6e8;
`;

const answersData = [0, 0, 0, 0, 0, 0, 0];

const QuestionAnswers = () => {
  const state = useSelector((state) => state.questionReducer);
  const { question_id, data } = state;
  console.log(question_id, data);
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
