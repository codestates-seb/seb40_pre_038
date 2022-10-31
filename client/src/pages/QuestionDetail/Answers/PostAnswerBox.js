import styled from 'styled-components';
import MyEditor from './Toastinput';

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
      <MyEditor />
    </PostAnswerBoxContainer>
  );
};

export default PostAnswerBox;
