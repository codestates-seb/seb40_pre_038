import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import { deleteQuestionComment } from '../../../_actions/question_action';

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
  .comment-delete {
    color: #c22e33;
    visibility: hidden;
    padding: 0;
    background-color: transparent;
    border: none;
    &:hover {
      cursor: pointer;
    }
  }
  &:hover {
    .comment-delete {
      visibility: visible;
    }
  }
`;

const Comment = ({ commentId, body, nickname, date }) => {
  const dispatch = useDispatch();

  const handleOnClickDelete = () => {
    console.log('delete!');
    dispatch(deleteQuestionComment(commentId));
  };

  return (
    <CommentWrapper>
      <span className="comment-body">{body} - </span>
      <span className="comment-nickname">{nickname}</span>
      <span className="comment-date">{` ${date}`}</span>&nbsp;&nbsp;&nbsp;
      <button className="comment-delete" onClick={handleOnClickDelete}>
        delete
      </button>
    </CommentWrapper>
  );
};

export default Comment;
