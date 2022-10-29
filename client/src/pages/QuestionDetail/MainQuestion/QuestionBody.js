import styled from 'styled-components';
import QuestionBodyTxt from './QuestionBodyTxt';
import QuestionTags from './QuestionTags';
import QuestionBodyBtns from './QuestionBodyBtns';
import QuestionUserinfo from './QuestionUserinfo';

const QuestionBodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const QuestionBodyBottom = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;

const QuestionBody = () => {
  return (
    <QuestionBodyContainer>
      <QuestionBodyTxt />
      <QuestionTags />
      <QuestionBodyBottom>
        <QuestionBodyBtns />
        <QuestionUserinfo />
      </QuestionBodyBottom>
    </QuestionBodyContainer>
  );
};

export default QuestionBody;
