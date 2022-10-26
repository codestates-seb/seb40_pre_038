import QuestionSummary from './QuestionSummary';

const QListWrapper = () => {
  return (
    <div id="qlist-wrapper" className="flush-left">
      <div id="question-mini-list">
        <div>
          <QuestionSummary />
        </div>
      </div>
    </div>
  );
};

export default QListWrapper;
