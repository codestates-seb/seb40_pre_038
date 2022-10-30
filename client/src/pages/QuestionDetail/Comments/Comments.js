import styled from 'styled-components';
import { useState } from 'react';
import Comment from './Comment';
import AddComment from './AddComment';

const CommentsContainer = styled.div`
  width: 100%;
  margin-top: 35px;
`;

const Comments = ({ data }) => {
  const [commentData] = useState(data);
  return (
    <CommentsContainer>
      {commentData.map((comment) => {
        return (
          <Comment
            key={comment.commentId}
            body={comment.body}
            nickname={comment.memberResponseDto.nickName}
            date={comment.createdAt}
          ></Comment>
        );
      })}
      <AddComment />
    </CommentsContainer>
  );
};

export default Comments;
