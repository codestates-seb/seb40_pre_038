//import { Container } from '../pages/Home/HomePage/HomePage';
import styled from 'styled-components';
import Nav from '../../components/Nav';
import Footer from '../../components/Footer';
import Sidebar from '../../components/Sidebar.js';
import QuestionTitle from './QuestionTitle';
import MainQuestion from './MainQuestion/MainQuestion';
import QuestionAnswers from './Answers/QuestionAnswers';

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
  return (
    <>
      <MainContent>
        <Nav />
        <QuestionDetailMainContent>
          <QuestionTitle />
          <MainWrapper>
            <div>
              <MainQuestion />
              <QuestionAnswers />
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
