import styled from 'styled-components';
import QuestionBody from './MainQuestion/QuestionBody';
import QuestionComments from './MainQuestion/QuestionComments';

const ContentBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  padding-right: 10px;
`;

const QuestionContentBox = ({ data }) => {
  return (
    <ContentBoxContainer>
      <QuestionBody data={data} />
      <QuestionComments data={data} />
    </ContentBoxContainer>
  );
};

export default QuestionContentBox;
