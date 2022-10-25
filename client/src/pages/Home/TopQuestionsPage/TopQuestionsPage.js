import PageHeader from './PageHeader';
import SortTab from './SortTab';
import QListWrapper from './QuestionList/QListWrapper';

const TopQuestionsPage = () => {
  return (
    <div id="content" className="snippet-hidden">
      <div id="mainbar">
        <PageHeader />
        <SortTab />
        <QListWrapper />
        <br className="clear-both" />
        <h2 className="bottom-notice" data-loc={2}>
          Looking for more? Browse the{' '}
          <a href="/questions">complete list of questions</a>, or{' '}
          <a href="/tags">popular tags</a>. Help us answer{' '}
          <a href="/unanswered">unanswered questions</a>.
        </h2>
      </div>
    </div>
  );
};

export default TopQuestionsPage;
