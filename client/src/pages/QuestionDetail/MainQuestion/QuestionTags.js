import styled from 'styled-components';

const QuestionTagsContainer = styled.div`
  width: 100%;
  display: flex;
  margin: 24px 0 12px 0;
`;

const Tag = styled.button`
  height: 24px;
  margin: 2px;
  padding: 4px 6px;
  text-align: center;
  font-size: 12px;
  color: #39739d;
  background-color: #e1ecf4;
  border-radius: 3px;
  border: none;
  &:hover {
    cursor: pointer;
    background-color: #d0e3f1;
  }
`;

const QuestionTags = () => {
  return (
    <QuestionTagsContainer>
      <Tag>java</Tag>
      <Tag>javascript</Tag>
      <Tag>MySQL</Tag>
      <Tag>React</Tag>
    </QuestionTagsContainer>
  );
};

export default QuestionTags;
