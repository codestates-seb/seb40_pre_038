import QuestionVote from '../QuestionVote';
import QuestionContentBox from '../QuestionContentBox';
import styled from 'styled-components';

const MainQuestionContainer = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainQuestion = ({ data, type }) => {
  return (
    <MainQuestionContainer>
      <QuestionVote data={data} />
      <QuestionContentBox data={data} type={type} />
    </MainQuestionContainer>
  );
};

export default MainQuestion;
