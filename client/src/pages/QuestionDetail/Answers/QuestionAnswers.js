import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import AnswersInfo from './AnswersInfo';
import Answer from '../MainQuestion/MainQuestion';
import PostAnswerBox from './PostAnswerBox';
import { useEffect } from 'react';
import { getAnswers } from '../../../_actions/answer_action';

const QuestionAnswersContainer = styled.div``;
const AnswerConatiner = styled.div`
  padding: 16px 0;
  border-bottom: 1px solid #e3e6e8;
`;

const QuestionAnswers = ({ questionId }) => {
  const answersState = useSelector((state) => state.answerReducer);
  const data = answersState.data;

  const dispatch = useDispatch();

  const answersLen = Object.keys(data).length === 0 ? '0' : data.length;
  const answersData = Object.keys(data).length === 0 ? [] : data;

  useEffect(() => {
    dispatch(getAnswers(questionId));
  }, [dispatch]);

  return (
    <QuestionAnswersContainer>
      <AnswersInfo answersCnt={answersLen} />
      {answersData.map((el) => {
        return (
          <AnswerConatiner key={el.answerId}>
            <Answer data={el} type="answer" />
          </AnswerConatiner>
        );
      })}
      <PostAnswerBox />
    </QuestionAnswersContainer>
  );
};

export default QuestionAnswers;
