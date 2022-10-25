import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import styled from 'styled-components';
import GlobalStyle from './GlobalStyle';

// import Header from './components/Header';
// import Nav from './components/Nav';
import LandingPage from './pages/Home/LandingPage/LandingPage';
import TopQuestionsPage from './pages/Home/TopQuestionsPage/TopQuestionsPage';
import QuestionsPage from './pages/QuestionsPage';
import TagsPage from './pages/TagsPage';
import UsersPage from './pages/UsersPage';

const App = () => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(true);
  }, []);

  return (
    <>
      <GlobalStyle />
      {/* <Header /> */}
      <Container>
        <BrowserRouter>
          {/* <Nav /> */}
          <Content id="content" className="snippet-hidden">
            <Routes>
              <Route
                path="/"
                element={isLogin ? <TopQuestionsPage /> : <LandingPage />}
              />
              <Route path="/questions" element={<QuestionsPage />} />
              <Route path="/tags" element={<TagsPage />} />
              <Route path="/users" element={<UsersPage />} />
              <Route path="*" element={<NoMatch />} />
            </Routes>
          </Content>
        </BrowserRouter>
      </Container>
    </>
  );
};

export default App;

const Container = styled.div`
  margin-top: 0;
  max-width: 1264px;
  width: 100%;
  background: none;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  position: relative;
  flex: 1 0 auto;
  text-align: left;
`;

const Content = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  background-color: #ffffff;
  border-radius: 0;
  border: 1px solid #d6d9dc;
  border-top-width: 0;
  border-bottom-width: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: 24px;
  box-sizing: border-box;
  margin: 0 auto;

  &:before,
  &:after {
    content: '';
    display: table;
  }

  @media screen and (max-width: 640px) {
    width: 100%;
    border-left: 0;
    border-right: 0;
  }
  @media screen and (max-width: 980px) {
    padding-left: 16px;
    padding-right: 16px;
  }
`;

const NoMatch = () => {
  return (
    <div>
      <h2>Nothing to see here!</h2>
      <p>
        <Link to="/">Go to the home page</Link>
      </p>
    </div>
  );
};
