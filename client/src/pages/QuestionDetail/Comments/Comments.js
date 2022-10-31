import styled from 'styled-components';
import { useState } from 'react';
import Comment from './Comment';
import AddComment from './AddComment';

const CommentsContainer = styled.div`
  width: 100%;
  margin-top: 35px;
`;

const createDate = (str) => {
  const date = new Date(str.slice(0, 19));
  const arr = date.toDateString().split(' ');

  return `${arr[1]} ${arr[2]}, ${
    arr[3]
  } at ${date.getHours()}:${date.getMinutes()}`;
};

const Comments = ({ data }) => {
  const [commentData] = useState(data);

  return (
    <CommentsContainer>
      {commentData.map((comment) => {
        const date = createDate(comment.createdAt);
        return (
          <Comment
            key={comment.commentId}
            body={comment.body}
            nickname={comment.memberResponseDto.nickName}
            date={date}
          ></Comment>
        );
      })}
      <AddComment />
    </CommentsContainer>
  );
};

export default Comments;
