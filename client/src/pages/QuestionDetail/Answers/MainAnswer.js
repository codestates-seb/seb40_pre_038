import QuestionContentBox from '../QuestionContentBox';
import styled from 'styled-components';
import AnswerVote from './AnswerVote';

const MainQuestionContainer = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainAnswer = ({ data, type, answerId }) => {
  return (
    <MainQuestionContainer>
      <AnswerVote data={data} />
      <QuestionContentBox data={data} type={type} answerId={answerId} />
    </MainQuestionContainer>
  );
};

export default MainAnswer;
