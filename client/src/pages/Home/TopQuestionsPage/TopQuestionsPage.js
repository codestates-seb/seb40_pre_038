import PageHeader from './PageHeader';
import SortTabs from './SortTabs';
import QuestionListWrapper from './QuestionList/QuestionListWrapper';
import styled from 'styled-components';

const TopQuestionsPage = () => {
  return (
    <Mainbar>
      <PageHeader />
      <SortTabs />
      <QuestionListWrapper />
      <br className="clear-both" />
      <h2 className="bottom-notice" data-loc={2}>
        Looking for more? Browse the{' '}
        <a href="/questions">complete list of questions</a>, or{' '}
        <a href="/tags">popular tags</a>. Help us answer{' '}
        <a href="/unanswered">unanswered questions</a>.
      </h2>
    </Mainbar>
  );
};

export default TopQuestionsPage;

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
