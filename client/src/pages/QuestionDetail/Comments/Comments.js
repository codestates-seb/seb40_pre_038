import styled from 'styled-components';
import { useState, useEffect } from 'react';
import Comment from './Comment';
import AddComment from './AddComment';

const CommentsContainer = styled.div`
  width: 100%;
  margin-top: 35px;
`;

const createDate = (str) => {
  const date = new Date(str.slice(0, 19));
  const arr = date.toDateString().split(' ');

  return `${arr[1]} ${arr[2]}, ${arr[3]} at ${String(date.getHours()).padStart(
    2,
    '0'
  )}:${String(date.getMinutes()).padStart(2, '0')}`;
};

const Comments = ({ data, type, answerId }) => {
  const [commentData, setcommentData] = useState(data);

  useEffect(() => {
    setcommentData(data);
  }, [data]);

  return (
    <CommentsContainer>
      {commentData.map((comment) => {
        const date = createDate(comment.createdAt);
        return (
          <Comment
            key={comment.commentId}
            commentId={comment.commentId}
            body={comment.body}
            nickname={comment.userResponseDto.nickName}
            date={date}
          ></Comment>
        );
      })}
      <AddComment type={type} answerId={answerId} />
    </CommentsContainer>
  );
};

export default Comments;
