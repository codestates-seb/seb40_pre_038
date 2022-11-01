import QuestionVote from '../QuestionVote';
import QuestionContentBox from '../QuestionContentBox';
import styled from 'styled-components';

const MainQuestionContainer = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainQuestion = ({ data }) => {
  return (
    <MainQuestionContainer>
      <QuestionVote data={data} />
      <QuestionContentBox data={data} />
    </MainQuestionContainer>
  );
};

export default MainQuestion;
