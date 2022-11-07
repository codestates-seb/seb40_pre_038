import QuestionVote from '../QuestionVote';
import QuestionContentBox from '../QuestionContentBox';
import styled from 'styled-components';

const MainQuestionContainer = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainQuestion = ({ data, type, answerId }) => {
  return (
    <MainQuestionContainer>
      <QuestionVote data={data} />
      <QuestionContentBox data={data} type={type} answerId={answerId} />
    </MainQuestionContainer>
  );
};

export default MainQuestion;
