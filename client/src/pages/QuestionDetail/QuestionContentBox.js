import styled from 'styled-components';
import QuestionBody from './MainQuestion/QuestionBody';
import QuestionReply from './MainQuestion/QuestionReply';

const ContentBoxContainer = styled.div`
  display: flex;
  flex-grow: 1;
  padding-right: 10px;
`;

const QuestionContentBox = () => {
  return (
    <ContentBoxContainer>
      <QuestionBody />
      <QuestionReply />
    </ContentBoxContainer>
  );
};

export default QuestionContentBox;
