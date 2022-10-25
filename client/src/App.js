import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import LandingPage from './pages/LandingPage/LandingPage';
import QuestionsPage from './pages/QuestionsPage';
import TagsPage from './pages/TagsPage';
import UsersPage from './pages/UsersPage';
import Nav from './components/Nav';

const App = () => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(false);
  }, []);

  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={isLogin ? null : <LandingPage />} />
        <Route
          path="/questions"
          element={isLogin ? <QuestionsPage /> : <LandingPage />}
        />
        <Route
          path="/tags"
          element={isLogin ? <TagsPage /> : <LandingPage />}
        />
        <Route
          path="/users"
          element={isLogin ? <UsersPage /> : <LandingPage />}
        />
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
