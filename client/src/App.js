import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
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
      <div>
        <BrowserRouter>
          <Routes>
            <Route
              path="/"
              element={isLogin ? <TopQuestionsPage /> : <LandingPage />}
            />
            <Route path="*" element={<NoMatch />} />
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
};

export default App;

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
