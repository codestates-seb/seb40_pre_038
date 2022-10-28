import styled from 'styled-components';

const ContentBoxContainer = styled.div`
  border: 1px solid red;
  display: flex;
  //width: 620px;
  //flex-basis: content;
  flex-grow: 1;
  padding-right: 10px;
`;

const QuestionContentBox = () => {
  return <ContentBoxContainer></ContentBoxContainer>;
};

export default QuestionContentBox;
