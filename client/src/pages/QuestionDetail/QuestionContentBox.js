import styled from 'styled-components';
import QuestionBody from './MainQuestion/QuestionBody';
import QuestionComments from './MainQuestion/QuestionComments';

const ContentBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  padding-right: 10px;
`;

const QuestionContentBox = ({ data, type, answerId }) => {
  return (
    <ContentBoxContainer>
      <QuestionBody data={data} type={type} answerId={answerId} />
      <QuestionComments data={data} type={type} answerId={answerId} />
    </ContentBoxContainer>
  );
};

export default QuestionContentBox;
