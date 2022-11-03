import styled from 'styled-components';
import { useState, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { SearchbarInput } from '../../../components/Searchbar';
import {
  getQuestion,
  postQuestionComment,
} from '../../../_actions/question_action';

const AddCommentContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 10px 0;
  border-top: 1px solid #f1f2f3;
`;

const AddACommentBtn = styled.button`
  border: none;
  background-color: transparent;
  font-size: 13px;
  color: #838c95;
  &:hover {
    cursor: pointer;
    color: #0a95ff;
  }
`;

const AddCommentForm = styled.form`
  width: 100%;
  display: flex;
  padding: 2px;
`;

const CommentInput = styled(SearchbarInput)`
  width: 100%;
  height: 65px;
  margin-right: 8px;
  padding: 2px;
`;

const AddCommentBtn = styled.button`
  background: #0995ff;
  color: #ffffff;
  font-weight: 400;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: 130px;
  height: 40px;
  font-size: 13px;
  &:hover {
    cursor: pointer;
    background-color: #0063bf;
  }
`;

const CommentGuideBox = styled.div`
  display: flex;
  margin: 3px;
  color: #6a737c;
`;

const AddComment = ({ setcommentData }) => {
  const [isBtnClicked, setIsBtnClicked] = useState(false);
  const [commentInput, setCommentInput] = useState('');
  const inputRef = useRef();

  const dispatch = useDispatch();
  const state = useSelector((state) => state.questionReducer);
  const question_id = state.question_id;

  const handleOnClickAddAComment = () => {
    setIsBtnClicked(true);
  };

  const handleOnChangeInput = (e) => {
    setCommentInput(e.target.value);
  };

  const handleOnClickAddComment = (e) => {
    e.preventDefault();
    dispatch(postQuestionComment(question_id, commentInput));
    inputRef.current.value = '';
    setIsBtnClicked(false);
  };

  return (
    <AddCommentContainer>
      {isBtnClicked ? (
        <>
          <AddCommentForm>
            <CommentInput
              type="text"
              onChange={handleOnChangeInput}
              ref={inputRef}
            />
            <AddCommentBtn onClick={handleOnClickAddComment}>
              Add Comment
            </AddCommentBtn>
          </AddCommentForm>
          <CommentGuideBox>Enter at least 15 characters</CommentGuideBox>
        </>
      ) : (
        <AddACommentBtn onClick={handleOnClickAddAComment}>
          Add a comment
        </AddACommentBtn>
      )}
    </AddCommentContainer>
  );
};

export default AddComment;
