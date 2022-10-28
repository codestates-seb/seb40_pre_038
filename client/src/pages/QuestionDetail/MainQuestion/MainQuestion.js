import QuestionVote from '../QuestionVote';
import QuestionContentBox from '../QuestionContentBox';
import styled from 'styled-components';

const MainQuestionContainer = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainQuestion = () => {
  return (
    <MainQuestionContainer>
      <QuestionVote />
      <QuestionContentBox />
    </MainQuestionContainer>
  );
};

export default MainQuestion;
