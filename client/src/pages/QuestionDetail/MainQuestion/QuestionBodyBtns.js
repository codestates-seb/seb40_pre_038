import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { deleteQuestion } from '../../../_actions/question_action';
import { deleteAnswer } from '../../../_actions/answer_action';

const QuestionBodyBtnsContainer = styled.div`
  display: flex;
  width: 247px;
`;

const QuestionBtn = styled.button`
  background-color: transparent;
  font-size: 13px;
  color: #6a737c;
  height: 15px;
  border: none;
  &:hover {
    cursor: pointer;
    color: #838c95;
  }
`;
const ShareBtn = styled(QuestionBtn)`
  padding-left: 0;
`;
const EditBtn = styled(QuestionBtn)``;
const FollowBtn = styled(QuestionBtn)``;
const DeleteBtn = styled(QuestionBtn)``;

const QuestionBodyBtns = ({ type, answerId }) => {
  const questionState = useSelector((state) => state.questionReducer);
  const question_id = questionState.question_id;

  const dispatch = useDispatch();

  const handleQuestionDelete = () => {
    dispatch(deleteQuestion(question_id));
  };
  const handleAnswerDelete = () => {
    dispatch(deleteAnswer(answerId));
  };

  return (
    <QuestionBodyBtnsContainer>
      <ShareBtn>Share</ShareBtn>
      <EditBtn>Edit</EditBtn>
      <FollowBtn>Follow</FollowBtn>
      {type === 'question' ? (
        <Link to="/questions">
          <DeleteBtn onClick={handleQuestionDelete}>Delete</DeleteBtn>
        </Link>
      ) : (
        <DeleteBtn onClick={handleAnswerDelete}>Delete</DeleteBtn>
      )}
    </QuestionBodyBtnsContainer>
  );
};

export default QuestionBodyBtns;
