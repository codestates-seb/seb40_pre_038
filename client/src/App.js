import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';

import Header from './components/Header';
import HomePage from './pages/Home/TopQuestionsPage/HomePage';
import Login from './pages/LoginPage/Login';
import Signup from './pages/SignUpPage/SignUp';
import LandingPage from './pages/Home/LandingPage/LandingPage';
import QuestionsPage from './pages/QuestionsPage';
import TagsPage from './pages/TagsPage';
import UsersPage from './pages/UsersPage';
import AskQuestionPage from './pages/AskQuestionPage';

const App = () => {
  /**
   * isLogin, setIsLogin
   * 로그인 인증부분 완료되면 삭제될 예정입니다.
   */
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(true);
  }, []);

  return (
    <BrowserRouter>
      <GlobalStyle />
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <Routes>
        <Route path="/" element={isLogin ? <HomePage /> : <LandingPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/questions" element={<QuestionsPage />} />
        <Route path="/questions/ask" element={<AskQuestionPage />} />
        <Route path="/tags" element={<TagsPage />} />
        <Route path="/users" element={<UsersPage />} />
        <Route path="*" element={<NoMatch />} />
      </Routes>
    </BrowserRouter>
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
