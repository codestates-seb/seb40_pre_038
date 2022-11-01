import { Link } from 'react-router-dom';
import PageHeader from './PageHeader';
import SortTabs from './SortTabs';
import QuestionListWrapper from './QuestionList/QuestionListWrapper';
import styled from 'styled-components';

const Mainbar = styled.div`
  width: calc(100% - 300px - 24px);
  float: left;
  margin: 0;
  padding: 0;

  @media screen and (max-width: 980px) {
    width: 100%;
    float: none;
  }
`;

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
  return (
    <Mainbar>
      <PageHeader />
      <SortTabs />
      <QuestionListWrapper />
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
