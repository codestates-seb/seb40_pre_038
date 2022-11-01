import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setQuestionId, getQuestion } from '../../_actions/question_action';
import styled from 'styled-components';
import Nav from '../../components/Nav';
import Footer from '../../components/Footer';
import Sidebar from '../../components/Sidebar.js';
import QuestionTitle from './QuestionTitle';
import MainQuestion from './MainQuestion/MainQuestion';
import QuestionAnswers from './Answers/QuestionAnswers';
import { useEffect } from 'react';

const MainContent = styled.div`
  display: flex;
  justify-content: center;
  @media screen and (max-width: 980px) {
    justify-content: flex-start;
  }
`;
const QuestionDetailMainContent = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 1100px;
  padding: 24px 16px;
`;
const MainWrapper = styled.div`
  display: flex;
  @media screen and (max-width: 980px) {
    flex-direction: column;
  }
  flex-direction: row;
  flex-grow: 1;
`;

const QuestionDetailPage = () => {
  const dispatch = useDispatch();
  const { question_id } = useParams();

  const state = useSelector((state) => state.questionReducer);
  const { data } = state.data;

  useEffect(() => {
    dispatch(setQuestionId(question_id));
    dispatch(getQuestion(question_id));
  }, [dispatch]);

  return (
    <>
      <MainContent>
        <Nav />
        <QuestionDetailMainContent>
          <QuestionTitle />
          <MainWrapper>
            <div>
              <MainQuestion data={data} type="question" />
              <QuestionAnswers questionId={question_id} />
            </div>
            <Sidebar />
          </MainWrapper>
        </QuestionDetailMainContent>
      </MainContent>
      <Footer />
    </>
  );
};

export default QuestionDetailPage;
