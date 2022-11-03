import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import PageHeader from './PageHeader';
import SortTabs from './SortTabs';
import QuestionListWrapper from '../../../components/QuestionList/QuestionListWrapper';
import Mainbar from '../../../components/Mainbar';
import { getTopQuestions } from '../../../_actions/questions_action';

const ClearBr = styled.br`
  clear: both !important;
`;

const BottomNotice = styled.h2`
  font-weight: 400;
  margin-top: 15px;
  padding: 0 10px 0 0;
  line-height: 1.4;
  font-size: 1.30769231rem; ;
`;

const TopQuestionsPage = () => {
  const dispatch = useDispatch();
  const topQuestions = useSelector((state) => state.topQuestions);

  useEffect(() => {
    dispatch(getTopQuestions(topQuestions.sortTabValue));
  }, [dispatch]);

  return (
    <Mainbar>
      <PageHeader />
      <SortTabs />
      <QuestionListWrapper className="question-mini-list" />
      <ClearBr />
      <BottomNotice>
        Looking for more? Browse the{' '}
        <Link href="/questions">complete list of questions</Link>, or{' '}
        <Link href="/tags">popular tags</Link>. Help us answer{' '}
        <Link href="/unanswered">unanswered questions</Link>.
      </BottomNotice>
    </Mainbar>
  );
};

export default TopQuestionsPage;
