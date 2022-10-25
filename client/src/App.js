import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import LandingPage from './pages/LandingPage/LandingPage';
import QuestionsPage from './pages/QuestionsPage';
import TagsPage from './pages/TagsPage';
import UsersPage from './pages/UsersPage';

const App = () => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(false);
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={isLogin ? null : <LandingPage />} />
        <Route path="/questions" element={<QuestionsPage />} />
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
