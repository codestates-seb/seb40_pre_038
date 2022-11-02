import styled from 'styled-components';
import QuestionBody from './MainQuestion/QuestionBody';
import QuestionComments from './MainQuestion/QuestionComments';

const ContentBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  padding-right: 10px;
`;

const QuestionContentBox = ({ data, type }) => {
  return (
    <ContentBoxContainer>
      <QuestionBody data={data} type={type} />
      <QuestionComments data={data} type={type} />
    </ContentBoxContainer>
  );
};

export default QuestionContentBox;
