import styled from 'styled-components';

const CommentWrapper = styled.div`
  width: 100%;
  padding: 6px;
  border-top: 1px solid #f1f2f3;
  overflow-wrap: break-word;
  font-size: 13px;
  .comment-nickname {
    color: #0074cc;
    &:hover {
      cursor: pointer;
      color: #0a95ff;
    }
  }
  .comment-date {
    color: #9199a1;
  }
`;

const Comment = ({ body, nickname, date }) => {
  return (
    <CommentWrapper>
      <span className="comment-body">{body} - </span>
      <span className="comment-nickname">{nickname} </span>
      <span className="comment-date">{date}</span>
    </CommentWrapper>
  );
};

export default Comment;
