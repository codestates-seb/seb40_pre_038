import styled from 'styled-components';

const AddCommentContainer = styled.div`
  width: 100%;
  display: flex;
  padding: 10px 0;
  border-top: 1px solid #f1f2f3;
`;

const AddCommentBtn = styled.button`
  border: none;
  background-color: transparent;
  font-size: 13px;
  color: #838c95;
  &:hover {
    cursor: pointer;
    color: #0a95ff;
  }
`;

const AddComment = () => {
  const handleOnClickAddComment = () => {};

  return (
    <AddCommentContainer>
      <AddCommentBtn onClick={() => handleOnClickAddComment()}>
        Add a comment
      </AddCommentBtn>
    </AddCommentContainer>
  );
};

export default AddComment;
