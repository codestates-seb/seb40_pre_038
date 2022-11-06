import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import 'antd/dist/antd.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import ScrollTop from './components/ScrollTop';
import Header from './components/Header';
import HomePage from './pages/HomePage/HomePage/HomePage';
import Login from './pages/LoginPage/Login';
import Signup from './pages/SignUpPage/SignUp';
// import LandingPage from './pages/HomePage/LandingPage/LandingPage';
import QuestionsPage from './pages/QuestionsPage/QuestionsPage';
import TagsPage from './pages/TagsPage/TagsPage';
import UsersPage from './pages/UsersPage/UsersPage';
import AskQuestionPage from './pages/AskQuestionPage/AskQuestionPage';
import QuestionDetailPage from './pages/QuestionDetail/QuestionDetailPage';
import SearchPage from './pages/SearchPage/SearchPage';
import NoMatch from './pages/NoMatch';

const App = () => {
  /**
   * isLogin, setIsLogin
   * 로그인 인증부분 완료되면 삭제될 예정입니다.
   */
  const isLogin1 = () => !!sessionStorage.getItem('Authorization');
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    isLogin1() ? setIsLogin(true) : setIsLogin(false);
  }, []);

  return (
    <BrowserRouter>
      <GlobalStyle />
      <ScrollTop />
      <Header isLogin={isLogin} setIsLogin={setIsLogin} />
      <Routes>
        <Route path="/" element={isLogin ? <HomePage /> : <Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/questions" element={<QuestionsPage />} />
        <Route path="/questions/ask" element={<AskQuestionPage />} />
        <Route
          path="/questions/:question_id"
          element={<QuestionDetailPage />}
        />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/tags" element={<TagsPage />} />
        <Route path="/users" element={<UsersPage />} />
        <Route path="*" element={<NoMatch />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
