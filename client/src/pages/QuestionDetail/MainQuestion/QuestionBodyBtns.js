import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { deleteQuestion } from '../../../_actions/question_action';

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

const QuestionBodyBtns = ({ type }) => {
  const questionState = useSelector((state) => state.questionReducer);
  const question_id = questionState.question_id;

  const dispatch = useDispatch();

  const handleQuestionDelete = () => {
    console.log('question delete');
    dispatch(deleteQuestion(question_id));
  };
  const handleAnswerDelete = () => {
    console.log('answer delete');
  };

  return (
    <QuestionBodyBtnsContainer>
      <ShareBtn>Share</ShareBtn>
      <EditBtn>Edit</EditBtn>
      <FollowBtn>Follow</FollowBtn>
      <Link to="/questions">
        <DeleteBtn
          onClick={
            type === 'question' ? handleQuestionDelete : handleAnswerDelete
          }
        >
          Delete
        </DeleteBtn>
      </Link>
    </QuestionBodyBtnsContainer>
  );
};

export default QuestionBodyBtns;
