import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import styled from 'styled-components';
import GlobalStyle from './GlobalStyle';
import LandingPage from './pages/Home/LandingPage/LandingPage';
import TopQuestionsPage from './pages/Home/TopQuestionsPage/TopQuestionsPage';

const App = () => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(true);
  }, []);

  return (
    <>
      <GlobalStyle />
      <Container>
        <BrowserRouter>
          <Routes>
            <Route
              path="/"
              element={isLogin ? <TopQuestionsPage /> : <LandingPage />}
            />
            <Route path="*" element={<NoMatch />} />
          </Routes>
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
