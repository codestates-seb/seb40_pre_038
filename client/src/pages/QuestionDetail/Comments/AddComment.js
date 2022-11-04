import styled from 'styled-components';
import { useState, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { postQuestionComment } from '../../../_actions/question_action';
import { postAnswerComment } from '../../../_actions/answer_action';

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

const CommentInput = styled.textarea`
  width: 100%;
  height: 65px;
  margin-right: 8px;
  padding: 7px 9px;
  border: 1px solid #babfc4;
  border-radius: 3px;
  vertical-align: text-top;
  &:focus {
    outline: none;
    border: 1px solid #6bbbf7;
    box-shadow: 0px 0px 0px 4px #d8e5f2;
  }
  &::placeholder {
    color: #babfc4;
  }
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

const AddComment = ({ type, answerId }) => {
  const [isBtnClicked, setIsBtnClicked] = useState(false);
  const [commentInput, setCommentInput] = useState('');
  const inputRef = useRef();

  const dispatch = useDispatch();
  const questionState = useSelector((state) => state.questionReducer);
  const question_id = questionState.question_id;

  const QuestionPlaceholder =
    'Use comments to reply to other users or notify them of changes. If you are adding new information, edit your post instead of commenting.';
  const AnswerPlaceholder =
    'Use comments to ask for more information or suggest improvements. Avoid comments like "+1" or "thanks".';

  const handleOnClickAddAComment = () => {
    setIsBtnClicked(true);
  };

  const handleOnChangeInput = (e) => {
    setCommentInput(e.target.value);
  };

  const handleOnClickAddQuestionComment = (e) => {
    e.preventDefault();
    dispatch(postQuestionComment(question_id, commentInput));
    inputRef.current.value = '';
    setIsBtnClicked(false);
  };

  const handleOnClickAddAnswerComment = (e) => {
    e.preventDefault();
    dispatch(postAnswerComment(question_id, answerId, commentInput));
    inputRef.current.value = '';
    setIsBtnClicked(false);
  };

  const handleOnKeyPress = (e) => {
    console.log('엔터!');
    if (e.key === 'Enter') {
      if (type === 'question') handleOnClickAddQuestionComment(e);
      else if (type === 'answer') handleOnClickAddAnswerComment(e);
    }
  };

  return (
    <AddCommentContainer>
      {isBtnClicked ? (
        <>
          <AddCommentForm>
            <CommentInput
              onChange={handleOnChangeInput}
              ref={inputRef}
              placeholder={
                type === 'answer'
                  ? `${AnswerPlaceholder}`
                  : `${QuestionPlaceholder}`
              }
              onKeyPress={handleOnKeyPress}
            />
            <AddCommentBtn
              //type = question or answer
              onClick={
                type === 'question'
                  ? handleOnClickAddQuestionComment
                  : handleOnClickAddAnswerComment
              }
            >
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
