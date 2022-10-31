import styled from 'styled-components';

const PostAnswerBoxContainer = styled.div`
  width: 100%;
`;

const PostAnswerInfo = styled.div`
  padding: 20px 0;
  font-size: 19px;
  color: #232629;
`;

const PostAnswerBox = () => {
  return (
    <PostAnswerBoxContainer>
      <PostAnswerInfo>Your Answer</PostAnswerInfo>
    </PostAnswerBoxContainer>
  );
};

export default PostAnswerBox;