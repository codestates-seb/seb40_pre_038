import styled from 'styled-components';

const QuestionTagsContainer = styled.div`
  width: 100%;
  display: flex;
  margin-top: 24px;
`;

const Tag = styled.button`
  height: 24px;
  margin: 2px 4px 2px 0;
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

const QuestionTags = ({ tagList }) => {
  return (
    <QuestionTagsContainer>
      {tagList.map((tag, idx) => {
        return <Tag key={idx}>{tag}</Tag>;
      })}
    </QuestionTagsContainer>
  );
};

export default QuestionTags;
